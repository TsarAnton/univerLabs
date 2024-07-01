#define _CRT_SECURE_NO_WARNINGS
#include <Windows.h>
#include <iostream>

using namespace std;

wchar_t* strncatCopy(wchar_t* strDest, const wchar_t* strSource, size_t count) {
	wchar_t* ptr = strDest + wcslen(strDest);
	while (*strSource != '\0' && count--) {
		*ptr++ = *strSource++;
	}
	*ptr = '\0';
	return strDest;
}

INT main() {
	wchar_t str1[20] = L"Hello \0";
	wchar_t str2[11] = L"World!ABC\0";
	//strncatCopy(str1, str2, 6);
	//wcout << str1 << endl;
	wcsncat(str1, str2, 6);
	wcout << str1 << endl;

	return 0;
}