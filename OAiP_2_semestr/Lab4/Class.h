#ifndef Class_h
#define Class_h
#include <iostream>
using namespace std;

class Drob { //����� �����
	int chisl;//���������
	int znam;//�����������
public:
	Drob();
	void set_chisl(int n);
	void set_znam(int n);
	int get_chisl();
	int get_znam();


	friend istream& operator >> (istream& fin, Drob& num);//���������� �����
	friend ostream& operator << (ostream& fout, Drob& num);//���������� ������

	friend Drob operator -(Drob& num,Drob& num1);//���������� ������
	friend Drob operator /(Drob& num, Drob& num1);//���������� �������
};
int find_NOD(int num1, int num2);//��� ���� �����
#endif
