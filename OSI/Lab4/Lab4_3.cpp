#include <Windows.h>
#include <iostream>

using namespace std;

int main() {

	ShellExecute(NULL, L"OPEN", L"D:\\java1\\history.csv", NULL, NULL, SW_RESTORE);

	cout << "Process started" << endl;

	return 0;
}