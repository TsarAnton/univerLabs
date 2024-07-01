#include <iostream>
#include <iomanip>
using namespace std;

int return_min(int a, int a_pos, int b, int b_pos, int c, int c_pos,int d,int d_pos) {
	int max_el = min(min(a, b), min(c, d));
	if (max_el == a) {
		return a_pos;
	}
	else if (max_el == b) {
		return b_pos;
	}
	else if(max_el == c){
		return c_pos;
	}
	else {
		return d_pos;
	}
}
class D_heap {
	int* arr;
	int pos;
public:
	D_heap(int N) {
		N += 3 * N + 3;
		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = INT_MAX;
		pos = 0;
	}
	void vost_add(int d) {
		if (d >= arr[(pos - 1) / 3])
			return;

		int pos1 = pos;
		bool con = true;
		while (con) {
			int buff = arr[(pos1 - 1) / 3];
			arr[(pos1 - 1) / 3] = d;
			arr[pos1] = buff;
			pos1 = (pos1 - 1) / 3;
			if (pos1 == 0 || d >= arr[(pos1 - 1) / 3])
				con = false;
		}
	}
	void add(int d){
		arr[pos] = d;
		vost_add(d);
		pos++;
	}
	void vost_del() {
		int pos_kuda = 0;
		bool con = true;
		while(con){
			int min = return_min(arr[3 * pos_kuda + 1], 3 * pos_kuda + 1, arr[3 * pos_kuda + 2], 3 * pos_kuda + 2, arr[3 * pos_kuda + 3], 3 * pos_kuda + 3, arr[pos - 1], pos - 1);
			arr[pos_kuda] = arr[min];
			arr[min] = NULL;
			pos_kuda = min;
			if (pos_kuda == pos - 1)
				con = false;
		}
	}
	int delete_min() {
		int ret = arr[0];
		vost_del();
		pos--;
		return ret;
	}
	void print() {
		int kol_level = 1;
		int N = 0;
		while (arr[N] != INT_MAX) {
			kol_level++;
			N += 3 * N + 3;
		}
		int buff1 = kol_level;
		kol_level = 1;
		for (int i = 0; i < buff1 - 1; i++)
			kol_level *= 3;
		int num_print = -1, buff = kol_level, first_otsp = 2, otsp_mezhdy = 3, palki = 9;
		for (int i1 = 0; i1 < N; i1++) {
			num_print += buff;
			buff /= 3;
		}

		for (int i = 0; i < buff1; i++) {
			for (int i1 = 0; i1 < first_otsp-2; i1++)
				cout << " ";
			for (int j = 0; j < kol_level; j++) {
				if (arr[num_print] == INT_MAX) {
					cout << "  -";
					num_print--;
				}
				else {
					cout << setw(3) << arr[num_print];
					num_print--;
				}
				for (int i1 = 0; i1 < otsp_mezhdy-2; i1++)
					cout << " ";
			}
			cout << endl;

			for (int i1 = 0; i1 < first_otsp - 2; i1++)
				cout << " ";
			for (int j = 0; j < kol_level; j++) {
				cout << "  |";
				for (int i1 = 0; i1 < otsp_mezhdy - 2; i1++)
					cout << " ";
			}
			cout << endl;

			kol_level/=3;

			for (int i1 = 0; i1 < first_otsp; i1++)
				cout << " ";
			for (int j = 0; j < kol_level; j++) {
				for (int j1 = 0; j1 < palki; j1++)
					cout << "-";
				for (int i1 = 0; i1 < kol_level; i1++)
					cout << " ";
			}
			cout << endl;
			otsp_mezhdy = (otsp_mezhdy * 3) + 2;
			first_otsp *= 3;
			palki = (palki * 3) - 2;

			for (int i1 = 0; i1 < first_otsp - 2; i1++)
				cout << " ";
			for (int j = 0; j < kol_level; j++) {
				cout << "  |";
				for (int i1 = 0; i1 < otsp_mezhdy - 2; i1++)
					cout << " ";
			}
			cout << endl;
		}
	}
};

int main() {
	setlocale(LC_ALL, "Russian");
	int N;
	cout << "Введите кол-во элементов кучи" << endl;
	cin >> N;
	D_heap heap(N);
	int x;
	cout << "Вводите элементы кучи" << endl;
	for (int i = 0; i < N; i++) {
		cin >> x;
		heap.add(x);
	}
	heap.print();
	cout << "Удалили " << heap.delete_min() << endl;
	heap.print();
	system("pause");
	return 0;
}