#define _CRT_SECURE_NO_WARNINGS
#include <Windows.h>
#include <iostream>
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");

	HANDLE hFile;
	LPCSTR name = "file.txt";
	hFile = CreateFileA(name, GENERIC_READ | GENERIC_WRITE, 0, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);
	if (hFile == INVALID_HANDLE_VALUE) {
		cerr << "������ �������� �����" << endl;
	}

	HANDLE hFileMapping;
	hFileMapping = CreateFileMapping(hFile, NULL, PAGE_READWRITE, 0, 0, NULL);

	if (!hFileMapping) {
		cerr << "������ �������� �����" << endl;
	}

	LPSTR file = NULL;
	file = (LPSTR)MapViewOfFile(hFileMapping, FILE_MAP_ALL_ACCESS, 0, 0, 0);

	//������� ���� ��� ����������
	HANDLE hHeap;
	hHeap = HeapCreate(0, 500, 0);
	if (!hHeap) {
		cerr << "������ �������� ����" << endl;
	}

	//������� 2 ���������� � ����
	LPSTR resultStr = (LPSTR)HeapAlloc(hHeap, 0, 100);
	LPSTR block = (LPSTR)HeapAlloc(hHeap, 0, 100);

	resultStr[0] = '\0';
	block[0] = '\0';

	int j = 0;
	for (int i = strlen(file) - 1; i >= 0; i--) {
		//���� ���� ��������
		if (j == 32) {
			block[j] = '\0';
			strcat(resultStr, _strrev(block));
			j = 0;
		}
		block[j] = file[i];
		j++;
	}
	if (strlen(resultStr) != strlen(file)) {
		block[j] = '\0';
		//���������� ���
		strcat(resultStr, _strrev(block));
	}

	//�������������� ����
	MoveMemory(file, resultStr, strlen(resultStr));

	HeapFree(hHeap, 0, resultStr);
	HeapFree(hHeap, 0, block);
	HeapDestroy(hHeap);
	CloseHandle(hHeap);

	UnmapViewOfFile((LPVOID)file);
	CloseHandle(hFileMapping);
	CloseHandle(hFile);

	cout << "�����" << endl;
	system("pause");
	return 0;
}