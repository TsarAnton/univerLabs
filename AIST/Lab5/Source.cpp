#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <chrono>
using namespace std;

void zap_upor(int arr[], int N) {
	for (int i = 0; i < N; i++)
		arr[i] = i + 1;
}

void zap_unupor(int arr[], int N) {
	for (int i = N - 1, j = 0; i >= 0; i--, j++)
		arr[j] = i + 1;
}

void zap_rand(int arr[], int N) {
	for (int i = 0; i < N; i++)
		arr[i] = rand() % 100 + 1;
}

void cop_arr(int* v, int* iz, int N) {
	for (int i = 0; i < N; i++)
		v[i] = iz[i];
}

bool is_file_empty(string name) {
	ifstream file;
	file.open(name);
	bool result =  file.peek() == EOF;
	file.close();
	return result;
}

int file_size(string name) {
	ifstream file_first;
	file_first.open(name);
	bool pend1 = true;
	int y, N = 0;
	for (int i = 0; pend1; i++) {
		file_first >> y;
		if (!file_first.eof())
			N++;
		else
			pend1 = false;
	}
	return N;
}

void print_file(int arr[], int N,string name) {
	ofstream fout;
	fout.open(name);
	for (int i = 0; i < N; i++)
		fout << arr[i] << " ";
	fout.close();
}

void clear_file(string name) {
	ofstream fout;
	fout.open(name, ios::trunc);
	fout.close();
}

void clear_files() {
	clear_file("File_10_upor.txt");
	clear_file("File_10_unupor.txt");
	clear_file("File_10_rand.txt");
	clear_file("File_500_upor.txt");
	clear_file("File_500_unupor.txt");
	clear_file("File_500_rand.txt");
	clear_file("File_10000_upor.txt");
	clear_file("File_10000_unupor.txt");
	clear_file("File_10000_rand.txt");
	clear_file("buff_1.txt");
	clear_file("buff_2.txt");
}

void zap_files() {
	int arr_10[10];
	int arr_500[500];
	int arr_10000[10000];
	zap_upor(arr_10, 10);
	print_file(arr_10, 10, "File_10_upor.txt");
	zap_unupor(arr_10, 10);
	print_file(arr_10, 10, "File_10_unupor.txt");
	zap_rand(arr_10, 10);
	print_file(arr_10, 10, "File_10_rand.txt");

	zap_upor(arr_500, 500);
	print_file(arr_500, 500, "File_500_upor.txt");
	zap_unupor(arr_500, 500);
	print_file(arr_500, 500, "File_500_unupor.txt");
	zap_rand(arr_500, 500);
	print_file(arr_500, 500, "File_500_rand.txt");

	zap_upor(arr_10000, 10000);
	print_file(arr_10000, 10000, "File_10000_upor.txt");
	zap_unupor(arr_10000, 10000);
	print_file(arr_10000, 10000, "File_10000_unupor.txt");
	zap_rand(arr_10000, 10000);
	print_file(arr_10000, 10000, "File_10000_rand.txt");
}
int* fibonach(int N) {
	int x1 = 0, x2 = 1,x3 = 1 ,buff1,buff2;
	while (x3 < N) {
		x1 = x2;
		buff2 = x2;
		x2 = x3;
		x3 += buff2;
	}
	int* arr = new int[3];
	arr[0] = x1;
	arr[1] = x2;
	arr[2] = x3;
	return arr;
}

void first_rasp(string name,int N) {
	
	ifstream fin;
	fin.open(name);
	int* arr = new int[N];
	for (int i = 0; i < N; i++) {
		fin >> arr[i];
	}
	fin.close();
	ofstream buff1, buff2;
	buff1.open("buff_1.txt",ios::app);
	buff2.open("buff_2.txt",ios::app);

	int* arr_rasp = fibonach(N);

	for (int i = 0; i < arr_rasp[2] - N; i++) {
		buff1 << 0 << " ";
	}
	for (int i = 0; i < arr_rasp[1] - arr_rasp[2] + N; i++) {
		buff1 << arr[i] << " ";
	}
	for (int i = arr_rasp[1] - arr_rasp[2] + N; i < N; i++) {
		buff2 << arr[i] << " ";
	}
	buff2.close();
	buff1.close();
	clear_file(name);
}

int* create_arr(int N, string name) {
	ifstream fin;
	fin.open(name);
	int x;

	int* arr_rasp = fibonach(N);
	int* arr = new int[arr_rasp[1]];
	for (int i = 0; i < arr_rasp[1]; i++) {
		arr[i] = 0;
	}
	bool pend = true;
	for (int i = 0; i < arr_rasp[1] && pend; i++) {
		fin >> x;
		if (!fin.eof()) {
			if (x == 0)
				arr[i] = -1;
			else
				arr[i] = 1;
		}
		else
			pend = false;
	}
	return arr;
	fin.close();
}
int length(int N) {
	int* arr = fibonach(N);
	return arr[1];
}
void make_small(ifstream& in, string name, int N) {
	int* arr_buff = new int[length(N)];
	int count = 0;
	bool pend = true;
	int x1;
	for (int i = 0; pend; i++) {
		in >> x1;
		if (!in.eof()) {
			arr_buff[i] = x1;
			count++;
		}
		else
			pend = false;
	}
	in.close();
	ofstream this_out;
	this_out.open(name, ios::app);
	clear_file(name);
	for (int i = 0; i < count; i++) {
		this_out << arr_buff[i] << " ";
	}
	this_out.close();
	in.close();
}

void Fibonachi_sort(string name, int& kol_sravn,int& kol_file) {

	int N = file_size(name);
	if (N != 1) {

		first_rasp(name, N);

		int* arr_buff1 = create_arr(N, "buff_1.txt");
		int* arr_buff2 = create_arr(N, "buff_2.txt");
		int* arr_main = create_arr(N, name);

		int size = length(N), buuf1 = 0, buuf2 = 0, maain = 0, x;
		bool exit = false, main_exit = false, is_full = false;

		while (!is_full) {

			string file_in, file_out_bigger, file_out_smaller;

			int* arr_file_in = new int[size], * arr_bigger = new int[size], * arr_smaller = new int[size];

			bool prove = is_file_empty(name);
			if (prove) {
				file_in = name;
				cop_arr(arr_file_in, arr_main, size);

				file_out_bigger = "buff_1.txt";
				cop_arr(arr_bigger, arr_buff1, size);

				file_out_smaller = "buff_2.txt";
				cop_arr(arr_smaller, arr_buff2, size);
			}
			else {
				prove = is_file_empty("buff_1.txt");
				if (prove) {
					file_in = "buff_1.txt";
					cop_arr(arr_file_in, arr_buff1, size);

					file_out_bigger = "buff_2.txt";
					cop_arr(arr_bigger, arr_buff2, size);

					file_out_smaller = name;
					cop_arr(arr_smaller, arr_main, size);
				}
				else {
					file_in = "buff_2.txt";
					cop_arr(arr_file_in, arr_buff2, size);

					file_out_bigger = name;
					cop_arr(arr_bigger, arr_main, size);

					file_out_smaller = "buff_1.txt";
					cop_arr(arr_smaller, arr_buff1, size);
				}
			}

			ofstream file_sliv;
			ifstream file_bigger, file_smaller;

			file_sliv.open(file_in, ios::app);
			file_bigger.open(file_out_bigger);
			file_smaller.open(file_out_smaller);

			for (int i = 0; i < size && !main_exit; i++) {//ñëèâ
				buuf1 = 0;
				buuf2 = 0;
				int i1 = 0, i2 = 0;

				for (i1; i1 < size && !exit; i1++) {
					if (arr_bigger[i1] != 0) {
						buuf1 = arr_bigger[i1];
						exit = true;
					}
				}

				exit = false;

				for (i2 = 0; i2 < size && !exit; i2++) {
					if (arr_smaller[i2] != 0) {
						buuf2 = arr_smaller[i2];
						exit = true;
					}
				}

				exit = false;

				if (buuf1 == -1 && buuf2 != 0) {
					arr_bigger[i1 - 1] = 0;
					arr_smaller[i2 - 1] = 0;
					arr_file_in[i] = buuf2;

					file_bigger >> x;
					kol_file++;
					file_smaller >> x;
					kol_file++;
					file_sliv << x << " ";
					kol_file++;
				}

				else if (buuf1 != 0 && buuf2 != 0) {
					arr_bigger[i1 - 1] = 0;
					arr_smaller[i2 - 1] = 0;
					arr_file_in[i] = buuf1 + buuf2;

					int* arr_sliv1 = new int[buuf1];
					int* arr_sliv2 = new int[buuf2];

					for (int j = 0; j < buuf1; j++) {
						file_bigger >> arr_sliv1[j];
						kol_file++;
					}

					for (int j = 0; j < buuf2; j++) {
						file_smaller >> arr_sliv2[j];
						kol_file++;
					}

					int j1 = 0, j2 = 0;

					while (j1 != buuf1 || j2 != buuf2) {

						if (j1 == buuf1) {
							file_sliv << arr_sliv2[j2] << " ";
							kol_file++;
							j2++;
						}

						else if (j2 == buuf2) {
							file_sliv << arr_sliv1[j1] << " ";
							kol_file++;
							j1++;
						}

						else {
							int mn = min(arr_sliv1[j1], arr_sliv2[j2]);
							kol_sravn++;
							file_sliv << mn << " ";
							kol_file++;
							if (mn == arr_sliv1[j1])
								j1++;
							else
								j2++;
						}
					}
				}
				else if (buuf1 == 0 && buuf2 == 0)
					main_exit = true;
			}

			if (file_in == name) {
				cop_arr(arr_main, arr_file_in, size);
				cop_arr(arr_buff1, arr_bigger, size);
				cop_arr(arr_buff2, arr_smaller, size);
			}

			else if (file_in == "buff_1.txt") {
				cop_arr(arr_main, arr_smaller, size);
				cop_arr(arr_buff1, arr_file_in, size);
				cop_arr(arr_buff2, arr_bigger, size);
			}

			else {
				cop_arr(arr_main, arr_bigger, size);
				cop_arr(arr_buff1, arr_smaller, size);
				cop_arr(arr_buff2, arr_file_in, size);
			}

			if (arr_buff1[0] == N || arr_buff2[0] == N || arr_main[0] == N)
				is_full = true;
			exit = false;
			main_exit = false;

			clear_file(file_out_smaller);
			file_sliv.close();
			file_smaller.close();
			make_small(file_bigger, file_out_bigger, N);
			file_bigger.close();
		}

		bool buff1_empty = is_file_empty("buff_1.txt");
		if (!buff1_empty) {
			ifstream buf1("buff_1.txt");
			int select;
			ofstream maiin;
			maiin.open(name, ios::app);
			for (int g = 0; g < N; g++) {
				buf1 >> select;
				kol_file++;
				maiin << select << " ";
				kol_file++;
			}
			maiin.close();
			clear_file("buff_1.txt");
			buf1.close();
		}
		else {
			bool buff2_empty = is_file_empty("buff_2.txt");
			if (!buff2_empty) {
				ifstream buf2("buff_2.txt");
				int select;
				ofstream maiin;
				maiin.open(name, ios::app);
				for (int g = 0; g < N; g++) {
					buf2 >> select;
					kol_file++;
					maiin << select << " ";
					kol_file++;
				}
				maiin.close();
				clear_file("buff_2.txt");
				buf2.close();
			}
		}
	}
}
void otchet_for_one(string name,int N) {
	int kol_file = 0, kol_sravn = 0;

	auto start = chrono::steady_clock::now();

	Fibonachi_sort(name, kol_sravn, kol_file);

	auto end = chrono::steady_clock::now();

	auto t = chrono::duration_cast<chrono::nanoseconds>(end - start);

	cout << "    Êîë-âî ýëåìåíòîâ = " << N << endl;
	cout << "    Êîë-âî ñðàâíåíèé = " << kol_sravn << endl;
	cout << "    Êîë-âî îáðàùåíèé ê ôàéëó = " <<kol_file<< endl;
	cout << "    Âðåìÿ ðàáîòû = " << t.count() * 0.000001 << " ìñ" << endl;
	cout << endl;
}
void otchet_Fibonachi_sort() {
	cout << "ÝËÅÌÅÍÒÛ ÓÏÎÐßÄÎ×ÅÍÛ" << endl;
	otchet_for_one("File_10_upor.txt", 10);
	otchet_for_one("File_500_upor.txt", 500);
	otchet_for_one("File_10000_upor.txt", 10000);
	cout << "ÝËÅÌÅÍÒÛ ÍÅ ÓÏÎÐßÄÎ×ÅÍÛ" << endl;
	otchet_for_one("File_10_unupor.txt", 10);
	otchet_for_one("File_500_unupor.txt", 500);
	otchet_for_one("File_10000_unupor.txt", 10000);
	cout << "ÝËÅÌÅÍÒÛ ÑËÓ×ÀÉÍÛ" << endl;
	otchet_for_one("File_10_rand.txt", 10);
	otchet_for_one("File_500_rand.txt", 500);
	otchet_for_one("File_10000_rand.txt", 10000);
}

int main() {
	setlocale(LC_ALL, "Russian");
	char druid;

	zap_files();
	cin >> druid;
	otchet_Fibonachi_sort();
	//int kol1, kol2;
	//Fibonachi_sort("File.txt", kol1, kol2);
	cout << "Î÷èñòèòü ôàéëû?(y/n)" << endl;
	cin >> druid;
	if (druid == 'y') {
		clear_file("File.txt");
		cout << "Ôàéëû î÷èùåíû" << endl;
		clear_files();
	}

	system("pause");
	return 0;
}
