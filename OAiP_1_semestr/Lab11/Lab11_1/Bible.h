#ifndef Bible_h
#define Bible_h
struct Otdel {
    string surname;
    string name;
    string otch;
    char pol;
    int year;
    string spec;
    int stag;
};
void Input(Otdel* baza, int N);
void SORTub(Otdel* baza, int N);
void Output(Otdel* a, int N);
void OutputPens(Otdel* a, int N);
#endif