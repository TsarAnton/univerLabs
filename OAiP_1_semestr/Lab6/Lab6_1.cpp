#include <iostream>
#include <math.h>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	double n, sum=0; 
	int i = 0,N;
	cout << "������� ���������� ���������" << endl;
	cin >> N;
	int* array = (int*)calloc(N, sizeof(double));
	cout << "������� �������� �������" << endl;
	for (i; i < N; i++) {
		cin >> array[i];
	}
	i = 0;
	while (i < N) {
		if (array[i] > 0) 
		{ sum = sum + array[i]; } 
		i = i + 1;
	}
	cout << "����� ������������� ��������� ������� = " << sum << endl;
	system("pause");
	return 0;
}