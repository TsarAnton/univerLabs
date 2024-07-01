#include <iostream>
#include <ctime>
#include "Funk.h"
using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");

	int arr_10_upor[10];
	zap_upor(arr_10_upor, 10);
	int arr_10_unupor[10];
	zap_unupor(arr_10_unupor, 10);
	int arr_10_random[10];
	zap_random(arr_10_random, 10);

	int arr_100_upor[100];
	zap_upor(arr_100_upor, 100);
	int arr_100_unupor[100];
	zap_unupor(arr_100_unupor, 100);
	int arr_100_random[100];
	zap_random(arr_100_random, 100);

	int arr_1000_upor[1000];
	zap_upor(arr_1000_upor, 1000);
	int arr_1000_unupor[1000];
	zap_unupor(arr_1000_unupor, 1000);
	int arr_1000_random[1000];
	zap_random(arr_1000_random, 1000);

	int arr_10000_upor[10000];
	zap_upor(arr_10000_upor, 10000);
	int arr_10000_unupor[10000];
	zap_unupor(arr_10000_unupor, 10000);
	int arr_10000_random[10000];
	zap_random(arr_10000_random, 10000);

	int arr_10[10],arr_100[100],arr_1000[1000],arr_10000[10000];

	int arra[2];
	print(arr_100_unupor, 100);
	Introsort(arr_100_unupor, arr_100_unupor, arr_100_unupor + 99, arra);
	print(arr_100_unupor, 100);
	/*cout << "Сортировка выбором" << endl;
	otchet_selection_sort(arr_10_upor, arr_10_unupor, arr_10_random, arr_10, 10);
	cout << endl;
	otchet_selection_sort(arr_100_upor, arr_100_unupor, arr_100_random, arr_100, 100);
	cout << endl; 
	otchet_selection_sort(arr_1000_upor, arr_1000_unupor, arr_1000_random, arr_1000, 1000);
	cout << endl; 
	otchet_selection_sort(arr_10000_upor, arr_10000_unupor, arr_10000_random, arr_10000, 10000);
	cout << endl << endl;
	
	cout << "Сортировка вставками" << endl;
	otchet_insert_sort(arr_10_upor, arr_10_unupor, arr_10_random, arr_10, 10);
	cout << endl;
	otchet_insert_sort(arr_100_upor, arr_100_unupor, arr_100_random, arr_100, 100);
	cout << endl;
	otchet_insert_sort(arr_1000_upor, arr_1000_unupor, arr_1000_random, arr_1000, 1000);
	cout << endl;
	otchet_insert_sort(arr_10000_upor, arr_10000_unupor, arr_10000_random, arr_10000, 10000);
	cout << endl << endl;
	
	cout << "Сортировка обменом" << endl;
	otchet_obmen_sort(arr_10_upor, arr_10_unupor, arr_10_random, arr_10, 10);
	cout << endl;
	otchet_obmen_sort(arr_100_upor, arr_100_unupor, arr_100_random, arr_100, 100);
	cout << endl;
	otchet_obmen_sort(arr_1000_upor, arr_1000_unupor, arr_1000_random, arr_1000, 1000);
	cout << endl;
	otchet_obmen_sort(arr_10000_upor, arr_10000_unupor, arr_10000_random, arr_10000, 10000);
	cout << endl << endl;
	
	cout << "Быстрая сортировка" << endl;
	otchet_quick_sort(arr_10_upor, arr_10_unupor, arr_10_random, arr_10, 0 , 9);
	cout << endl;
	otchet_quick_sort(arr_100_upor, arr_100_unupor, arr_100_random, arr_100, 0, 99);
	cout << endl;
	otchet_quick_sort(arr_1000_upor, arr_1000_unupor, arr_1000_random, arr_1000, 0, 999);
	cout << endl;
	otchet_quick_sort(arr_10000_upor, arr_10000_unupor, arr_10000_random, arr_10000, 0, 9999);
	cout << endl << endl;

	cout << "Интроспективная сортировка" << endl;
	otchet_introsort(arr_10_upor, arr_10_unupor, arr_10_random, arr_10, 0, 9);
	cout << endl;
	otchet_introsort(arr_100_upor, arr_100_unupor, arr_100_random, arr_100, 0, 99);
	cout << endl;
	otchet_introsort(arr_1000_upor, arr_1000_unupor, arr_1000_random, arr_1000, 0, 999);
	cout << endl;
	otchet_introsort(arr_10000_upor, arr_10000_unupor, arr_10000_random, arr_10000, 0, 9999);
	cout << endl << endl;*/

	/*cout << "ПРОВЕРКА СОРТИРОВОК" << endl;
	cout << "Сортировка выбором" << endl;
	print(arr_100_unupor, 100);
	selection_sort(arr_100_unupor, 100);
	print(arr_100_unupor, 100);
    
	   
	cout << "Сортировка вставками" << endl;
	print(arr_100_unupor, 100);
	insert_sort(arr_100_unupor, 100);
	print(arr_100_unupor, 100);
    
	zap_unupor(arr_100_unupor, 100);
    
	cout << "Сортировка обменом" << endl;
	print(arr_100_unupor, 100);
	obmen_sort(arr_100_unupor, 100);
	print(arr_100_unupor, 100);
    
	zap_unupor(arr_100_unupor, 100);
	int arra[] = { 0,0 };
    
	cout << "Быстрая сортировка" << endl;
	print(arr_100_unupor, 100);
	quick_sort(arr_100_unupor, 0, 99, arra);
	print(arr_100_unupor, 100);
    
	zap_unupor(arr_100_unupor, 100);
    
	cout << "Интроспективная сортировка" << endl;
	print(arr_100_unupor, 100);
	Introsort(arr_100_unupor, arr_100_unupor,arr_100_unupor + 99,arra);
	print(arr_100_unupor, 100);*/
	system("pause");
	return 0;
}