#ifndef IOSTUDENT_H
#define IOSTUDENT_H

#include <iostream>
#include "Funk.h"
using namespace std;


istream& operator >> (istream& in, Otdel& avto);
ostream& operator << (ostream& out, Otdel avto);
istream& operator >> (istream& in, Otdel1& avto);
ostream& operator << (ostream& out, Otdel1 avto);
void Sort(Otdel1 avto);
void Mark(Otdel1 avto, char b[],ostream& out);
#endif
