#include <Windows.h>
#include <iostream>

using namespace std;

INT main() {
	setlocale(LC_ALL, "Russian");

	WCHAR str1[13] = TEXT("Hello World!");
	WCHAR str2[13] = TEXT("Привет, Мир!");
	wcout << str1 << endl;
	wcout << str2 << endl;
	return 0;
}