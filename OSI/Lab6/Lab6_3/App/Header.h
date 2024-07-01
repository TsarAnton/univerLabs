#include <Windows.h>

extern "C" __declspec(dllimport) LPWSTR enterMessage;
extern "C" __declspec(dllimport) LPWSTR calcMessage;
extern "C" __declspec(dllimport) LPWSTR lengthMessage;
extern "C" __declspec(dllimport) LPWSTR speedMessage;
extern "C" __declspec(dllimport) LPWSTR mbMessage;
extern "C" __declspec(dllimport) LPWSTR mMessage;
extern "C" __declspec(dllimport) LPWSTR kMessage;
extern "C" __declspec(dllimport) LPWSTR errorMessage;
extern "C" __declspec(dllimport) void RussianLanguage();
extern "C" __declspec(dllimport) void EnglishLanguage();
extern "C" __declspec(dllimport) LPWSTR ConcateStr(LPWSTR str1, LPWSTR str2);
extern "C" __declspec(dllimport) LPWSTR MakeLPWSTR(LPCWSTR str);
extern "C" __declspec(dllimport) ICONINFO getIconInfo();