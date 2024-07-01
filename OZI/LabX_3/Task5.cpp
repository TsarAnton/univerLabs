#include <iostream>
#include <Windows.h>

using namespace std;

int main() {
    //указываем путь к dll
    HMODULE hMyDll = LoadLibrary(L"D:\\LibraryA5.dll");
    if (hMyDll == NULL) {
        wcerr << L"Error in loading library" << endl;
        system("pause");
        return 1;
    }
    BOOL (*SomeFunction)(PWCHAR, INT) = (BOOL(*)(PWCHAR, INT))GetProcAddress(hMyDll, "SomeFunction");
    if (!SomeFunction) {
        wcerr << L"Error in loading function from library" << endl;
        system("pause");
        return 1;
    }

    cout << SomeFunction((PWCHAR)L"Hello", 42) << endl;

    //освобождвем dll
    FreeLibrary(hMyDll);
    system("pause");
    return 0;
}
