#include <Windows.h>
#include <iostream>

using namespace std;

INT main() {
	setlocale(LC_ALL, "Russian");

	CHAR str1[13] = "Hello World!";
	CHAR str2[13] = "������, ���!";
	cout << str1 << endl;
	cout << str2 << endl;
	return 0;
}