#include <iostream>
#include <stdlib.h>
#include <iomanip>
using namespace std;
void RANDOM(double** array, int N, int M) {
	for (int i = 0; i < N; i++) {
		for (int j=0; j < M; j++) {
			array[i][j] = (rand() % 89 + 10);
		}
		int j = 0;
	}
}
void VYVOD(double** array, int N, int M) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << setw(5) << array[i][j] << "   ";
		}
		cout << endl;
		int j = 0;
	}
}
void POVOROT(double** array, int N, int M) {
	if (N == 1) {
		cout << setw(5) << array[0][0] << endl;
	}
	else {
		int n = N - 1, m = M - 1, flag = 0, i1 = 0, smeh; int i = 0;
		while (flag == 0) {
			for (smeh = -1; n + i + smeh + 1 != i1; smeh--)
				swap(array[n + i][m + i], array[n + i + smeh][m + i]);
			for (smeh = -1; m + i + smeh + 1 != i1; smeh--)
				swap(array[n + i][m + i], array[i1][m + i + smeh]);
			for (smeh = 1; i1 + smeh - 1 != m + i; smeh++)
				swap(array[n + i][m + i], array[i1 + smeh][i1]);
			for (smeh = 1; i1 + smeh - 1 != m + i; smeh++)
				swap(array[n + i][m + i], array[n - i1][i1 + smeh]);
			i1 += 1;
			i -= 1;
			if (n + i == i1 && i1 == m + i && N % 2 == 1)
				flag = 1;
			if (N / (i1 + 1) == 1)
				flag = 1;
		}
	}
}
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
