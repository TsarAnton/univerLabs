#ifndef furnitureH
#define furnitureH

#include <windows.h>

#include <vector>
using namespace std;

class FIGURE {
private:
    int count; //кол-во сторон
    unsigned color; //цвет
    int centerX; //координаты центра (описанного вокруг многоугольника круга)
    int centerY;
public:
    FIGURE(int count, unsigned color, int centerX, int centerY);
    void Erase(HDC hDC);
    void Draw(HDC hDC);
};

#endif