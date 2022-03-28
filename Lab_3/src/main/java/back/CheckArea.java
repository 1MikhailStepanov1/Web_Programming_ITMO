package back;

import java.io.Serializable;

public class CheckArea implements ICheckArea, Serializable {
    @Override
    public boolean hit(double x, double y, double r) {
        return (hitCircle(x,y,r) || hitSquare(x,y,r) || hitTriangle(x,y,r));
    }

    @Override
    public boolean hitCircle(double x, double y, double r) {
        if ((x >= 0) && (y <= 0)){
            return x * x + y * y <= r/2 * r/2;
        } else return false;
    }

    @Override
    public boolean hitSquare(double x, double y, double r) {
        if ((x<=0) && (y<=0)){
            return (x<=r && y <=r);
        }
        else return false;
    }

    @Override
    public boolean hitTriangle(double x, double y, double r) {
        if ((x>=0) && (y>=0)){
            return (y <= (-x+r));
        } else return false;
    }
}
