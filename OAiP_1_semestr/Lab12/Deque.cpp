#include "Deque.h"
Deque* first2(char d) {
	Deque* pv = new Deque;
	pv->d = d;
	pv->n = 0;
	pv->p = 0;
	return pv;
}
void add_right(Deque** right, char d) {
	Deque* pv = new Deque;
	pv->d = d;
	pv->n = 0;
	pv->p = (*right);
	(*right)->n = pv;
	*right = pv;
}
void add_left(Deque** left, char d) {
	Deque* pv = new Deque;
	pv->d = d;
	pv->p = 0;
	pv->n = (*left);
	(*left)->p = pv;
	*left = pv;
}
char izv_right(Deque** right) {
	char ud = (*right)->d;
	Deque* pv = *right;
	*right = (*right)->p;
	delete pv;
	return ud;
}
char izv_left(Deque** left) {
	char ud = (*left)->d;
	Deque* pv = *left;
	*left = (*left)->n;
	delete pv;
	return ud;
}
