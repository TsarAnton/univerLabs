#include <iostream>
#include <fstream>

#include "Class.h"

int main(){
    setlocale(LC_ALL, "Russian");

    char FileName[100];
    cout << "�� ������ ����� ������� ������?" << endl;
    cin.getline(FileName, sizeof(FileName));
    Baza_GAI Group;
    
    ifstream fin(FileName);
    if (fin.is_open()) {
        fin >> Group;
        fin.close();

        cout << "� ����� ���� �������� ������?\n";
        cin.getline(FileName, sizeof(FileName));
        ofstream fout(FileName);
        if (fout.is_open()){
            fout << Group;
            fout.close();
            cout << "������ �������� � ���� " << FileName << endl;
        }
        else{
            cout << "������ ������ � ���� " << FileName << endl;
        }

        string mark;
        cout << "������� �����" << endl;
        getline(cin, mark);
        Baza_GAI part_mark;
        part_mark.create_mark(mark, Group);
        ofstream fout1("Particular_mark.txt");
        if (fout1.is_open()) {
            fout1 << part_mark;
            fout1.close();
            cout << "���������� ����� " << mark << " �������� � ���� Particular_mark.txt" << endl;
        }
        else 
            cout << "������ ������ � ����" << endl;

        Group.sort();
        ofstream fout2("Sort_year.txt");
        if (fout2.is_open()) {
            fout2 << Group;
            fout2.close();
            cout << "������ �����������, ��������������� �� ����, ������� � ���� Sort_year.txt" << endl;
        }
        else
            cout << "������ ������ � ����" << endl;

    }
    else{
        cout << "���� " << FileName << " �� ������" << endl;
    }

    system("pause");
    return 0;
}