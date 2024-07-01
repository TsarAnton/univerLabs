#include <Windows.h>

//все строки из приложения
extern "C" __declspec(dllexport) LPWSTR enterMessage;
extern "C" __declspec(dllexport) LPWSTR calcMessage;
extern "C" __declspec(dllexport) LPWSTR lengthMessage;
extern "C" __declspec(dllexport) LPWSTR speedMessage;
extern "C" __declspec(dllexport) LPWSTR mbMessage;
extern "C" __declspec(dllexport) LPWSTR mMessage;
extern "C" __declspec(dllexport) LPWSTR kMessage;
extern "C" __declspec(dllexport) LPWSTR errorMessage;
extern "C" __declspec(dllexport) void RussianLanguage();
extern "C" __declspec(dllexport) void EnglishLanguage();
extern "C" __declspec(dllexport) LPWSTR ConcateStr(LPWSTR str1, LPWSTR str2);
extern "C" __declspec(dllexport) LPWSTR MakeLPWSTR(LPCWSTR str);

//для передачи иконки будем использовать структуру ICONINFO - хранит всю информацию об иконке
extern "C" __declspec(dllexport) ICONINFO getIconInfo();