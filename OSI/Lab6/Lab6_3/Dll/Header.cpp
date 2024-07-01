#include <Windows.h>
#include "Header.h"
#include "resource.h"

//по умолчанию записываем русские строки
extern "C" __declspec(dllexport) LPWSTR enterMessage = MakeLPWSTR(L"Введите спецификацию Ethernet");
extern "C" __declspec(dllexport) LPWSTR calcMessage = MakeLPWSTR(L"Посчитать длинну и скорость");
extern "C" __declspec(dllexport) LPWSTR lengthMessage = MakeLPWSTR(L"Длинна сегмента: ");
extern "C" __declspec(dllexport) LPWSTR speedMessage = MakeLPWSTR(L"Скорость передачи данных: ");
extern "C" __declspec(dllexport) LPWSTR mbMessage = MakeLPWSTR(L"Мбит/c");
extern "C" __declspec(dllexport) LPWSTR mMessage = MakeLPWSTR(L"метров");
extern "C" __declspec(dllexport) LPWSTR kMessage = MakeLPWSTR(L"километров");
extern "C" __declspec(dllexport) LPWSTR errorMessage = MakeLPWSTR(L"Ошибка");

//записывает во все строки русские значения
extern "C" __declspec(dllexport) void RussianLanguage() {
	enterMessage = MakeLPWSTR(L"Введите спецификацию Ethernet");
	calcMessage = MakeLPWSTR(L"Посчитать длинну и скорость");
	lengthMessage = MakeLPWSTR(L"Длинна сегмента: ");
	speedMessage = MakeLPWSTR(L"Скорость передачи данных: ");
	mbMessage = MakeLPWSTR(L"Мбит/c");
	mMessage = MakeLPWSTR(L"метров");
	kMessage = MakeLPWSTR(L"километров");
	errorMessage = MakeLPWSTR(L"Ошибка");
}

//записывает во все строки английские значения
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

//опять же, ненужная функция (есть встроенная wcscat())
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

//смешная функция, из констатной строки делает неконстатную
//она понадобилась, потому что иначе нельзя нормально инициализировать переменные LPWSTR
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