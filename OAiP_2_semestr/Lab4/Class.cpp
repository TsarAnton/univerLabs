#include "Class.h"
#include <string>

int find_NOD(int n1, int n2) {//���
	
	//���� ����� 1 ��� ����� 2 �������������, ������ �� ��������������
	if (n1 < 0)
		n1 *= -1;
	if (n2 < 0)
		n2 *= -1;

	//������� � n1 ������� �� ���� ����� n1 � n2
	if (n1 < n2) {
		int buff = n1;
		n1 = n2;
		n2 = buff;
	}

	int ost = n1 % n2;//������� �������

	//�������� ������� (������ � ����� ��������)
	while (ost != 0) {
		n1 = n2;
		n2 = ost;
		ost = n1 % n2;
	}
	return n2;
}

Drob::Drob() {//����������� �� ���������
	znam = NULL;
	chisl = NULL;
}
int Drob::get_chisl() {
	return chisl;
}
int Drob::get_znam() {
	return znam;
}
void Drob::set_chisl(int n) {
	chisl = n;
}
void Drob::set_znam(int n) {
	znam = n;
}
istream& operator >>(istream& fin, Drob& num) {//���� �����
	string get, buff, buff1;
	getline(fin, get);//������ ������ ���� "a/b" (�������� "5/4" ��� "3/2"_
	bool kuda = true;//���� true, �� ������� � ���������, ����� � �����������
	
	for (int i = 0; i < get.length(); i++) {
		char b = get[i];//����� ������ �� ��������� ������
		if (b != '/') {//���� ������� ������ '/' , �� �������� ���������� � �����������
			if (kuda)//���� ���������� � ���������
				buff.push_back(b);//������� ������ b � ����� ������ buff
			else//����� (���� ���������� � �����������)
				buff1.push_back(b);//������� ������ � ����� ������ buff1
		}
		else
			kuda = false;
	}

	//ch - ��������� ���������, zn - �����������
	int ch = stoi(buff), zn = stoi(buff1);//stoi - ���������� ������ � �����
	try {//��������� �������������� ��������
		if (find_NOD(ch, zn) != 1)//���� ��� ��������� � ����������� �� ����� 1, �� ����� ���������� 
			throw 1;//���� ����� ���������, ���������� ������ 1
		//���� ����� �����������
		num.set_chisl(ch);//���������� � ��������� �������� ����� ch
		num.set_znam(zn);//���������� � ����������� �������� ����� zn
		return fin;
	}
	catch (int e){
		if (e == 1) {//���� ������� ������ 1
			cout << "������ �����: ����� ���������" << endl;
		}
	}
}
ostream& operator <<(ostream& fout, Drob& num) {//�����
	fout << num.chisl << "/" << num.znam;
	return fout;
}
Drob operator /( Drob& num,Drob& num1) {//�������
	Drob ret;//������������ �����
	ret.set_chisl(num.get_chisl() * num1.get_znam());//��������� ������������ ����� = ��������� ����� 1 * ����������� ����� 2
	ret.set_znam(num.get_znam() * num1.get_chisl());//����������� ������������ ����� = ����������� ����� 1 * ��������� ����� 2
	int nod = find_NOD(ret.get_chisl(), ret.get_znam());//������� ��� ��������� � �����������
	if (nod != 1) {//���� ����� ����� ���������
		//���������
		ret.set_chisl(ret.get_chisl() / nod);
		ret.set_znam(ret.get_znam() / nod);
	}
	return ret;
}
Drob operator -(Drob& num, Drob& num1) {//���������
	Drob ret;//������������ �����
	ret.set_znam(num.get_znam() * num1.get_znam());//������� ����� ����������� ���� ������ � ������� ��� � ����������� ������������ �����
	ret.set_chisl(num.get_chisl() * num1.get_znam() - num1.get_chisl() * num.get_znam());//������� ��������� ������������ �����
	//���������
	int nod = find_NOD(ret.get_chisl(), ret.get_znam());
	if (nod != 1) {
		ret.set_chisl(ret.get_chisl() / nod);
		ret.set_znam(ret.get_znam() / nod);
	}
	return ret;
}