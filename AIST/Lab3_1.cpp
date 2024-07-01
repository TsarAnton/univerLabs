#include <iostream>
#include <iomanip>
using namespace std;


void print_day(int N) {
	for (int i = 0; i < N; i++)
		cout << setw(5) <<i;
	cout << endl;
}
void print_tsena(int* h, int n) {
	for (int i = 0; i < n; i++)
		cout << setw(5)<<h[i];
	cout << endl;
}
void print_razn(int* h, int n) {
	cout << setw(5) << " ";
	for (int i = 0; i < n - 1; i++)
		cout << setw(5) <<h[i];
	cout << endl;
}


int max_ser(int* arr, int beg, int ser, int kon){
	int sum = 0;
	int summa1 = INT_MIN;
	for (int i = ser; i >= beg; i--){
		sum = sum + arr[i];
		if (sum > summa1)
			summa1 = sum;
	}
	sum = 0;
	int summa2 = INT_MIN;
	for (int i = ser + 1; i <= kon; i++){
		sum = sum + arr[i];
		if (sum > summa2)
			summa2 = sum;
	}
	return summa1 + summa2;
}

int max_pribul(int* arr, int beg, int kon){
	if (beg == kon)
		return arr[beg];
	int ser = (beg + kon) / 2;
		return max(max(max_pribul(arr, beg, ser),
			max_pribul(arr, ser + 1, kon)),
			max_ser(arr, beg,ser, kon));
}

void print_index(int* arr, int N, int pribul) {
	bool flag = true;
	for (int i = 1; i < N && flag; i++) {
		for (int j = i - 1; j >= 0; j--) {
			if (arr[j] == arr[i] - pribul) {
				cout << "Купить надо в день " << j << endl;
				cout << "Продать надо в день " << i << endl;
				flag = false;
			}
		}
	}
}
int main() {
	setlocale(LC_ALL, "Russian");

	int N;
	cout << "Введите кол-во дней" << endl;
	cin >> N;
	if (N > 0) {
		int* tseny = new int[N];
		cout << "Вводите цены акций в каждый день" << endl;
		for (int i = 0; i < N; i++)
			cin >> tseny[i];

		cout << setw(9)<<"День|";
		print_day(N);

		cout << setw(9)<<"Цена|";
		print_tsena(tseny, N);

		int* zmena = new int[N - 1];
		for (int i = 0; i < N - 1; i++)
			zmena[i] = tseny[i + 1] - tseny[i];

		cout << "Разность|";
		print_razn(zmena, N);

		cout << endl << "Максимальная прибыль: ";
		int prib = max_pribul(zmena, 0, N - 1);
		cout<< prib <<endl;
		print_index(tseny, N, prib);
	}
	else
		cout << "Кол-во дней должно быть больше 0" << endl;


	system("pause");
	return 0;
}