#include <iostream>
#include "Class.h"
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	double R,H;

	cout << "Введите радиус сферы" << endl;
	cin >> R;

	if (R > 0) {
		Sphere sphere(R);
		cout << "Объем сферы радиусом " << sphere.getR() << " равен " << sphere.Square() << endl;

		cout << "Введите высоту конуса" << endl;
		cin >> H;
		if (H > 0) {
			Konus konus(R, H);
			cout << "Объем конуса равен " << konus.Square() << endl;;
		}
		else
			cout << "Высота должна быть больше 0" << endl;
	}
	else
		cout << "Радиус должен быть больше 0" << endl;
	system("pause");
	return 0;
}