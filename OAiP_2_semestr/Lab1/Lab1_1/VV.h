
#ifndef VV_H
#define VV_H
#include <iostream>
#include "Funk.h"
using namespace std;
Otdel ReadAvto(istream& in);
void WriteAvto(ostream& out, Otdel baza);
void ReadAvtomn (istream& in,Otdel* baza, int N);
void WriteAvtomn (ostream& out, Otdel* baza, int N);
void WriteAvtoMark (ostream& out, Otdel* baza, int N, char b[15]);
void AvtoSort (Otdel* baza, int N);
#endif
