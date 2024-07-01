#include <iostream>
#include <windows.h>
#include <vector>

using namespace std;

//���������� ��������
#define MASTERS_NUMBER 3

int sword = 0;
//��� �������
HANDLE firstEvent; //������� ������ ������� ���������
HANDLE secondEvent; //������� ������ �������

struct DATA {
	int master; //��������� ��� �������� ���� ������� (0 - �������, 1 - �������, 2 - ������)
};

DWORD WINAPI DoSword(CONST LPVOID lpParam) {
	//�������� ��� �������
	DATA* data;
	data = (DATA*)lpParam;
	switch (data->master) {
		//���� �������
		case(0):
			sword += 5;
			cout << "������� ������ ������� ���������" << endl;
			//������������� "�������� �������" (������ �����) � ���, ��� �� ����� �������� ������
			if (!SetEvent(firstEvent)) {
				cout << "������ SetEvent" << endl;
			}
			break;
		//���� ������� ������
		case(1):
			DWORD waitResult;
			//����, ���� "������� ������" �� �������������� ���
			waitResult = WaitForSingleObject(firstEvent, INFINITE);
			switch (waitResult) {
				//���� "������� ������" �����������������
				case WAIT_OBJECT_0:
					sword *= 3;
					cout << "������� ������ ������� ���" << endl;
					//������������� "�������", ��� �� ����� �������� ������
					if (!SetEvent(secondEvent)) {
						cout << "������ SetEvent" << endl;
					}
					break;
				default:
					cout << "������ �������� ������� 1" << endl;
					break;
			}
			break;
		//���� ������
		case(2):
			DWORD waitResult1;
			//���� ������� �� "�������� �������"
			waitResult1 = WaitForSingleObject(secondEvent, INFINITE);
			switch (waitResult1) {
				//����� �������� ������, ��������� ������ ��� �����
				case WAIT_OBJECT_0:
					sword -= 6;
					cout << "������ �������� ���" << endl;
					break;
				default:
					cout << "������ �������� ������� 2" << endl;
					break;
			}
			break;
	}
	//��������� �����
	ExitThread(0);
}

int main() {
	setlocale(LC_ALL, "Russian");
	//������� ��� �������
	firstEvent = CreateEvent(NULL, TRUE, FALSE, L"firstEvent");
	secondEvent = CreateEvent(NULL, TRUE, FALSE, L"secondEvent");

	//������
	HANDLE threads[MASTERS_NUMBER];

	DATA data[MASTERS_NUMBER];

	for (int i = 0; i < MASTERS_NUMBER; i++) {
		//��������� ���� ��������
		data[i].master = i;
	}

	for (int i = 0; i < MASTERS_NUMBER; i++) {
		//������� 3 ������ (������� ������, ������� ������, ������)
		threads[i] = CreateThread(NULL, 0, &DoSword, &data[i], 0, NULL);
		if (threads[i] == NULL) {
			cout << "������ � �������� ������" << endl;
		}
	}
	//���� ���� ������ �������� ������
	WaitForMultipleObjects(MASTERS_NUMBER, threads, TRUE, INFINITE);
	for (int i = 0; i < MASTERS_NUMBER; i++) {
		CloseHandle(threads[i]);
	}
	CloseHandle(firstEvent);
	CloseHandle(secondEvent);
	
	cout << "���������� ������ (�������� ���������� sword = " << sword << ")" << endl;
	return 0;
}