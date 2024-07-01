#ifndef furnitureH
#define furnitureH

#include <windows.h>

#include <vector>
using namespace std;

class FIGURE {
private:
    int count; //���-�� ������
    unsigned color; //����
    int centerX; //���������� ������ (���������� ������ �������������� �����)
    int centerY;
public:
    FIGURE(int count, unsigned color, int centerX, int centerY);
    void Erase(HDC hDC);
    void Draw(HDC hDC);
};

#endif