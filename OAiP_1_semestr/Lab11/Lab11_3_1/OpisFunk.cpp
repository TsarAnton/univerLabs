#include <iostream>
#include <stdlib.h>
#include <iomanip>
using namespace std;
void VVOD(double** array, int N, int M) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> array[i][j];
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
void ZDABYTOK(double** array, int N, int M) {
	int i = 0, j = 0, flag = 0, j1 = M - 1; double zd = 1;
	for (i; i < N; i++) {
		for (j; j < M; j++) {
			if (array[i][j] == 0) {
				flag = 1;
			}
		}
		if (flag == 0) {
			for (j1; j1 >= 0; j1--) {
				zd = zd * array[i][j1];
			}
		}
		if (flag == 0)
			cout << "Произведение элементов в строке " << i << " = " << zd << endl;
		zd = 1;
		j = 0;
		flag = 0;
		j1 = M - 1;
	}

}