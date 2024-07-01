#include <iostream>
#include <fstream>

#include "Class.h"

int main(){
    setlocale(LC_ALL, "Russian");

    char FileName[100];
    cout << "Из какого файла вводить данные?" << endl;
    cin.getline(FileName, sizeof(FileName));
    Baza_GAI Group;
    
    ifstream fin(FileName);
    if (fin.is_open()) {
        fin >> Group;
        fin.close();

        cout << "В какой файл выводить данные?\n";
        cin.getline(FileName, sizeof(FileName));
        ofstream fout(FileName);
        if (fout.is_open()){
            fout << Group;
            fout.close();
            cout << "Данные выведены в файл " << FileName << endl;
        }
        else{
            cout << "Ошибка записи в файл " << FileName << endl;
        }

        string mark;
        cout << "Введите марку" << endl;
        getline(cin, mark);
        Baza_GAI part_mark;
        part_mark.create_mark(mark, Group);
        ofstream fout1("Particular_mark.txt");
        if (fout1.is_open()) {
            fout1 << part_mark;
            fout1.close();
            cout << "Автомобили марки " << mark << " выведены в файл Particular_mark.txt" << endl;
        }
        else 
            cout << "Ошибка записи в файл" << endl;

        Group.sort();
        ofstream fout2("Sort_year.txt");
        if (fout2.is_open()) {
            fout2 << Group;
            fout2.close();
            cout << "Список автомобилей, отсортированный по дате, выведен в файл Sort_year.txt" << endl;
        }
        else
            cout << "Ошибка записи в файл" << endl;

    }
    else{
        cout << "Файл " << FileName << " не найден" << endl;
    }

    system("pause");
    return 0;
}