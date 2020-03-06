import java.util.Arrays;

class Point{
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(Point point){
        this.x = point.getX();
        this.y = point.getY();
    }

    public Point(){
        this(0,0);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public String toString(){
        return "x = " + x + "; y = " + y + ";\n";
    }
}   //// class Point

abstract public class Shape {
    protected Point [] points;
    abstract double findArea();

    @Override
    public String toString(){
        String toPrint = "";
        for (Point point : points) {
            toPrint += point;
        }
        return toPrint;
    }

    final static double sumAreas(Shape ... shapes){
        double areas = 0;
        for (Shape shape : shapes) {
            areas += shape.findArea();
        }
        return areas;
    }
}   //// class Shape

class Triangle extends Shape{
    public Triangle(Point[] points) {
        if(points.length != 3){
            throw new IllegalArgumentException("Exception: The object must contain 3 vertices");
        }
        for(int i = 0; i < 3; i++){
            if(points[i] == null){
                throw new NullPointerException("Exception: One of the vertices of the triangle is null");
            }
        }
        super.points = points.clone();
    }

    public Triangle(Point a, Point b, Point c){
        super.points = new Point[] {a,b,c};
        for(int i = 0; i < 3; i++){
            if(super.points[i] == null){
                throw new NullPointerException("Exception: One of the vertices of the triangle is null");
            }
        }
    }

    @Override
    public double findArea(){
        //По формуле "определителя второго порядка"
        int[][] vertices = new int[2][2];
        vertices[0][0] = super.points[0].getX() - super.points[2].getX();
        vertices[0][1] = super.points[1].getX() - super.points[2].getX();
        vertices[1][0] = super.points[0].getY() - super.points[2].getX();
        vertices[1][1] = super.points[1].getY() - super.points[2].getX();

        return 0.5 * ((vertices[0][0] * vertices[1][1]) - (vertices[0][1] * vertices[1][0]));
    }

    @Override
    public String toString(){
        return "Triangle: \n" + super.toString();
    }
}   //// class Triangle extends Shape

class Rectangle extends Shape {
    public Rectangle(Point[] points) {
        if(points.length != 4){
            throw new IllegalArgumentException("Exception: The object must contain 4 vertices");
        }
        for(int i = 0; i < 4; i++){
            if(points[i] == null){
                throw new NullPointerException("Exception: One of the vertices of the triangle is null");
            }
        }
        super.points = points.clone();
    }

    public Rectangle(Point a, Point b, Point c, Point d) {
        super.points = new Point[] {a,b,c,d};
        for(int i = 0; i < super.points.length; i++){
            if(super.points[i] == null){
                throw new NullPointerException("Exception: One of the vertices of the triangle is null");
            }
        }
    }

    @Override
    public double findArea(){
        //По теореме Пифагора
        int a_dX = Math.abs(super.points[0].getX() - super.points[3].getX());
        int a_dY = Math.abs(super.points[0].getY() - super.points[3].getY());
        int a_bX = Math.abs(super.points[0].getX() - super.points[1].getX());
        int a_bY = Math.abs(super.points[0].getX() - super.points[1].getX());

        double lengthAD = Math.sqrt(Math.pow(a_dX,2) + Math.pow(a_dY,2));
        double lengthAB = Math.sqrt(Math.pow(a_bX,2) + Math.pow(a_bY,2));

        return  lengthAB * lengthAD;
    }

    @Override
    public String toString(){
        return "Rectangle: \n" + super.toString();
    }

}

class Trapezoid extends Shape{
    public Trapezoid(Point[] points) {
        if(points.length != 4){
            throw new IllegalArgumentException("Exception: The object must contain 4 vertices");
        }
        for(int i = 0; i < 4; i++){
            if(points[i] == null){
                throw new NullPointerException("Exception: One of the vertices of the triangle is null");
            }
        }
        super.points = points.clone();
    }

    public Trapezoid(Point a, Point b, Point c, Point d) {
        super.points = new Point[] {a,b,c,d};
        for(int i = 0; i < super.points.length; i++){
            if(super.points[i] == null){
                throw new NullPointerException("Exception: One of the vertices of the triangle is null");
            }
        }
    }

    public int getHeight(){
        int [] arrayY = new int[super.points.length];

        for (int i = 0; i < arrayY.length; i++){
            arrayY[i] = super.points[i].getY();
        }
        Arrays.sort(arrayY);

        return arrayY[arrayY.length -1] - arrayY[0];
    }


    @Override
    public double findArea(){
        int a = Math.abs(super.points[0].getX() - super.points[1].getX());
        int b = Math.abs(super.points[2].getX() - super.points[3].getX());

        int height = getHeight();

        return ((a+b)/2.) * height;
    }

    @Override
    public String toString(){
        return "Trapezoid: \n" + super.toString();
    }
}

class Square extends Shape{

    public Square(Point[] points) {
        if(points.length != 4){
            throw new IllegalArgumentException("Exception: The object must contain 4 vertices");
        }
        for(int i = 0; i < 4; i++){
            if(points[i] == null){
                throw new NullPointerException("Exception: One of the vertices of the triangle is null");
            }
        }
        super.points = points.clone();
    }

    public Square(Point a, Point b, Point c, Point d) {
        super.points = new Point[] {a,b,c,d};
        for(int i = 0; i < super.points.length; i++){
            if(super.points[i] == null){
                throw new NullPointerException("Exception: One of the vertices of the triangle is null");
            }
        }
    }

    @Override
    public double findArea(){
        double sideX = Math.pow(super.points[0].getX() - super.points[1].getX(),2);
        double sideY = Math.pow(super.points[0].getY() - super.points[1].getY(),2);

        return sideX + sideY;
    }

    @Override
    public String toString(){
        return "Trapezoid: \n" + super.toString();
    }
}