#include <iostream>
#include <windows.h>
#include "Header.h"
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");

	cout << "Введите кол-во билетов" << endl;
	int N1, N2;
	cin >> N1;
	cout << "Введите кол-во задач" << endl;
	cin >> N2;


	Queue students;
	Deque bilets;
	Queue exercizes;
	Queue answer;


	for (int i = 0; i < N1; i++) {
		if (i % 2 == 0)
			bilets.push_back(i + 1);
		else
			bilets.push_beg(i + 1);
	}
	for (int i = 0; i < N2; i++)
		exercizes.push(i + 1);

	bool infinity = true;
	int stud = 1;
	while (infinity) {
		int r = 1;
		if (r == 1 && !students.is_empty() && !bilets.is_empty() && !exercizes.is_empty()) {
			int student = students.pop();
			int ex = exercizes.pop();
			r = 1 + rand() % 2;
			int bilet;
			if (r == 1) {
				bilet = bilets.pop_beg();
			}
			else {
				bilet = bilets.pop_back();
			}
			answer.push(student);
			answer.push(bilet);
			answer.push(ex);
			cout << "Студент № " << student << " берет билет № " << bilet << " и задачу № " << ex << " и отправляется в очередь отвечающих" << endl;
			Sleep(2000);
		}
		if (r == 2 && !answer.is_empty()) {
			int ex = answer.pop();
			int bilet = answer.pop();
			int student = answer.pop();
			r = 1 + rand() % 2;
			if (r == 1)
				bilets.push_beg(bilet);
			else
				bilets.push_back(bilet);
			exercizes.push(ex);
			r = 1 + rand() % 4;
			if (r == 1) {
				cout << "Студент № " << student << " сдал экзамен" << endl;
			}
			else {
				cout << "Студент № " << student << " не сдал экзамен и направляется в очередь пересдающих" << endl;
				students.push(student);
			}
			Sleep(2000);
		}
		if (r == 3 && !answer.is_empty()) {
			cout << "Студент № " << stud << " попал в очередь пересдающих" << endl;
			students.push(stud);
			stud++;
			Sleep(2000);
		}
		r = 1 + rand() % 3;
	}
	system("pause");
	return 0;
}