#include <Windows.h>

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

	HWND hWnd = CreateWindow(
		L"secondLabClass",
		L"Window",
		WS_CAPTION |  //заголовок
		//WS_MAXIMIZEBOX | //закрытие окна
		WS_THICKFRAME,  //меняет размеры
		//WS_MINIMIZEBOX | //сворачивание
		//WS_SYSMENU, //разворачивание
		//WS_OVERLAPPEDWINDOW,
		55,
		55,
		600,
		400,
		HWND_DESKTOP,
		NULL,
		hThisInst,
		NULL
	);

	ShowWindow(hWnd, SW_SHOW);
	UpdateWindow(hWnd);

	MSG msg;
	while (GetMessage(&msg, NULL, 0, 0)) {
		DispatchMessage(&msg);
	}
	return 0;
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam) {
	switch (uMsg) {
		case WM_DESTROY:
			PostQuitMessage(0);
			break;
		default:
			return DefWindowProc(hWnd, uMsg, wParam, lParam);
	}
		return 0;
}