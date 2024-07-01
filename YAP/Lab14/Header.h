#ifndef Header_h
#define Header_h
#include <string>
using namespace std;

enum tokens {//список токенов
	t_plus,//+
	t_minus,//-
	t_mul,//*
	t_div,// /
	t_lpar,//(
	t_rpar,//)
	t_id,//i
	t_num,//n
	t_end//$
};

void next(int& s);//перейти к следующему токену

int token(string input, int& s);//возвратить текущий токен

void ReadF(string input, int& s);//считать F

void ReadT_(string input, int& s);//считать Т'

void ReadT(string input, int& s);//считать T

void ReadE_(string input, int& s);//считать E'

void ReadE(string input, int& s);//считать Е

#endif
