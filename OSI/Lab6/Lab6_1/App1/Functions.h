//создаем прототипы функций из dll 
//единственное отличие - вместо dllexport => dllimport
//чтоб показать, что функции импортируются
extern "C" __declspec(dllimport) void hello();
extern "C" __declspec(dllimport) int mySum(int a, int b);
