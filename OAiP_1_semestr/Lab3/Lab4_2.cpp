#include <iostream>
#define _USE_MATH_DEFINES
#include <math.h>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int y, x; double z, pr1, pr2, pr3, pr4;  
	cout << "¬ведите у и х" << endl; 
	cin >> x >> y; 
	if (y != 0) {
		pr3 = (M_E < fabs(y - x)) ? M_E : fabs(y - x);
		pr2 = (x > y) ? x : y;
		pr1 = (pr3 < pr2) ? pr3 : pr2;
		pr4 = (fabs(y - x) > y) ? fabs(y - x) : y;
		z = pr1 / pr4;
		cout << z << endl;
	}
	else cout << "ќшибка : деление на 0" << endl;
	system("pause");
	return 0;
}