#define _CRT_SECURE_NO_WARNINGS
#include "framework.h"
#include "Project1.h"
#include "Strings.h"
#include "Parsers.h"

//значения длин строк
#define MAX_KEY_LENGTH 256
#define MAX_STRING_LENGTH 512 
#define MAX_VALUE_NAME 256
#define MAX_LOADSTRING 100

//id окон
#define hListBoxId 3000
#define hValuesId 3001
#define hValuesStaticId 3002
#define hSectionsStaticId 3003
#define hAddSectionButtonId 3004
#define hDeleteSectionButtonId 3005
#define hAddSectionEditId 3009
#define hStaticId 3006
#define hAddSectionDoId 3007
#define hAddSectionUndoId 3008
#define hAddValueButtonId 3010
#define hDeleteValueButtonId 3011
#define hAddEditValueEditId 3012
#define hAddEditValueTypeId 3013
#define hAddEditValueDoId 3014
#define hAddEditValueUndoId 3015
#define hAddEditValueValueId 3016
#define hExplainId 3017

HINSTANCE hInst;                                
WCHAR szTitle[MAX_LOADSTRING];                  
WCHAR szWindowClass[MAX_LOADSTRING];
//текущая ветка реестра
HKEY currentHkey = HKEY_LOCAL_MACHINE;
//видимое имя текущего раздела
LPWSTR visibleName = new WCHAR[MAX_STRING_LENGTH];
//реальное имя текущего раздела
LPWSTR realName = new WCHAR[MAX_STRING_LENGTH];
//переменные для корректной отрисовки
BOOL nextIsLast = FALSE;
BOOL standartPrint = TRUE;
BOOL isVisible = FALSE;
//переменные для редактирования параметров
BOOL change = FALSE;
LPWSTR changingName = new WCHAR[MAX_STRING_LENGTH];

//функции стандартного проекта
ATOM                MyRegisterClass(HINSTANCE hInstance);
BOOL                InitInstance(HINSTANCE, int);
LRESULT CALLBACK    WndProc(HWND, UINT, WPARAM, LPARAM);
INT_PTR CALLBACK    About(HWND, UINT, WPARAM, LPARAM);

//создание(редактирование) параметра реестра
BOOL InsertValue(HWND hWnd, LPWSTR name, LPWSTR type, LPWSTR value) {
    HKEY hKey;
    if (RegOpenKeyEx(currentHkey, realName, 0, KEY_ALL_ACCESS, &hKey) != ERROR_SUCCESS) {
        MessageBox(NULL, L"Недостаточно прав (запустите приложение от имени администратора)", L"Ошибка", MB_OK);
    }
    else {
        DWORD dwType = NULL;
        //создание строки об успешном создании параметра
        WCHAR success[MAX_STRING_LENGTH];
        if (change) {
            wcscpy(success, L"Параметр успешно отредактирован");
        }
        else {
            wcscpy(success, L"Параметр успешно добавлен");
        }
        //определение типа добавляемого параметра
        if (wcscmp(type, L"REG_SZ") == 0) {
            dwType = REG_SZ;
        }
        else if (wcscmp(type, L"REG_BINARY") == 0) {
            //проверка валидности
            if (!ParserBINARY(hWnd, value, TRUE)) {
                return FALSE;
            }
            //преобразование строки в массив байт
            BYTE* val = new BYTE[wcslen(value) / 2];
            for (int i = 0, j = 0; j < wcslen(value) / 2; i++, j++) {
                WCHAR buf[3];
                BYTE number;
                buf[0] = value[i];
                buf[1] = value[i + 1];
                buf[2] = '\0';
                wtob(buf, &number);
                val[j] = number;
                i++;
            }
            //запись значения
            if (RegSetValueExW(hKey, name, NULL, REG_BINARY, (LPBYTE)val, wcslen(value) / 2) == ERROR_SUCCESS) {
                HWND hList = GetDlgItem(hWnd, hValuesId);
                SendMessage(hList, LB_INSERTSTRING, (WPARAM)0, (LPARAM)name);
                MessageBox(hWnd, success, L"Успех", MB_OK);
                SendMessage(hWnd, WM_COMMAND, WPARAM(hAddEditValueUndoId), 0L);
                delete[] val;
                return TRUE;
            }
            else {
                delete[] val;
                MessageBox(hWnd, L"А как это ты сюда попал?", L"Ошибка", MB_OK);
                return FALSE;
            }
        }
        else if (wcscmp(type, L"REG_DWORD") == 0) {
            //проверка валидности
            if (!ParserDWORD(hWnd, value, TRUE)) {
                return FALSE;
            }
            //преобразование строки в 32-битное число 
            DWORD val = wtodw(value);
            //запись значения
            if (RegSetValueExW(hKey, name, NULL, REG_DWORD, (LPBYTE)&val, sizeof(val)) == ERROR_SUCCESS) {
                HWND hList = GetDlgItem(hWnd, hValuesId);
                SendMessage(hList, LB_INSERTSTRING, (WPARAM)0, (LPARAM)name);
                MessageBox(hWnd, success, L"Успех", MB_OK);
                SendMessage(hWnd, WM_COMMAND, WPARAM(hAddEditValueUndoId), 0L);
                return TRUE;
            }
            else {
                MessageBox(hWnd, L"А как это ты сюда попал?", L"Ошибка", MB_OK);
                return FALSE;
            }
        }
        else if (wcscmp(type, L"REG_QWORD") == 0) {
            //проверка валидности
            if (!ParserQWORD(hWnd, value, TRUE)) {
                return FALSE;
            }
            //преобразование строки в 64-битное число
            DWORD64 val = wtodwl(value);
            //запись значения
            if (RegSetValueExW(hKey, name, NULL, REG_QWORD, (LPBYTE)&val, sizeof(val)) == ERROR_SUCCESS) {
                HWND hList = GetDlgItem(hWnd, hValuesId);
                SendMessage(hList, LB_INSERTSTRING, (WPARAM)0, (LPARAM)name);
                MessageBox(hWnd, success, L"Успех", MB_OK);
                SendMessage(hWnd, WM_COMMAND, WPARAM(hAddEditValueUndoId), 0L);
                return TRUE;
            }
            else {
                MessageBox(hWnd, L"А как это ты сюда попал?", L"Ошибка", MB_OK);
                return FALSE;
            }
        }
        else if (wcscmp(type, L"REG_MULTI_SZ") == 0) {
            dwType = REG_MULTI_SZ;
        }
        else if (wcscmp(type, L"REG_EXPAND_SZ") == 0) {
            dwType = REG_EXPAND_SZ;
        }
        //запись значения строковых параметров
        if (RegSetValueExW(hKey, name, NULL, dwType, (LPBYTE)value, wcslen(value) * sizeof(WCHAR)) == ERROR_SUCCESS) {
            HWND hList = GetDlgItem(hWnd, hValuesId);
            SendMessage(hList, LB_INSERTSTRING, (WPARAM)0, (LPARAM)name);
            MessageBox(hWnd, success, L"Успех", MB_OK);
            SendMessage(hWnd, WM_COMMAND, WPARAM(hAddEditValueUndoId), 0L);
            return TRUE;
        }
        else {
            MessageBox(hWnd, L"А как это ты сюда попал?", L"Ошибка", MB_OK);
            return FALSE;
        }
    }
    RegCloseKey(hKey);
}

//main функция стандартного проекта
int APIENTRY wWinMain(_In_ HINSTANCE hInstance, _In_opt_ HINSTANCE hPrevInstance, _In_ LPWSTR lpCmdLine, _In_ int nCmdShow) {
    UNREFERENCED_PARAMETER(hPrevInstance);
    UNREFERENCED_PARAMETER(lpCmdLine);

    LoadStringW(hInstance, IDS_APP_TITLE, szTitle, MAX_LOADSTRING);
    LoadStringW(hInstance, IDC_PROJECT1, szWindowClass, MAX_LOADSTRING);
    MyRegisterClass(hInstance);

    if (!InitInstance (hInstance, nCmdShow)) {
        return FALSE;
    }

    HACCEL hAccelTable = LoadAccelerators(hInstance, MAKEINTRESOURCE(IDC_PROJECT1));

    MSG msg;

    while (GetMessage(&msg, nullptr, 0, 0)) {
        if (!TranslateAccelerator(msg.hwnd, hAccelTable, &msg)) {
            TranslateMessage(&msg);
            DispatchMessage(&msg);
        }
    }

    return (int) msg.wParam;
}

//функция регистрации класса окна стандартного проекта
ATOM MyRegisterClass(HINSTANCE hInstance) {
    WNDCLASS wcex;

    wcex.style          = CS_HREDRAW | CS_VREDRAW;
    wcex.lpfnWndProc    = WndProc;
    wcex.cbClsExtra     = 0;
    wcex.cbWndExtra     = 0;
    wcex.hInstance      = hInstance;
    wcex.hIcon          = LoadIcon(hInstance, MAKEINTRESOURCE(IDI_ICON1));
    wcex.hCursor        = LoadCursor(nullptr, IDC_ARROW);
    wcex.hbrBackground  = (HBRUSH)(COLOR_WINDOW+1);
    wcex.lpszMenuName   = MAKEINTRESOURCEW(IDC_PROJECT1);
    wcex.lpszClassName  = szWindowClass;

    return RegisterClass(&wcex);
}

//функция создания окна стандартного проекта
BOOL InitInstance(HINSTANCE hInstance, int nCmdShow) {
   hInst = hInstance;

   HWND hWnd = CreateWindowW(szWindowClass, szTitle, WS_CAPTION | WS_MAXIMIZEBOX | WS_MINIMIZEBOX | WS_SYSMENU, 50, 50, 1075, 600, nullptr, nullptr, hInstance, nullptr);

   if (!hWnd) {
      return FALSE;
   }

   ShowWindow(hWnd, nCmdShow);
   UpdateWindow(hWnd);

   return TRUE;
}

//функция-обработчик сообщений
LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam) {
    //окна приложения
    HWND hListBox, hValues, hValuesStatic, hSectionsStatic, hAddSectionButton, hDeleteSectionButton, hAddSectionEdit, hStatic, hAddSectionDo, hAddSectionUndo;
    HWND hAddValueButton, hDeleteValueButton, hAddEditValueEdit, hAddEditValueDo, hAddEditValueUndo, hAddEditValueType, hAddEditValueValue, hExplain;
    switch (message) {
    //создание главного окна
    case WM_CREATE: {
        //создаем дочерние окна
        //список разделов реестра
        hListBox = CreateWindow(L"LISTBOX", NULL, WS_CHILD | WS_VISIBLE | LBS_NOTIFY | WS_BORDER | WS_VSCROLL, 20, 50, 300, 430, hWnd, (HMENU)hListBoxId, hInst, NULL);
        //встроенные разделы
        SendMessage(hListBox, LB_ADDSTRING, 0, (LPARAM)L"HKEY_CLASSES_ROOT");
        SendMessage(hListBox, LB_ADDSTRING, 0, (LPARAM)L"HKEY_CURRENT_USER");
        SendMessage(hListBox, LB_ADDSTRING, 0, (LPARAM)L"HKEY_LOCAL_MACHINE");
        SendMessage(hListBox, LB_ADDSTRING, 0, (LPARAM)L"HKEY_USERS");
        SendMessage(hListBox, LB_ADDSTRING, 0, (LPARAM)L"HKEY_CURRENT_CONFIG");
        //список параметров раздела реестра
        hValues = CreateWindow(L"LISTBOX", NULL, WS_CHILD | WS_VISIBLE | LBS_STANDARD, 330, 50, 400, 430, hWnd, (HMENU)hValuesId, hInst, NULL);
        //подписи колонок разделов и параметров
        hValuesStatic = CreateWindow(L"STATIC", L"Параметры:", WS_CHILD | WS_VISIBLE, 330, 20, 400, 25, hWnd, (HMENU)hValuesStaticId, hInst, NULL);
        hSectionsStatic = CreateWindow(L"STATIC", L"Разделы:", WS_CHILD | WS_VISIBLE, 20, 20, 300, 25, hWnd, (HMENU)hSectionsStaticId, hInst, NULL);
        //кнопки добавления/удаления разделов
        hAddSectionButton = CreateWindow(L"BUTTON", L"Добавить раздел", WS_CHILD | WS_VISIBLE | WS_BORDER | BS_PUSHBUTTON, 20, 470, 300, 25, hWnd, (HMENU)hAddSectionButtonId, hInst, NULL);
        hDeleteSectionButton = CreateWindow(L"BUTTON", L"Удалить раздел", WS_CHILD | WS_VISIBLE | WS_BORDER | BS_PUSHBUTTON, 20, 497, 300, 25, hWnd, (HMENU)hDeleteSectionButtonId, hInst, NULL);
        //окно для ввода имени раздела
        hAddSectionEdit = CreateWindow(L"EDIT", L"Название раздела", WS_CHILD | WS_VISIBLE | WS_BORDER, 740, 50, 300, 25, hWnd, (HMENU)hAddSectionEditId, hInst, NULL);
        //подпись текущей операции
        hStatic = CreateWindow(L"STATIC", L"Все равно этот текст никогда не появится", WS_CHILD | WS_VISIBLE, 740, 20, 300, 25, hWnd, (HMENU)hStaticId, hInst, NULL);
        //кнопки подтверждения создания/отмены создания раздела
        hAddSectionDo = CreateWindow(L"BUTTON", L"Добавить раздел", WS_CHILD | WS_VISIBLE | WS_BORDER | BS_PUSHBUTTON, 740, 80, 300, 25, hWnd, (HMENU)hAddSectionDoId, hInst, NULL);
        hAddSectionUndo = CreateWindow(L"BUTTON", L"Отмена", WS_CHILD | WS_VISIBLE | WS_BORDER | BS_PUSHBUTTON, 740, 110, 300, 25, hWnd, (HMENU)hAddSectionUndoId, hInst, NULL);
        //кнопки добавления/удаления параметров
        hAddValueButton = CreateWindow(L"BUTTON", L"Добавить параметр", WS_CHILD | WS_VISIBLE | WS_BORDER | BS_PUSHBUTTON, 330, 470, 400, 25, hWnd, (HMENU)hAddValueButtonId, hInst, NULL);
        hDeleteValueButton = CreateWindow(L"BUTTON", L"Удалить параметр", WS_CHILD | WS_VISIBLE | WS_BORDER | BS_PUSHBUTTON, 330, 497, 400, 25, hWnd, (HMENU)hDeleteValueButtonId, hInst, NULL);
        //окно для ввода названия параметра
        hAddEditValueEdit = CreateWindow(L"EDIT", L"Название параметра", WS_CHILD | WS_VISIBLE | WS_BORDER, 740, 50, 300, 25, hWnd, (HMENU)hAddEditValueEditId, hInst, NULL);
        //выпадающий список с типами данных реестра
        hAddEditValueType = CreateWindow(L"COMBOBOX", L"", WS_CHILD | WS_VISIBLE | CBS_HASSTRINGS | CBS_DROPDOWNLIST | WS_OVERLAPPED, 740, 80, 300, 180, hWnd, (HMENU)hAddEditValueTypeId, hInst, NULL);
        //заполнение стандартными типами
        SendMessage(hAddEditValueType, CB_ADDSTRING, 1, (LPARAM)L"REG_SZ");
        SendMessage(hAddEditValueType, CB_ADDSTRING, 1, (LPARAM)L"REG_BINARY");
        SendMessage(hAddEditValueType, CB_ADDSTRING, 1, (LPARAM)L"REG_DWORD");
        SendMessage(hAddEditValueType, CB_ADDSTRING, 1, (LPARAM)L"REG_QWORD");
        SendMessage(hAddEditValueType, CB_ADDSTRING, 1, (LPARAM)L"REG_MULTI_SZ");
        SendMessage(hAddEditValueType, CB_ADDSTRING, 1, (LPARAM)L"REG_EXPAND_SZ");
        SendMessage(hAddEditValueType, CB_SETCURSEL, (WPARAM)0, 0L);
        //окно с пояснениями к типам данных
        hExplain = CreateWindow(L"STATIC", L"Строка любой длинны", WS_CHILD | WS_VISIBLE, 740, 110, 300, 50, hWnd, (HMENU)hExplainId, hInst, NULL);
        //окно для ввода значения параметра
        hAddEditValueValue = CreateWindow(L"EDIT", L"Значение параметра", WS_CHILD | WS_VISIBLE | WS_BORDER, 740, 170, 300, 25, hWnd, (HMENU)hAddEditValueValueId, hInst, NULL);
        //кнопки подтверждения создания/отмены создания параметра
        hAddEditValueDo = CreateWindow(L"BUTTON", L"Добавить параметр", WS_CHILD | WS_VISIBLE | WS_BORDER | BS_PUSHBUTTON, 740, 210, 300, 25, hWnd, (HMENU)hAddEditValueDoId, hInst, NULL);
        hAddEditValueUndo = CreateWindow(L"BUTTON", L"Отмена", WS_CHILD | WS_VISIBLE | WS_BORDER | BS_PUSHBUTTON, 740, 240, 300, 25, hWnd, (HMENU)hAddEditValueUndoId, hInst, NULL);
        //сокрытие некоторых окон
        ShowWindow(hAddSectionButton, SW_HIDE);
        ShowWindow(hDeleteSectionButton, SW_HIDE);
        ShowWindow(hAddSectionEdit, SW_HIDE);
        ShowWindow(hStatic, SW_HIDE);
        ShowWindow(hAddSectionDo, SW_HIDE);
        ShowWindow(hAddSectionUndo, SW_HIDE);
        ShowWindow(hAddValueButton, SW_HIDE);
        ShowWindow(hDeleteValueButton, SW_HIDE);
        ShowWindow(hAddEditValueEdit, SW_HIDE);
        ShowWindow(hAddEditValueValue, SW_HIDE);
        ShowWindow(hAddEditValueDo, SW_HIDE);
        ShowWindow(hAddEditValueUndo, SW_HIDE);
        ShowWindow(hAddEditValueType, SW_HIDE);
        ShowWindow(hExplain, SW_HIDE);
    }
        break;
    case WM_COMMAND: {
        //получение id окна
        int wmId = LOWORD(wParam);
        switch (wmId) {
        //кнопка "О программе" в меню
        case IDM_ABOUT: {
            DialogBox(hInst, MAKEINTRESOURCE(IDD_ABOUTBOX), hWnd, About);
        }
            break;
        //кнопка "Закрыть" в меню
        case IDM_EXIT: {
            DestroyWindow(hWnd);
        }
            break;
        //список разделов
        case hListBoxId: {
            //событие: двойной клик по разделу
            if (HIWORD(wParam) == LBN_DBLCLK) {
                HWND hList = GetDlgItem(hWnd, hListBoxId);
                //получаем индекс выбранного раздела в списке
                int uSelectedItem = (int)SendMessage(hList, LB_GETCURSEL, 0, 0);
                WCHAR Buffer[MAX_STRING_LENGTH];
                if (uSelectedItem != LB_ERR) {
                    //закрытие открытых окон добавления раздела/параметра
                    SendMessage(hWnd, WM_COMMAND, (WPARAM)hAddEditValueUndoId, 0L);
                    SendMessage(hWnd, WM_COMMAND, (WPARAM)hAddSectionUndoId, 0L);
                    //получаем название выбранного раздела
                    SendMessage(hList, LB_GETTEXT, (WPARAM)(int)uSelectedItem, (LPARAM)(LPCTSTR)Buffer);
                    //если была выбрана верхняя строка("назад")
                    if (uSelectedItem == 0 && !IsStandart(Buffer)) {
                        //в отображаемом имени удаляем последний раздел
                        wcscpy(visibleName, GetLastName(Buffer));
                        //составляем реальное имя по отображаемому
                        wcscpy(realName, GetName(visibleName));
                    }
                    else {
                        //если возвращаемся к списку начальных разделов
                        if (uSelectedItem == 0 && IsStandart(Buffer) && nextIsLast) {
                            nextIsLast = FALSE;
                            //отменяем стандартое отображение
                            standartPrint = FALSE;
                            HWND hValues = GetDlgItem(hWnd, hValuesId);
                            //очищаем список параметров и разделов
                            SendMessage(hValues, LB_RESETCONTENT, 0, 0L);
                            SendMessage(hList, LB_RESETCONTENT, 0, 0L);
                            //заполняем список разделов стандартными
                            SendMessage(hList, LB_ADDSTRING, 0, (LPARAM)L"HKEY_CLASSES_ROOT");
                            SendMessage(hList, LB_ADDSTRING, 0, (LPARAM)L"HKEY_CURRENT_USER");
                            SendMessage(hList, LB_ADDSTRING, 0, (LPARAM)L"HKEY_LOCAL_MACHINE");
                            SendMessage(hList, LB_ADDSTRING, 0, (LPARAM)L"HKEY_USERS");
                            SendMessage(hList, LB_ADDSTRING, 0, (LPARAM)L"HKEY_CURRENT_CONFIG");
                            //прячем кнопки добавления/удаления разделов/параметров
                            HWND hAddSection = GetDlgItem(hWnd, hAddSectionButtonId);
                            HWND hDeleteSection = GetDlgItem(hWnd, hDeleteSectionButtonId);
                            ShowWindow(hAddSection, SW_HIDE);
                            ShowWindow(hDeleteSection, SW_HIDE);
                            HWND hAddValue = GetDlgItem(hWnd, hAddValueButtonId);
                            HWND hDeleteValue = GetDlgItem(hWnd, hDeleteValueButtonId);
                            ShowWindow(hAddValue, SW_HIDE);
                            ShowWindow(hDeleteValue, SW_HIDE);
                            //отменяем отрисовку этих кнопок (чтобы потом они отрисовались один раз)
                            isVisible = FALSE;
                        }
                        //если выбрали встроенный раздел
                        else if (wcscmp(Buffer, L"HKEY_CLASSES_ROOT") == 0) {
                            //перезаписываем текущий ключ
                            currentHkey = HKEY_CLASSES_ROOT;
                            //начинаем отрисовку видимого и реального имени
                            wcscpy(visibleName, L"HKEY_CLASSES_ROOT");
                            wcscpy(realName, L"");
                            //указываем, что при следуещем нажатии "назад" вернемся к списку начальных разделов
                            nextIsLast = TRUE;
                        }
                        else if (wcscmp(Buffer, L"HKEY_CURRENT_USER") == 0) {
                            currentHkey = HKEY_CURRENT_USER;
                            wcscpy(visibleName, L"HKEY_CURRENT_USER");
                            wcscpy(realName, L"");
                            nextIsLast = TRUE;
                        }
                        else if (wcscmp(Buffer, L"HKEY_LOCAL_MACHINE") == 0) {
                            currentHkey = HKEY_LOCAL_MACHINE;
                            wcscpy(visibleName, L"HKEY_LOCAL_MACHINE");
                            wcscpy(realName, L"");
                            nextIsLast = TRUE;
                        }
                        else if (wcscmp(Buffer, L"HKEY_USERS") == 0) {
                            currentHkey = HKEY_USERS;
                            wcscpy(visibleName, L"HKEY_USERS");
                            wcscpy(realName, L"");
                            nextIsLast = TRUE;
                        }
                        else if (wcscmp(Buffer, L"HKEY_CURRENT_CONFIG") == 0) {
                            currentHkey = HKEY_CURRENT_CONFIG;
                            wcscpy(visibleName, L"HKEY_CURRENT_CONFIG");
                            wcscpy(realName, L"");
                            nextIsLast = TRUE;
                        }
                        else {
                            //если отображаемое имя является стандартным разделом
                            if (IsStandart(visibleName)) {
                                //если при этом не было выбрано стандартное имя (что равносильно "назад")
                                if (!IsStandart(Buffer)) {
                                    //к видимому имени через "/" добавляем выбранный раздел
                                    wcscat(visibleName, L"\\");
                                    wcscat(visibleName, Buffer);
                                    //реальное имя начинается с выбранного раздела
                                    wcscpy(realName, Buffer);
                                }
                            }
                            else {
                                wcscat(visibleName, L"\\");
                                wcscat(visibleName, Buffer);
                                wcscpy(realName, GetName(visibleName));
                            }
                        }
                    }
                    //стандартный вывод
                    if (standartPrint) {
                        //проверяем, нужно ли отрисовывать кнопки добаления/удаления разделов/параметров
                        if (!isVisible) {
                            HWND hAddSection = GetDlgItem(hWnd, hAddSectionButtonId);
                            HWND hDeleteSection = GetDlgItem(hWnd, hDeleteSectionButtonId);
                            HWND hAddValue = GetDlgItem(hWnd, hAddValueButtonId);
                            HWND hDeleteValue = GetDlgItem(hWnd, hDeleteValueButtonId);
                            ShowWindow(hAddSection, SW_SHOW);
                            ShowWindow(hDeleteSection, SW_SHOW);
                            ShowWindow(hAddValue, SW_SHOW);
                            ShowWindow(hDeleteValue, SW_SHOW);
                            isVisible = TRUE;
                        }
                        HKEY hKey;
                        if (RegOpenKeyEx(currentHkey, realName, 0, KEY_ALL_ACCESS, &hKey) != ERROR_SUCCESS) {
                            MessageBox(hWnd, L"Недостаточно прав (запустите приложение от имени администратора)", L"Ошибка", MB_OK);
                        }
                        else {
                            TCHAR    achKey[MAX_KEY_LENGTH];
                            DWORD    cbName;
                            DWORD    cSubKeys = 0;
                            DWORD    cValues;
                            DWORD i, retCode;
                            TCHAR  achValue[MAX_VALUE_NAME];
                            DWORD cchValue = MAX_VALUE_NAME;
                            //получаем информацию о количествах подразделов и параметров раздела
                            retCode = RegQueryInfoKey(hKey, NULL, NULL, NULL, &cSubKeys, NULL, NULL, &cValues, NULL, NULL, NULL, NULL);
                            //очищаем список подразделов
                            SendMessage(hList, LB_RESETCONTENT, 0, 0L);
                            //в начало добавляем строку "назад"
                            SendMessage(hList, LB_ADDSTRING, 0, (LPARAM)visibleName);
                            if (cSubKeys) {
                                //перебираем подразделы
                                for (i = 0; i < cSubKeys; i++) {
                                    cbName = MAX_KEY_LENGTH;
                                    retCode = RegEnumKeyEx(hKey, i, achKey, &cbName, NULL, NULL, NULL, NULL);
                                    if (retCode == ERROR_SUCCESS) {
                                        //отрисовываем подразделы
                                        SendMessage(hList, LB_ADDSTRING, 0, (LPARAM)achKey);
                                    }
                                }
                            }
                            //очищаем список параметров
                            HWND hValues = GetDlgItem(hWnd, hValuesId);
                            SendMessage(hValues, LB_RESETCONTENT, 0, 0L);
                            if (cValues) {
                                //перебираем параметры
                                for (i = 0, retCode = ERROR_SUCCESS; i < cValues; i++) {
                                    cchValue = MAX_VALUE_NAME;
                                    achValue[0] = '\0';
                                    retCode = RegEnumValue(hKey, i, achValue, &cchValue, NULL, NULL, NULL, NULL);
                                    if (retCode == ERROR_SUCCESS) {
                                        //отрисовываем пармаметры
                                        SendMessage(hValues, LB_ADDSTRING, 0, (LPARAM)achValue);
                                    }
                                }
                            }
                        }
                        RegCloseKey(hKey);
                    }
                    else {
                        //указываем, что следующий вывод будет стандартным
                        standartPrint = TRUE;
                    }
                }
            }
        }
            break;
        //список параметров
        case hValuesId: {
            //событие: двойной клик по параметру
            if (HIWORD(wParam) == LBN_DBLCLK) {
                //окна для редактирования параметра
                HWND hStatic = GetDlgItem(hWnd, hStaticId);
                HWND hAddEdit = GetDlgItem(hWnd, hAddEditValueEditId);
                HWND hAddType = GetDlgItem(hWnd, hAddEditValueTypeId);
                HWND hAddValue = GetDlgItem(hWnd, hAddEditValueValueId);
                HWND hAddDo = GetDlgItem(hWnd, hAddEditValueDoId);
                HWND hAddUndo = GetDlgItem(hWnd, hAddEditValueUndoId);
                HWND hList = GetDlgItem(hWnd, hValuesId);
                HWND hExp = GetDlgItem(hWnd, hExplainId);
                //получаем индекс выбранного параметра в списке
                int uSelectedItem = (int)SendMessage(hList, LB_GETCURSEL, 0, 0);
                WCHAR Buffer[MAX_STRING_LENGTH];
                if (uSelectedItem != LB_ERR) {
                    HKEY hKey;
                    if (RegOpenKeyEx(currentHkey, realName, 0, KEY_ALL_ACCESS, &hKey) != ERROR_SUCCESS) {
                        MessageBox(hWnd, L"Недостаточно прав (запустите приложение от имени администратора)", L"Ошибка", MB_OK);
                    }
                    else {
                        //получаем название выбранного параметра
                        SendMessage(hList, LB_GETTEXT, (WPARAM)(int)uSelectedItem, (LPARAM)(LPCTSTR)Buffer);
                        DWORD lpType;
                        DWORD retCode;
                        WCHAR value[MAX_STRING_LENGTH];
                        DWORD pcbData;
                        //получаем значения выбранного параметра, а также его тип (некоторые типы обрабатываются отдельно)
                        retCode = RegGetValueW(currentHkey, realName, Buffer, RRF_RT_ANY, &lpType, (LPVOID)value, &pcbData);
                        WCHAR type[MAX_STRING_LENGTH];
                        if (lpType == REG_SZ) {
                            //записываем тип
                            wcscpy(type, L"REG_SZ");
                            //отображаем объяснение
                            SetWindowText(hExp, L"Строка любой длинны");
                        }
                        else if (lpType == REG_BINARY) {
                            wcscpy(type, L"REG_BINARY");
                            SetWindowText(hExp, L"Двоичные данные в\rшестнадцатиричном формате");
                            //массив байт для записи значения
                            BYTE buffer[1024];
                            DWORD dwType = REG_BINARY, dwSize = sizeof(buffer);
                            HKEY hKey;
                            if (RegOpenKeyEx(currentHkey, realName, 0, KEY_ALL_ACCESS, &hKey) != ERROR_SUCCESS) {
                                MessageBox(hWnd, L"Недостаточно прав (запустите приложение от имени администратора)", L"Ошибка", MB_OK);
                            }
                            else {
                                //записываем значение
                                RegQueryValueExW(hKey, Buffer, NULL, &dwType, buffer, &dwSize);
                            }
                            RegCloseKey(hKey);
                            //преобразовываем массив байт в строку (для корректоного отображения)
                            LPWSTR buf = new WCHAR[MAX_STRING_LENGTH];
                            wcscpy(buf, L"\0");
                            LPWSTR another = new WCHAR[MAX_STRING_LENGTH];
                            for(int i = 0; i < dwSize; i++) {
                                _itow(buffer[i], another, 16);
                                if (wcslen(another) == 1) {
                                    wcscpy(value, another);
                                    wcscpy(another, L"0");
                                    wcscat(another, value);
                                }
                                wcscat(buf, another);
                            }
                            wcscpy(value, buf);
                            delete[] buf;
                            delete[] another;
                        }
                        else if (lpType == REG_DWORD) {
                            wcscpy(type, L"REG_DWORD");
                            SetWindowText(hExp, L"Целое число от 0 до 4294967296");
                            DWORD val;
                            //получаем 32-битное значение
                            retCode = RegGetValueW(currentHkey, realName, Buffer, RRF_RT_ANY, &lpType, (LPVOID)&val, &pcbData);
                            //преобразовываем в строку
                            _i64tow(val, value, 10);
                        }
                        else if (lpType == REG_QWORD) {
                            wcscpy(type, L"REG_QWORD");
                            SetWindowText(hExp, L"Целое число \rот 0 до 18446744073709551616");
                            DWORD64 val;
                            //получаем 64-битное значение
                            retCode = RegGetValueW(currentHkey, realName, Buffer, RRF_RT_ANY, &lpType, (LPVOID)&val, &pcbData);
                            //преобразовываем в строку
                            _i64tow(val, value, 10);
                        }
                        else if (lpType == REG_MULTI_SZ) {
                            wcscpy(type, L"REG_MULTI_SZ");
                            SetWindowText(hExp, L"Последовательность строк\rСтроки разделяются пробелами");
                        }
                        else if (lpType == REG_EXPAND_SZ) {
                            wcscpy(type, L"REG_EXPAND_SZ");
                            SetWindowText(hExp, L"Строка, содержащая необъявленные\rссылки на переменные среды\r(например, \" % PATH % \")");
                        }
                        //заполняем форму для редактирования
                        SetWindowText(hStatic, L"Редактирование параметра");
                        SetWindowText(hAddEdit, Buffer);
                        SetWindowText(hAddValue, value);
                        int cur = SendMessage(hAddType, CB_FINDSTRING, (WPARAM)(-1), (LPARAM)type);
                        SendMessage(hAddType, CB_SETCURSEL, (WPARAM)cur, 0L);
                        SetWindowText(hAddDo, L"Редактировать параметр");
                        //отображаем окна
                        ShowWindow(hStatic, SW_SHOW);
                        ShowWindow(hAddEdit, SW_SHOW);
                        ShowWindow(hAddType, SW_SHOW);
                        ShowWindow(hAddValue, SW_SHOW);
                        ShowWindow(hAddDo, SW_SHOW);
                        ShowWindow(hAddUndo, SW_SHOW);
                        ShowWindow(hExp, SW_SHOW);
                        //указываем, что параметр будет редактироваться
                        change = TRUE;
                        //запоминаем текущще имя
                        wcscpy(changingName, Buffer);
                    }
                    RegCloseKey(hKey);
                }
            }
        }
            break;
        //кнопка добаления раздела
        case hAddSectionButtonId: {
            //отображаем окна для добавления раздела
            SendMessage(hWnd, WM_COMMAND, (WPARAM)hAddEditValueUndoId, 0L);
            HWND hStatic = GetDlgItem(hWnd, hStaticId);
            HWND hAddSection = GetDlgItem(hWnd, hAddSectionEditId);
            HWND hDo = GetDlgItem(hWnd, hAddSectionDoId);
            HWND hUndo = GetDlgItem(hWnd, hAddSectionUndoId);
            HWND hEdit = GetDlgItem(hWnd, hAddSectionEditId);
            SetWindowText(hStatic, L"Добавление раздела");
            ShowWindow(hStatic, SW_SHOW);
            ShowWindow(hAddSection, SW_SHOW);
            ShowWindow(hDo, SW_SHOW);
            ShowWindow(hUndo, SW_SHOW);
            ShowWindow(hEdit, SW_SHOW);
        }
            break;
        //кнопка отмены добавления раздела
        case hAddSectionUndoId: {
            //скрываем окна добавления параметра
            HWND hStatic = GetDlgItem(hWnd, hStaticId);
            HWND hAddSection = GetDlgItem(hWnd, hAddSectionEditId);
            HWND hDo = GetDlgItem(hWnd, hAddSectionDoId);
            HWND hUndo = GetDlgItem(hWnd, hAddSectionUndoId);
            HWND hEdit = GetDlgItem(hWnd, hAddSectionEditId);
            SetWindowText(hEdit, L"Название раздела");
            ShowWindow(hStatic, SW_HIDE);
            ShowWindow(hAddSection, SW_HIDE);
            ShowWindow(hDo, SW_HIDE);
            ShowWindow(hUndo, SW_HIDE);
            ShowWindow(hEdit, SW_HIDE);
        }
            break;
        //кнопка подтверждения добавления раздела
        case hAddSectionDoId: {
            HKEY hKey;
            HWND hStatic = GetDlgItem(hWnd, hStaticId);
            HWND hAddSection = GetDlgItem(hWnd, hAddSectionEditId);
            HWND hDo = GetDlgItem(hWnd, hAddSectionDoId);
            HWND hUndo = GetDlgItem(hWnd, hAddSectionUndoId);
            HWND hEdit = GetDlgItem(hWnd, hAddSectionEditId);
            WCHAR Buffer[MAX_STRING_LENGTH];
            WCHAR BufferCopy[MAX_STRING_LENGTH];
            //получаем название добавляемого раздела
            GetWindowText(hEdit, Buffer, MAX_STRING_LENGTH);
            wcscpy(BufferCopy, Buffer);
            //если раздел создан не в стондартном разделе
            if (wcscmp(realName, L"") != 0) {
                //добаляем к названию раздела путь до него
                WCHAR buf[MAX_STRING_LENGTH];
                wcscpy(buf, realName);
                wcscat(buf, L"\\");
                wcscat(buf, Buffer);
                wcscpy(Buffer, buf);
            }
            DWORD dwDisposition;
            //создание раздела
            RegCreateKeyExW(currentHkey, Buffer, 0, NULL, 0, KEY_ALL_ACCESS, NULL, &hKey, &dwDisposition);
            if (dwDisposition == REG_CREATED_NEW_KEY) {
                HWND hList = GetDlgItem(hWnd, hListBoxId);
                //отрисовываем созданный раздел
                SendMessage(hList, LB_INSERTSTRING, (WPARAM)1, (LPARAM)BufferCopy);
                MessageBox(hWnd, L"Новый раздел успешно создан", L"Успех", MB_OK);
                //скрываем окна создания
                SendMessage(hWnd, WM_COMMAND, WPARAM(hAddSectionUndoId), 0L);
            }
            else if (dwDisposition == REG_OPENED_EXISTING_KEY) {
                MessageBox(hWnd, L"Раздел с таким именем уже существует", L"Ошибка", MB_OK);
            }
            else {
                MessageBox(hWnd, L"Ошибка в создании раздела", L"Ошибка", MB_OK);
            }
            RegCloseKey(hKey);
        }
            break;
        //кнопка удаления раздела
        case hDeleteSectionButtonId: {
            HWND hList = GetDlgItem(hWnd, hListBoxId);
            //получаем индекс выбранного раздела
            int uSelectedItem = (int)SendMessage(hList, LB_GETCURSEL, 0, 0);
            //если раздел не выбран - ошибка
            if (uSelectedItem == CB_ERR) {
                MessageBox(hWnd, L"Выберите раздел", L"Ошибка", MB_OK);
            }
            else {
                WCHAR Buffer[MAX_STRING_LENGTH];
                //получаем название раздела
                SendMessage(hList, LB_GETTEXT, (WPARAM)(int)uSelectedItem, (LPARAM)(LPCTSTR)Buffer);
                //если название не пустое
                if (wcscmp(Buffer, L"") != 0) {
                    HKEY hKey;
                    if (RegOpenKeyEx(currentHkey, realName, 0, KEY_ALL_ACCESS, &hKey) != ERROR_SUCCESS) {
                        MessageBox(NULL, L"Недостаточно прав (запустите приложение от имени администратора)", L"Ошибка", MB_OK);
                    }
                    else {
                        //удаляем раздел в реестре
                        if (RegDeleteKey(hKey, Buffer) == ERROR_SUCCESS) {
                            //удаляем название в списке
                            SendMessage(hList, LB_DELETESTRING, (WPARAM)uSelectedItem, 0L);
                            MessageBox(hWnd, L"Раздел успешно удален", L"Успех", MB_OK);
                        }
                        else {
                            MessageBox(hWnd, L"Ошибка удаления раздела", L"Ошибка", MB_OK);
                        }
                    }
                }
            }
        }
            break;
        //кнопка добавления параметра
        case hAddValueButtonId: {
            //отрисовываем окна создания параметра
            SendMessage(hWnd, WM_COMMAND, (WPARAM)hAddSectionUndoId, 0L);
            HWND hStatic = GetDlgItem(hWnd, hStaticId);
            HWND hExp = GetDlgItem(hWnd, hExplainId);
            HWND hAddEdit = GetDlgItem(hWnd, hAddEditValueEditId);
            HWND hAddType = GetDlgItem(hWnd, hAddEditValueTypeId);
            HWND hAddValue = GetDlgItem(hWnd, hAddEditValueValueId);
            HWND hAddDo = GetDlgItem(hWnd, hAddEditValueDoId);
            HWND hAddUndo = GetDlgItem(hWnd, hAddEditValueUndoId);
            SetWindowText(hStatic, L"Добавление параметра");
            SetWindowText(hAddEdit, L"Название параметра");
            SetWindowText(hAddValue, L"Значение параметра");
            SetWindowText(hAddDo, L"Добавить параметр");
            ShowWindow(hStatic, SW_SHOW);
            ShowWindow(hAddEdit, SW_SHOW);
            ShowWindow(hAddType, SW_SHOW);
            ShowWindow(hAddValue, SW_SHOW);
            ShowWindow(hAddDo, SW_SHOW);
            ShowWindow(hAddUndo, SW_SHOW);
            ShowWindow(hExp, SW_SHOW);
            change = FALSE;
        }
            break;
        //кнопка отмены добавления(редактирования) параметра
        case hAddEditValueUndoId: {
            //скрываем окна добаления параметра
            HWND hStatic = GetDlgItem(hWnd, hStaticId);
            HWND hAddEdit = GetDlgItem(hWnd, hAddEditValueEditId);
            HWND hAddType = GetDlgItem(hWnd, hAddEditValueTypeId);
            HWND hAddValue = GetDlgItem(hWnd, hAddEditValueValueId);
            HWND hAddDo = GetDlgItem(hWnd, hAddEditValueDoId);
            HWND hAddUndo = GetDlgItem(hWnd, hAddEditValueUndoId);
            HWND hExp = GetDlgItem(hWnd, hExplainId);
            ShowWindow(hStatic, SW_HIDE);
            ShowWindow(hAddEdit, SW_HIDE);
            ShowWindow(hAddType, SW_HIDE);
            ShowWindow(hAddValue, SW_HIDE);
            ShowWindow(hAddDo, SW_HIDE);
            ShowWindow(hAddUndo, SW_HIDE);
            ShowWindow(hExp, SW_HIDE);
        }
            break;
        //кнопка подтверждения добаления(редактирования) параметра
        case hAddEditValueDoId: {
            HWND hAddEdit = GetDlgItem(hWnd, hAddEditValueEditId);
            HWND hAddType = GetDlgItem(hWnd, hAddEditValueTypeId);
            HWND hAddValue = GetDlgItem(hWnd, hAddEditValueValueId);
            HWND hList = GetDlgItem(hWnd, hValuesId);
            LPWSTR name = new WCHAR[MAX_STRING_LENGTH];
            LPWSTR type = new WCHAR[MAX_STRING_LENGTH];
            LPWSTR value = new WCHAR[MAX_STRING_LENGTH];
            //получаем введенные данные
            GetWindowText(hAddEdit, name, MAX_STRING_LENGTH);
            GetWindowText(hAddValue, value, MAX_STRING_LENGTH);
            int uCurrentItem = (int)SendMessage(hAddType, CB_GETCURSEL, 0, 0L);
            SendMessage(hAddType, CB_GETLBTEXT, (WPARAM)uCurrentItem, (LPARAM)type);
            int N = SendMessage(hList, LB_GETCOUNT, 0, 0L);
            BOOL exist = FALSE;
            //проверяем, существует ли параметр с таким именем
            if (N != LB_ERR) {
                for (int i = 0; i < N; i++) {
                    WCHAR str[MAX_STRING_LENGTH];
                    SendMessage(hList, LB_GETTEXT, (WPARAM)i, (LPARAM)str);
                    if (wcscmp(str, name) == 0) {
                        if (!change) {
                            exist = TRUE;
                            MessageBox(hWnd, L"Такой параметр уже существует", L"Ошибка", MB_OK);
                        }
                        else {
                            //если редактируем параметр, не учитываем его старое название
                            if (wcscmp(changingName, str) != 0) {
                                exist = TRUE;
                                MessageBox(hWnd, L"Такой параметр уже существует", L"Ошибка", MB_OK);
                            }
                        }
                    }
                }
            }
            BOOL doo = TRUE;
            //если параметра с таким названием не существует
            if (!exist) {
                //если редакируем параметр, то удалим его из реестра, а затем добавим с новыми данными
                if (change) {
                    int cur = SendMessage(hList, LB_FINDSTRING, (WPARAM)(-1), (LPARAM)changingName);
                    if (cur != CB_ERR) {
                        if (wcscmp(changingName, L"") != 0) {
                            //а эта проверка - костыль: при редактировании данных и невалидных значениях старый параметр удалялся, а новый не создавался :(
                            if (((wcscmp(type, L"REG_BINARY") == 0) && ParserBINARY(hWnd, value, FALSE)) ||
                                ((wcscmp(type, L"REG_DWORD") == 0) && ParserDWORD(hWnd, value, FALSE)) ||
                                ((wcscmp(type, L"REG_QWORD") == 0) && ParserQWORD(hWnd, value, FALSE)) ||
                                (wcscmp(type, L"REG_SZ") == 0) ||
                                (wcscmp(type, L"REG_MULTI_SZ") == 0) ||
                                (wcscmp(type, L"REG_EXPAND_SZ") == 0)) {
                                HKEY hKey;
                                if (RegOpenKeyEx(currentHkey, realName, 0, KEY_ALL_ACCESS, &hKey) != ERROR_SUCCESS) {
                                    MessageBox(NULL, L"Недостаточно прав (запустите приложение от имени администратора)", L"Ошибка", MB_OK);
                                }
                                else {
                                    //удаляем старое значение
                                    if (RegDeleteValue(hKey, changingName) == ERROR_SUCCESS) {
                                        SendMessage(hList, LB_DELETESTRING, (WPARAM)cur, 0L);
                                    }
                                    else {
                                        MessageBox(hWnd, L"Ошибка удаления параметра", L"Ошибка", MB_OK);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        //если во время редактирования пользователь удалил параметр
                        MessageBox(hWnd, L"Редактируемый параметр не найден", L"Ошибка", MB_OK);
                        doo = FALSE;
                    }
                }
                if (doo) {
                    //добавляем параметр
                    InsertValue(hWnd, name, type, value);
                }
                delete[] name;
                delete[] value;
                delete[] type;
            }
        }
            break;
        //выпадающий список типов значений параметра
        case hAddEditValueTypeId: {
            //если выбрали новый элемент
            if (HIWORD(wParam) == CBN_SELCHANGE) {
                HWND hAddType = GetDlgItem(hWnd, hAddEditValueTypeId);
                LPWSTR type = new WCHAR[MAX_STRING_LENGTH];
                //получили индекс нового значения
                int uCurrentItem = (int)SendMessage(hAddType, CB_GETCURSEL, 0, 0L);
                //получили его значение
                SendMessage(hAddType, CB_GETLBTEXT, (WPARAM)uCurrentItem, (LPARAM)type);
                HWND hExp = GetDlgItem(hWnd, hExplainId);
                //в зависимости от типа, меняем его описание
                if (wcscmp(type, L"REG_SZ") == 0) {
                    SetWindowText(hExp, L"Строка любой длинны");
                }
                else if (wcscmp(type, L"REG_BINARY") == 0) {
                    SetWindowText(hExp, L"Двоичные данные в\rшестнадцатиричном формате");
                }
                else if (wcscmp(type, L"REG_DWORD") == 0) {
                    SetWindowText(hExp, L"Целое число от 0 до 4294967296");
                }
                else if (wcscmp(type, L"REG_QWORD") == 0) {
                    SetWindowText(hExp, L"Целое число \rот 0 до 18446744073709551616");
                }
                else if (wcscmp(type, L"REG_MULTI_SZ") == 0) {
                    SetWindowText(hExp, L"Последовательность строк\rСтроки разделяются пробелами");
                }
                else if (wcscmp(type, L"REG_EXPAND_SZ") == 0) {
                    SetWindowText(hExp, L"Строка, содержащая необъявленные\rссылки на переменные среды\r(например, \" % PATH % \")");
                }
            }
        }
            break;
        //кнопка удаления параметра
        case hDeleteValueButtonId: {
            HWND hList = GetDlgItem(hWnd, hValuesId);
            //получпем индекс параметра
            int uSelectedItem = (int)SendMessage(hList, LB_GETCURSEL, 0, 0);
            if (uSelectedItem == CB_ERR) {
                MessageBox(hWnd, L"Выберите параметр", L"Ошибка", MB_OK);
            }
            else {
                WCHAR Buffer[MAX_STRING_LENGTH];
                //получаем его название
                SendMessage(hList, LB_GETTEXT, (WPARAM)(int)uSelectedItem, (LPARAM)(LPCTSTR)Buffer);
                if (wcscmp(Buffer, L"") != 0) {
                    HKEY hKey;
                    if (RegOpenKeyEx(currentHkey, realName, 0, KEY_ALL_ACCESS, &hKey) != ERROR_SUCCESS) {
                        MessageBox(NULL, L"Недостаточно прав (запустите приложение от имени администратора)", L"Ошибка", MB_OK);
                    }
                    else {
                        //удаляем параметр из реестра
                        if (RegDeleteValue(hKey, Buffer) == ERROR_SUCCESS) {
                            //удаляем название параметра из списка
                            SendMessage(hList, LB_DELETESTRING, (WPARAM)uSelectedItem, 0L);
                            MessageBox(hWnd, L"Параметр успешно удален", L"Успех", MB_OK);
                        }
                        else {
                            MessageBox(hWnd, L"Ошибка удаления параметра", L"Ошибка", MB_OK);
                        }
                    }
                }
            }
        }
            break;
        default:
            return DefWindowProc(hWnd, message, wParam, lParam);
        }
    }
        break;
    case WM_PAINT: {
        PAINTSTRUCT ps;
        HDC hdc = BeginPaint(hWnd, &ps);
        // TODO: Добавьте сюда любой код прорисовки, использующий HDC...
        EndPaint(hWnd, &ps);
    }
        break;
    case WM_DESTROY: {
        delete[] visibleName;
        delete[] realName;
        delete[] changingName;
        PostQuitMessage(0);
    }
        break;
    default: {
        return DefWindowProc(hWnd, message, wParam, lParam);
    }
    }
    return 0;
}

INT_PTR CALLBACK About(HWND hDlg, UINT message, WPARAM wParam, LPARAM lParam) {
    UNREFERENCED_PARAMETER(lParam);
    switch (message) {
    case WM_INITDIALOG:
        return (INT_PTR)TRUE;

    case WM_COMMAND:
        if (LOWORD(wParam) == IDOK || LOWORD(wParam) == IDCANCEL) {
            EndDialog(hDlg, LOWORD(wParam));
            return (INT_PTR)TRUE;
        }
        break;
    }
    return (INT_PTR)FALSE;
}
