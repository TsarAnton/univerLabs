#include <iostream>
#include <windows.h>
#include <iomanip>
using namespace std;

struct Tree {
	double d;
	Tree* left;
	Tree* right;
	Tree* futher;
};

void first(Tree*& top, double d) {
	top = new Tree;
	top->d = d;
	top->left = top->right = top->futher = NULL;
}

Tree* search_for_add(Tree* top, double d) {
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

Tree* search(Tree* top, double d) {
	Tree* pv = top, * ppv = top;
	bool cont = true;
	while (pv && cont) {
		if (pv->d == d) {
			ppv = pv;
			cont = false;
		}
		else if (d < pv->d)
			pv = pv->left;
		else
			pv = pv->right;
	}
	return ppv;
}

void add(Tree* top, double d) {
	Tree* ppv, * pnew = new Tree;
	pnew->d = d;
	pnew->left = NULL;
	pnew->right = NULL;
	ppv = search_for_add(top, d);
	pnew->futher = ppv;
	if (d < ppv->d)
		ppv->left = pnew;
	else
		ppv->right = pnew;
}

void delete_el(Tree*& top, double d, string kak) {
	Tree* ppv = search(top, d);
	if (ppv->left == NULL && ppv->right == NULL) {
		if (d < (ppv->futher)->d)
			(ppv->futher)->left = NULL;
		else
			(ppv->futher)->right = NULL;
		delete ppv;
	}
	else if (ppv->right == NULL) {
		if (d < (ppv->futher)->d) {
			(ppv->futher)->left = ppv->left;
		}
		else {
			(ppv->futher)->right = ppv->left;
		}
		delete ppv;
	}
	else if (ppv->left == NULL) {
		if (d < (ppv->futher)->d)
			(ppv->futher)->left = ppv->right;
		else
			(ppv->futher)->right = ppv->right;
		delete ppv;
	}
	else {
		if (kak == "right") {
			Tree* zamena = ppv->right;
			bool con = true, is_top = false;
			if (ppv->futher == NULL)
				is_top = true;

			while (con) {
				if (zamena->left != NULL)
					zamena = zamena->left;
				else
					con = false;
			}

			if (zamena->right == NULL) {
				if ((zamena->d < (zamena->futher)->d))
					(zamena->futher)->left = NULL;
				else
					(zamena->futher)->right = NULL;
			}
			else {
				ppv->right = zamena->right;
			}

			if (!is_top) {
				if (d < (ppv->futher)->d)
					(ppv->futher)->left = zamena;
				else
					(ppv->futher)->right = zamena;
				zamena->futher = ppv->futher;
			}
			else {
				zamena->futher = NULL;
				top = zamena;
			}

			zamena->left = ppv->left;
			zamena->right = ppv->right;
			delete ppv;
		}
		else {
			Tree* zamena = ppv->left;
			bool con = true, is_top = false;
			if (ppv->futher == NULL)
				is_top = true;
			while (con) {
				if (zamena->right != NULL)
					zamena = zamena->right;
				else
					con = false;
			}

			if (zamena->left == NULL) {
				if ((zamena->d < (zamena->futher)->d))
					(zamena->futher)->left = NULL;
				else
					(zamena->futher)->right = NULL;
			}
			else {
				ppv->left = zamena->left;
			}

			if (!is_top) {
				if (d < (ppv->futher)->d)
					(ppv->futher)->left = zamena;
				else
					(ppv->futher)->right = zamena;
				zamena->futher = ppv->futher;
			}
			else {
				zamena->futher = NULL;
				top = zamena;
			}

			zamena->left = ppv->left;
			zamena->right = ppv->right;
			delete ppv;
		}
	}
}
int* make_longer(int* arr, int N) {
	int* new_arr = new int[N + 1];
	for (int i = 0; i < N; i++)
		new_arr[i] = arr[i];
	new_arr[N] = 0;
	return new_arr;
}
int* make_smaller(int* arr, int N) {
	int* new_arr = new int[N - 1];
	for (int i = 0; i < N - 1; i++)
		new_arr[i] = arr[i];
	return new_arr;
}

void sim_obhod(Tree* top) {
	if (top) {
		sim_obhod(top->left);
		cout << top->d << " ";
		sim_obhod(top->right);
	}
}
void prym_obhod(Tree* top) {
	if (top) {
		cout << top->d << " ";
		prym_obhod(top->left);
		prym_obhod(top->right);
	}
}
void obr_obhod(Tree* top) {
	if (top) {
		obr_obhod(top->left);
		obr_obhod(top->right);
		cout << top -> d << " ";
	}
}

int deep(Tree* top, int d) {
	Tree* pv = top;
	bool con = true;
	int dep = 0;
	while (pv && con) {
		if (pv->d == d)
			con = false;
		if (con) {
			if (d < pv->d) {
				pv = pv->left;
				dep++;
			}
			else {
				pv = pv->right;
				dep++;
			}
		}
	}
	return dep;
}
int high(Tree* top, int d) {
	Tree*ppv = search(top, d);
	if (ppv->left == NULL && ppv->right == NULL)
		return 0;
	int left = 0;
	if (ppv->left != NULL) {
		left = high(ppv->left, d);
	}
	int right = 0;
	if (ppv->right != NULL) {
		right = high(ppv->right, d);
	}
	return (max(left, right) + 1);
}

int level(Tree* top, int d) {
	int high_tree = high(top, top->d);
	int glub = deep(top, d);
	return high_tree - glub;
}

void arr_max_glub(Tree* real_top, Tree* top, int* arr_ret, int& N, int max_high) {
	if (top) {
		if (deep(real_top, top->d) == max_high) {
			arr_ret[N] = top->d;
			N++;
		}
		arr_max_glub(real_top, top->left, arr_ret, N, max_high);
		arr_max_glub(real_top, top->right, arr_ret, N, max_high);
	}
}

void search_arr_of_max(Tree* top, double d, int* arr_max) {
	Tree* pv = top;
	int N = 0;
	bool cont = true;
	while (pv && cont) {
		if (pv->d == d) {
			arr_max[N] = pv->d;
			N++;
			cont = false;
		}
		else if (d < pv->d) {
			arr_max[N] = pv->d;
			N++;
			pv = pv->left;
		}
		else {
			arr_max[N] = pv->d;
			N++;
			pv = pv->right;
		}
	}
}
int max_el_sec(int* arr, int N) {
	int max = 0;
	for (int i = 0; i < N; i++) {
		if (arr[i] > max)
			max = arr[i];
	}
	int max_sec = 0;
	for (int i = 0; i < N; i++) {
		if (arr[i] > max_sec && arr[i] < max)
			max_sec = arr[i];
	}
	return max_sec;
}
int result(Tree* top) {
	int N = high(top, top->d) + 1;
	if (N == 1)
		return top->d;
	int* arr_max_glub1 = new int[1];
	int N_arr_max_glub1 = 0;
	arr_max_glub(top, top, arr_max_glub1, N_arr_max_glub1, high(top, top->d));
	int res = 0;
	int* arr_max = new int[N];
	cout << "Максимальные пути в дереве:" << endl;
	for (int i = 0; i < N_arr_max_glub1; i++) {
		search_arr_of_max(top, arr_max_glub1[i], arr_max);
		for (int j = 0; j < N; j++)
			cout << arr_max[j] << " ";
		cout << endl;
		if (max_el_sec(arr_max, N) > res)
			res = max_el_sec(arr_max, N);
	}
	return res;
}
void print(Tree* a, int level = 0, int mode = 0, bool to_print = true, int* vlozhenost = new int[0],int N = 1) {
	if (to_print) {
		HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
		for (int i = 0; i < N;i++) {
			if (vlozhenost[i] == 0)
				cout << "|";
			else {
				for (int i1 = 0; i1 < vlozhenost[i]; i1++)
					cout << " ";
			}
		}
		if (mode == 2) {
			cout << "L";
		}
		if (mode == 1 || mode == 0) {
			cout << "L";
		}
		if (mode == 2) {
			SetConsoleTextAttribute(hConsole, (WORD)((0 << 4) | 5));
			cout << setw(2) << "L:";
			SetConsoleTextAttribute(hConsole, (WORD)((0 << 4) | 7));
		}
		if (mode == 1) { //если 1 то значение в правом поддреве
			SetConsoleTextAttribute(hConsole, (WORD)((0 << 4) | 2));
			cout << setw(2) << "R:";
			SetConsoleTextAttribute(hConsole, (WORD)((0 << 4) | 7));
		}
		if (a == NULL) {
			SetConsoleTextAttribute(hConsole, (WORD)((0 << 4) | 4));
			cout << "-" << endl;
			SetConsoleTextAttribute(hConsole, (WORD)((0 << 4) | 7));
			if (mode == 1) {
				vlozhenost = make_smaller(vlozhenost, N);
				N--;
			}
		}
		else {
			cout << setw(1) << a->d << endl;
			if (a->left == NULL && a->right == NULL)
				to_print = false;
			else
				to_print = true;
		}

		vlozhenost = make_longer(vlozhenost, N);
		N++;
		if (a != NULL) {
			if ((mode == 1 && (a->left != NULL || a->right != NULL))|| mode == 0)
				vlozhenost[N - 1] += 1;
			if (mode == 1 && a->left == NULL && a->right == NULL) {
				vlozhenost = make_smaller(vlozhenost, N);
				N--;
			}
			print(a->left, level + 1, 2, to_print,vlozhenost,N);
			print(a->right, level + 1, 1, to_print,vlozhenost,N);
		}
	}
}
int main() {
	setlocale(LC_ALL, "");
	int N;
	cout << "Введите кол-во элементов бинарного дерева" << endl;
	cin >> N;

	if (N > 0) {
		Tree* top;
		cout << "Вводите элементы дерева" << endl;
		double x;
		cin >> x;
		first(top, x);
		for (int i = 1; i < N; i++) {
			cin >> x;
			add(top, x);
		}
		cout << endl;
		int* vlozhenost = new int[1];
		vlozhenost[0] = -1;
		print(top,0,0,true,vlozhenost);
		int res = result(top);
		cout << "Второй по величине элемент = " << res << endl;
		delete_el(top, res, "right");
		prym_obhod(top);
		cout << endl;
	}
	system("pause");
	return 0;
}