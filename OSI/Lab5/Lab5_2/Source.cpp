#include <iostream>
#include <Windows.h>
#include "Header.h"

using namespace std;

int main() {
	HANDLE thread;
	//создаем поток, передаем функцию проигрывания мелодии
	thread = CreateThread(NULL, 0, &PlayMusic, NULL, 0, NULL);

	cout << "1 - play, 0 - stop, 2 - exit" << endl;
	int a = 1;
	bool con = true;
	while (con) {
		int last_a = a;
		cin >> a;
		switch (a) {
		case(1):
			if (last_a == 1) {
				cout << "Music is already playing" << endl;
				break;
			}
			//продолжает работу потока
			ResumeThread(thread);
			break;
		case(0):
			if (last_a == 0) {
				cout << "Music is already stoped" << endl;
				break;
			}
			//останавливает работу потока
			SuspendThread(thread);
			break;
		case(2):
			con = false;
			//завершает работу потока
			TerminateThread(thread, 0);
			CloseHandle(thread);
		}
	}
	cin >> a;
	return 0;
}