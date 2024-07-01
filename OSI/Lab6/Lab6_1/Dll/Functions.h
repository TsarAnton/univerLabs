//прототипы функций
extern "C" __declspec(dllexport) void hello();
//extern "C" - делает название функции в таблице dll "читаемым" (без этого там храниться что-то типа 1231231312hello)
//__declspec(dllexport) - помечает функцию как экспортируемую

extern "C" __declspec(dllexport) int mySum(int a, int b);
