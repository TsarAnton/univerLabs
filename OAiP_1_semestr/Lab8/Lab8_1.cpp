#include <iostream>
#include <stdlib.h>
#include <iomanip>
using namespace std;
void SORTub(int* array, int N) {
	for (int i = 1; i < N; i++)
		for (int j = i; j > 0 && array[j - 1] < array[j]; j--) {
			swap(array[j - 1], array[j]);
		}
}
void VVOD(int* array, int N) {
	for (int i=0; i < N; i++) {
		cin >> array[i];
	}
}
void VYVOD(int* array, int N) {
	for (int i = 0; i < N; i++) {                     
		cout << array[i] << " ";
	}
}
void SORTvoz(int* array, int N) {
	for (int i = 1; i < N; i++)
		for (int j = i; j > 0 && array[j - 1] > array[j]; j--) {
			swap(array[j - 1], array[j]);
		}
}
void POISK(int* array, int N,double a) {
	int flag = 0; int levo = 0, pravo = N - 1; int ser;
	while (levo <= pravo && flag == 0) {
		ser = (levo + pravo) / 2;
		if (array[ser] == a) {
			flag += 1;
		}
		if (array[ser] < a)
			levo = ser + 1;
		else
			pravo = ser - 1;
	}
	if (flag == 0)cout << "В массиве нет такого числа" << endl;
	else cout << "Индекс числа " << a << " равен " << ser << endl;
}
int main() {
	setlocale(LC_ALL, "Russian");
	int i = 0, N;
	cout << "Введите количество элементов массива" << endl;
	cin >> N;
	if (N > 0) {
		int* array = new int[N];
		cout << "Вводите элементы массива" << endl;
		VVOD(array, N);
		cout << "Отсортированный массив по убыванию: ";
		SORTub(array, N);
		VYVOD(array, N);
		cout << endl;
		cout << "Отсортированный массив по возрастанию: ";
		SORTvoz(array, N);
		VYVOD(array, N);
		cout << endl;
		cout << "Введите число для поиска" << endl;
		int a;
		cin >> a;
		POISK(array, N, a);
		delete[] array;
	}
	system("pause");
	return 0;
}