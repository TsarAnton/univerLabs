#include <iostream>
#include <iomanip>
#include <stdlib.h>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int i = 0, i2 = 0, N; double a, b;
	cout << "������� ���������� ���������" << endl;
	cin >> N;
	int* array = (int*)calloc(N, sizeof(double));
	int lik = N;
	cout << "������� �������� [a,b]" << endl;
	cin >> a >> b;
	if (a < b) {
		cout << "������� �������� �������" << endl;
		for (i = 0; i < N; i++) {
			cin >> array[i];
		}
		i = 0;
		while (i < N) {
			if (fabs(array[i]) > a && fabs(array[i]) < b) {
				lik -= 1;
			}
			i += 1;
		}
		int* array2 = (int*)calloc(lik, sizeof(double));
		cout << "����� ������: ";
		for (i = 0; i < N; i++) {
			if ((fabs(array[i]) < a || fabs(array[i]) > b) && i2 < lik) {
				array2[i2] = array[i];
				cout << array2[i2] << " ";
				i2 += 1;
			}
		}
	}
	else cout << "������" << endl;
	cout << endl;
	system ("pause");
    return 0;
}