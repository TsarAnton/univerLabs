#include <iostream>
#include "Class.h"
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");

	int n;
	cout << "Введите кол-во сторон" << endl;
	cin >> n;

	if (n >= 3) {

		Figure<int> mnygol(n);
		cout << "Вводите координаты точек многоугольника" << endl;
		cin >> mnygol;

		cout << "Координаты точек многоугольника:" << mnygol << endl;
		cout << "Периметр многоугольника = " << mnygol.Perimetr() << endl;
		cout << "Площадь многоугольника = " << mnygol.Square() << endl;


		Figure<int> mnygol1(mnygol);
		cout << "Проверка конструктора копирования: координаты скопированного многоугольника равны " << mnygol1 <<" и кол-во сторон = "<<mnygol1.get_N()<< endl;
	}
	else
		cout << "У многоугольника должно быть больше 2 сторон" << endl;
	system("pause");
	return 0;
}