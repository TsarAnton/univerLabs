#include <iostream>
#include <iomanip>

using namespace std;
double max_array(double* arr, int N) {         //������������ ������� �������
	double max = arr[0];
	for (int i = 1; i < N; i++) {
		if (arr[i] > max)
			max = arr[i];
	}
	return max;
}
double min_array(double* arr, int N) {     //����������� ������� �������
	double min = arr[0];
	for (int i = 1; i < N; i++) {
		if (arr[i] < min)
			min = arr[i];
	}
	return min;
}
void cop_arr(int* kuda, int* otkuda, int n) {    //�������� ������
	for (int i = 0; i < n; i++) {
		kuda[i] = otkuda[i];
	}
}

double* find_x(int* R_arr, int N) {          //���� ������ �
	double* x_arr = new double[N];          //������ �
	double* buff = new double[N - 1];       //��������� ������
	x_arr[0] = 0;                       //� ������� �������� = 0
	for (int i = 1; i < N; i++) {       // �������� ������� ��������( � ������� ������� = 0) �� 1
		int j = 0;
		for (j; j < i; j++)
			buff[j] = x_arr[j] + 2 * sqrt(R_arr[j] * R_arr[i]);    //"�������" ���� � ������ ���������� � ������� ������ �
		x_arr[i] = max_array(buff, j);     //���������� ����� ������ ��������
	}
	return x_arr;
}

double max_length(int* r_arr, int N) {      //������������ ������ ������� ��� ������������ ������������
	double* x_arr = new double[N];        //������� ������ �
	x_arr = find_x(r_arr, N);          
	double* left = new double[N];   //������ ��� ������� ����� ������� ������� �����
	double* right = new double[N];   //������ ��� ������� ������ ������� ������� �����
	for (int i = 0; i < N; i++) {
		left[i] = x_arr[i] - r_arr[i];
		right[i] = x_arr[i] + r_arr[i];
	}
	return max_array(right, N) - min_array(left, N);   //������������ ������ ������� - ������������ �����
}
double find_min_len(int* arr_zmen, int voshedshy, int number_of_sravn) {// ������� � ������ ������ ��������� � ������� ��� ����������� ������������ � ����������� ������ ��� ����
	if (number_of_sravn == 2) {         //���� ����� 2-� �������
		arr_zmen[1] = voshedshy;
	//	cout << "�������� "<<voshedshy<<"    ������ = " << max_length(arr_zmen, 2) << endl;
		return max_length(arr_zmen, 2);
	}

	int* arr_zmen_buff = new int[number_of_sravn];      //������� ����� �������    
	cop_arr(arr_zmen_buff, arr_zmen, number_of_sravn);
	arr_zmen_buff[number_of_sravn - 1] = arr_zmen[number_of_sravn - 1] = voshedshy;  //�� ��������� ������� ������ ���������

	double min_len = max_length(arr_zmen_buff, number_of_sravn), buff ,buff1;    //����������� ������ 
	for (int i = number_of_sravn - 1; i >= 0; i--) {     //������ �� ���������� ��������(��������) �� �������
		
		buff = arr_zmen_buff[number_of_sravn - 1];               
		arr_zmen_buff[number_of_sravn - 1] = arr_zmen[i];      //������ ��������� �� ������ �������
		arr_zmen_buff[i] = buff;     

		buff1 = max_length(arr_zmen_buff, number_of_sravn);   //������ ������ ����� �������� �� ������ �������
	//	cout << "�������� " << voshedshy << "     ������ = " << buff1 << endl;
		if (buff1 < min_len) {     //���� ������ �� ������ ������� ������ ��� �� ����������
			
			buff = arr_zmen[number_of_sravn - 1];
			arr_zmen[number_of_sravn - 1] = arr_zmen[i];    //������ ������� � �������� �������
			arr_zmen[i] = buff;

			min_len = buff1;   //����������� ����������� ������
		}
	}
	return min_len;
}
double final_min_length(int* r_arr, int N) {   //������� ����������� ������ ��� ����������� ������������
	if (N == 1) {
		cout << "������������ �������� � �������: " << r_arr[0] << endl;
		return r_arr[0];
	}
	int* arr_zmen = new int[N];       //������ ��� �������� ������������ ������������
	arr_zmen[0] = r_arr[0];         //������� ������ ������
	double real_len;
	for (int i = 1, number_of_sravn = 2; i < N; i++,number_of_sravn++) {
			real_len = find_min_len(arr_zmen, r_arr[i], number_of_sravn);   //������� ������, ���� ��� ����������� ������������� � ����������� ������ �������
	}
	cout << "������������ �������� � �������: ";   //������� ����������� �������������
	for (int i = 0; i < N; i++)
		cout << arr_zmen[i] << " ";
	cout << endl;
	return real_len;

}

int main() {
	setlocale(LC_ALL, "Russian");

	int N;
	cout << "������� ���-�� ������" << endl;
	cin >> N;
	int* R_array = new int[N];
	cout << "������� ������� ������" << endl;
	for (int i = 0; i < N; i++) {
		cin >> R_array[i];
	}
	cout << "��������� �������: ";
	for (int i = 0; i < N; i++)
		cout << R_array[i] << " ";
	cout << endl;

	int sravn = 2;

	cout << "����������� ������ ������� = " << final_min_length(R_array,N) << endl;
	delete[] R_array;


	system("pause");
	return 0;
}