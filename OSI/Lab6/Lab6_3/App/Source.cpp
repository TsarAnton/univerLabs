//используем статистическое подключение dll
#define _CRT_SECURE_NO_WARNINGS
#include "Header.h"

#define idEdit 3000
#define idButton 3001
#define idSegment 3002
#define idSpeed 3003
//2 новые кнопки для переключения языка
#define idEnglish 3004
#define idRussian 3005
#pragma comment(lib, "DLL.lib")
HINSTANCE hInst;

BOOL strCompare(LPWSTR str1, LPWSTR str2) {
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

LPWSTR getSegment(LPWSTR str) {
	size_t strLength = wcslen(str);
	LPWSTR strNum = new WCHAR[3];
	strNum[0] = str[strLength - 2];
	strNum[1] = str[strLength - 1];
	strNum[2] = L'\0';
	LPWSTR ending;
	if (strCompare(strNum, MakeLPWSTR(L"E5"))) {
		ending = ConcateStr(MakeLPWSTR(L"500"), mMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"E2"))) {
		ending = ConcateStr(MakeLPWSTR(L"185"), mMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"-T"))) {
		ending = ConcateStr(MakeLPWSTR(L"100"), mMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"-F"))) {
		ending = ConcateStr(MakeLPWSTR(L"2"), kMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"FL"))) {
		ending = ConcateStr(MakeLPWSTR(L"2"), kMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"TX"))) {
		ending = ConcateStr(MakeLPWSTR(L"100"), mMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"FX"))) {
		ending = ConcateStr(MakeLPWSTR(L"400"), mMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"SX"))) {
		ending = ConcateStr(MakeLPWSTR(L"10"), kMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"LX"))) {
		ending = ConcateStr(MakeLPWSTR(L"550"), mMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"CX"))) {
		ending = ConcateStr(MakeLPWSTR(L"25"), mMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"LH"))) {
		ending = ConcateStr(MakeLPWSTR(L"100"), kMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"X4"))) {
		ending = ConcateStr(MakeLPWSTR(L"15"), mMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"SR"))) {
		ending = ConcateStr(MakeLPWSTR(L"300"), mMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"ER"))) {
		ending = ConcateStr(MakeLPWSTR(L"40"), mMessage);
	}
	else {
		return errorMessage;
	}
	return ConcateStr(lengthMessage, ending);
}

LPWSTR getSpeed(LPWSTR str) {
	INT i = 0;
	LPWSTR strNum = new WCHAR[100];
	for (i; i < wcslen(str); i++) {
		if (str[i] != L'B') {
			strNum[i] = str[i];
		}
		else {
			break;
		}
	}
	strNum[i] = L'\0';
	LPWSTR ending;
	if (strCompare(strNum, MakeLPWSTR(L"1"))) {
		ending = ConcateStr(MakeLPWSTR(L"1"), mbMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"10"))) {
		ending = ConcateStr(MakeLPWSTR(L"10"), mbMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"100"))) {
		ending = ConcateStr(MakeLPWSTR(L"100"), mbMessage);
	}
	else if (strCompare(strNum, MakeLPWSTR(L"1000"))) {
		ending = ConcateStr(MakeLPWSTR(L"1000"), mbMessage);
	}
	else {
		return errorMessage;
	}

	return ConcateStr(speedMessage, ending);
}

void ButtonClick(HWND hWnd) {
	HWND hEdit = GetDlgItem(hWnd, idEdit);
	HWND hSegment = GetDlgItem(hWnd, idSegment);
	HWND hSpeed = GetDlgItem(hWnd, idSpeed);
	WCHAR str[100];
	GetWindowText(hEdit, str, sizeof(str));
	SetWindowText(hSegment, getSegment(str));
	SetWindowText(hSpeed, getSpeed(str));
}

//обновляет строки в приложении
void UpdateWindows(HWND hWnd) {
	HWND hSpeed = GetDlgItem(hWnd, idSpeed);
	HWND hButton = GetDlgItem(hWnd, idButton);
	HWND hSegment = GetDlgItem(hWnd, idSegment);
	HWND hEdit = GetDlgItem(hWnd, idEdit);
	SetWindowText(hButton, calcMessage);
	WCHAR str[100];
	//проверяем, не записано ли чего в окнах
	//чтоб не очистить пользавательский ввод
	GetWindowText(hEdit, str, sizeof(str));
	if (wcsstr(str, L"BASE") == nullptr) {
		SetWindowText(hEdit, enterMessage);
	}
	GetWindowText(hSpeed, str, sizeof(str));
	if (wcslen(str) == 26 || wcslen(str) == 15) {
		SetWindowText(hSpeed, speedMessage);
		SetWindowText(hSegment, lengthMessage);
	}
	else {
		ButtonClick(hWnd);
	}
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam);

INT CALLBACK wWinMain(HINSTANCE hThisInst, HINSTANCE hPrevInst, LPTSTR cmdLine, INT cmdShow) {
	WNDCLASS wnd;
	wnd.lpfnWndProc = WndProc;
	wnd.lpszClassName = L"secondLabClass";
	wnd.hInstance = hThisInst;
	wnd.cbClsExtra = 0;
	wnd.cbWndExtra = 0;
	wnd.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
	wnd.hCursor = LoadCursor(NULL, IDC_ARROW);

	ICONINFO info = getIconInfo();
	wnd.hIcon = CreateIconIndirect(&info);

	wnd.lpszMenuName = NULL;
	wnd.style = CS_HREDRAW | CS_VREDRAW;

	if (!RegisterClass(&wnd)) {
		return 1;
	}

	hInst = hThisInst;

	HWND hWnd = CreateWindow(
		L"secondLabClass",
		L"Ethernet приложение",
		WS_OVERLAPPEDWINDOW,
		55, 55,
		600, 400,
		HWND_DESKTOP,
		NULL,
		hThisInst,
		NULL
	);

	ShowWindow(hWnd, SW_SHOW);

	MSG msg;
	while (GetMessage(&msg, NULL, 0, 0)) {
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
	return 0;
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam) {

	HWND hEdit;
	HWND hButton;
	HWND hSegment;
	HWND hSpeed;
	HWND hEnglish;
	HWND hRussian;

	switch (uMsg) {
	case WM_CREATE:
		hEdit = CreateWindow(
			L"EDIT",
			enterMessage,
			WS_CHILD | WS_VISIBLE | WS_BORDER,
			10, 10,
			305, 25,
			hWnd,
			(HMENU)idEdit,
			hInst,
			NULL
		);
		hButton = CreateWindow(
			L"BUTTON",
			calcMessage,
			WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | WS_BORDER,
			10, 45,
			305, 25,
			hWnd,
			(HMENU)idButton,
			hInst,
			NULL
		);
		hSegment = CreateWindow(
			L"STATIC",
			lengthMessage,
			WS_CHILD | WS_VISIBLE | WS_BORDER,
			10, 80,
			305, 25,
			hWnd,
			(HMENU)idSegment,
			hInst,
			NULL
		);
		hSpeed = CreateWindow(
			L"STATIC",
			speedMessage,
			WS_CHILD | WS_VISIBLE | WS_BORDER,
			10, 115,
			305, 25,
			hWnd,
			(HMENU)idSpeed,
			hInst,
			NULL
		);
		//создаем кнопки переключения языка
		hRussian = CreateWindow(
			L"BUTTON",
			L"Русский",
			WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | WS_BORDER,
			10, 150,
			142, 25,
			hWnd,
			(HMENU)idRussian,
			hInst,
			NULL
		);
		hEnglish = CreateWindow(
			L"BUTTON",
			L"English",
			WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | WS_BORDER,
			170, 150,
			143, 25,
			hWnd,
			(HMENU)idEnglish,
			hInst,
			NULL
		);
		break;
	case WM_COMMAND:
		if (HIWORD(wParam) == BN_CLICKED) {
			switch (LOWORD(wParam)) {
			case idButton:
				ButtonClick(hWnd);
				break;
			//обработчики кнопок переключения языка
			case idEnglish:
				//меняем значения строк на английский
				EnglishLanguage();
				//хоть значения строк и поменялись, в самих окнах они не обновились
				//поэтому "обновляем" окна
				UpdateWindows(hWnd);
				break;
			case idRussian:
				RussianLanguage();
				UpdateWindows(hWnd);
				break;
			}
		}
		break;
	case WM_DESTROY:
		PostQuitMessage(0);
		break;
	default:
		return DefWindowProc(hWnd, uMsg, wParam, lParam);
	}
	return 0;
}