#include <iostream>
#include <Windows.h>

LPWSTR MakeLPWSTR(LPCWSTR str) {
	LPWSTR res = new wchar_t[wcslen(str) + 1];
	for (int i = 0; i < wcslen(str); i++) {
		res[i] = str[i];
	}
	res[wcslen(str)] = '\0';
	return res;
}

LPWSTR ConcateStr(LPWSTR str1, LPWSTR str2) {
	LPWSTR res = new wchar_t[wcslen(str1) + wcslen(str2) + 1];
	int i = 0;
	for (i; i < wcslen(str1); i++) {
		res[i] = str1[i];
	}
	for (int k = 0; k < wcslen(str2); i++, k++) {
		res[i] = str2[k];
	}
	res[wcslen(str1) + wcslen(str2)] = '\0';
	return res;
}

using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");
	LPCWSTR a = L"AAAAAAAÿÿÿÿÿÿ";
	LPCWSTR b = L" 12345ÿÿ";
	wcout << a << b << endl;
	wcout << ConcateStr(MakeLPWSTR(a), MakeLPWSTR(b)) << endl;
	return 0;
}