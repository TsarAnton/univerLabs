#ifndef Queue_h
#define Queue_h
struct Queue {
	char d;
	Queue* p;
};
Queue* first1(char d);
void add1(Queue** kon, char d);
char izv1(Queue** beg);
#endif
