#ifndef Funk_h
#define Funk_h

void cop(int arr_v[], int arr_iz[], int N);
void print(int arr[], int N);
void zap_upor(int arr[], int N);
void zap_unupor(int arr[], int N);
void zap_random(int arr[], int N);


void selection_sort(int arr[], int N);
void otchet_selection_sort(int arr_upor[], int arr_unupor[], int arr_random[],int arr[], int N);

void insert_sort(int arr[], int N);
void otchet_insert_sort(int arr_upor[], int arr_unupor[], int arr_random[], int arr[], int N);

void obmen_sort(int arr[], int N);
void otchet_obmen_sort(int arr_upor[], int arr_unupor[], int arr_random[], int arr[], int N);

void quick_sort(int arr[], int left, int right,int arra[]);
void otchet_quick_sort(int arr_upor[], int arr_unupor[], int arr_random[], int arr[], int left,int right);

void swapValue(int* a, int* b, int arra[]);
void InsertionSort_for_InsortSort(int arr[], int* begin, int* end, int arra[]);
int* Delenie(int arr[], int low, int high, int arra[]);
int* SeredinaOfThree(int* a, int* b, int* c, int arra[]);
void heapify(int arr[], int n, int i, int arra[]);
void heapSort(int arr[], int n, int arra[]);
void Introsort_one(int arr[], int* begin, int* end, int glubina, int arra[]);
void Introsort(int arr[], int* begin, int* end, int arra[]);
void otchet_introsort(int arr_upor[], int arr_unupor[], int arr_random[], int arr[], int left, int right);
#endif
