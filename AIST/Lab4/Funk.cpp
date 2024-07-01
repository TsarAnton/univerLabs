#include "Funk.h"
#include <iostream>
#include <chrono>
#include <iomanip>
using namespace std;

void cop(int arr_v[], int arr_iz[], int N) {
	for (int i = 0; i < N; i++)
		arr_v[i] = arr_iz[i];
}
void print(int arr[], int N) {
	for (int i = 0; i < N; i++) {
		cout << arr[i]<<" ";
	}
	cout << endl;
}
void zap_upor(int arr[], int N) {
	for (int i = 0; i < N; i++)
		arr[i] = i;
}
void zap_unupor(int arr[], int N) {
	for (int i = N - 1, k = 0; i >= 0; i--, k++)
		arr[k] = i;
}
void zap_random(int arr[], int N) {
	for (int i = 0; i < N; i++) {
		arr[i] = rand() % 999 + 0;
	}
}



void selection_sort(int arr[], int N) {
	int buff;
	int kol_sravn = 0, kol_obmen = 0;
	for (int i = 0; i < N - 1; i++) {
		int min = i;
		for (int j = i + 1;  j < N; j++) {
			kol_sravn++;
			if (arr[j] < arr[min])
				min = j;
		}

		buff = arr[i];
		arr[i] = arr[min];
		arr[min] = buff;

		kol_obmen++;
	}
	cout << "    Количество сравнений = " << kol_sravn << endl;
	cout << "    Количество обменов = " << kol_obmen << endl;
}

void otchet_selection_sort(int arr_upor[], int arr_unupor[], int arr_random[], int arr[], int N) {
	cop(arr, arr_upor, N);
	cout << "  Элементы упорядочены, количество элементов = " << N << endl;
	auto start = chrono::steady_clock::now();
	selection_sort(arr, N);
	auto end = chrono::steady_clock::now();
	auto t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;

	cop(arr, arr_unupor, N);
	cout << "  Элементы не упорядочены, количество элементов = " << N << endl;
	start = chrono::steady_clock::now();
	selection_sort(arr, N);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;

	cop(arr, arr_random, N);
	cout << "  Элементы случайны, количество элементов = " << N << endl;
	start = chrono::steady_clock::now();
	selection_sort(arr, N);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;
}


void insert_sort(int arr[], int N) {
	int kol_sravn = 0, kol_obmen = 0;
	for (int i = 1; i < N; i++){
		int key = i;
		int temp = arr[key];
		for (int j = i; j > 0; j--){
			kol_sravn++;
			if (temp < arr[j - 1]){
				kol_obmen++;
				arr[j] = arr[j - 1];
				key = j - 1;
			}
		}
		arr[key] = temp;
	}
	cout << "    Количество сравнений = " << kol_sravn << endl;
	cout << "    Количество обменов = " << kol_obmen << endl;
}

void otchet_insert_sort(int arr_upor[], int arr_unupor[], int arr_random[], int arr[], int N) {
	cop(arr, arr_upor, N);
	cout << "  Элементы упорядочены, количество элементов = " << N << endl;
	auto start = chrono::steady_clock::now();
	insert_sort(arr, N);
	auto end = chrono::steady_clock::now();
	auto t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;

	cop(arr, arr_unupor, N);
	cout << "  Элементы не упорядочены, количество элементов = " << N << endl;
	start = chrono::steady_clock::now();
	insert_sort(arr, N);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;

	cop(arr, arr_random, N);
	cout << "  Элементы случайны, количество элементов = " << N << endl;
	start = chrono::steady_clock::now();
	insert_sort(arr, N);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;
}

void obmen_sort(int arr[], int N) {
	int kol_sravn = 0, kol_obmen = 0;
	int buff;
	for (int i = 0; i < N - 1; i++) {
		for (int j = 0; j < N - i - 1; j++) {
			kol_sravn++;
			if (arr[j] > arr[j + 1]) {
				kol_obmen++;
				buff = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = buff;
			}
		}
	}
	cout << "    Количество сравнений = " << kol_sravn << endl;
	cout << "    Количество обменов = " << kol_obmen << endl;
}

void otchet_obmen_sort(int arr_upor[], int arr_unupor[], int arr_random[], int arr[], int N) {
	cop(arr, arr_upor, N);
	cout << "  Элементы упорядочены, количество элементов = " << N << endl;
	auto start = chrono::steady_clock::now();
	obmen_sort(arr, N);
	auto end = chrono::steady_clock::now();
	auto t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;

	cop(arr, arr_unupor, N);
	cout << "  Элементы не упорядочены, количество элементов = " << N << endl;
	start = chrono::steady_clock::now();
	obmen_sort(arr, N);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;

	cop(arr, arr_random, N);
	cout << "  Элементы случайны, количество элементов = " << N << endl;
	start = chrono::steady_clock::now();
	obmen_sort(arr, N);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;
}


void quick_sort(int arr[], int left, int right,int kol_vsego[]){
	int i = left, j = right;
	int buff, pivot = arr[(left + right) / 2];

	while (i <= j){
		while (arr[i] < pivot) {
			i++;
			kol_vsego[0]++;
		}
		while (arr[j] > pivot) {
			kol_vsego[0]++;
			j--;
		}
		if (i <= j){
			if (arr[i] > arr[j]){
				kol_vsego[1]++;
				buff = arr[i];
				arr[i] = arr[j];
				arr[j] = buff;
			}
			i++;
			j--;
		}
	};

	if (left < j) 
		quick_sort(arr, left, j, kol_vsego);
	if (i < right) 
		quick_sort(arr, i, right, kol_vsego);
}

void otchet_quick_sort(int arr_upor[], int arr_unupor[], int arr_random[], int arr[], int left,int right) {
	int kol_vsego[2] = { 0,0 };
	cop(arr, arr_upor, right + 1);
	cout << "  Элементы упорядочены, количество элементов = " << right + 1 << endl;
	auto start = chrono::steady_clock::now();
	quick_sort(arr, left, right, kol_vsego);
	auto end = chrono::steady_clock::now();
	auto t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Количество сравнений = " << kol_vsego[0] << endl;
	cout << "    Количество обменов = " << kol_vsego[1] << endl;
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;

	kol_vsego[0] = 0;
	kol_vsego[1] = 0;
	cop(arr, arr_unupor, right + 1);
	cout << "  Элементы не упорядочены, количество элементов = " << right + 1 << endl;
	start = chrono::steady_clock::now();
	quick_sort(arr, left, right, kol_vsego);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Количество сравнений = " << kol_vsego[0] << endl;
	cout << "    Количество обменов = " << kol_vsego[1] << endl;
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;
	
	kol_vsego[0] = 0;
	kol_vsego[1] = 0;
	cop(arr, arr_random, right + 1);
	cout << "  Элементы случайны, количество элементов = " << right + 1 << endl;
	start = chrono::steady_clock::now();
	quick_sort(arr, left, right, kol_vsego);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Количество сравнений = " << kol_vsego[0] << endl;
	cout << "    Количество обменов = " << kol_vsego[1] << endl;
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;
}





void swapValue(int* a, int* b, int kol_vsego[]){//меняет 2 значения
	int* temp = a;
	a = b;
	b = temp;
	kol_vsego[1]++;
}

void InsertionSort_for_InsortSort(int arr[], int* begin, int* end, int kol_vsego[]){//сортировка вставками
	int left = begin - arr;
	int right = end - arr;
	for (int i = left + 1; i <= right; i++){
		int key = arr[i];
		int j = i - 1;
			while (j >= left && arr[j] > key){
				kol_vsego[0]++;
				kol_vsego[1]++;
				arr[j + 1] = arr[j];
				j = j - 1;
			}
		arr[j + 1] = key;
	}
}

int* Part(int arr[], int low, int high, int kol_vsego[]){//быстрая
	int pivot = arr[high];  
	//cout << "Пивот = " << pivot << endl;
	int i = (low - 1);//индекс первого
	//cout << "i = " << i << endl;
	int buff;
	for (int j = low; j <= high - 1; j++){
		//cout << "  j = " << j << endl;
		if (arr[j] <= pivot){
			i++;//увеличиваем индек меньшего

			buff = arr[i];
			arr[i] = arr[j];
			arr[j] = buff;

			kol_vsego[1]++;
			//print(arr, 10);
		}
	}
	//cout << "arr[i + 1] = " << arr[i + 1] << endl;
	//cout << "arr[high] = " << arr[high] << endl;
	buff = arr[i + 1];
	arr[i + 1] = arr[high];
	arr[high] = buff;
	kol_vsego[1]++;
	//print(arr, 10);

	return arr + i + 1;
}

int* SeredinaOfThree(int* a, int* b, int* c,int kol_vsego[]){//поиск точки разделения по методу "медианна трех"
	if (*a < *b && *b < *c) {
		kol_vsego[0] += 2;
		return b;
	}
	if (*a < *c && *c <= *b) {
		kol_vsego[0] += 2;
		return c;
	}
	if (*b <= *a && *a < *c) {
		kol_vsego[0] += 2;
		return a;
	}
	if (*b < *c && *c <= *a) {
		kol_vsego[0] += 2;
		return c;
	}
	if (*c <= *a && *a < *b) {
		kol_vsego[0] += 2;
		return a;
	}
	if (*c <= *b && *b <= *a) {
		kol_vsego[0] += 2;
		return b;
	}
}

void heapify(int arr[], int n, int i, int kol_vsego[]){//куча

	int root = i;  //корень        
	int potomok_1 = 2 * i + 1;  //его потомки
	int potomok_2 = 2 * i + 2; 

	int buff;

	if (potomok_1 < n && arr[potomok_1] > arr[root]) {//если левый потомок > корня
		kol_vsego[0]++;
		root = potomok_1;//он становиться корнем
	}

	if (potomok_2 < n && arr[potomok_2] > arr[root]) {
		kol_vsego[0]++;
		root = potomok_2;
	}

	if (root != i){//если корень был заменен

		buff = arr[i];
		arr[i] = arr[root];
		arr[root] = buff;
		kol_vsego[1]++;

		heapify(arr, n, root, kol_vsego);//то же самое, но для нового корня 
	}
}

void heapSort(int arr[], int n,int kol_vsego[]){//пирамидальная

	int buff;

	for (int i = n / 2 - 1; i >= 0; i--)
		heapify(arr, n, i,kol_vsego);

	for (int i = n - 1; i >= 0; i--){

		buff = arr[0];
		arr[0] = arr[i];//выносим 0(там сейчас max) в конец
		arr[i] = buff;

		kol_vsego[1]++;

		heapify(arr, i, 0,kol_vsego);//упорядочиваем кучу
	}
}


void Introsort_one(int arr[], int* begin, int* end, int glubina, int kol_vsego[]){

	int size = end - begin;//кол-во элементов

	if (size < 16){
		InsertionSort_for_InsortSort(arr, begin, end, kol_vsego);
		return;
	}

	if (glubina == 0){//глубина в след. функции
		heapSort(arr, size, kol_vsego);
		return;
	}

	int* pivot = SeredinaOfThree(begin, begin + size / 2, end, kol_vsego);//точка разделения
	//cout << "SredinaOfThree = " << pivot << endl;
	swapValue(pivot, end, kol_vsego);//выносим точку в конец

	int* point = Part(arr, begin - arr, end - arr,kol_vsego);//быстрая сортировка
	Introsort_one(arr, begin, point - 1, glubina - 1,kol_vsego);
	Introsort_one(arr, point + 1, end, glubina - 1,kol_vsego);
}

void Introsort(int arr[], int* begin, int* end, int kol_vsego[]){
	int glubina = 2 * log(end - begin);
	Introsort_one(arr, begin, end, glubina, kol_vsego);
}

void otchet_introsort(int arr_upor[], int arr_unupor[], int arr_random[], int arr[], int left, int right) {
	int kol_vsego[2] = { 0,0 };
	cop(arr, arr_unupor, right + 1);
	cout << "  Элементы упорядочены, количество элементов = " << right + 1 << endl;
	auto start = chrono::steady_clock::now();
	Introsort(arr, arr, arr + right - 1, kol_vsego);
	auto end = chrono::steady_clock::now();
	auto t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Количество сравнений = " << kol_vsego[0] << endl;
	cout << "    Количество обменов = " << kol_vsego[1] << endl;
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;

	kol_vsego[0] = 0;
	kol_vsego[1] = 0;
	cop(arr, arr_unupor, right + 1);
	cout << "  Элементы не упорядочены, количество элементов = " << right + 1 << endl;
	start = chrono::steady_clock::now();
	Introsort(arr, arr, arr + right - 1, kol_vsego);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Количество сравнений = " << kol_vsego[0] << endl;
	cout << "    Количество обменов = " << kol_vsego[1] << endl;
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;

	kol_vsego[0] = 0;
	kol_vsego[1] = 0;
	cop(arr, arr_random, right + 1);
	cout << "  Элементы случайны, количество элементов = " << right + 1 << endl;
	start = chrono::steady_clock::now();
	Introsort(arr, arr, arr + right - 1, kol_vsego);
	end = chrono::steady_clock::now();
	t = chrono::duration_cast<chrono::nanoseconds>(end - start);
	cout << "    Количество сравнений = " << kol_vsego[0] << endl;
	cout << "    Количество обменов = " << kol_vsego[1] << endl;
	cout << "    Время работы = " << t.count() * 0.000001 << " мс" << endl;
}