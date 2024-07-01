#include <iostream>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int A, A1,A2;
	cout << "¬ведите A" << endl;
	cin >> A;
	A = A * A;//1 операци€
	A = A * A;//2 операци€
	A = A * A;//3 операци€
	cout << "ј в степени 8 = " << A << endl;
	cin >> A;
	A1 = A * A;//1 операци€
	A = A1 * A1;//2 операци€
	A = A * A;//3 операци€
	A = A1 * A;//4 операци€
	cout << "ј в степени 10 = " << A << endl;
	cin >> A;
	A1 = A * A;//1 операци€
	A2 = A1 * A;//2 операци€
	A1 = A2 * A1;//3 операци€
	A = A1 * A1;//4 операци€
	A = A * A2;//5 операци€
	cout << "ј в степени 13 = " << A << endl;
	cin >> A;
	A1 = A * A;//1 операци€
	A2 = A1 * A;//2 операци€
	A2 = A1 * A2;//3 операци€
	A = A2 * A2;//4 операци€
	A = A * A2;//5 операци€
	cout << "ј в степени 15 = " <<A<< endl;
	cin >> A;
	A1 = A * A;//1 операци€
	A2= A1 * A1;//2 операци€
	A1 = A2 * A2;//3 операци€
	A1 = A1 * A1;//4 операци€
	A1 = A1 * A2;//5 операци€
	A = A1 * A;//6 операци€
	cout << "ј в степени 21 = " <<A<< endl;
	system("pause");
		return 0;

}
