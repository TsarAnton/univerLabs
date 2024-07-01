#include <Windows.h>
#include <iostream>

using namespace std;

int main() {

	STARTUPINFO sif;

	ZeroMemory(&sif, sizeof(STARTUPINFO));

	PROCESS_INFORMATION pi;

	BOOL res = CreateProcess(
		L"C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\EXCEL.exe",
		NULL,
		NULL,
		NULL,
		FALSE,
		NULL,
		NULL,
		NULL,
		&sif,
		&pi
	);

	if (res) {
		cout << "Process started" << endl;
		cout << "Process handle: " << pi.hProcess << endl;
		cout << "Process id: " << pi.dwProcessId << endl;
	}
	else {
		cout << "Error in creating process" << endl;
	}

	return 0;
}