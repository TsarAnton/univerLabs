#include <Windows.h>
#ifndef STRINGS_H
#define STRINGS_H

BOOL IsStandart(LPWSTR str);
LPWSTR GetName(LPWSTR str);
LPWSTR GetLastName(LPWSTR str);
BOOL IsNumber(WCHAR chr);
BOOL IsHex(WCHAR chr);
#endif