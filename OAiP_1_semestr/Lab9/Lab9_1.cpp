#include <iostream>
#include <string.h>
#include <cstring>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	cout << "������� ������" << endl;
	char* stroka = new char[1000]; 
	cin.getline(stroka, 1000);
	char a;
	cout << "������� ������" << endl;
	cin >> a;
	char* funk = strrchr(stroka, a);
	if (funk - stroka + 1 >= 0) {
		cout << "��������� ����� �������: " << funk << endl;
		cout << "��������� ��������� ������� " << a << " , ���������� � ������� �������, ����� ������� " << funk - stroka << endl;
	}
	else cout << "� ������ ��� ������ �������" << endl;
	int flag = 0, q = strlen(stroka)+1, i = q - 1;
	cout << "q =" <<q<< endl;
	for (i; i >= 0 && flag == 0; i--) {
		if (stroka[i] == a) {
			int g = i;
			char* nova = new char[q-i];
			for (int n = 0; n < q-i; n++) {
				nova[n] = stroka[g];
				g += 1;
			}
			cout << "��������� ������ �������: " << nova << endl;
			cout << "��������� ��������� ������� " << a << " , ���������� � ������� �������, ����� ������� " << i << endl;
			flag = 1;
		}
	}
	if (flag == 0)
		cout << "� ������ ��� ������ �������" << endl;
	delete[] (stroka);
	system("pause");
	return 0;
}