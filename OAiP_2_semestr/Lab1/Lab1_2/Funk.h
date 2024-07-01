#ifndef STUDENTS_H
#define STUDENTS_H

struct Otdel {
    char surname[15];
    char name[15];
    char otch[15];
    int number;
    char mark[15];
    char model[15];
    int year;
};
struct Otdel1 {
    int N;
    Otdel* lik;
};
#endif
