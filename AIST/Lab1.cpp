#include <iostream>
using namespace std;

double Method_Peremn(int n) {
	if (n == 0)
		return 1;
	double zd = 1;
	for (int i = 2; i <= n; i++)
		zd *= i;
	return zd;
}

double P(int l,int m) {
	if (m == l)
		return l;
	if (m - l == 1)
		return l * m;
	int ser = (m + l) / 2;
	return P(l, ser) * P(ser + 1, m);
}
double Method_Tree(int n) {
	if (n == 0)
		return 1;
	return P(2, n);
}

int main() {
	setlocale(LC_ALL, "Russian");

	cout << "������� �����" << endl;
	int n;
	cin >> n;
	if (n >= 0)
		cout << "���������, ���������� ���������������� ������������� " << n << " = " << Method_Peremn(n) << endl;
	else 
		cout << "������" << endl;

	cout << "������� �����" << endl;
	cin >> n;
	if (n >= 0)
		cout << "���������, ���������� � ������� ��������� ������ " << n << " = " << Method_Tree(n) << endl;
	else
		cout << "������" << endl;

	system("pause");
	return 0;
}