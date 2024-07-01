#include <Windows.h>
#include "Header.h"
//не знаю зачем я ее создал, вместо ее можно использовать встроенную функцию wcscat()
BOOL strCompare(LPCWSTR str1, LPCWSTR str2) {
	if (wcslen(str1) != wcslen(str2)) {
		return false;
	}

	for (int i = 0; i < wcslen(str1); i++) {
		if (str1[i] != str2[i]) {
			return false;
		}
	}

	return true;
}
//функция из условия вырианта (определяет длинну сегмента ethernet)
extern "C" __declspec(dllexport) LPCWSTR getSegment(LPCWSTR str) {
	size_t strLength = wcslen(str);
	WCHAR strNum[3];
	strNum[0] = str[strLength - 2];
	strNum[1] = str[strLength - 1];
	strNum[2] = L'\0';
	LPCWSTR res;
	if (strCompare(strNum, L"E5")) {
		res = L"Длинна сегмента: 500 метров\0";
	}
	else if (strCompare(strNum, L"E2")) {
		res = L"Длинна сегмента: 185 метров\0";
	}
	else if (strCompare(strNum, L"-T")) {
		res = L"Длинна сегмента: 100 метров\0";
	}
	else if (strCompare(strNum, L"-F")) {
		res = L"Длинна сегмента: 2 километрa\0";
	}
	else if (strCompare(strNum, L"FL")) {
		res = L"Длинна сегмента: 2 километра\0";
	}
	else if (strCompare(strNum, L"TX")) {
		res = L"Длинна сегмента: 100 метров\0";
	}
	else if (strCompare(strNum, L"FX")) {
		res = L"Длинна сегмента: 400 метров\0";
	}
	else if (strCompare(strNum, L"SX")) {
		res = L"Длинна сегмента: 10 километров\0";
	}
	else if (strCompare(strNum, L"LX")) {
		res = L"Длинна сегмента: 550 метров\0";
	}
	else if (strCompare(strNum, L"CX")) {
		res = L"Длинна сегмента: 25 метров\0";
	}
	else if (strCompare(strNum, L"LH")) {
		res = L"Длинна сегмента: 100 километров\0";
	}
	else if (strCompare(strNum, L"X4")) {
		res = L"Длинна сегмента: 15 метров\0";
	}
	else if (strCompare(strNum, L"SR")) {
		res = L"Длинна сегмента: 300 метров\0";
	}
	else if (strCompare(strNum, L"ER")) {
		res = L"Длинна сегмента: 40 километров\0";
	}
	else {
		res = L"Ошибка\0";
	}
	return res;
}
//функция из варианта (определяет скорость передачи данных (я немного поменял условие задания))
extern "C" __declspec(dllexport) LPCWSTR getSpeed(LPCWSTR str) {
	INT i = 0;
	WCHAR strNum[100];
	for (i; i < wcslen(str); i++) {
		if (str[i] != L'B') {
			strNum[i] = str[i];
		}
		else {
			break;
		}
	}
	strNum[i] = L'\0';
	LPCWSTR res;
	if (strCompare(strNum, L"1")) {
		res = L"Скорость передачи данных: 1 Мбит/c\0";
	}
	else if (strCompare(strNum, L"10")) {
		res = L"Скорость передачи данных: 10 Мбит/c\0";
	}
	else if (strCompare(strNum, L"100")) {
		res = L"Скорость передачи данных: 100 Мбит/c\0";
	}
	else if (strCompare(strNum, L"1000")) {
		res = L"Скорость передачи данных: 1000 Мбит/c\0";
	}
	else {
		res = L"Ошибка\0";
	}

	return res;
}
