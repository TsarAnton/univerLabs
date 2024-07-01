#include <iostream>
using namespace std;

#define ENUM_AND_NAMES(type, value1, value2, value3, valueN) enum type { value1, value2, value3, valueN }; static const char* const typenames[4] = {"value1","value2","value3","valueN"};

int main() {
	setlocale(LC_ALL, "Russian");

	ENUM_AND_NAMES(name, val1, val2, val3, valN);
	name num(valN);
	cout << num << endl;
	cout << typenames[num] << endl;

	system("pause");
	return 0;
}