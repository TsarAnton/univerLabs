#include <iostream>
#include <cmath>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int i = 0, pr; double h, x, sum = 0, slag = 0, fact;
	cout << "������� ��������� � � ��������" << endl;
	cin >> x >> h; fact = 1; 
	if (x>1) {
		slag = 1; sum = slag; cout << fact << endl;
		while (fabs(slag) > h && fact > 0) {
			i = i + 1;
			fact = fact * (2 * i - 1) * 2 * i;
			slag = pow(x, 2 * i) / fact;
			if (sum > 0) { sum = sum + slag; }
			else sum = sum;
			cout << fact << endl;
		}
		cout << "����� = "<<sum << endl; 
		cout << "��������, ���������� � ������� ��������������� ������� =" << cosh(x) << endl;
		if (sum < cosh(x)) { cout << "���������� �������� ������ ����������� � ������� ��������������� ������� ����������� ����������" << endl; }
		else cout << "���������� �������� ������ ����������� � ������� ��������������� ������� ����������� ����������" << endl;
	}
	else cout << "������" << endl;
	system("pause");
	return 0;
	
}