#ifndef Stack_h
#define Stack_h
struct Stack {
	char d;
	Stack* p;
};
Stack* first(char d);
void add(Stack** top, char d);
char izv(Stack** top);
#endif
