#include <iostream>
#include <fstream>
#include "Funk.h"
#include "VV.h"
using namespace std;
int main(int argc, char* argv[]) {
    setlocale(LC_ALL, "Russian");
    char file[50], file1[50];
    cout << "�� ������ ����� ������� ������?" << endl;
    cin.getline(file, sizeof(file));


    cout << "� ����� ���� �������� ������?" << endl;
    cin.getline(file1, sizeof(file1));

    cout << "� ����� ���� �������� ��������������� ������?" << endl;
    char file2[50];
    cin.getline(file2, sizeof(file2));


    cout << "������� ���-�� �������" << endl;
    int N;
    cin >> N;
    Otdel* baza = new Otdel[N];
    ifstream fin(file);
    if (fin.is_open()) {
        ReadAvtomn(fin, baza, N);
        fin.close();
        WriteAvtomn(cout, baza, N);


        ofstream fout(file1);
        if (fout.is_open()) {
            WriteAvtomn(fout, baza, N);
            fout.close();
            cout << "������ �������� � ���� " << file1 << endl;
        }
        else {
            cout << "������ ������ � ���� " << file1 << endl;
        }

        char b[15];
        cout << "������� �����" << endl;
        cin >> b;
        WriteAvtoMark(cout, baza, N,b);

        AvtoSort(baza, N);
        ofstream fout1(file2);
        if (fout1.is_open()) {
            WriteAvtomn(fout1, baza, N);
            fout1.close();
            cout << "�������������� ������ �������� � ���� " << file2 << endl;
        }
        else cout << "������" << endl;
    }
    else {
        cout << "���� " << file << " �� ������"<<endl;
    }
    system("pause");
    return 0;
}

