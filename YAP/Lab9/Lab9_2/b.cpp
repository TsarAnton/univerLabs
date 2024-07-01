#include "Header.h"
#include <iostream>
using namespace std;

int main() {
	cout << object1 << " " << object2 << endl;
	static int a = 15;
	static int b = 123;
	object1 = a;
	object2 = b;
	cout << object1 << " " << object2 << endl;
	system("pause");
	return 0;
}