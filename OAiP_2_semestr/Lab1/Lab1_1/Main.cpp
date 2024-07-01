#include <iostream>
#include <fstream>
#include "Funk.h"
#include "VV.h"
using namespace std;
int main(int argc, char* argv[]) {
    setlocale(LC_ALL, "Russian");
    char file[50], file1[50];
    cout << "Из какого файла вводить данные?" << endl;
    cin.getline(file, sizeof(file));


    cout << "В какой файл выводить данные?" << endl;
    cin.getline(file1, sizeof(file1));

    cout << "В какой файл выводить отсортированные данные?" << endl;
    char file2[50];
    cin.getline(file2, sizeof(file2));


    cout << "Введите кол-во записей" << endl;
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
            cout << "Данные выведены в файл " << file1 << endl;
        }
        else {
            cout << "Ошибка записи в файл " << file1 << endl;
        }

        char b[15];
        cout << "Введите марку" << endl;
        cin >> b;
        WriteAvtoMark(cout, baza, N,b);

        AvtoSort(baza, N);
        ofstream fout1(file2);
        if (fout1.is_open()) {
            WriteAvtomn(fout1, baza, N);
            fout1.close();
            cout << "Отсортированые данные выведены в файл " << file2 << endl;
        }
        else cout << "Ошибка" << endl;
    }
    else {
        cout << "Файл " << file << " не найден"<<endl;
    }
    system("pause");
    return 0;
}

