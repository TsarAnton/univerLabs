#include <iostream>
#include <string>
#include "Stack.h"
#include "Queue.h"
#include "Deque.h"
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int N;
	char x, a, b;
	cout << "Введите кол-во элементов стека" << endl;
	cin >> N;
	int flag = 0;
	if (N > 0) {
		if (N != 1) {
			cout << "Вводите элементы стека" << endl;
			cin >> x;
			Stack* stop = first(x);
			for (int i = 1; i < N; i++) {
				cin >> x;
				add(&stop, x);
			}
			int n = N / 2;
			Stack* stop1 = first(izv(&stop));
			for (int i = 1; i < n; i++) {
				add(&stop1, izv(&stop));
			}
			if (N % 2 == 1)
				izv(&stop);
			for (int i = n - 1; i >= 0 && flag == 0; i--) {
				a = izv(&stop);
				b = izv(&stop1);
				if (b != a)
					flag = 1;
			}
			if (flag == 1)
				cout << "Стек не симметричен" << endl;
			else
				cout << "Стек симметричен" << endl;
		}
		else
			cout << "Стек симметричен" << endl;
	}
	else
		  cout << "Кол-во элементов должно быть больше 0" << endl;
	  cout << "Введите кол-во элементов очереди" << endl;
	  cin >> N;
	  if (N > 0) {
		  if (N != 1) {
			  cout << "Вводите элементы очереди" << endl;
			  cin >> x;
			  Queue* beg = first1(x);
			  Queue* kon = beg;
			  for (int i = 1; i < N; i++) {
				  cin >> x;
				  add1(&kon, x);
			  }
			  int n = N / 2, n1 = n-1;
			  char* mas = new char[n];
			  for (int i = 0; i < n; i++){
				  mas[i] = izv1(&beg);
			  }
			  if (N % 2 == 1)
				  izv1(&beg);
			  for (int i = 0; i < N / 2; i++) {
				  swap(mas[i], mas[n1]);
				  n1 -= 1;
			  }
			  for (int i = n - 1; i >= 0 && flag == 0; i--) {
				  a = izv1(&beg);
				  if (mas[i] != a)
					  flag = 1;
			  }
			  if (flag == 1)
				  cout << "Очередь не симметрична" << endl;
			  else
				  cout << "Очередь симметрична" << endl;
		  }
		  else
			  cout << "Очередь симметрична" << endl;
	  }
	  else
	  	cout << "Кол-во элементов должно быть больше 0" << endl;
	  cout << "Введите кол-во элементов дека" << endl;
	  cin >> N;
	  if (N > 0) {
		  if (N != 1) {
			  cout << "Вводите элементы дека" << endl;
			  cin >> x;
			  Deque* right = first2(x);
			  Deque* left = right;
			  for (int i = 1; i < N; i++) {
				  cin >> x;
				  add_right(&right, x);
			  }
			  int n = N / 2;
			  flag = 0;
			  for (int i = 0; i < n && flag == 0; i++) {
				  a = izv_left(&left);
				  b = izv_right(&right);
				  if (a != b)
					  flag = 1;
			  }
			  if (flag == 0)
				  cout << "Дек симметричен" << endl;
			  else
				  cout << "Дек не симметричен" << endl;
		  }
		  else
			  cout << "Дек симметричен" << endl;
	  }
	  else
		  cout<<"Кол-во элементов должно быть больше 0"<<endl;
		system("pause");
	return 0;
}