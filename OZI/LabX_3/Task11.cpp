#define _CRT_SECURE_NO_WARNINGS
#include <Windows.h>

#define idCombobox 3000
HINSTANCE hInst;


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
		L"Combobox приложение",
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

	HWND hCombobox = {};

	switch (uMsg) {
	case WM_CREATE:
		hCombobox = CreateWindowW(L"COMBOBOX", L"", WS_CHILD | WS_VISIBLE | CBS_HASSTRINGS | CBS_DROPDOWNLIST | WS_OVERLAPPED, 10, 10, 300, 180, hWnd, (HMENU)hCombobox, hInst, NULL);
		//заполнение цветами
		SendMessage(hCombobox, CB_ADDSTRING, 1, (LPARAM)L"Красный");
		SendMessage(hCombobox, CB_ADDSTRING, 1, (LPARAM)L"Желтый");
		SendMessage(hCombobox, CB_ADDSTRING, 1, (LPARAM)L"Зеленый");
		SendMessage(hCombobox, CB_SETCURSEL, (WPARAM)0, 0L);
		break;
	case WM_DESTROY:
		PostQuitMessage(0);
		break;
	default:
		return DefWindowProc(hWnd, uMsg, wParam, lParam);
	}
	return 0;
}