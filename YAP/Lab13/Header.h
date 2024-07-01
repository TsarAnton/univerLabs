#ifndef Header_h
#define Header_h
#include <string>
using namespace std;

enum tokens {
	t_a,
	t_b,
	t_c,
	t_end
};

void next(int& s);

int token(string input, int& s);

void ReadA(string input, int& s);

void ReadB(string input, int& s);

extern int p;

#endif