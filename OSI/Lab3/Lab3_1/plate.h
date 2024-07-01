#ifndef PlateH
#define PlateH

#include <windows.h>

#include <vector>
using namespace std;

#include "Hole.h"

class PLATE {
private:
    int width, height;                     // ������� ��������
    vector<HOLE*> Hole;                   // ������ ���������
public:
    PLATE(int width, int height);          // �����������
    void AddHole(HOLE* Hole);             // �������� ��������� �� ��������
    HOLE* GetHoleFromPoint(int x, int y);   // ���������� ��������� �� ����������
    void Draw(HDC hDC);                    // ���������� �������� � �����������
};

#endif
