#include <iostream>
#include <string>
#include "Header.h"
using namespace std;

void next(int& s) {
	s++;
}

int token(string input, int& s) {//input - вводимая строка, s - номер текущего токена
	char a = input[s];//текущий символ строки
	switch (a) {//в зависимости от значения 'а' возвращаем токен
	case '+':
		return t_plus;
	case '-':
		return t_minus;
	case '*':
		return t_mul;
	case '/':
		return t_div;
	case '(':
		return t_lpar;
	case ')':
		return t_rpar;
	case 'i':
		return t_id;
	case 'n':
		return t_num;
	case '$':
		return t_end;
	default://если встречается токен, не поддерживаемый грамматикой, то ошибка
		cout << "Error\n";
		exit(0);
	}
}

void ReadF(string input, int& s) {
	switch (token(input, s)) {//в зависимости от токена выполняем правило
	case t_id://правило F->id
		next(s);
		break;
	case t_num://правило F->num
		next(s);
		break;
	case t_lpar://правило F->(E)
		next(s);
		ReadE(input, s);
		if (token(input, s) == t_rpar) {//после считывания Е проверяем, есть ли закрывающая скобка
			next(s);
		}
		else {
			cout << "Error\n";
			exit(0);
		}
		break;
	case t_end://правило T'->e
		break;
	default:
		cout << "Error\n";
		exit(0);
	}
}

void ReadT_(string input, int& s) {
	switch (token(input, s)) {
	case t_plus://правило T'->e
		break;
	case t_minus://правило T'->e
		break;
	case t_mul://правило T'->*FT'
		next(s);
		ReadF(input, s);
		ReadT_(input, s);
		break;
	case t_div://правило T'->/FT'
		next(s);
		ReadF(input, s);
		ReadT_(input, s);
		break;
	case t_rpar://правило T'->e
		break;
	case t_end://правило T'->e
		break;
	default:
		cout << "Error\n";
		exit(0);
	}
}

void ReadT(string input, int& s) {
	switch (token(input, s)) {
	case t_id://правило T->FT'
		ReadF(input, s);
		ReadT_(input, s);
		break;
	case t_num://правило T->FT'
		ReadF(input, s);
		ReadT_(input, s);
		break;
	case t_lpar://правило T->FT'
		ReadF(input, s);
		ReadT_(input, s);
		break;
	default:
		cout << "Error\n";
		exit(0);
	}
}

void ReadE_(string input, int& s) {
	switch (token(input, s)) {
	case t_plus://правило E'->+TE'
		next(s);
		ReadT(input, s);
		ReadE_(input, s);
		break;
	case t_minus://правило E'->-TE'
		next(s);
		ReadT(input, s);
		ReadE_(input, s);
		break;
	case t_rpar://правило E'->e
		break;
	case t_end://правило E'->e
		break;
	default:
		cout << "Error\n";
		exit(0);
	}
}

void ReadE(string input, int& s) {
	switch (token(input, s)) {
	case t_id://правило E->TE'
		ReadT(input, s);
		ReadE_(input, s);
		break;
	case t_num://правило E->TE'
		ReadT(input, s);
		ReadE_(input, s);
		break;
	case t_lpar://правило E->TE'
		ReadT(input, s);
		ReadE_(input, s);
		break;
	case t_end://правило E->e
		break;
	default:
		cout << "Error\n";
		exit(0);
	}
}