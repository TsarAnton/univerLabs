#include "Header.h"
#include <iostream>
using namespace std;

Stack::Stack() {
	N = 1;
	array = new int[N];
	top = -1;
}
void Stack::push(int ex) {
	this->bigger_size();
	top++;
	array[top] = ex;
}
int Stack::pop() {
	if (top != -1) {
		top--;
		return array[top + 1];
	}
	else
		cout << "Стек пуст" << endl;
	this->less_size();
}
void Stack::bigger_size() {
	int* buff = new int[N + 1];
	for (int i = 0; i < N; i++) {
		buff[i] = array[i];
	}
	this->array = buff;
	N++;
}
void Stack::less_size() {
	int* buff = new int[N - 1];
	for (int i = 0; i < N - 1; i++) {
		buff[i] = array[i];
	}
	this->array = buff;
	N--;
}


Queue::Queue() {
	N = 1;
	array = new int[N];
	head = 0;
	tail = 0;
}
void Queue::push(int name) {
	this->bigger_size();
	array[tail] = name;
	tail++;
}
int Queue::pop() {
	if (head != tail) {
		head++;
		return array[head - 1];
	}
	else
		cout << "Очередь пуста" << endl;
	this->less_size();
}
void Queue::bigger_size() {
	int* buff = new int[N + 1];
	for (int i = 0; i < N; i++)
		buff[i] = array[i];
	this->array = buff;
	N++;
}
void Queue::less_size() {
	int* buff = new int[N - 1];
	for (int i = 0, k = 1; i < N - 1; k++, i++)
		buff[i] = array[k];
	tail--;
	head--;
}


Deque::Deque() {
	N = 1;
	array = new int[N];
	head = 0;
	tail = 0;
}
void Deque::push_beg(int x) {
	this->bigger_size_beg();
	array[head] = x;
	head++;
}
void Deque::push_back(int x) {
	this->bigger_size_back();
	array[tail] = x;
	head++;
}
int Deque::pop_beg() {
	if (head != tail) {
		head--;
		return array[head];
	}
	else
		cout << "Дек пуст" << endl;
	this->less_size_beg();
}
int Deque::pop_back() {
	if (head != tail) {
		tail++;
		return array[tail - 1];
	}
	else
		cout << "Дек пуст" << endl;
	this->less_size_back();
}
void Deque::bigger_size_beg() {
	int* buff = new int[N + 1];
	for (int i = 0; i < N; i++)
		buff[i] = array[i];
	this->array = buff;
	N++;
}
void Deque::bigger_size_back() {
	int* buff = new int[N + 1];
	for (int i = 1, k = 0; i < N + 1; i++, k++)
		buff[i] = array[k];
	this->array = buff;
	N++;
}
void Deque::less_size_beg() {
	int* buff = new int[N - 1];
	for (int i = 0; i < N - 1; i++)
		buff[i] = array[i];
	array = buff;
	N--;
}
void Deque::less_size_back() {
	int* buff = new int[N - 1];
	for (int i = 1, k = 0; i < N - 1; i++, k++)
		buff[i] = array[k];
	array = buff;
	N--;
}