#include <iostream>
#include "Header.h"
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int N, M, i = 0, j = 0, j1;
	cout << "Введите кол-во строк и столбцов" << endl;
	cin >> N >> M;
	if (N > 0 && M > 0) {
		double** array = new double* [N];
		for (i; i < N; i++) {
			array[i] = new double[M];
		}
		cout << "Вводите элементы массива" << endl;
		VVOD(array, N, M);
		VYVOD(array, N, M);
		ZDABYTOK(array, N, M);
		for (i = 0; i < N; i++)   delete   array[i];
		delete[]   array;

	}
	else cout << "Размерность должна быть > 0" << endl;
	system("pause");
	return 0;
}