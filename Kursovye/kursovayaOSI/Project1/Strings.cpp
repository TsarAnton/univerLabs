#include "Strings.h"

//проверка символа на число
BOOL IsNumber(WCHAR chr) {
	if (chr == '0' || chr == '1' || chr == '2' || chr == '3' || chr == '4' || chr == '5' || chr == '6' || chr == '7' || chr == '8' || chr == '9') {
		return TRUE;
	}
	return FALSE;
}
//проверка символа на 16-ричное  число
BOOL IsHex(WCHAR chr) {
	if (IsNumber(chr) || chr == 'a' || chr == 'b' || chr == 'c' || chr == 'd' || chr == 'e' || chr == 'f') {
		return TRUE;
	}
	return FALSE;
}
//проверка на стандартный раздел
BOOL IsStandart(LPWSTR str) {
	if (wcscmp(str, L"HKEY_CLASSES_ROOT") == 0 ||
		wcscmp(str, L"HKEY_CURRENT_USER") == 0 ||
		wcscmp(str, L"HKEY_LOCAL_MACHINE") == 0 ||
		wcscmp(str, L"HKEY_USERS") == 0 ||
		wcscmp(str, L"HKEY_CURRENT_CONFIG") == 0) {
		return TRUE;
	}
	return FALSE;
}
//делаем из видимого имени реальное (удаляем из имени значение встроенного раздела реестра)
LPWSTR GetName(LPWSTR str) {
	WCHAR ret[512];
	BOOL copy = false;
	int j = 0;
	for (int i = 0; i < wcslen(str); i++) {
		if (copy) {
			ret[j] = str[i];
			j++;
		}
		else if(str[i] == '\\') {
			copy = TRUE;
		}
	}
	ret[j] = '\0';
	return ret;
}
//удаляем из пути последнее значение
LPWSTR GetLastName(LPWSTR str) {
	BOOL con = TRUE;
	int num = 0;
	for (int i = wcslen(str) - 1; con; i--) {
		if (str[i] != '\\') {
			num++;
		}
		else {
			con = FALSE;
		}
	}
	WCHAR ret[512];
	int i = 0;
	for (i; i < wcslen(str) - num; i++) {
		ret[i] = str[i];
	}
	ret[i - 1] = '\0';
	return ret;
}