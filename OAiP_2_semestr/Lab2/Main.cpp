#include <fstream>
#include "Class.h"
#include <string>
#include <iostream>
using namespace std;
int main() {
    setlocale(LC_ALL, "Russian");

    char file[50];
    cout << "� ����� ���� �������� ������?" << endl;
    cin.getline(file, sizeof(file)+1);

    double x1, y1, x2, y2, x3, y3;
    cout << "������� ���������� ������ ����� ������������"<<endl;
    cin >> x1 >> y1;
    cout << "������� ���������� ������ ����� ������������"<<endl;
    cin >> x2 >> y2;
    cout << "������� ���������� ������� ����� ������������" << endl;;
    cin >> x3 >> y3;

    Point A = Point(x1, y1), B = Point(x2, y2), C = Point(x3, y3);
    if (((A.getX() - B.getX() == 0) && (A.getY() - B.getY()) == 0) ||
        ((A.getX() - C.getX() == 0) && (A.getY() - C.getY()) == 0) ||
        ((B.getX() - C.getX() == 0) && (B.getY() - C.getY()) == 0) ||
        (((A.getX() - C.getX()) * (B.getY() - C.getY()) - (B.getX() - C.getX()) * (A.getY() - C.getY())) == 0))
        cout << "����� ����� �� ����� ������ ��� 2 ����� ���������" << endl;
    else {

        Segment Otrezok1 = Segment(A, B), Otrezok2 = Segment(B, C), Otrezok3 = Segment(A, C);

        cout << "������� ���������� ������ ����������" << endl;
        double x0, y0;
        cin >> x0 >> y0;
        Point Cen = Point(x0, y0);
        cout << "������� ������ ����������" << endl;
        double R;
        cin >> R;
        Circle Krug = Circle(Cen, R);

        if (pow(A.getX() - Krug.getCenter().getX(), 2) + pow(A.getY() - Krug.getCenter().getY(), 2) == pow(Krug.getR(), 2) &&
            pow(B.getX() - Krug.getCenter().getX(), 2) + pow(B.getY() - Krug.getCenter().getY(), 2) == pow(Krug.getR(), 2) &&
            pow(C.getX() - Krug.getCenter().getX(), 2) + pow(C.getY() - Krug.getCenter().getY(), 2) == pow(Krug.getR(), 2)) {
            cout << "����������� � ��������� � ������ (" << A.getX() << " , " << A.getY() << "), (" <<
                B.getX() << " , " << B.getY() << "), (" <<
                C.getX() << " , " << C.getY() << ")" << endl << "����� �� ���������� � ������� � ����� (" <<
                Krug.getCenter().getX() << " , " << Krug.getCenter().getY() << ") � �������� " << Krug.getR() << endl;
        }
        else {
            cout << "����������� � ��������� � ������ (" << A.getX() << " , " << A.getY() << "), (" <<
                B.getX() << " , " << B.getY() << "), (" <<
                C.getX() << " , " << C.getY() << ")" << endl << "�� ����� �� ���������� � ������� � ����� (" <<
                Krug.getCenter().getX() << " , " << Krug.getCenter().getY() << ") � �������� " << Krug.getR() << endl;
        }

        Vector A1 = Vector(A, B), A2 = Vector(B, C);
        cout <<"��������� ������������ �������� "<< A1.scalyr(A2) << endl;

        ofstream fout(file);
        if (fout.is_open()) {
            fout<<Krug;
            fout.close();
            cout << "������ �������� � ����" << endl;
        }
        else
            cout << "������ ������ � ����" << endl;
    }
    return 0;
}


