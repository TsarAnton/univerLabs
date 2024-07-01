package src;

public class Runner {
	public static void main(String[] args) {
		double x1 = Double.parseDouble(args[0]);
		double y1 = Double.parseDouble(args[1]);
		double x2 = Double.parseDouble(args[2]);
		double y2 = Double.parseDouble(args[3]);
		double x3 = Double.parseDouble(args[4]);
		double y3 = Double.parseDouble(args[5]);
		
		Point point1 = new Point(x1, y1);
		Point point2 = new Point(x2, y2);
		Point point3 = new Point(x3, y3);

		System.out.println("3 points:" + point1 + ", " + point2 + " and " + point3);

		if(!point1.prove(point2, point3)){
		
			System.out.println("Impossible to build a parallelogram");

		}
		else{

			Parallelogram parallelogram1 = new Parallelogram(point1, point2, point3);
			Parallelogram parallelogram2 = new Parallelogram(point1, point3, point2);
			Parallelogram parallelogram3 = new Parallelogram(point2, point1, point3);

			System.out.println("Possible variants of parallelograms:");
			System.out.println(parallelogram1);
			System.out.println(parallelogram2);
			System.out.println(parallelogram3);
			
		}
	}
}
