#include <iostream>
#include <fstream>

#include "VV.h"
#include "Funk.h"
using namespace std;
int main()
{
    setlocale(LC_ALL, "Russian");
    char FileName[100], file1[50];
    cout << "�� ������ ����� ������� ������?" << endl;
    cin.getline(FileName, sizeof(FileName));

    cout << "� ����� ���� �������� ������?" << endl;
    cin.getline(file1, sizeof(file1));

    cout << "� ����� ���� �������� ���������� ������������ �����" << endl;
    char file2[50];
    cin.getline(file2, sizeof(file2));

    Otdel1 avto;
    ifstream fin(FileName);

    if (fin.is_open()) {
        fin >> avto;
        fin.close();

        cout << avto;

        ofstream fout(file1);
        if (fout.is_open())
        {
            fout << avto;
            fout.close();
            cout << "������ �������� � ���� " << file1 << endl;
        }
        else
        {
            cout << "������ ������ � ���� " << FileName << endl;
        }
    }
    else
    {
        cout << "���� " << FileName << " �� ������" << endl;
    }

    Sort(avto);
    cout << "�� ����������� ������ �������" << endl;
    cout << avto;

    char b[15];
    cout << "������� ������" << endl;
    cin.getline(b, sizeof(b));
    ofstream fout1(file2);
    if (fout1.is_open()) {
        for (int i = 0; i < avto.N; i++)
            if (strcmp(avto.lik[i].mark, b) == 0)
                fout1 << avto.lik[i];
    }

    system("pause");
    return 0;
}