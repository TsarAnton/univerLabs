#include "figure.h"
#include <cmath>

#define M_PI 3.14159265

FIGURE::FIGURE(int count, unsigned color, int centerX, int centerY) {
    this->count = count;
    this->color = color;
    this->centerX = centerX;
    this->centerY = centerY;
}

void FIGURE::Draw(HDC hDC) {
    HPEN oldPen = (HPEN)SelectObject(hDC, CreatePen(PS_SOLID, 1, color));
    HBRUSH oldBrush = (HBRUSH)SelectObject(hDC, CreateSolidBrush(color));
    POINT* Pt = new POINT[count]; //массив точек многоугольника
    for (int i = 0; i < count; i++) {
        //по формулам считаем координаты
        POINT point;
        point.x = (centerX + 10 * sin(i * 2 * M_PI / count)) * 5;
        point.y = (centerY + 10 * cos(i * 2 * M_PI / count)) * 5;
        Pt[i] = point;
    }
    //Polygon рисуеет многоугольник
    Polygon(hDC, Pt, count);
    DeleteObject(SelectObject(hDC, oldPen));
    DeleteObject(SelectObject(hDC, oldBrush));
}

void FIGURE::Erase(HDC hDC) {
    // рисуем белым цветом
    SelectObject(hDC, GetStockObject(WHITE_PEN));
    SelectObject(hDC, GetStockObject(WHITE_BRUSH));
    POINT* Pt = new POINT[count]; //массив точек многоугольника
    for (int i = 0; i < count; i++) {
        //по формулам считаем координаты
        POINT point;
        point.x = (centerX + 10 * sin(i * 2 * M_PI / count)) * 5;
        point.y = (centerY + 10 * cos(i * 2 * M_PI / count)) * 5;
        Pt[i] = point;
    }
    //Polygon рисуеет многоугольник
    Polygon(hDC, Pt, count);
}