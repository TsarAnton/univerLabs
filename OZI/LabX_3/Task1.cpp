#include <Windows.h>
#include <iostream>

using namespace std;

int main() {

	STARTUPINFO sif;

	ZeroMemory(&sif, sizeof(STARTUPINFO));

	PROCESS_INFORMATION pi;

	//путь к paint может отличаться
	BOOL res = CreateProcess(
		L"C:\\Windows\\system32\\mspaint.exe",
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
	}
	else {
		cout << "Error in creating process" << endl;
	}

	return 0;
}