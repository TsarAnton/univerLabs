
#include "Class.h"
#define _USE_MATH_DEFINES
#include <math.h>


Sphere::Sphere(double r) {
	R = r;
}
double Sphere::getR() {
	return R;
}
void Sphere::setR(double r) {
	R = r;
}
double Sphere::Square() {
	return (4 * M_PI * pow(R, 3)) / 3;
}


Konus::Konus(double r1, double h1):Sphere(r1) {
	h = h1;
}
double Konus::getH() {
	return h;
}
void Konus::setH(double r1) {
	h = r1;
}
double Konus::Square() {
	return (this->Sphere::Square() * h) / (4 * R);
}