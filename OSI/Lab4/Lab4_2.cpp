#include <Windows.h>
#include <iostream>

using namespace std;

int main() {

	STARTUPINFO sif;

	ZeroMemory(&sif, sizeof(STARTUPINFO));

	PROCESS_INFORMATION pi;
	LPCWSTR file = L"C:\\Program Files (x86)\\WinRAR\\WinRaR.exe D:\\1615497371_animated_miku_cursors_by_blahblahidontcare_d544k9r.rar";
	LPWSTR newfile = new WCHAR[wcslen(file)];
	for (int i = 0; i < wcslen(file); i++) {
		newfile[i] = file[i];
	}
	newfile[wcslen(file)] = '\0';
	BOOL res = CreateProcess(
		NULL,
		newfile,
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