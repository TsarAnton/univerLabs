package src;

public class Point {
	protected double x;
	protected double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean prove(Point point2, Point point3){
        if((this.getX() == point2.getX() && this.getY() == point2.getY() && point2.getX() == point3.getX() && point2.getY() == point3.getY()) || (point3.getX() - this.getX()) / (point2.getX() - this.getX()) == (point3.getY() - this.getY()) / (point2.getY() - this.getY())){
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "(" + x + "; " + y + ")";
	}

	@Override
    public boolean equals(Object obj) {
    	if (obj == null || obj.getClass() != this.getClass()) {
        	return false;
    	}

    	Point p = (Point) obj;
        return this.x == p.getX() && this.y == p.getY();
    }
}
