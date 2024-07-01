#include <Windows.h>
#include "Header.h"
//�� ���� ����� � �� ������, ������ �� ����� ������������ ���������� ������� wcscat()
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
//������� �� ������� �������� (���������� ������ �������� ethernet)
extern "C" __declspec(dllexport) LPCWSTR getSegment(LPCWSTR str) {
	size_t strLength = wcslen(str);
	WCHAR strNum[3];
	strNum[0] = str[strLength - 2];
	strNum[1] = str[strLength - 1];
	strNum[2] = L'\0';
	LPCWSTR res;
	if (strCompare(strNum, L"E5")) {
		res = L"������ ��������: 500 ������\0";
	}
	else if (strCompare(strNum, L"E2")) {
		res = L"������ ��������: 185 ������\0";
	}
	else if (strCompare(strNum, L"-T")) {
		res = L"������ ��������: 100 ������\0";
	}
	else if (strCompare(strNum, L"-F")) {
		res = L"������ ��������: 2 ��������a\0";
	}
	else if (strCompare(strNum, L"FL")) {
		res = L"������ ��������: 2 ���������\0";
	}
	else if (strCompare(strNum, L"TX")) {
		res = L"������ ��������: 100 ������\0";
	}
	else if (strCompare(strNum, L"FX")) {
		res = L"������ ��������: 400 ������\0";
	}
	else if (strCompare(strNum, L"SX")) {
		res = L"������ ��������: 10 ����������\0";
	}
	else if (strCompare(strNum, L"LX")) {
		res = L"������ ��������: 550 ������\0";
	}
	else if (strCompare(strNum, L"CX")) {
		res = L"������ ��������: 25 ������\0";
	}
	else if (strCompare(strNum, L"LH")) {
		res = L"������ ��������: 100 ����������\0";
	}
	else if (strCompare(strNum, L"X4")) {
		res = L"������ ��������: 15 ������\0";
	}
	else if (strCompare(strNum, L"SR")) {
		res = L"������ ��������: 300 ������\0";
	}
	else if (strCompare(strNum, L"ER")) {
		res = L"������ ��������: 40 ����������\0";
	}
	else {
		res = L"������\0";
	}
	return res;
}
//������� �� �������� (���������� �������� �������� ������ (� ������� ������� ������� �������))
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
		res = L"�������� �������� ������: 1 ����/c\0";
	}
	else if (strCompare(strNum, L"10")) {
		res = L"�������� �������� ������: 10 ����/c\0";
	}
	else if (strCompare(strNum, L"100")) {
		res = L"�������� �������� ������: 100 ����/c\0";
	}
	else if (strCompare(strNum, L"1000")) {
		res = L"�������� �������� ������: 1000 ����/c\0";
	}
	else {
		res = L"������\0";
	}

	return res;
}
