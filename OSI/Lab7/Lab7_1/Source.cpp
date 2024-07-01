#define _CRT_SECURE_NO_WARNINGS
#include <Windows.h>
#include <iostream>
using namespace std;
//14
LPSTR makeNewYear(LPSTR str) {
	INT len = strlen(str);
	INT cpy = len;
	LPSTR ret = new CHAR[len + 1];
	ret[0] = '\0';
	while(cpy > 0) {
		if (cpy >= 14) {
			strcat(ret, "� ����� �����!");
			cpy -= 14;
		}
		else if (cpy > 0 && cpy < 14) {
			strncat(ret, "� ����� �����!", cpy);
			cpy = 0;
		}
	}
	ret[len] = '\0';
	return ret;
}

int main() {
	setlocale(LC_ALL, "");
	HANDLE hFile;
	LPCSTR name = "file.txt";//�������� �����
	hFile = CreateFileA(name, GENERIC_READ | GENERIC_WRITE, 0, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);
	if (hFile == INVALID_HANDLE_VALUE) {
		cerr << "������ �������� �����" << endl;
	}

	HANDLE hFileMapping;
	hFileMapping = CreateFileMapping(hFile, NULL, PAGE_READWRITE, 0, 0, NULL);

	if (!hFileMapping) {
		cerr << "������ �������� �����" << endl;
	}

	LPSTR file;
	file = (LPSTR)MapViewOfFile(hFileMapping, FILE_MAP_ALL_ACCESS, 0, 0, 0);
	cout << file << endl;
	LPSTR newYear = makeNewYear(file);
	cout << newYear << endl;

	MoveMemory(file, newYear, strlen(newYear) + 1);

	cout << "�����" << endl;

	UnmapViewOfFile((LPVOID)file);
	CloseHandle(hFileMapping);
	CloseHandle(hFile);
	system("pause");
	return 0;
}