#include <iostream>
#include <fstream>

#include "VV.h"
#include "Funk.h"
using namespace std;
int main()
{
    setlocale(LC_ALL, "Russian");
    char FileName[100], file1[50];
    cout << "Из какого файла вводить данные?" << endl;
    cin.getline(FileName, sizeof(FileName));

    cout << "В какой файл выводить данные?" << endl;
    cin.getline(file1, sizeof(file1));

    cout << "В какой файл выводить автомобили определенной марки" << endl;
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
            cout << "Данные выведены в файл " << file1 << endl;
        }
        else
        {
            cout << "Ошибка записи в файл " << FileName << endl;
        }
    }
    else
    {
        cout << "Файл " << FileName << " не найден" << endl;
    }

    Sort(avto);
    cout << "По возрастанию города выпуска" << endl;
    cout << avto;

    char b[15];
    cout << "Введите строку" << endl;
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