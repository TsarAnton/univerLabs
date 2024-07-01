
#ifndef GEOMETRY_H
#define GEOMETRY_H
#include <iostream>
using namespace std;

class Point {
    double x, y; 
public:
    Point(double X, double Y); 
    double getX();             
    double getY(); 
};

class Circle {
    Point center; 
    double r;     
public:
    Circle(Point Center, double R);   
    Point getCenter();                
    double getR();   
    friend ostream& operator <<(ostream& out, Circle& circle);
};

class Segment {
    double x1, y1, x2, y2; 
public:
    Segment(Point p1, Point p2);       
    Point getMidpoint();              
    double getLength();               
};

class Vector {
    double a, b;
public:
    Vector(Point beg, Point pend);
    double module();
    double scalyr(Vector & b); 
    double vect(Vector& b);
    double ygol(Vector& b);
};
#endif

