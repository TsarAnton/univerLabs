#define _CRT_SECURE_NO_WARNINGS
#include <Windows.h>

#define idEdit 3000
#define idButton 3001
#define idSegment 3002
#define idSpeed 3003
HINSTANCE hInst;

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

LPCWSTR getSegment(LPCWSTR str) {
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

LPCWSTR getSpeed(LPCWSTR str) {
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

void ButtonClick(HWND hWnd) {
	HWND hEdit = GetDlgItem(hWnd, idEdit);
	HWND hSegment = GetDlgItem(hWnd, idSegment);
	HWND hSpeed = GetDlgItem(hWnd, idSpeed);
	WCHAR str[100];
	GetWindowText(hEdit, str, sizeof(str));
	SetWindowText(hSegment, getSegment(str));
	SetWindowText(hSpeed, getSpeed(str));
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
	wnd.hIcon = LoadIcon(NULL, IDI_APPLICATION);
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

	switch (uMsg) {
	case WM_CREATE:
		hEdit = CreateWindow(
			L"EDIT",
			L"Введите спецификацию Ethernet",
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
			L"Посчитать кол-во хостов и скорость",
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
			L"Длинна сегмента: ?",
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
			L"Скорость передачи данных: ?",
			WS_CHILD | WS_VISIBLE | WS_BORDER,
			10, 115,
			305, 25,
			hWnd,
			(HMENU)idSpeed,
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