#ifndef Header_h
#define Header_h

class Stack {
	int* array;
	int top;
	int N;
public:
	Stack();
	void push(int ex);
	int pop();
	void bigger_size();
	void less_size();
};

class Queue {
	int* array;
	int head;
	int tail;
	int N;
public:
	Queue();
	void push(int name);
	int pop();
	bool is_empty() {
		return head == tail;
	}
	void bigger_size();
	void less_size();
};

class Deque {
	int* array;
	int head;
	int tail;
	int N;
public:
	Deque();
	void push_beg(int x);
	void push_back(int x);
	int pop_beg();
	int pop_back();
	bool is_empty() {
		return head == tail;
	}
	void bigger_size_beg();
	void bigger_size_back();
	void less_size_beg();
	void less_size_back();
};
#endif
