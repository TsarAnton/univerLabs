#include "Plate.h"

// конструктор
PLATE::PLATE(int width, int height) {
    this->width = width;
    this->height = height;
}

// добавить отверстие на пластину
void PLATE::AddHole(HOLE* Hole) {
    (this->Hole).push_back(Hole);
}

// определить отверстие по координате
HOLE* PLATE::GetHoleFromPoint(int x, int y) {
    HOLE* result = NULL;                   // пока отверстие не найдено
    for (unsigned i = 0; i < Hole.size(); i++) {
        if (Hole[i]->IsPointInside(x, y)) {  // если точка внутри отверстия
            result = Hole[i];                   // запомнить ссылку на этот объект
        }
    }
    return result;
}

// нарисовать пластину и набор отверстий
void PLATE::Draw(HDC hDC) {
    // сначала рисуем пластину
    SelectObject(hDC, GetStockObject(BLACK_PEN));
    SelectObject(hDC, GetStockObject(WHITE_BRUSH));
    Rectangle(hDC, 0, 0, width, height);
    // затем рисуем все отверстия
    for (unsigned i = 0; i < Hole.size(); i++) {
        Hole[i]->Draw(hDC);
    }
}