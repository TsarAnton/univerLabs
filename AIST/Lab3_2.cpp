#include <iostream>
#include <iomanip>

using namespace std;
double max_array(double* arr, int N) {         //максимальный элемент массива
	double max = arr[0];
	for (int i = 1; i < N; i++) {
		if (arr[i] > max)
			max = arr[i];
	}
	return max;
}
double min_array(double* arr, int N) {     //минимальный элемент массива
	double min = arr[0];
	for (int i = 1; i < N; i++) {
		if (arr[i] < min)
			min = arr[i];
	}
	return min;
}
void cop_arr(int* kuda, int* otkuda, int n) {    //копирует массив
	for (int i = 0; i < n; i++) {
		kuda[i] = otkuda[i];
	}
}

double* find_x(int* R_arr, int N) {          //ищет массив х
	double* x_arr = new double[N];          //массив х
	double* buff = new double[N - 1];       //буфферный массив
	x_arr[0] = 0;                       //х первого элемента = 0
	for (int i = 1; i < N; i++) {       // просмотр массива радиусов( х первого радиуса = 0) от 1
		int j = 0;
		for (j; j < i; j++)
			buff[j] = x_arr[j] + 2 * sqrt(R_arr[j] * R_arr[i]);    //"упираем" диск в каждый предыдущий и находим каждый х
		x_arr[i] = max_array(buff, j);     //возвращаем самое правое значение
	}
	return x_arr;
}

double max_length(int* r_arr, int N) {      //максимальная длинна массива без оптимального расположения
	double* x_arr = new double[N];        //создаем массив х
	x_arr = find_x(r_arr, N);          
	double* left = new double[N];   //массив для крайней левой позиции каждого диска
	double* right = new double[N];   //массив для крайней правой позиции каждого диска
	for (int i = 0; i < N; i++) {
		left[i] = x_arr[i] - r_arr[i];
		right[i] = x_arr[i] + r_arr[i];
	}
	return max_array(right, N) - min_array(left, N);   //максимальная правая позиция - максимальная левая
}
double find_min_len(int* arr_zmen, int voshedshy, int number_of_sravn) {// заносим в массив радиус вошедшего и находим его оптимальное расположение и минимальную длинну при этом
	if (number_of_sravn == 2) {         //если вошел 2-й элемент
		arr_zmen[1] = voshedshy;
	//	cout << "Добавлен "<<voshedshy<<"    Длинна = " << max_length(arr_zmen, 2) << endl;
		return max_length(arr_zmen, 2);
	}

	int* arr_zmen_buff = new int[number_of_sravn];      //создаем копию массива    
	cop_arr(arr_zmen_buff, arr_zmen, number_of_sravn);
	arr_zmen_buff[number_of_sravn - 1] = arr_zmen[number_of_sravn - 1] = voshedshy;  //на последнюю позицию ставим вошедшего

	double min_len = max_length(arr_zmen_buff, number_of_sravn), buff ,buff1;    //минимальная длинна 
	for (int i = number_of_sravn - 1; i >= 0; i--) {     //проход от последнего элемента(вошедший) до первого
		
		buff = arr_zmen_buff[number_of_sravn - 1];               
		arr_zmen_buff[number_of_sravn - 1] = arr_zmen[i];      //ставим вошедшего на каждую позицию
		arr_zmen_buff[i] = buff;     

		buff1 = max_length(arr_zmen_buff, number_of_sravn);   //находи длинну когда вошедший на данной позиции
	//	cout << "Добавлен " << voshedshy << "     Длинна = " << buff1 << endl;
		if (buff1 < min_len) {     //если длинна на данной позиции меньше чем на предудущей
			
			buff = arr_zmen[number_of_sravn - 1];
			arr_zmen[number_of_sravn - 1] = arr_zmen[i];    //меняем позиции в реальном массиве
			arr_zmen[i] = buff;

			min_len = buff1;   //присваиваем минимальную длинну
		}
	}
	return min_len;
}
double final_min_length(int* r_arr, int N) {   //находим минимальную длинну при оптимальном расположении
	if (N == 1) {
		cout << "Расположение радиусов в коробке: " << r_arr[0] << endl;
		return r_arr[0];
	}
	int* arr_zmen = new int[N];       //массив для хранения оптимального расположения
	arr_zmen[0] = r_arr[0];         //заносим первый радиус
	double real_len;
	for (int i = 1, number_of_sravn = 2; i < N; i++,number_of_sravn++) {
			real_len = find_min_len(arr_zmen, r_arr[i], number_of_sravn);   //заносим радиус, ищем его оптимальное рассположение и минимальную длинну коробки
	}
	cout << "Расположение радиусов в коробке: ";   //выводим оптимальное рассположение
	for (int i = 0; i < N; i++)
		cout << arr_zmen[i] << " ";
	cout << endl;
	return real_len;

}

int main() {
	setlocale(LC_ALL, "Russian");

	int N;
	cout << "Введите кол-во дисков" << endl;
	cin >> N;
	int* R_array = new int[N];
	cout << "Вводите радиусы дисков" << endl;
	for (int i = 0; i < N; i++) {
		cin >> R_array[i];
	}
	cout << "Введенные радиусы: ";
	for (int i = 0; i < N; i++)
		cout << R_array[i] << " ";
	cout << endl;

	int sravn = 2;

	cout << "Минимальная длинна коробки = " << final_min_length(R_array,N) << endl;
	delete[] R_array;


	system("pause");
	return 0;
}