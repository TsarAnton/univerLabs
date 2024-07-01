#include "Class.h"
#include <iterator>
using namespace std;

string Machine::get_mark() {
    return mark;
}
int Machine::get_year() {
    return year;
}

istream& operator >> (istream& in, Machine& auto1) {
    in >> auto1.number;
    in >> auto1.mark;
    in >> auto1.model;
    in >> auto1.FirstName;
    in >> auto1.LastName;
    in >> auto1.MiddleName;
    in >> auto1.year;
    return in;
}

ostream& operator << (ostream& out, Machine auto1) {
     out << "Номер автомобиля: " << auto1.number << endl
        << "Марка автомобиля: " << auto1.mark << endl
        << "Модель автомобиля: " << auto1.model << endl
        << "Владелец: " << auto1.LastName << " " << auto1.FirstName << " " << auto1.MiddleName << endl
        << "Год выпуска: " << auto1.year << endl << endl;
    return out;
}

istream& operator >> (istream& in, Baza_GAI& Group) {
    istream_iterator<Machine> in_iter(in);
    istream_iterator<Machine> in_end;
    while (in_iter != in_end) {
        Group.machines.push_back(*in_iter);
        in_iter++;
    }
    return in;
}

ostream& operator << (ostream& out, Baza_GAI Group) {
    out << "База ГАИ " << endl;
    vector<Machine>::iterator iter;
    iter = Group.machines.begin();
    while (iter != Group.machines.end()) {
        out << *iter << endl;
        iter++;
    }
    return out;
}

//void Baza_GAI::print_mark(string m, ostream& out) {
//    vector<Machine>::iterator iter;
//    iter = this->machines.begin();
//    while (iter != this->machines.end()) {
//        Machine auto1 = *iter;
//        if (auto1.get_mark() == m) {
//            out << auto1;
//        }
//        iter++;
//    }
//}

void Baza_GAI::create_mark(string mark, Baza_GAI& Group) {
    vector<Machine>::iterator iter;
        iter = Group.machines.begin();
        while (iter != Group.machines.end()) {
            Machine auto1 = *iter;
            if (auto1.get_mark() == mark) {
                this->machines.push_back(*iter);
            }
            iter++;
        }
}

void Baza_GAI::sort() {
    vector<Machine>::iterator i, j, key;
    for (i = this->machines.begin() + 1; i < this->machines.end(); i++) {
        key = i;
        Machine auto1 = *key;
        int temp = auto1.get_year();
        for (j = i; j > this->machines.begin(); j--) {
            Machine auto2 = *(j - 1);
            if (temp < auto2.get_year()) {
                *j = *(j - 1);
                key = j - 1;

            }
        }
        *key = auto1;
    }
}