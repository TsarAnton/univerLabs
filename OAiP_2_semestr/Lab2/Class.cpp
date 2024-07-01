#include <math.h>
#include "Class.h"

Point::Point(double X, double Y) {
    x = X; y = Y;
}
double Point::getX() {
    return x;
}
double Point::getY() {
    return y;
}


Circle::Circle(Point Center, double R) :center(Center) {
    r = R;
}
Point Circle::getCenter() {
    return center;
}
double Circle::getR() {
    return r;
}
ostream& operator <<(ostream& out, Circle& circle) {
    out << "Окружность с центром в точке (" << circle.getCenter().getX() << " , " << circle.getCenter().getY() << ") и радиусом " << circle.getR() << endl;
    return out;
}


Segment::Segment(Point p1, Point p2) {
    x1 = p1.getX(); y1 = p1.getY();
    x2 = p2.getX(); y2 = p2.getY();
}
Point Segment::getMidpoint() {
    return Point((x1 + x2) / 2, (y1 + y2) / 2);
}
double Segment::getLength() {
    return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
}

Vector::Vector(Point beg, Point pend) {
    a = pend.getX() - beg.getX();
    b = pend.getY() - beg.getY();
}
double Vector::module() {
    return sqrt(pow(a, 2) + pow(b, 2));
}
double Vector::scalyr(Vector& M) {
    return a * M.a + b * M.b;
}
double Vector::ygol(Vector& M) {
    return acos(this->scalyr(M) / (this->module() * M.module()));
}
double Vector::vect(Vector& M) {
    return this->module() * M.module() * sin(this->ygol(M));
}
