#include <iostream>
#include <vector>
#include <iterator>
using namespace std;

void enter_vector(vector<double> &v) {
    istream_iterator<double> enter(cin);
	istream_iterator<double> end;
	while (enter != end) {
		v.push_back(*enter);
		enter++;
	}
}
void print_vector(vector<double> &v) {
	vector<double>::iterator that;
	that = v.begin();
	while (that < v.end()) {
		cout << *that << "   ";
		that++;
	}
	cout << endl;
}
double modul_sum(vector<double> &v) {
	bool minus = false;
	double sum = 0;
	vector<double>::iterator enter;
	enter = v.begin();
	while (enter < v.end()) {
		if (*enter < 0)
			minus = true;
		if (minus)
			sum += abs(*enter);
		enter++;
	}
	return sum;
}
void insert_sort_vozr(vector<double> &v) {
	vector<double>::iterator i, j, key;
	for (i = v.begin() + 1; i < v.end(); i++) {
		key = i;
		double temp = *key;
		for (j = i; j > v.begin(); j--) {
			if (temp < *(j - 1)) {
				*j = *(j - 1);
				key = j - 1;

			}
		}
		*key = temp;
	}
}
void insert_sort_ub(vector<double>& v) {
	vector<double>::iterator i, j, key;
	for (i = v.begin() + 1; i < v.end(); i++) {
		key = i;
		double temp = *key;
		for (j = i; j > v.begin(); j--) {
			if (temp > *(j - 1)) {
				*j = *(j - 1);
				key = j - 1;

			}
		}
		*key = temp;
	}
}

int main() {
	setlocale(LC_ALL, "Russian");

	vector<double> v;
	cout << "Введите вектор" << endl;
	enter_vector(v);
	cout << "Введенный вектор: ";
	print_vector(v);
	cout << "Сумма модулей от первого отрицательного и до конца вектора: " << modul_sum(v) << endl;
	insert_sort_vozr(v);
	cout << "Отсортированный по возрастанию вектор: ";
	print_vector(v);
	insert_sort_ub(v);
	cout << "Отсортированный по убыванию вектор: ";
	print_vector(v);

	system("pause");
	return 0;
}