#include <iostream>
#include <windows.h>
#include <vector>

using namespace std;

//количество мастеров
#define MASTERS_NUMBER 3

int sword = 0;
//два событи€
HANDLE firstEvent; //младший ученик выковал заготовку
HANDLE secondEvent; //старший ученик закалил

struct DATA {
	int master; //структура дл€ передачи типа мастера (0 - младший, 1 - старший, 2 - мастер)
};

DWORD WINAPI DoSword(CONST LPVOID lpParam) {
	//получаем тип мастера
	DATA* data;
	data = (DATA*)lpParam;
	switch (data->master) {
		//если младший
		case(0):
			sword += 5;
			cout << "ћладший ученик выковал заготовку" << endl;
			//сигнализируем "старшего ученика" (другой поток) о том, что он может начинать работу
			if (!SetEvent(firstEvent)) {
				cout << "ќшибка SetEvent" << endl;
			}
			break;
		//если старший ученик
		case(1):
			DWORD waitResult;
			//ждет, пока "младший ученик" не просигнализует его
			waitResult = WaitForSingleObject(firstEvent, INFINITE);
			switch (waitResult) {
				//если "младший ученик" просигнализировал
				case WAIT_OBJECT_0:
					sword *= 3;
					cout << "—тарший ученик закалил меч" << endl;
					//сигнализируем "мастера", что он может начинать работу
					if (!SetEvent(secondEvent)) {
						cout << "ќшибка SetEvent" << endl;
					}
					break;
				default:
					cout << "ќшибка ожидани€ событи€ 1" << endl;
					break;
			}
			break;
		//если мастер
		case(2):
			DWORD waitResult1;
			//ждет сигнала от "старшего ученика"
			waitResult1 = WaitForSingleObject(secondEvent, INFINITE);
			switch (waitResult1) {
				//когда получает сигнал, завершает работу над мечем
				case WAIT_OBJECT_0:
					sword -= 6;
					cout << "ћастер завершил меч" << endl;
					break;
				default:
					cout << "ќшибка ожидани€ событи€ 2" << endl;
					break;
			}
			break;
	}
	//завершаем поток
	ExitThread(0);
}

int main() {
	setlocale(LC_ALL, "Russian");
	//создаем два событи€
	firstEvent = CreateEvent(NULL, TRUE, FALSE, L"firstEvent");
	secondEvent = CreateEvent(NULL, TRUE, FALSE, L"secondEvent");

	//потоки
	HANDLE threads[MASTERS_NUMBER];

	DATA data[MASTERS_NUMBER];

	for (int i = 0; i < MASTERS_NUMBER; i++) {
		//заполн€ем типы мастеров
		data[i].master = i;
	}

	for (int i = 0; i < MASTERS_NUMBER; i++) {
		//создаем 3 потока (младший ученик, старший ученик, мастер)
		threads[i] = CreateThread(NULL, 0, &DoSword, &data[i], 0, NULL);
		if (threads[i] == NULL) {
			cout << "ќшибка в создании потока" << endl;
		}
	}
	//ждем пока потоки завершат работу
	WaitForMultipleObjects(MASTERS_NUMBER, threads, TRUE, INFINITE);
	for (int i = 0; i < MASTERS_NUMBER; i++) {
		CloseHandle(threads[i]);
	}
	CloseHandle(firstEvent);
	CloseHandle(secondEvent);
	
	cout << "«авершение работы (значение переменной sword = " << sword << ")" << endl;
	return 0;
}