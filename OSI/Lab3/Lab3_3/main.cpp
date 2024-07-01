//������-�� ������ �� �������� (�����)
#include <windows.h>
#include "figure.h"

#define idCount 4000
#define idColor 4001
#define idPaint 4002

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
    if (!RegisterClass(&wcl)) return 0;

    HWND hWnd;
    hWnd = CreateWindow(L"MainWindowClass", L"����������� 1",
        WS_OVERLAPPEDWINDOW, 0, 0, 700, 500, HWND_DESKTOP, NULL, hThisInst, NULL);
    ShowWindow(hWnd, nWinMode);
    UpdateWindow(hWnd);

    CreateWindow(L"EDIT", L"���������� �����", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | ES_NUMBER,
        450, 20, 210, 25, hWnd, (HMENU)idCount, hThisInst, NULL
    );

    HWND color = CreateWindow(L"Combobox", NULL, WS_CHILD | WS_VISIBLE | WS_VSCROLL | CBS_DROPDOWNLIST | CBS_HASSTRINGS,
        450, 50, 210, 25, hWnd, (HMENU)idColor, hThisInst, NULL
    );

    //��������� �����, �� ��� �� ������������(���� ��� ��� ����)
    SendMessage(color, CB_ADDSTRING, 1, (LPARAM)L"�������");
    SendMessage(color, CB_ADDSTRING, 2, (LPARAM)L"�������");
    SendMessage(color, CB_ADDSTRING, 3, (LPARAM)L"�����");
    SendMessage(color, CB_SETCURSEL, 0, 0L);

    CreateWindow(L"BUTTON", L"����������!", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
        450, 150, 210, 25, hWnd, (HMENU)idPaint, hThisInst, NULL
    );

    MSG msg;
    while (GetMessage(&msg, NULL, 0, 0))
    {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }

    return msg.wParam;
}

LRESULT CALLBACK MainWindowProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam)
{
    switch (uMsg)
    {
    case WM_COMMAND:
    {
        if (HIWORD(wParam) == BN_CLICKED) {
            switch (LOWORD(wParam)) {
            case(idPaint): //���� ������ �� ������ ���������
                HWND hCount = GetDlgItem(hWnd, idCount);
                HWND hColor = GetDlgItem(hWnd, idColor);
                WCHAR str[100];
                GetWindowText(hCount, str, sizeof(str));
                int count = _wtoi(str); //���-�� ������
                GetWindowText(hColor, str, sizeof(str));
                //����� ������ ���� � if ��������� ������ ������ � � color �������� ����� ������� �����, � ���� ��������
                int color = 5; //����

                PAINTSTRUCT ps;
                HDC hDC = BeginPaint(hWnd, &ps);  
                FIGURE Figure(count, color, 200, 200); //������� ������
                Figure.Draw(hDC); //�� ������� ������ ���� ����������, �� ���-�� �� ������
                EndPaint(hWnd, &ps);

                break;
            }
        }
        break;
    }
    case WM_DESTROY:
        PostQuitMessage(0);
        break;
    default:
        return DefWindowProc(hWnd, uMsg, wParam, lParam);
    }
    return 0;
}