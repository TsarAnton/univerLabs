#include <iostream>
#include "Header.h"
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int i = 0, N;
	cout << "¬ведите количество элементов массива" << endl;
	cin >> N;
	if (N > 0) {
		int* array = new int[N];
		cout << "¬водите элементы массива" << endl;
		VVOD(array, N);
		cout << "ќтсортированный массив по убыванию: ";
		SORTub(array, N);
		VYVOD(array, N);
		cout << endl;
		cout << "ќтсортированный массив по возрастанию: ";
		SORTvoz(array, N);
		VYVOD(array, N);
		cout << endl;
		cout << "¬ведите число дл€ поиска" << endl;
		int a;
		cin >> a;
		POISK(array, N, a);
		delete[] array;
	}
	system("pause");
	return 0;
}