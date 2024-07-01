#include "Functions.h"
#include <iostream>
//реализуем функции
extern "C" __declspec(dllexport) void hello() {
	std::cout << "Hello World!" << std::endl;
}

extern "C" __declspec(dllexport) int mySum(int a, int b) {
	return a + b;
}