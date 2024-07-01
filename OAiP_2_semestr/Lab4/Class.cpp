#include "Class.h"
#include <string>

int find_NOD(int n1, int n2) {//НОД
	
	//если число 1 или число 2 отрицательные, делаем их положительными
	if (n1 < 0)
		n1 *= -1;
	if (n2 < 0)
		n2 *= -1;

	//заносим в n1 большее из двух чисел n1 и n2
	if (n1 < n2) {
		int buff = n1;
		n1 = n2;
		n2 = buff;
	}

	int ost = n1 % n2;//находим остаток

	//Алгоритм Евклида (Можешь в инете почитать)
	while (ost != 0) {
		n1 = n2;
		n2 = ost;
		ost = n1 % n2;
	}
	return n2;
}

Drob::Drob() {//конструктор по умолчанию
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
istream& operator >>(istream& fin, Drob& num) {//ввод дрлби
	string get, buff, buff1;
	getline(fin, get);//вводим строку вида "a/b" (например "5/4" или "3/2"_
	bool kuda = true;//если true, то заносим в числитель, иначе в знаменатель
	
	for (int i = 0; i < get.length(); i++) {
		char b = get[i];//берем символ из введенной строки
		if (b != '/') {//если попался символ '/' , то начинаем записывать в знаменатель
			if (kuda)//если записываем в числитель
				buff.push_back(b);//заносим символ b в конец строки buff
			else//иначе (если записываем в знаменатель)
				buff1.push_back(b);//заносим символ в конец строки buff1
		}
		else
			kuda = false;
	}

	//ch - введенный числитель, zn - знаменатель
	int ch = stoi(buff), zn = stoi(buff1);//stoi - превращает строку в число
	try {//обработка исключительной ситуации
		if (find_NOD(ch, zn) != 1)//если НОД числителя и знаменателя не равен 1, то дробь сократимая 
			throw 1;//если дробь сократима, возвращаем ошибку 1
		//если дробь несократима
		num.set_chisl(ch);//записываем в числитель вводимой дроби ch
		num.set_znam(zn);//записываем в знаменатель вводимой дроби zn
		return fin;
	}
	catch (int e){
		if (e == 1) {//если словили ошибку 1
			cout << "Ошибка ввода: дробь сократима" << endl;
		}
	}
}
ostream& operator <<(ostream& fout, Drob& num) {//вывод
	fout << num.chisl << "/" << num.znam;
	return fout;
}
Drob operator /( Drob& num,Drob& num1) {//деление
	Drob ret;//возвращаемая дробь
	ret.set_chisl(num.get_chisl() * num1.get_znam());//числитель возвращаемой дроби = числитель дроби 1 * знаменатель дроби 2
	ret.set_znam(num.get_znam() * num1.get_chisl());//знаменатель возвращаемой дроби = знаменатель дроби 1 * числитель дроби 2
	int nod = find_NOD(ret.get_chisl(), ret.get_znam());//находим НОД числителя и знаменателя
	if (nod != 1) {//если дробь можно сократить
		//сокращаем
		ret.set_chisl(ret.get_chisl() / nod);
		ret.set_znam(ret.get_znam() / nod);
	}
	return ret;
}
Drob operator -(Drob& num, Drob& num1) {//отнимание
	Drob ret;//возвращаемая дробь
	ret.set_znam(num.get_znam() * num1.get_znam());//находим общий знаменатель двух дробей и заносим его в знаменатель возвращаемой дроби
	ret.set_chisl(num.get_chisl() * num1.get_znam() - num1.get_chisl() * num.get_znam());//считаем числитель возвращаемой дроби
	//сокращаем
	int nod = find_NOD(ret.get_chisl(), ret.get_znam());
	if (nod != 1) {
		ret.set_chisl(ret.get_chisl() / nod);
		ret.set_znam(ret.get_znam() / nod);
	}
	return ret;
}