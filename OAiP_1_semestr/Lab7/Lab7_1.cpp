#include <iostream>
#include <stdlib.h>
#include <iomanip>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int i = 0,N;
	cout << "¬ведите количество элементов массива" << endl;
	cin >> N;
	if (N > 0) {
		int* array = new int[N];
		cout << "¬водите элементы массива" << endl;
		for (i; i < N; i++) {                                         
			cin >> array[i];
		}
		int i_min, c, buff,flag=0,k;
		for (int i = 1; i < N; i++) {
			buff = array[i];
			k = i;
			for (int j = i-1; j >= 0 && buff<array[j]; j--) {
				array[k] = array[j];
				k -= 1;
			}
			array[k] = buff;
		}
		cout << "New massive: ";
		for (i=0; i < N; i++) {
			cout<< array[i]<<" ";
		}
		cout << endl;
		delete[] array;
	}
	system("pause");
	return 0;
}