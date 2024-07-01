#ifndef Class_h
#define Class_h

class Sphere {
protected:
	double R;
public:
	Sphere(double r);

	double getR();
	void setR(double r);

	double Square();
};

class Konus:private Sphere {
	double h;
public:
	Konus(double r1, double h1);

	double getH();
	void setH(double r1);

	double Square();
};
#endif
