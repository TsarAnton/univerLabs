#include <iostream>
using namespace std;

struct Road {
	int house1;
	int house2;
	int rast;
};

int** create_matrix(int N, Road* arr, int M) {
	int** ret_arr = new int* [N];
	for (int i = 0; i < N; i++) {
		ret_arr[i] = new int[N];
	}
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			ret_arr[i][j] = 0;
	for (int i = 0; i < M; i++) {
		ret_arr[arr[i].house1][arr[i].house2] = arr[i].rast;
		ret_arr[arr[i].house2][arr[i].house1] = arr[i].rast;
	}
	return ret_arr;
}

int* min_rast_do_vershin(int N, int** matrix, int begin_index) {//алгоритм Дейкстры
	int* d = new int[N]; // минимальное расстояние
	int* v = new int[N]; // посещенные вершины
	int temp, minindex, min;
	for (int i = 0; i < N; i++)
	{
		d[i] = INT_MAX;
		v[i] = 1;
	}
	d[begin_index] = 0;
	do {
		minindex = INT_MAX;
		min = INT_MAX;
		for (int i = 0; i < N; i++) { // Если вершину ещё не обошли и вес меньше min
			if ((v[i] == 1) && (d[i] < min)) { // Переприсваиваем значения
				min = d[i];
				minindex = i;
			}
		}
		// Добавляем найденный минимальный вес
		// к текущему весу вершины
		// и сравниваем с текущим минимальным весом вершины
		if (minindex != INT_MAX) {
			for (int i = 0; i < N; i++) {
				if (matrix[minindex][i] > 0) {
					temp = min + matrix[minindex][i];
					if (temp < d[i]) {
						d[i] = temp;
					}
				}
			}
			v[minindex] = 0;
		}
	} while (minindex < 10000);
	return d;
}
int arr_sum(int* arr, int N) {
	int sum = 0;
	for (int i = 0; i < N; i++)
		sum += arr[i];
	return sum;
}
int main() {
	setlocale(LC_ALL, "Russian");

	int M, N;
	cout << "Введите кол-во домов" << endl;
	cin >> N;
	cout << "Введите кол-во дорог" << endl;
	cin >> M;

	int x, y;
	int* arr_house = new int[N];
	for (int i = 0; i < N + 1; i++) {
		arr_house[i] = i;
	}
	Road* arr_dor = new Road[M];
	int p, r, t;
	//cout << "Вводите номера перекрестков, которые соединяет дорога, и длинну дороги" << endl;
	for (int i = 0; i < M; i++) {
		cout << "Введите номер дома 1" << endl;
		cin >> p;
		cout << "Введите номкр дома 2" << endl;
		cin >> r;
		cout << "Введите длинну дороги" << endl;
		cin >> t;
		arr_dor[i].house1 = arr_house[p];
		arr_dor[i].house2 = arr_house[r];
		arr_dor[i].rast = t;
	}

	int** matrix = create_matrix(N, arr_dor, M);

	int number_of_house;
	int min = INT_MAX;
	for (int i = 0; i < N; i++) {
		int buff = arr_sum(min_rast_do_vershin(N, matrix, i), N);
		if (buff < min) {
			min = buff;
			number_of_house = i;
		}
	}
	cout << number_of_house << " " << min << endl;

	system("pause");
	return 0;
}