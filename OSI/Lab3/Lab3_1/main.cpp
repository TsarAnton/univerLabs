#include <windows.h>

#include "PlateWindow.h"

// �������� ������� �������
LRESULT CALLBACK MainWindowProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam);

int WINAPI WinMain(HINSTANCE hThisInst, HINSTANCE hPrevInst, LPSTR lpszArgs, int nWinMode)
{
    // �������� ������ �������� ���� ����������
    WNDCLASS wcl;
    wcl.hInstance = hThisInst;
    wcl.lpszClassName = L"MainWindowClass";
    wcl.lpfnWndProc = MainWindowProc;
    wcl.style = 0;
    wcl.lpszMenuName = NULL;
    wcl.hIcon = LoadIcon(NULL, IDI_WINLOGO);
    wcl.hCursor = LoadCursor(NULL, IDC_ARROW);
    wcl.hbrBackground = (HBRUSH)(1 + COLOR_BTNFACE);
    wcl.cbClsExtra = 0;
    wcl.cbWndExtra = 0;
    // ����������� ������ �������� ���� ����������
    if (!RegisterClass(&wcl)) return 0;

    // �������� �������� ���� ����������
    HWND hWnd;
    hWnd = CreateWindow(L"MainWindowClass", L"������ ��������� ���������",
        WS_OVERLAPPEDWINDOW, 0, 0, 300, 300, HWND_DESKTOP, NULL, hThisInst, NULL);
    ShowWindow(hWnd, nWinMode);
    UpdateWindow(hWnd);

    // ����������� ������ ����������� ����
    RegisterPlateWindow();
    // �������� ����������� ����
    CreateWindow(L"PlateWindowClass", L"��������", WS_CHILD | WS_VISIBLE,
        20, 20, 200, 200, hWnd, NULL, hThisInst, NULL);

    // ���� ��������� ���������
    MSG msg;
    while (GetMessage(&msg, NULL, 0, 0))
    {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }

    return msg.wParam;
}

// ������� ������� �������� ���� ����������
LRESULT CALLBACK MainWindowProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam)
{
    switch (uMsg)
    {
    case WM_DESTROY:
        PostQuitMessage(0);
        break;
    default:
        return DefWindowProc(hWnd, uMsg, wParam, lParam);
    }
    return 0;
}