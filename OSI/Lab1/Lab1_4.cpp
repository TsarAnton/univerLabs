#include <Windows.h>
#include <iostream>

using namespace std;

INT main() {
	WCHAR str1[13] = L"Hello World!";
	CHAR str2[13];
	WideCharToMultiByte(CP_ACP, NULL, str1, 13, str2, 13, NULL, NULL);
	cout << str2 << endl;
	return 0;
}