#define _CRT_SECURE_NO_WARNINGS
#include "Parsers.h"
#include "Strings.h"

//проверка корректности типа DWORD
BOOL ParserDWORD(HWND hWnd, LPWSTR str, BOOL print) {
	BOOL numberWas = FALSE;
	BOOL ok = true;
	WCHAR errStr[256];
	for (int i = 0; i < wcslen(str); i++) {
		if (IsNumber(str[i])) {
			if (str[i] == '0' && wcslen(str) != 1) {
				if (!numberWas) {
					ok = false;
					wcscpy(errStr, L"Ќекорректные данные");
				}
			}
			else {
				numberWas = TRUE;
			}
		}
		else {
			ok = false;
			wcscpy(errStr, L"Ќекорректные данные");
		}
	}
	if (wcslen(str) > 10 && ok) {
		ok = false;
		wcscpy(errStr, L"DWORD не должен превышать 4294967296");
	}
	if (wcslen(str) == 10 && ok) {
		if (_wtoi(lpwtow(str[0])) > 4) {
			ok = false;
			wcscpy(errStr, L"DWORD не должен превышать 4294967296");
		}
		else if (_wtoi(lpwtow(str[1])) > 2) {
			ok = false;
			wcscpy(errStr, L"DWORD не должен превышать 4294967296");
		}
		else if (_wtoi(lpwtow(str[3])) > 4) {
			ok = false;
			wcscpy(errStr, L"DWORD не должен превышать 4294967296");
		}
		else if (_wtoi(lpwtow(str[5])) > 6) {
			ok = false;
			wcscpy(errStr, L"DWORD не должен превышать 4294967296");
		}
		else if (_wtoi(lpwtow(str[6])) > 7) {
			ok = false;
			wcscpy(errStr, L"DWORD не должен превышать 4294967296");
		}
		else if (_wtoi(lpwtow(str[7])) > 2) {
			ok = false;
			wcscpy(errStr, L"DWORD не должен превышать 4294967296");
		}
		else if (_wtoi(lpwtow(str[9])) > 6) {
			ok = false;
			wcscpy(errStr, L"DWORD не должен превышать 4294967296");
		}
	}
	if (!ok) {
		if (print) {
			MessageBox(hWnd, errStr, L"ќшибка", MB_OK);
		}
		return FALSE;
	}
	return TRUE;
}
//проверка корректности типа QWORD
BOOL ParserQWORD(HWND hWnd, LPWSTR str, BOOL print) {
	BOOL numberWas = FALSE;
	BOOL ok = true;
	WCHAR errStr[256];
	for (int i = 0; i < wcslen(str); i++) {
		if (IsNumber(str[i])) {
			if (str[i] == '0' && wcslen(str) != 1) {
				if (!numberWas) {
					ok = false;
					wcscpy(errStr, L"Ќекорректные данные");
				}
			}
			else {
				numberWas = TRUE;
			}
		}
		else {
			ok = false;
			wcscpy(errStr, L"Ќекорректные данные");
		}
	}
	if (wcslen(str) > 20 && ok) {
		ok = false;
		wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
	}
	if (wcslen(str) == 20 && ok) {
		if (_wtoi(lpwtow(str[0])) > 1) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[1])) > 8) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[2])) > 4) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[3])) > 4) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[4])) > 6) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[5])) > 7) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[6])) > 4) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[7])) > 4) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[8])) > 0) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[9])) > 7) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[10])) > 3) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[11])) > 7) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[12])) > 0) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[14])) > 5) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[15])) > 5) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[16])) > 1) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[17])) > 6) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[18])) > 1) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
		else if (_wtoi(lpwtow(str[19])) > 6) {
			ok = false;
			wcscpy(errStr, L"QDWORD не должен превышать 18446744073709551616");
		}
	}
	if (!ok) {
		if (print) {
			MessageBox(hWnd, errStr, L"ќшибка", MB_OK);
		}
		return FALSE;
	}
	return TRUE;
}
//проверка корректности типа BINARY
BOOL ParserBINARY(HWND hWnd, LPWSTR str, BOOL print) {
	for (int i = 0; i < wcslen(str); i++) {
		if (!IsHex(str[i])) {
			if (print) {
				MessageBox(hWnd, L"Ќеккоректные данные", L"ќшибка", MB_OK);
			}
			return FALSE;
		}
	}
	if (wcslen(str) % 2 != 0) {
		if (print) {
			MessageBox(hWnd, L" оличество символов должно быть четным", L"ќшибка", MB_OK);
		}
		return FALSE;
	}
	return TRUE;
}
//преобразовать строку в DWORD
DWORD wtodw(LPWSTR str) {
	DWORD ret = 0;
	DWORD p = 1;
	for(int i = wcslen(str) - 1; i >= 0; i--) {
		ret += _wtoi(lpwtow(str[i])) * p;
		p *= 10;
	}
	return ret;
}
//преобразовать строку в QWORD
DWORDLONG wtodwl(LPWSTR str) {
	DWORDLONG ret = 0;
	DWORDLONG p = 1;
	for(int i = wcslen(str) - 1; i >= 0; i--) {
		ret += _wtoi(lpwtow(str[i])) * p;
		p *= 10;
	}
	return ret;
}
//преобразовать строку с 16-ричным представлени€м числа в BYTE
int wtob(LPWSTR s, BYTE* h) {
	LPWSTR p;
	BYTE n;
	for (n = 0, p = s; *p; ++p) {
		if (*p >= '0' && *p <= '9')
			n = (n << 4) | (BYTE)(*p - '0');
		else if (*p >= 'a' && *p <= 'f')
			n = (n << 4) | (BYTE)(*p - 'a' + 10);
		else
			break;
	}

	if (((int)(p - s) - 2) > (int)(sizeof(n) << 1))
		return 0;

	*h = n;
	return (p != s + 2) ? (int)(p - s) : 0;
}
LPWSTR lpwtow(WCHAR a) {
	LPWSTR ret = new WCHAR[2];
	ret[0] = a;
	ret[1] = '\0';
	return ret;
}