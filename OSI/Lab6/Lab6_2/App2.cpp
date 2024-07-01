//динамическое подключение, все как в первом задании
#define _CRT_SECURE_NO_WARNINGS
#include <Windows.h>

#define idEdit 3000
#define idButton 3001
#define idSegment 3002
#define idSpeed 3003
HINSTANCE hInst;

void ButtonClick(HWND hWnd) {
	HWND hEdit = GetDlgItem(hWnd, idEdit);
	HWND hSegment = GetDlgItem(hWnd, idSegment);
	HWND hSpeed = GetDlgItem(hWnd, idSpeed);
	WCHAR str[100];
	GetWindowText(hEdit, str, sizeof(str));
	HMODULE hMyDll = LoadLibrary(L"D:\\labs\\OSI\\lab6_2\\Project1\\Debug\\Project1.dll");
	if (hMyDll == NULL) {
		exit(0);
	}
	LPCWSTR(*getSegment)(LPCWSTR) = (LPCWSTR(*)(LPCWSTR))GetProcAddress(hMyDll, "getSegment");
	if (!getSegment) {
		exit(0);
	}
	LPCWSTR(*getSpeed)(LPCWSTR) = (LPCWSTR(*)(LPCWSTR))GetProcAddress(hMyDll, "getSpeed");
	if (!getSpeed) {
		exit(0);
	}
	SetWindowText(hSegment, getSegment(str));
	SetWindowText(hSpeed, getSpeed(str));
	FreeLibrary(hMyDll);
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