#include <iostream>
using namespace std;
int main() {
	setlocale(LC_ALL, "Russian");
	int A, A1,A2;
	cout << "������� A" << endl;
	cin >> A;
	A = A * A;//1 ��������
	A = A * A;//2 ��������
	A = A * A;//3 ��������
	cout << "� � ������� 8 = " << A << endl;
	cin >> A;
	A1 = A * A;//1 ��������
	A = A1 * A1;//2 ��������
	A = A * A;//3 ��������
	A = A1 * A;//4 ��������
	cout << "� � ������� 10 = " << A << endl;
	cin >> A;
	A1 = A * A;//1 ��������
	A2 = A1 * A;//2 ��������
	A1 = A2 * A1;//3 ��������
	A = A1 * A1;//4 ��������
	A = A * A2;//5 ��������
	cout << "� � ������� 13 = " << A << endl;
	cin >> A;
	A1 = A * A;//1 ��������
	A2 = A1 * A;//2 ��������
	A2 = A1 * A2;//3 ��������
	A = A2 * A2;//4 ��������
	A = A * A2;//5 ��������
	cout << "� � ������� 15 = " <<A<< endl;
	cin >> A;
	A1 = A * A;//1 ��������
	A2= A1 * A1;//2 ��������
	A1 = A2 * A2;//3 ��������
	A1 = A1 * A1;//4 ��������
	A1 = A1 * A2;//5 ��������
	A = A1 * A;//6 ��������
	cout << "� � ������� 21 = " <<A<< endl;
	system("pause");
		return 0;

}
