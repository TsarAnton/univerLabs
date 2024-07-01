#include <Windows.h>
#include "resource.h"

#define idButton1 3000
#define idButton2 3001
#define idButton3 3002

HINSTANCE hInst;
int x, y, width, height;
BOOL isVisible;
HANDLE hThread;

DWORD WINAPI CheckMouse(CONST LPVOID lpParam) {
	HWND hWnd = (HWND)lpParam;
	POINT p;
	while (true) {
		GetCursorPos(&p);
		if (!isVisible && p.x < width && p.y < height) {
			isVisible = true;
			ShowWindow(hWnd, SW_SHOW);
		}
		if (isVisible && p.x > width || p.y > height) {
			isVisible = false;
			ShowWindow(hWnd, SW_HIDE);
		}
	}
	ExitThread(0);
}

BOOL StartApplication(LPCWSTR appName) {
	STARTUPINFO sif;
	ZeroMemory(&sif, sizeof(STARTUPINFO));
	PROCESS_INFORMATION pi;
	return CreateProcess(
		appName,
		NULL,
		NULL,
		NULL,
		FALSE,
		NULL,
		NULL,
		NULL,
		&sif,
		&pi
	);
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

	hInst = hThisInst;

	if (!RegisterClass(&wnd)) {
		return 1;
	}

	x = 10;
	y = 10;
	width = 275;
	height = 125;

	HWND hWnd = CreateWindow(
		L"secondLabClass",
		L"",
		0,
		x,
		y,
		width,
		height,
		HWND_DESKTOP,
		NULL,
		hThisInst,
		NULL
	);

	ShowWindow(hWnd, SW_HIDE);
	isVisible = false;
	UpdateWindow(hWnd);

	hThread = CreateThread(NULL, 0, &CheckMouse, hWnd, 0, NULL);
	MSG msg;
	while (GetMessage(&msg, NULL, 0, 0)) {
		DispatchMessage(&msg);
	}
	return 0;
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam) {
	HWND button1;
	HWND button2;
	HWND button3;
	switch (uMsg) {
	case WM_CREATE:
		button1 = CreateWindow(
			L"BUTTON",
			L"",
			WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | BS_ICON,
			20, 0,
			60, 60,
			hWnd,
			(HMENU)idButton1,
			hInst,
			NULL
		);
		SendMessage(
			button1,
			BM_SETIMAGE,
			IMAGE_ICON,
			(LPARAM)LoadIcon(hInst, MAKEINTRESOURCE(IDI_ICON1))
		);
		button2 = CreateWindow(
			L"BUTTON",
			L"",
			WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | BS_ICON,
			100, 0,
			60, 60,
			hWnd,
			(HMENU)idButton2,
			hInst,
			NULL
		);
		SendMessage(
			button2,
			BM_SETIMAGE,
			IMAGE_ICON,
			(LPARAM)LoadIcon(hInst, MAKEINTRESOURCE(IDI_ICON2))
		);
		button3 = CreateWindow(
			L"BUTTON",
			L"",
			WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | BS_ICON,
			180, 0,
			60, 60,
			hWnd,
			(HMENU)idButton3,
			hInst,
			NULL
		);
		SendMessage(
			button3,
			BM_SETIMAGE,
			IMAGE_ICON,
			(LPARAM)LoadIcon(hInst, MAKEINTRESOURCE(IDI_ICON3))
		);
		break;
	case WM_COMMAND:
		if (HIWORD(wParam) == BN_CLICKED) {
			switch (LOWORD(wParam)) {
				BOOL res;
			case idButton1:
				res = StartApplication(L"C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\EXCEL.exe");
				if (!res) {
					MessageBox(hWnd, L"Error", L"Cannot open Excel", MB_OK);
				}
				break;
			case idButton2:
				res = StartApplication(L"C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.exe");
				if (!res) {
					MessageBox(hWnd, L"Error", L"Cannot open Word", MB_OK);
				}
				break;
			case idButton3:
				res = StartApplication(L"C:\\Program Files\\Mozilla Firefox\\firefox.exe");
				if (!res) {
					MessageBox(hWnd, L"Error", L"Cannot open Firefox", MB_OK);
				}
				break;
			}
		}
		break;
	case WM_DESTROY:
		CloseHandle(hThread);
		PostQuitMessage(0);
		break;
	case WM_MOVE:
		ReleaseCapture();
		break;
	default:
		return DefWindowProc(hWnd, uMsg, wParam, lParam);
	}
	return 0;
}