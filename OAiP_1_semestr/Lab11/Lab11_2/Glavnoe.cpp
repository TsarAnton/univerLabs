#include <iostream>
#include "Header.h"
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int i = 0, N;
	cout << "������� ���������� ��������� �������" << endl;
	cin >> N;
	if (N > 0) {
		int* array = new int[N];
		cout << "������� �������� �������" << endl;
		VVOD(array, N);
		cout << "��������������� ������ �� ��������: ";
		SORTub(array, N);
		VYVOD(array, N);
		cout << endl;
		cout << "��������������� ������ �� �����������: ";
		SORTvoz(array, N);
		VYVOD(array, N);
		cout << endl;
		cout << "������� ����� ��� ������" << endl;
		int a;
		cin >> a;
		POISK(array, N, a);
		delete[] array;
	}
	system("pause");
	return 0;
}