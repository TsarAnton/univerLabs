#include <iostream>
#include <iomanip>
#define _USE_MATH_DEFINES
#include <math.h>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	double xn, xk, dX, y1, y2, y3, a, x1, x2, x3,b; int i;
	cout << fixed;
	cout.precision(3);
	cout << "¬ведите начальное и конечное х" << endl;
	cin >> xn >> xk;
	cout << "¬ведите шаг функции и коэфициенты a и b" << endl;
	cin >> dX >> a>>b; x1 = x2 = x3 = xn;
	cout << "_____________________________________________" << endl;
	cout << "|     X    |    Y1    |    Y2    |    Y3    |" << endl;
	cout << "|__________|__________|__________|__________|" << endl;
	for (i = 0; x1 <= xk&&x2<=xk&&x3<=xk&&dX>0; i++) {
		if (x1 <= -1) { y1 = sqrt(log10(fabs(x1)) * log10(fabs(x1)) + x1 * x1); cout << "|" << setw(10) << x1 << "|" << setw(10)<< y1 << "|"; x1 = x1 + dX; }
		else { y1 = (1 - a) * x1 * x1 + a; cout << "|" << setw(10)<<x1 << "|" << setw(10)<<y1 << "|"; x1 = x1 + dX; }
		if (x2 <= 0) { y2 = 1 - pow(M_E,x2); cout <<setw(10)<< y2 << "|"; x2 = x2 + dX; }
		else { y2 = cos(b*x2); cout << setw(10)<<y2 << "|"; x2 = x2 + dX; }
		if (x3 <= -1) { y3 = sin(log10(fabs(x3))); cout << "  " <<setw(10)<< y3 << "|"<<endl; x3 = x3 + dX; }
		else { y3 = sin((x3 + 1) * (x3 + 1)); cout << setw(10)<<y3 << "|"<<endl; x3 = x3 + dX; }

	}
	system("pause");
	return 0;
}