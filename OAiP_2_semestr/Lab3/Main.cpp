#include <iostream>
#include "Class.h"
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	double R,H;

	cout << "������� ������ �����" << endl;
	cin >> R;

	if (R > 0) {
		Sphere sphere(R);
		cout << "����� ����� �������� " << sphere.getR() << " ����� " << sphere.Square() << endl;

		cout << "������� ������ ������" << endl;
		cin >> H;
		if (H > 0) {
			Konus konus(R, H);
			cout << "����� ������ ����� " << konus.Square() << endl;;
		}
		else
			cout << "������ ������ ���� ������ 0" << endl;
	}
	else
		cout << "������ ������ ���� ������ 0" << endl;
	system("pause");
	return 0;
}