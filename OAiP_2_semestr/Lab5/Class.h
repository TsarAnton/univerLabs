#ifndef Class_h
#define Class_h

#include <iostream>
using namespace std;

template<typename Type>
class Figure {
private:
	int N;
	Type* x;
	Type* y;
public:

	Figure(int n){//конструктор
		N = n;
		x = new Type[N];
		y = new Type[N];
	}

	~Figure(){//деструктор
		delete[] x;
		delete[] y;
	}

	Figure(const Figure<Type> &that) {//конструктор копирования
		N = that.N;
		x = new Type[N];
		y = new Type[N];
		for (int i = 0; i < N; i++) {
			x[i] = that.x[i];
			y[i] = that.y[i];
		}
	}



	Type get_x(int b) {
		return x[b];
	}

	Type get_y(int b) {
		return y[b];
	}

	int get_N() {
		return N;
	}

	void set_N(int v) {
		N = v;
	}

	void set_x(Type b, int m) {
		x[m] = b;
	}

	void set_y(Type b, int m) {
		y[m] = b;
	}



	Type Perimetr() {
		Type per = 0;
		for (int i = 0; i < N; i++) {
			if (i == N - 1)
				per += sqrt(pow(x[i] - x[0], 2) + pow(y[i] - y[0], 2));
			else
				per += sqrt(pow(x[i] - x[i + 1], 2) + pow(y[i] - y[i + 1], 2));
		}
		return per;
	}

	Type Square() {
		Type sum = 0;
		for (int i = 0; i < N; i++) {
			if (i == N - 1)
				sum += x[i] * y[0] - y[i] * x[0];
			else
				sum += x[i] * y[i + 1] - y[i] * x[i + 1];
		}
		return abs(sum/2);
	}



	friend ostream& operator<<(ostream& out, Figure<Type>& n) {
		for (int i = 0; i < n.get_N(); i++) {
			out << " ( " << n.get_x(i) << " , " << n.get_y(i) << " )";
		}
		return out;
	}

	friend istream& operator>>(istream& in, Figure<Type>& n) {
		Type koor;
		for (int i = 0; i < n.get_N(); i++) {
			in >> koor;
			n.set_x(koor, i);
			in >> koor;
			n.set_y(koor, i);
		}
		return in;
	}
};
#endif
