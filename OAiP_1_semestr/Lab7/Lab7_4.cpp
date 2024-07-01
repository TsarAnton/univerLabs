#include <iostream>
#include <stdlib.h>
#include <iomanip>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int N, M, i = 0, j = 0;
	cout << "¬ведите размерность квадратной матрицы" << endl;
	cin >> N;
	if (N > 0) {
		M = N;
		int** array = new int* [N];
		for (i; i < N; i++) {
			array[i] = new int[M];
		}
		for (i = 0; i < N; i++) {
			for (j; j < M; j++) {
				array[i][j] = (rand() % 89 + 10);
			}
			j = 0;
		}
		i = 0; j = 0;
		for (i; i < N; i++) {
			for (j; j < M; j++) {
				cout << setw(5) << array[i][j] << "   ";
			}
			cout << endl;
			j = 0;
		}
		cout << "ћатрица, повернута€ против часовой стрелки:" << endl;
		if (N == 1) {
			cout << setw(5) << array[0][0] << endl;
		}
		else {
			int n = N - 1, m = M - 1, flag = 0, i1 = 0, smeh; i = 0;
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
			i = 0; j = 0;
			for (i; i < N; i++) {
				for (j; j < M; j++) {
					cout << setw(5) << array[i][j] << "   ";
				}
				cout << endl;
				j = 0;
			}
		}
		for (i = 0; i < N; i++)   delete   array[i]; 
		delete[]   array;

	}
	else cout << "–азмерность матрицы должна быть больше 0" << endl;
	system("pause");
	return 0;

}
