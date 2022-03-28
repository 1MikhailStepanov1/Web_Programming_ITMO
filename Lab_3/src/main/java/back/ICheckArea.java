package back;

public interface ICheckArea {
    boolean hit(double x, double y, double r);
    boolean hitCircle(double x, double y, double r);
    boolean hitSquare(double x, double y, double r);
    boolean hitTriangle(double x, double y, double r);
}
