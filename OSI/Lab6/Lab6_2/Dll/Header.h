#include <Windows.h>
BOOL strCompare(LPCWSTR str1, LPCWSTR str2);
extern "C" __declspec(dllexport) LPCWSTR getSegment(LPCWSTR str);
extern "C" __declspec(dllexport) LPCWSTR getSpeed(LPCWSTR str);
