#include <Windows.h>
#ifndef PARSERS_H
#define PARSERS_H
BOOL ParserDWORD(HWND hWnd, LPWSTR str, BOOL print);
BOOL ParserQWORD(HWND hWnd, LPWSTR str, BOOL print);
BOOL ParserBINARY(HWND hWnd, LPWSTR str, BOOL print);
DWORD wtodw(LPWSTR str);
LPWSTR lpwtow(WCHAR a);
DWORDLONG wtodwl(LPWSTR str);
int wtob(LPWSTR s, BYTE* h);
#endif