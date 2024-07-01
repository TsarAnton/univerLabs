
#include "VV.h"
Otdel ReadAvto(istream& in){
    Otdel avto;
    in >> avto.name;
    in >> avto.surname;
    in >> avto.otch;
    in >> avto.number;
    in >> avto.mark;
    in >> avto.model;
    in >> avto.year;
    return avto;
}
void WriteAvto(ostream& out, Otdel avto)
{
    out << "Владелец: " << avto.surname << " "
        << avto.name << " "
        << avto.otch << endl
        << "Автомобиль: " << endl
        << "Номер: " << avto.number << endl
        << "Марка: " << avto.mark << endl
        << "Модель: " << avto.model << endl
        << "Год: " << avto.year << endl<<endl;
}
void ReadAvtomn(istream& in, Otdel* avto,int N)
{
    for (int i = 0; i < N; i++)
    {
        avto[i] = ReadAvto(in);
    }
}
void WriteAvtomn(ostream& out, Otdel* avto,int N)
{
    for (int i = 0; i < N; i++) 
        WriteAvto(out, avto[i]);
}
void WriteAvtoMark(ostream& out, Otdel* baza, int N, char b[15])
{
    for (int i = 0; i < N; i++)
        if (strcmp(b, baza[i].mark) == 0) {
            WriteAvto(out, baza[i]);
        }
}
void AvtoSort(Otdel* baza, int N) {
    for (int i = 1; i < N; i++)
        for (int j = i; j > 0 && baza[j - 1].year > baza[j].year; j--) {
            swap(baza[j - 1], baza[j]);
        }
}