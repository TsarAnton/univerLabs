#include <iostream>
#include <string>
#include "Header.h"
using namespace std;

//при вводе строки вместо id вводить i, вместо num вводить n, в конце строки ставить символ $
int main() {
	string input;//вводимая строка
	cin >> input;

	int s = 0;//номер текущего символа(токена)

	ReadE(input, s);//считываем начальный символ Е
	if (token(input,s) == t_end) {//если после считывания строки текущий символ = $ (конец ввода)
		cout << "OK\n";//то строка поддерживается
	}
	else {
		cout << "Error\n";//иначе не поддерживается
		exit(0);
	}

	system("pause");
	return 0;
}