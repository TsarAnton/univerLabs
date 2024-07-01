#include "PlateWindow.h"
#include "Plate.h"
#include "Hole.h"
#include <math.h>

// �������� � ����������� ������ ���� "��������"
int RegisterPlateWindow()
{
    WNDCLASS wcl;
    wcl.hInstance = GetModuleHandle(NULL);
    wcl.lpszClassName = L"PlateWindowClass";
    wcl.lpfnWndProc = PlateWindowProc;
    wcl.style = CS_HREDRAW | CS_VREDRAW;
    wcl.lpszMenuName = NULL;
    wcl.hIcon = NULL;
    wcl.hCursor = LoadCursor(NULL, IDC_CROSS);
    wcl.hbrBackground = (HBRUSH)(1 + COLOR_WINDOW);
    wcl.cbClsExtra = 0;
    wcl.cbWndExtra = 0;
    return RegisterClass(&wcl);
}


// ������� ������� ����������� ����
LRESULT CALLBACK PlateWindowProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam)
{
    // ����������� ��������� ����������, ����������� ��� ������ ������� �������
    static PLATE Plate(200, 200);   // ������-��������
    static HOLE* LastHole;        // ������ �� ��������� ������-���������
    static int LastX, LastY;        // ��������� ���������� ����
    static enum { NO_OPERATION, CHANGE_SIZE, CHANGE_POS } Mode; // ����� ������ � ���������

                                                                // ������� ��������� ��������� ������������ "����"
    MouseState ms;
    ExtractMouseParams(wParam, lParam, &ms);

    switch (uMsg)
    {
    case WM_PAINT:
    {
        MessageBox(hWnd, L"A", L"A", MB_OK);
        PAINTSTRUCT ps;
        HDC hDC = BeginPaint(hWnd, &ps);  // �������� �������� ����������
        Plate.Draw(hDC);                  // ������� ����� ���������� ����������� ��������
        EndPaint(hWnd, &ps);              // ���������� �������� ����������

        //ReleaseDC(hWnd, hDC);

        break;
    }

    case WM_LBUTTONDOWN: // ������� ����� ������ ������������ "����"
                         // ������� ������ � �������� ������ ��� ������
    {
        LastHole = new HOLE(ms.xPos, ms.yPos, 5);  // ������� ����� ������-���������,
        Plate.AddHole(LastHole);               // ��������� ��� �� ��������
        HDC hDC = GetWindowDC(hWnd);
        LastHole->Draw(hDC);     // � ����� �� ������
        ReleaseDC(hWnd, hDC);
        Mode = CHANGE_SIZE; // ��������� � ����� ��������� ������� �������
        break;
    }

    case WM_MBUTTONDOWN: // ������� ������� ������ ������������ "����"
                         // �������� ������ ������ �������
    {
        LastHole = Plate.GetHoleFromPoint(ms.xPos, ms.yPos); // ������� ������ ��� ���������� ����
        if (LastHole != NULL) { // ���� �� ����
            Mode = CHANGE_SIZE;   // ��������� � ����� ��������� ������� �������
        }
        break;
    }

    case WM_RBUTTONDOWN: // ������� ������ ������ ������������ "����"
                         // �������� ������ ��������� �������
    {
        LastHole = Plate.GetHoleFromPoint(ms.xPos, ms.yPos); // ������� ������ ��� ���������� ����
        if (LastHole != NULL) { // ���� �� ����
            Mode = CHANGE_POS;    // ��������� � ����� ��������� ��������� �������
            LastX = ms.xPos; LastY = ms.yPos;  // �������� ���������� �������
        }
        break;
    }

    case WM_MOUSEMOVE: // ����������� ������������ "����"
    {
        if (LastHole != NULL) // �����-������ ������ ������?
            switch (Mode) {     // � ����� ������ ������ ���������?
            case CHANGE_SIZE: // --- ����� ��������� ������� ������� ---
            {
                int r = sqrt(pow(ms.xPos - LastHole->GetX(), 2)     // ������� ������ �������
                    + pow(ms.yPos - LastHole->GetY(), 2));
                HDC hDC = GetWindowDC(hWnd);
                LastHole->Erase(hDC);     // � ����� �� ������     // �������
                LastHole->SetSize(r);                    // �������� ������
                LastHole->Draw(hDC);     // � ����� �� ������
                ReleaseDC(hWnd, hDC);      // ���������� ������
                break;
            }
            case CHANGE_POS: // --- ����� ��������� ��������� ������� ---
            {
                // ����������� ������� ������
                HDC hDC = GetWindowDC(hWnd);
                LastHole->Erase(hDC);     // � ����� �� ������         // �������
                LastHole->MoveBy(ms.xPos - LastX, ms.yPos - LastY);  // �������� ����������
                LastHole->Draw(hDC);     // � ����� �� ������
                ReleaseDC(hWnd, hDC);            // ���������� ������
                                                                // ��������� ����������, ���� ��� ����������� ������
                LastX = ms.xPos; LastY = ms.yPos;
                break;
            }
            }
        break;
    }

    case WM_LBUTTONUP: // ��������� ����� ������
    case WM_MBUTTONUP: // ��������� ������� ������
    case WM_RBUTTONUP: // ��������� ������ ������
    {
        Mode = NO_OPERATION; // ������ ����� ������
        LastHole = NULL;     // ������, � ����� ���������� ��������
        InvalidateRect(hWnd, NULL, false); // �������� ����
        break;
    }

    default:
        return DefWindowProc(hWnd, uMsg, wParam, lParam);
    }
    return 0;
}


// ������� ��� ������� ���������� ��������� ������������ "����"
void ExtractMouseParams(WPARAM wParam, LPARAM lParam, MouseState* ms) {
    // ���������� ��������� ���������
    ms->xPos = LOWORD(lParam);
    ms->yPos = HIWORD(lParam);
    // ������ ��������� ������ � ������
    ms->lButtonPressed = (wParam & MK_LBUTTON) != 0;
    ms->rButtonPressed = (wParam & MK_RBUTTON) != 0;
    ms->mButtonPressed = (wParam & MK_MBUTTON) != 0;
    ms->shiftPressed = (wParam & MK_SHIFT) != 0;
    ms->controlPressed = (wParam & MK_CONTROL) != 0;
}