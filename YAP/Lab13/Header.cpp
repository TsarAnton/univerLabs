#include <iostream>
#include <string>
#include "Header.h"
using namespace std;

void next(int& s) {
	s++;
}

int token(string input, int& s) {
	char a = input[s];
	switch (a) {
	case 'a':
		return t_a;
	case 'b':
		return t_b;
	case 'c':
		return t_c;
	case '$':
		return t_end;
	default:
		cout << "Error\n";
		exit(0);
	}
}

void ReadA(string input, int& s) {
	switch (token(input, s)) {
	case t_a:
		ReadB(input, s);
		next(s);
		if (token(input, s) == t_c) {
			next(s);
			ReadA(input, s);
			if (token(input, s) != t_a) {
				cout << "Error A 1\n";
				exit(0);
			}
			else {
				next(s);
			}
		}
		else {
			cout << "Error A 2\n";
			exit(0);
		}
		break;
	case t_b:
		next(s);
		ReadB(input, s);
		next(s);
		if (token(input, s) != t_b) {
			cout << "Error A 3\n";
			exit(0);
		}
		else {
			next(s);
		}
		break;
	case t_c:
		ReadB(input, s);
		next(s);
		if (token(input, s) == t_c) {
			next(s);
			ReadA(input, s);
			if (token(input, s) != t_a) {
				cout << "Error A 4\n";
				exit(0);
			}
			else {
				next(s);
			}
		}
		else {
			cout << "Error A 5\n";
			exit(0);
		}
		break;
	case t_end:
		break;
	default:
		cout << "Error A 6\n";
		exit(0);
	}
}

void ReadB(string input, int& s) {
	switch (token(input, s)) {
	case t_a:
		break;
	case t_c:
		next(s);
		ReadA(input, s);
		next(s);
		if (token(input, s) != t_c) {
			cout << "Error B 1\n";
			exit(0);
		}
		break;
	case t_end:
		break;
	default:
		cout << "Error B 2\n";
		exit(0);
	}
}