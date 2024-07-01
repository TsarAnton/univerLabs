#include <iostream>
using namespace std;

int __declspec(naked) __stdcall find_del( int n)
{
	__asm {
		mov ECX, [ESP + 4]   

		mov eax, ecx
		mov edx, 0
		mov ebx, 2
		div ebx
		mov ebx, eax

		Begin :  

		mov eax, ecx
		mov edx, 0
		div ebx
		cmp edx, 0 

		je End                    

		dec ebx
		
		jmp Begin               

		End :
		mov eax, ebx
		ret 8
	}
}

int main(){

	setlocale(LC_ALL, "russian");

	int N;
	cin >> N;

	cout << find_del(N) << endl;

	
	system("pause");
	return 0;
}