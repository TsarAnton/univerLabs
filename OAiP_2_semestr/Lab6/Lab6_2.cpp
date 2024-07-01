#include <iostream>
#include <vector>
#define type char
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");

	int N;
	cout << "Введите кол-во элементов вектора" << endl;
	cin >> N;

	vector<type> V;
	type x;
	cout << "Вводите элементы вектора" << endl;
	for (int i = 0; i < N; i++) {
		cin >> x;
		V.push_back(x);
	}

	vector<type> V_buff;
	for (int i = 0; i < N; i++) {
		if (i % 2 != 0)
			V_buff.push_back(V[i]);
	}
	for (int i = 0; i < N; i++) {
		if (i % 2 == 0)
			V_buff.push_back(V[i]);
		cout << V_buff[i] << "  ";
	}
	cout << endl;

	system("pause");
	return 0;
}