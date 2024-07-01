#include "Plate.h"

// �����������
PLATE::PLATE(int width, int height) {
    this->width = width;
    this->height = height;
}

// �������� ��������� �� ��������
void PLATE::AddHole(HOLE* Hole) {
    (this->Hole).push_back(Hole);
}

// ���������� ��������� �� ����������
HOLE* PLATE::GetHoleFromPoint(int x, int y) {
    HOLE* result = NULL;                   // ���� ��������� �� �������
    for (unsigned i = 0; i < Hole.size(); i++) {
        if (Hole[i]->IsPointInside(x, y)) {  // ���� ����� ������ ���������
            result = Hole[i];                   // ��������� ������ �� ���� ������
        }
    }
    return result;
}

// ���������� �������� � ����� ���������
void PLATE::Draw(HDC hDC) {
    // ������� ������ ��������
    SelectObject(hDC, GetStockObject(BLACK_PEN));
    SelectObject(hDC, GetStockObject(WHITE_BRUSH));
    Rectangle(hDC, 0, 0, width, height);
    // ����� ������ ��� ���������
    for (unsigned i = 0; i < Hole.size(); i++) {
        Hole[i]->Draw(hDC);
    }
}