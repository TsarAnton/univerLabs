#include <iostream>
#include "Header.h"
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int N, M, i = 0, j = 0;
	cout << "������� ����������� ���������� �������" << endl;
	cin >> N;
	if (N > 0) {
		M = N;
		double** array = new double* [N];
		for (i; i < N; i++) {
			array[i] = new double[M];
		}
		RANDOM(array, N, M);
		VYVOD(array, N, M);
		cout << "�������, ���������� ������ ������� �������:" << endl;
		POVOROT(array, N, M);
		VYVOD(array, N, M);
		for (i = 0; i < N; i++)   delete   array[i];
		delete[]   array;

	}
	else cout << "����������� ������� ������ ���� ������ 0" << endl;
	system("pause");
	return 0;

}
