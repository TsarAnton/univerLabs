#ifndef Class_h
#define Class_h
#include <iostream>
using namespace std;

class Drob { //класс дробь
	int chisl;//числитель
	int znam;//знаменатель
public:
	Drob();
	void set_chisl(int n);
	void set_znam(int n);
	int get_chisl();
	int get_znam();


	friend istream& operator >> (istream& fin, Drob& num);//перегрузка ввода
	friend ostream& operator << (ostream& fout, Drob& num);//перегрузка вывода

	friend Drob operator -(Drob& num,Drob& num1);//перегрузка минуса
	friend Drob operator /(Drob& num, Drob& num1);//перегрузка деления
};
int find_NOD(int num1, int num2);//НОД двух чисел
#endif
