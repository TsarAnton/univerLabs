//почему-то ничего не работает (почти)
#include <windows.h>
#include "figure.h"

#define idCount 4000
#define idColor 4001
#define idPaint 4002

LRESULT CALLBACK MainWindowProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam);

int WINAPI WinMain(HINSTANCE hThisInst, HINSTANCE hPrevInst, LPSTR lpszArgs, int nWinMode)
{
    // ќписание класса главного окна приложени€
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
    hWnd = CreateWindow(L"MainWindowClass", L" онтрольна€ 1",
        WS_OVERLAPPEDWINDOW, 0, 0, 700, 500, HWND_DESKTOP, NULL, hThisInst, NULL);
    ShowWindow(hWnd, nWinMode);
    UpdateWindow(hWnd);

    CreateWindow(L"EDIT", L" оличество углов", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | ES_NUMBER,
        450, 20, 210, 25, hWnd, (HMENU)idCount, hThisInst, NULL
    );

    HWND color = CreateWindow(L"Combobox", NULL, WS_CHILD | WS_VISIBLE | WS_VSCROLL | CBS_DROPDOWNLIST | CBS_HASSTRINGS,
        450, 50, 210, 25, hWnd, (HMENU)idColor, hThisInst, NULL
    );

    //добавл€ем цвета, но они не отображаютс€(хоть они там есть)
    SendMessage(color, CB_ADDSTRING, 1, (LPARAM)L"зеленый");
    SendMessage(color, CB_ADDSTRING, 2, (LPARAM)L"красный");
    SendMessage(color, CB_ADDSTRING, 3, (LPARAM)L"синий");
    SendMessage(color, CB_SETCURSEL, 0, 0L);

    CreateWindow(L"BUTTON", L"Ќарисовать!", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON,
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
            case(idPaint): //если нажали на кнопку рисовани€
                HWND hCount = GetDlgItem(hWnd, idCount);
                HWND hColor = GetDlgItem(hWnd, idColor);
                WCHAR str[100];
                GetWindowText(hCount, str, sizeof(str));
                int count = _wtoi(str); //кол-во сторон
                GetWindowText(hColor, str, sizeof(str));
                //здесь должны были в if перебрать строки цветов и в color записать число нужного цвета, а пока заглушка
                int color = 5; //цвет

                PAINTSTRUCT ps;
                HDC hDC = BeginPaint(hWnd, &ps);  
                FIGURE Figure(count, color, 200, 200); //создали фигуру
                Figure.Draw(hDC); //по задумке должны были нарисовать, но что-то не рисует
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