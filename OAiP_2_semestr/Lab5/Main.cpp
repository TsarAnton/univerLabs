#include <iostream>
#include "Class.h"
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");

	int n;
	cout << "������� ���-�� ������" << endl;
	cin >> n;

	if (n >= 3) {

		Figure<int> mnygol(n);
		cout << "������� ���������� ����� ��������������" << endl;
		cin >> mnygol;

		cout << "���������� ����� ��������������:" << mnygol << endl;
		cout << "�������� �������������� = " << mnygol.Perimetr() << endl;
		cout << "������� �������������� = " << mnygol.Square() << endl;


		Figure<int> mnygol1(mnygol);
		cout << "�������� ������������ �����������: ���������� �������������� �������������� ����� " << mnygol1 <<" � ���-�� ������ = "<<mnygol1.get_N()<< endl;
	}
	else
		cout << "� �������������� ������ ���� ������ 2 ������" << endl;
	system("pause");
	return 0;
}