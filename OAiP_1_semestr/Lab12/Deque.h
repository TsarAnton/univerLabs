#ifndef Deque_h
#define Deque_h
struct Deque {
	char d;
	Deque* n;
	Deque* p;
};
Deque* first2(char d);
void add_right(Deque** right, char d);
void add_left(Deque** left, char d);
char izv_right(Deque** right);
char izv_left(Deque** left);
#endif
