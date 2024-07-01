#include "Queue.h"
Queue* first1(char d) {
	Queue* pv = new Queue;
	pv->d = d;
	pv->p = 0;
	return pv;
}
void add1(Queue** kon, char d) {
	Queue* pv = new Queue;
	pv->d = d;
	pv->p = 0;
	(*kon)->p = pv;
	*kon = pv;
}
char izv1(Queue** beg) {
	char ud = (*beg)->d;
	Queue* pv = *beg;
	*beg = (*beg)->p;
	delete pv;
	return ud;
}