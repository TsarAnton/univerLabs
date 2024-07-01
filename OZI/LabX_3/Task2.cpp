#include <Windows.h>
#include <iostream>

using namespace std;

int main() {

	//здесь нужно указать путь до файла .pptx
	ShellExecute(NULL, L"EDIT", L"D:\\labs\\Presentation1.pptx", NULL, NULL, SW_RESTORE);

	cout << "Process started" << endl;

	return 0;
}
