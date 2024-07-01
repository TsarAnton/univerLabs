#include <Windows.h>
#include <iostream>
#include <tchar.h>

using namespace std;

INT main() {
	//setlocale(LC_ALL, "Russian");
	TCHAR str1[13] = TEXT("SSSSττττ!");
	wcout << str1 << endl;
	return 0;
}