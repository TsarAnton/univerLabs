#include <iostream>
#include "Bible.h"
using namespace std;
int main() {
    setlocale(LC_ALL, "Russian");
    cout << "������� ���-�� �����������" << endl;
    int N;
    cin >> N;
    Otdel* baza = new Otdel[N];
    Input(baza, N);
    int prove = 0;
    for (int i = 0; i < N && prove == 0; i++)
        if (baza[i].year < 0 || baza[i].stag>2020 - baza[i].year || baza[i].stag < 0)
            prove = 1;
    if (prove == 0) {
        cout << "��������� ������ �����������" << endl;
        Output(baza, N);
        cout << endl;
        SORTub(baza, N);
        cout << "������ ����������� �� �������� �����" << endl;
        Output(baza, N);
        cout << endl;
        cout << "������ ����������� ����������� ��������" << endl;
        OutputPens(baza, N);
        cout << endl;
    }
    else cout << "������" << endl;
    system("pause");
    return 0;
}