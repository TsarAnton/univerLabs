#include <iostream>
#include <string>
#include <stdlib.h>
using namespace std;

template<typename T>
class Vector{
public:
	template<typename T>
	struct el{
		T data;
		el<T>* next;
		el<T>* prev;
	};
	el<T>* top;
	el<T>* tail;
	int size;

	Vector(){
		top = tail = nullptr;
		size = 0;
	}
	~Vector(){
		this->clear();
	}
	void clear(){
		while (!empty()){
			this->pop_back();
		}
	}
	void push_back(T a){
		if (top == nullptr && tail == nullptr){
			el<T>* n = new el<T>;
			n->data = a;
			n->next = nullptr;
			n->prev = nullptr;
			top = tail = n;
			size++;
		}
		else{
			el<T>* n = new el<T>;
			n->data = a;
			n->next = nullptr;
			n->prev = tail;
			tail->next = n;
			tail = n;
			size++;
		}
	}
	void pop_back(){
		if (top != nullptr && tail != nullptr){
			if (tail == top){
				delete top;
				top = tail = nullptr;
				size--;
			}
			else{
				el<T>* n = tail->prev;
				n->next = nullptr;
				delete tail;
				tail = n;
				size--;
			}
		}
	}
	T& operator[] (const int index){
		if (index >= size || index < 0){
			exit(0);
		}
		el<T>* temp = new el<T>;
		temp = this->top;
		for (int i = 0; i < size; i++){
			if (i == index){
				return temp->data;
			}
			temp = temp->next;
		}
	}
	void delete_element(const int index){
		if (index >= size || index < 0){
			return;
		}
		if (index == (size - 1)){
			this->pop_back();
			return;
		}

		el<T>* temp = this->top;
		for (int i = 0; i < index; i++){
			if (i == index){
				el<T>* temp1 = temp->prev;
				el<T>* temp2 = temp->next;
				temp1->next = temp2;
				temp2->prev = temp1;
				delete temp;
				size--;
				return;
			}
			temp = temp->next;
		}
	}

	bool empty(){
		return (top == nullptr && tail == nullptr);
	}
};

class Message {
public:
	string text;
	string date;

	Message(string t, string d){
		text = t;
		date = d;
	}
};

template<typename T>
class User {
public:
	string name;
	string date;
	Vector<T> groups;

	void add_group(T gr) {
		groups.push_back(gr);
	}

	void del_group(T u) {
		for (int i = 0; i < groups.size; i++) {
			if (u == groups[i]) {
				groups.delete_element(i);
			}
		}
	}

	User(string n, string d) {
		name = n;
		date = d;
	}
};

class Group {
public:
	string name;
	Vector<User<Group*>*> users;
	Vector<Message*> messages;

	Group(string n){
		name = n;
	}

	void add_user(User<Group*>* u){
		u->add_group(this);
		users.push_back(u);
	}

	void add_massege(Message* m){
		messages.push_back(m);
	}

	void del_user(User<Group*>* u){
		u->del_group(this);
		for (int i = 0; i < users.size; i++){
			if (u == users[i]){
				users.delete_element(i);
			}
		}
	}

	void delete_group(){
		if (this->users.size == 0){
			cout << "Группа: " << this->name << " была уничтожена\n";
			this->messages.clear();
			this->name = "";
		}
	}

};

ostream& operator<<(ostream& out, Group& g){
	out << "Название группы: " << g.name << endl;
	out << "Кол-во пользователей: " << g.users.size << endl;
	for (int i = 0; i < g.users.size; i++){
		out << i << ") " << g.users[i]->date << " : " << g.users[i]->name << endl;
	}
	out << "Сообщения" << endl << "Кол-во сообщений " << g.messages.size << endl;
	for (int i = 0; i < g.messages.size; i++){
		out << g.messages[i]->date << " : " << g.messages[i]->text << endl;
	}


	return out;
}

int main(){

	setlocale(LC_ALL, "Russian");

	Group* gr1 = new Group("Группа 1");
	shared_ptr<Group> g1(gr1);
	

	User<Group*>* us;
	Message* ms;
	string name, date, text;
	int n;
	do{
		cout << "0 - завершение работы" << endl;
		cout << "1 - добавить пользователя в группу" << endl;
		cout << "2 - добавить сообщение в группу" << endl;
		cout << "3 - удалить пользователя из группы" << endl;
		cout << "4 - информация о группе" << endl;
		cout << "Ввод: "; cin >> n;
		system("Cls");
		switch (n)
		{
		case 1:
			if (g1.use_count() == 0) { cout << "Группа была удалена\n"; break; }

			cout << "Имя пользователя: "; cin >> name;
			cout << "Дата регистрации пользователя:"; cin >> date;
			us = new User<Group*>(name, date);
			(*g1).add_user(us);
			system("Cls");
			break;
		case 2:
			if (g1.use_count() == 0) { cout << "Группа была удалена\n"; break; }

			cout << "Дата сообщения:"; cin >> date;
			cout << "Текст сообщения:"; cin >> text;
			ms = new Message(text, date);
			(*g1).add_massege(ms);
			n = 3;
			system("Cls");
			break;
		case 3:
			if (g1.use_count() == 0) { cout << "Группа была удалена\n"; break; }

			cout << "ID пользователя: "; cin >> n;
			g1->del_user(g1->users[n]);
			if (g1->users.size == 0)
			{
				g1->delete_group();
				g1.reset();
			}
			n = 5;
			break;
		case 4:
			system("Cls");
			if (g1.use_count() == 0) { cout << "Группа была удалена\n"; break; }

			cout << (*g1);
			break;
		default:
			break;
		}


	} while (n != 0);
	cout << "Завершение работы\n";
	system("pause");
	return 0;
}