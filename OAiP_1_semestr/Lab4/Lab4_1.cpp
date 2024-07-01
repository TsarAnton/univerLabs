#include <iostream>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int i,flag=0,a,sum,zd;
	cout << "Введите N первых чисел" << endl;
   	cin >> i; a = 0; sum = 0; zd = 1;
	for (flag; flag < i; flag++) { a = a + 1; sum = sum + a; zd = zd * a; }
	cout <<"а) Сумма первых "<<i<<" чисел = "<< sum << "       Произведение первых "<<i<<" чисел = "<<zd<<endl;
    a = -1; sum = 0; flag = 0; zd = 1;
	cout << "Введите N первых чисел" << endl;
	cin >> i;
	for (flag; flag < i; flag++) { a = a + 2;sum=sum+a ; zd = zd * a; }
	cout << "в) Сумма первых " << i << " чисел = " << sum << "       Произведение первых " << i << " чисел = " << zd << endl;
	double a1= 0, sum1 = 0, flag1 = 0, zd1 = 1;
	cout << "Введите N первых чисел" << endl;
	cin >> i;
	for (flag1; flag1 < i; flag1++) { a1 = 1/(flag1+1); sum1 = sum1 + a1; zd1 = zd1 * a1; }
	cout << "д) Сумма первых " << i << " чисел = " << sum1 << "       Произведение первых " << i << " чисел = " << zd1 << endl;
	a1 = 0, sum1 = 0, flag1 = 0, zd1 = 1; double dop = -1;
	cout << "Введите N первых чисел" << endl;
	cin >> i;
	for (flag1; flag1 < i; flag1++) { a1 = 1 / (dop + 2); dop = dop + 2; sum1 = sum1 + a1; zd1 = zd1 * a1; }
	cout << "ж) Сумма первых " << i << " чисел = " << sum1 << "       Произведение первых " << i << " чисел = " << zd1 << endl;
	a1 = 0, sum1 = 0, flag1 = 0, zd1 = 1; double minus = -1;
	cout << "Введите N первых чисел" << endl;
	cin >> i;
	for (flag1; flag1 < i; flag1++) { minus = -1 * minus; a1 = minus*1 / (flag1 + 1); sum1 = sum1 + a1; zd1 = zd1 * a1; }
	cout << "и) Сумма первых " << i << " чисел = " << sum1 << "       Произведение первых " << i << " чисел = " << zd1 << endl;
	system("pause");
	return 0;
}