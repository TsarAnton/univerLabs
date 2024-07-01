#include <Windows.h>
#include "Header.h"
#include "resource.h"

//�� ��������� ���������� ������� ������
extern "C" __declspec(dllexport) LPWSTR enterMessage = MakeLPWSTR(L"������� ������������ Ethernet");
extern "C" __declspec(dllexport) LPWSTR calcMessage = MakeLPWSTR(L"��������� ������ � ��������");
extern "C" __declspec(dllexport) LPWSTR lengthMessage = MakeLPWSTR(L"������ ��������: ");
extern "C" __declspec(dllexport) LPWSTR speedMessage = MakeLPWSTR(L"�������� �������� ������: ");
extern "C" __declspec(dllexport) LPWSTR mbMessage = MakeLPWSTR(L"����/c");
extern "C" __declspec(dllexport) LPWSTR mMessage = MakeLPWSTR(L"������");
extern "C" __declspec(dllexport) LPWSTR kMessage = MakeLPWSTR(L"����������");
extern "C" __declspec(dllexport) LPWSTR errorMessage = MakeLPWSTR(L"������");

//���������� �� ��� ������ ������� ��������
extern "C" __declspec(dllexport) void RussianLanguage() {
	enterMessage = MakeLPWSTR(L"������� ������������ Ethernet");
	calcMessage = MakeLPWSTR(L"��������� ������ � ��������");
	lengthMessage = MakeLPWSTR(L"������ ��������: ");
	speedMessage = MakeLPWSTR(L"�������� �������� ������: ");
	mbMessage = MakeLPWSTR(L"����/c");
	mMessage = MakeLPWSTR(L"������");
	kMessage = MakeLPWSTR(L"����������");
	errorMessage = MakeLPWSTR(L"������");
}

//���������� �� ��� ������ ���������� ��������
extern "C" __declspec(dllexport) void EnglishLanguage() {
	enterMessage = MakeLPWSTR(L"Enter Ethernet specification");
	calcMessage = MakeLPWSTR(L"Calculate length and speed");
	lengthMessage = MakeLPWSTR(L"Segment length: ");
	speedMessage = MakeLPWSTR(L"Transfer rate: ");
	mbMessage = MakeLPWSTR(L"Mbps");
	mMessage = MakeLPWSTR(L"meters");
	kMessage = MakeLPWSTR(L"kilometers");
	errorMessage = MakeLPWSTR(L"Error");
}

//����� ��, �������� ������� (���� ���������� wcscat())
extern "C" __declspec(dllexport) LPWSTR ConcateStr(LPWSTR str1, LPWSTR str2) {
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

//������� �������, �� ���������� ������ ������ ������������
//��� ������������, ������ ��� ����� ������ ��������� ���������������� ���������� LPWSTR
extern "C" __declspec(dllexport) LPWSTR MakeLPWSTR(LPCWSTR str) {
	LPWSTR res = new wchar_t[wcslen(str) + 1];
	for (int i = 0; i < wcslen(str); i++) {
		res[i] = str[i];
	}
	res[wcslen(str)] = '\0';
	return res;
}

extern "C" __declspec(dllexport) ICONINFO getIconInfo() {
	ICONINFO res;
	GetIconInfo(LoadIcon(GetModuleHandle(L"DLL.dll"), MAKEINTRESOURCE(IDI_ICON1)), &res);
	return res;
}