package src;

public class Parallelogram {
    
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    public Parallelogram(Point point1, Point point2, Point point3){

        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = this.findPoint(this.point1, this.point2, this.point3);
    }

    static public Point findPoint(Point point1, Point point2, Point point3){
        Segment diagonal = new Segment(point1, point3);
        Point diagonal_middle = diagonal.getMiddlePoint();
        Point new_point = new Point(2 * diagonal_middle.getX() - point2.getX(), 2 * diagonal_middle.getY() - point2.getY());
        return new_point;
    }

    @Override
	public String toString() {

		return point1 + " " + point2 + " " + point3 + " " + point4;

	}
}
