#include <iostream>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int i,flag=0,a,sum,zd;
	cout << "������� N ������ �����" << endl;
   	cin >> i; a = 0; sum = 0; zd = 1;
	for (flag; flag < i; flag++) { a = a + 1; sum = sum + a; zd = zd * a; }
	cout <<"�) ����� ������ "<<i<<" ����� = "<< sum << "       ������������ ������ "<<i<<" ����� = "<<zd<<endl;
    a = -1; sum = 0; flag = 0; zd = 1;
	cout << "������� N ������ �����" << endl;
	cin >> i;
	for (flag; flag < i; flag++) { a = a + 2;sum=sum+a ; zd = zd * a; }
	cout << "�) ����� ������ " << i << " ����� = " << sum << "       ������������ ������ " << i << " ����� = " << zd << endl;
	double a1= 0, sum1 = 0, flag1 = 0, zd1 = 1;
	cout << "������� N ������ �����" << endl;
	cin >> i;
	for (flag1; flag1 < i; flag1++) { a1 = 1/(flag1+1); sum1 = sum1 + a1; zd1 = zd1 * a1; }
	cout << "�) ����� ������ " << i << " ����� = " << sum1 << "       ������������ ������ " << i << " ����� = " << zd1 << endl;
	a1 = 0, sum1 = 0, flag1 = 0, zd1 = 1; double dop = -1;
	cout << "������� N ������ �����" << endl;
	cin >> i;
	for (flag1; flag1 < i; flag1++) { a1 = 1 / (dop + 2); dop = dop + 2; sum1 = sum1 + a1; zd1 = zd1 * a1; }
	cout << "�) ����� ������ " << i << " ����� = " << sum1 << "       ������������ ������ " << i << " ����� = " << zd1 << endl;
	a1 = 0, sum1 = 0, flag1 = 0, zd1 = 1; double minus = -1;
	cout << "������� N ������ �����" << endl;
	cin >> i;
	for (flag1; flag1 < i; flag1++) { minus = -1 * minus; a1 = minus*1 / (flag1 + 1); sum1 = sum1 + a1; zd1 = zd1 * a1; }
	cout << "�) ����� ������ " << i << " ����� = " << sum1 << "       ������������ ������ " << i << " ����� = " << zd1 << endl;
	system("pause");
	return 0;
}