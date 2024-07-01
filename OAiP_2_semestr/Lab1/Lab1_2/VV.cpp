#include "VV.h"

istream& operator >> (istream& in, Otdel& avto)
{
    in >> avto.name;
    in >> avto.surname;
    in >> avto.otch;
    in >> avto.number;
    in >> avto.mark;
    in >> avto.model;
    in >> avto.year;
    return in;
}


ostream& operator << (ostream& out, Otdel avto)
{
    out << "Владелец: " << avto.surname << " "
        << avto.name << " "
        << avto.otch << endl
        << "Автомобиль: " << endl
        << "Номер: " << avto.number << endl
        << "Марка: " << avto.mark << endl
        << "Модель: " << avto.model << endl
        << "Год: " << avto.year << endl << endl;
    return out;
}

istream& operator >> (istream& in, Otdel1& Group)
{
    in >> Group.N;
    Group.lik = new Otdel[Group.N];
    for (int i = 0; i < Group.N; i++)
    {
        in >> Group.lik[i];
    }
    return in;
}


ostream& operator << (ostream& out, Otdel1 Group)
{
    for (int i = 0; i < Group.N; i++)
        out << Group.lik[i];
    return out;
}

void Sort(Otdel1 baza) {
    for (int i = 0; i < baza.N; i++)
        for (int j = i; j > 0 && baza.lik[j - 1].year > baza.lik[j].year; j--) {
            swap(baza.lik[j - 1], baza.lik[j]);
        }
}
void Mark(Otdel1 baza, char b[],ostream& out) {
    for (int i = 0; i < baza.N; i++)
        if (strcmp(b, baza.lik[i].mark) == 0)
            out << baza.lik[i];
}