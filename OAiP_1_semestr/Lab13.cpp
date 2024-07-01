#include <iostream>
using namespace std;
struct Tree {
	double d;
	Tree* left;
	Tree* right;
};
void first(Tree*& top, double d) {
	top = new Tree;
	top->d = d;
	top->left = top->right = 0;
}
Tree* search(Tree* top, double d) {
	Tree* pv = top, * ppv = top;
	while (pv) {
		ppv = pv;
		if (d < pv->d)
			pv = pv->left;
		else
			pv = pv->right;
	}
	return ppv;
}
void add(Tree* top, double d) {
	Tree* ppv, * pnew = new Tree;
	pnew->d = d;
	pnew->left = 0;
	pnew->right = 0;
	ppv = search(top, d);
	if (d < ppv->d)
		ppv->left = pnew;
	else
		ppv->right = pnew;
}
void print(Tree* top, int level) {
	if (top) {
		print(top->left, level + 1);
		for (int i = 0; i < level; i++)
			cout << "'";
		cout << top->d << endl;
		print(top->right, level + 1);
	}
}
double printArr(double arr[],int schet[]){
	return arr[0] / schet[0];
}
int high(Tree*top){
	if (top->left == NULL && top->right == NULL)
		return 0;
	int left = 0;
	if (top->left != NULL) {
		left = high(top->left);
	}
	int right = 0;
	if (top->right != NULL) {
		right = high(top->right);
	}
	return (max(left, right) + 1);
}
void sum_and_schet(Tree* node, int level,int n, double sum[],int schet[]){
	if (node == NULL)
		return;
	if (level == n) {
		sum[0] += node->d;
		schet[0] += 1;
	}
	sum_and_schet(node->left, level + 1,n, sum, schet);
	sum_and_schet(node->right, level + 1,n, sum, schet);
}
/*void sum_and_schet1(Tree* node, int level,int n, double sum, int schet) {
	if (node == NULL)
		return ;
	if (level == n) {
		sum += node->d;
		schet += 1;
	}
	cout << "����� ��������� �� ������ " << sum << "                     ���-�� ��������� �� ������  " << schet << endl;
	sum_and_schet1(node->left, level + 1,n, sum,schet);
	sum_and_schet1(node->right, level + 1,n, sum,schet);
}
double print1(double arr, int schet) {
	return arr / schet;
}*/
int main() {
	setlocale(LC_ALL, "Russian");
	int N;
	cout << "������� ���-�� ��������� ��������� ������" << endl;
	cin >> N;
	if (N > 0) {
		Tree* top;
		cout << "������� �������� ������" << endl;
		double x;
		cin >> x;
		first(top, x);
		for (int i = 1; i < N; i++) {
			cin >> x;
			add(top, x);
		}
		cout << "�������� ������(�� �������)" << endl;
		print(top, 0);
		int levels = high(top);
		cout << "������������ ������� " << levels << endl;
		double sum[1] = { 0 };
		int schet[1] = { 0 };
		//double sum1 = 0;
		//int schet1 = 0;
		cout << "������� �������" << endl;
		int b;
		cin >> b;
		if (b <= levels) {
			sum_and_schet(top, 0, b, sum, schet);
			cout << "������� �������������� ����� �� ������ " <<b<< endl;
			cout << printArr(sum, schet) << endl;
			//cout << print1(sum1, schet1) << endl;
		}
		else
			cout << "B�������� ������� ������� �������" << endl;
	}
	system("pause");
	return 0;
}