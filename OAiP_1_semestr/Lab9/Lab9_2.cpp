#include <iostream>
#include <string.h>
#include <string>
#include <cstring>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	cout << "Введите строку" << endl;
	string stroka;
	getline(cin,stroka);
	int a = stroka.size();
	int i, j, sum = 0, prove = 0, flag = 0;
	for (i = 0; i < a; i++) {
		j = i;
		for (j; j < a; j++) {
			if (stroka[j] == stroka[i])
				sum += 1;
		}
		if (i > 0) {
			j = i - 1;
			for (j; j >= 0; j--) {
				if (stroka[j] == stroka[i])
					prove = 1;
			}
		}
		if (prove == 0&&i>0) {
			cout << "Количество символов " << stroka[i] << " в строке = " << sum << endl;
		}
		if (i == 0) {
			cout << "Количество символов " << stroka[i] << " в строке = " << sum << endl;
		}
		sum = 0;
		prove = 0;
	}
	//delete[] (stroka);
	system("pause");
	return 0;
}