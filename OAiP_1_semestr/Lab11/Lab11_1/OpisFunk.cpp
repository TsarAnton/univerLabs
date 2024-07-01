#include <iostream>
#include <iomanip>
#include <string>
#include "Bible.h"
using namespace std;
void Input(Otdel* baza, int N) {
    int prove = 0;
    cin.get();
    for (int i = 0; i < N && prove == 0; i++) {
        cout << "Фамилия: ";
        getline(cin, baza[i].name);
        cout << "Имя: ";
        getline(cin, baza[i].surname);
        cout << "Отчество: ";
        getline(cin, baza[i].otch);
        cout << "Пол: ";
        cin >> baza[i].pol;
        cin.get();
        cout << "Специальность: ";
        getline(cin, baza[i].spec);
        cout << "Год рождения: ";
        cin >> baza[i].year;
        cout << "Стаж: ";
        cin >> baza[i].stag;
        cin.get();
        cout << endl;
        if (baza[i].year < 0 || baza[i].stag>2020 - baza[i].year || baza[i].stag < 0)
            prove = 1;
    }
}
void SORTub(Otdel* baza, int N) {
    for (int i = 1; i < N; i++)
        for (int j = i; j > 0 && baza[j - 1].stag < baza[j].stag; j--) {
            swap(baza[j - 1], baza[j]);
        }
}
void Output(Otdel* a, int N) {
    cout << "_____________________________________________________________________________________" << endl;
    cout << "|  Фамилия  |    Имя     |    Отчество    |Пол|   Специальность   |Год рождения|Стаж|" << endl;
    cout << "|___________|____________|________________|___|___________________|____________|____|" << endl;
    for (int i = 0; i < N; i++) {
        cout << "|" << setw(11) << a[i].name << "|" << setw(12) << a[i].surname << "|" << setw(16) << a[i].otch << "|" << setw(3) << a[i].pol << "|" << setw(19) << a[i].spec << "|" << setw(12) << a[i].year << "|" << setw(4) << a[i].stag << "|" << endl;
        cout << "|___________|____________|________________|___|___________________|____________|____|" << endl;
    }
}
void OutputPens(Otdel* a, int N) {
    cout << "_____________________________________________________________________________________" << endl;
    cout << "|  Фамилия  |    Имя     |    Отчество    |Пол|   Специальность   |Год рождения|Стаж|" << endl;
    cout << "|___________|____________|________________|___|___________________|____________|____|" << endl;
    for (int i = 0; i < N; i++) {
        if (a[i].year <= 2020 - 55) {
            cout << "|" << setw(11) << a[i].name << "|" << setw(12) << a[i].surname << "|" << setw(16) << a[i].otch << "|" << setw(3) << a[i].pol << "|" << setw(19) << a[i].spec << "|" << setw(12) << a[i].year << "|" << setw(4) << a[i].stag << "|" << endl;
            cout << "|___________|____________|________________|___|___________________|____________|____|" << endl;
        }
    }
}