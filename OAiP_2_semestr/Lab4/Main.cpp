#include <iostream>
#include "Class.h"


using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");

	/*Drob d1, d2;
	cout << "¬ведите дробь 1 (например 1/2)" << endl;
	cin >> d1;
	cout << "¬ведите дробь 2" << endl;
	cin >> d2;

	Drob dzel = d1 / d2, minus = d1 - d2;
	cout << d1 << " : " << d2 << " = " << dzel << endl;
	cout << d1 << " - " << d2 << " = " << minus << endl;
	system("pause");*/

	int x1, y1, width1, width2, x2, y2, height1, height2;
	x1 = 3;
	y1 = 4;
	width1 = 4;
	height1 = 2;

	x2 = 10;
	y2 = 10;
	width2 = 2;
	height2 = 3;

	if ((x1 >= x2 && x1 <= x2 + width2 && y1 <= y2 + height2 && y1 >= y2) ||
		(x1 + width1 >= x2 && x1 + width1 <= x2 + width2 && y1 <= y2 + height2 && y1 >= y2) ||
		(x1 >= x2 && x1 <= x2 + width2 && y1 + height1 <= y2 + height2 && y1 + height1 >= y2) ||
		(x1 + width1 >= x2 && x1 + width1 <= x2 + width2 && y1 + height1 <= y2 + height2 && y1 + height1 >= y2)) {
		cout << "inside" << endl;
	}
	else {
		cout << "outside" << endl;
	}
	return 0;
}