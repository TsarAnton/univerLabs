#include <iostream>
#include <windows.h>
#include <vector>
#include <iomanip>

using namespace std;

#define N 5

int matrix1[N][N];
int matrix2[N][N];
int matrixResult[N][N];//matrixResult = matrix1 * matrix2

struct DATA {
	int lineNumber;
};

void printMatrix(int matrix[N][N]) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << setw(4) << matrix[i][j] << " ";
		}
		cout << endl;
	}
}

//каждый поток находит отдельную строку matrixResult
DWORD WINAPI MulMatrix(CONST LPVOID lpParam) {
	//получаем номер строки matrix1
	DATA* data;
	data = (DATA*)lpParam;
	
	for(int i = 0; i < N; i++) {
		int res = 0;
		for (int j = 0; j < N; j++) {
			res += matrix1[data->lineNumber][j] * matrix2[j][i];
		}
		matrixResult[data->lineNumber][i] = res;
	}

	ExitThread(0);
}

int main() {
	setlocale(LC_ALL, "Russian");

	//потоки
	HANDLE threads[N];

	DATA data[N];

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			//рандомно заполняем матрицы
			matrix1[i][j] = 1 + rand() % 20;
			matrix2[i][j] = 1 + rand() % 20;

			matrixResult[i][j] = 0;
		}
	}

	cout << "matrix1:" << endl;
	printMatrix(matrix1);
	cout << "matrix2:" << endl;
	printMatrix(matrix2);

	for (int i = 0; i < N; i++) {
		data[i].lineNumber = i;
	}

	for (int i = 0; i < N; i++) {
		threads[i] = CreateThread(NULL, 0, &MulMatrix, &data[i], 0, NULL);
		if (threads[i] == NULL) {
			cout << "Ошибка в создании потока" << endl;
		}
	}
	
	WaitForMultipleObjects(N, threads, TRUE, INFINITE);
	for (int i = 0; i < N; i++) {
		CloseHandle(threads[i]);
	}

	cout << "resultMatrix:" << endl;
	printMatrix(matrixResult);

	return 0;
}
