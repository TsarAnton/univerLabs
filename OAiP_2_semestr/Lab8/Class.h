#include <string>
#include <vector>
#include <iostream>
#ifndef Class_h
#define Class_h
using namespace std;

class Machine {
	int number;
	string mark;
	string model;
	string LastName;      
	string FirstName;   
	string MiddleName;
	int year;     
public:
	string get_mark();
	int get_year();
	friend ostream& operator << (ostream& out, Machine auto1);
	friend istream& operator >> (istream& in, Machine& auto1);
};

class Baza_GAI {
	vector<Machine> machines;
public:
	void sort();
	friend ostream& operator << (ostream& out, Baza_GAI Group);
	friend istream& operator >> (istream& in, Baza_GAI& Group);
	void create_mark(string mark, Baza_GAI& Group);
};

#endif
