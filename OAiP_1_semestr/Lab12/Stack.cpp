#include "Stack.h"
Stack* first(char d) {
	Stack* pv = new Stack;
	pv->d = d;
	pv->p = 0;
	return pv;
}
void add(Stack** top, char d) {
	Stack* pv = new Stack;
	pv->d = d;
	pv->p = *top;
	*top = pv;
}
char izv(Stack** top) {
	char ud = (*top)->d;
	Stack* pv = *top;
	*top = (*top)->p;
	delete pv;
	return ud;
}