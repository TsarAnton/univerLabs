
#include <iostream>
using namespace std;
int main(){
	setlocale(LC_ALL, "Russian");
	int i=0,i1,N,flag=0;
	int zd=1, poisk = 0;
	cout << "������� ���������� ���������" << endl;
	cin >> N;
	int* array = (int*)calloc(N, sizeof(double));
	cout << "������� �������� �������" << endl;
	for (i; i < N; i++) {
		cin >> array[i];
	}
	for (i=0; i < N; i++) {
		if (array[i] == 0) {
			poisk = poisk + 1;
		}
		if (poisk == 2) {
			for (i1 = i - 1; i1 > 0 && array[i1]; i1--) {
				zd = zd * array[i1];
			}
			    poisk = poisk + 1;
				flag += 1;
		}
	}
	if (flag != 0) {
		cout << "������������ ��������� ����� ������ � ������ 0 =" << " " << zd << endl;
	}
	else cout << "������" << endl;
	system("pause");
	return 0;
}