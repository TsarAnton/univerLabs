#include <iostream>
#include <string>
#include <chrono>
#include <ctime>
using namespace std;
struct Spisok {
	int n;
	string* arr;
	int* hash;
};
class Hash_table {


	Spisok* array;
	int N;

	int hash_function(string name) {
		string new_str;
		int result = 0;
		for (int i = 0; i < name.length(); i++) {
			char buff = name[i];
			int code = (int)buff;
			string buff_str = to_string(code);
			new_str.insert(new_str.length(), buff_str);
		}
		for (int i = 0; i < new_str.length(); i++) {
			char buff = new_str[i];
			int number = buff - '0';
			int dd = number * number;
			result += dd;
		}
		return result;
	};
public:
	Hash_table() {
		N = 1;
		array = new Spisok[1];
		array[0].n = 0;
	}
	void size_bigger_array(int hash) {
		Spisok* buff = new Spisok[hash + 1];
		for (int i = 0; i < hash + 1; i++) {
			buff[i].n = 0;
		}
		for (int i = 0; i < N; i++) {
			buff[i] = array[i];
			if (array[i].n > 0) {
				buff[i].arr = new string[array[i].n];
				buff[i].hash = new int[array[i].n];
				for (int j = 0; j < array[i].n; j++) {
					buff[i].arr[j] = array[i].arr[j];
					buff[i].hash[j] = array[i].hash[j];
				}
			}
		}
		array = buff;
		N = hash + 1;
	}
	void size_bigger_arr(int j) {
		string* buff_arr = new string[array[j].n + 1];
		int* buff_hash = new int[array[j].n + 1];
		for (int i = 0; i < array[j].n; i++) {
			buff_arr[i] = array[j].arr[i];
			buff_hash[i] = array[j].hash[i];
		}
		array[j].arr = buff_arr;
		array[j].hash = buff_hash;
		array[j].n += 1;
	}
	void push(string name, int zn) {
		int pos = hash_function(name);
		if (pos > N) {
			this->size_bigger_array(pos);
		}
		if (array[pos].n > 0) {
			this->size_bigger_arr(pos);
			array[pos].arr[array[pos].n - 1] = name;
			array[pos].hash[array[pos].n - 1] = zn;
		}
		else {
			array[pos].n = 1;
			array[pos].arr = new string[1];
			array[pos].hash = new int[1];
			array[pos].arr[0] = name;
			array[pos].hash[0] = zn;
		}
	}
	int find(string name) {
		int hash = hash_function(name);
		if (hash > N || array[hash].n == 0) {
			cout << "Нет элемента" << endl;
		}
		else{
			bool found = false;
			for (int i = 0; i < array[hash].n && !found; i++)
				if (name == array[hash].arr[i]) {
					found = true;
					return array[hash].hash[i];
				}
			if (!found)
				cout << "Нет элемента" << endl;
		}
	}
	void print() {
		for (int i = 0; i < N; i++) {
			cout << i << ".   ";
			if (array[i].n != 0) {
				for (int j = 0; j < array[i].n; j++)
					cout << array[i].arr[j] << "  " << array[i].hash[j] << "       ";
			}
			cout << endl;
		}
	}
};

int main() {
	setlocale(LC_ALL, "Russian");
	int N, number;
	Hash_table table;
	string str;
	cout << "Введите кол-во элементов" << endl;
	cin >> N;
	cout << "Вводите ключ и значение" << endl;
	for (int i = 0; i < N; i++) {
		cin >> str;
		cin >> number;
		table.push(str, number);
	}
	cout << endl << "Хеш-таблица" << endl;
	table.print();
	cout << "Введите ключ для поиска" << endl;
	cin >> str;
	int f = table.find(str);
	if (f)
		cout << "Найден " << f << endl;
	system("pause");
	return 0;
}