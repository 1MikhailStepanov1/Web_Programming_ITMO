package beans;

import back.CheckArea;
import back.Validation;
import database.dao.DataAO;
import entity.Shots;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Named("hitBean")
@ApplicationScoped
public class HitBean implements Serializable {
    private final Validation validation;
    private final CheckArea checkArea;
    private final DataAO dataAO;


    public HitBean() {
        validation = new Validation();
        checkArea = new CheckArea();
        dataAO = new DataAO();
    }

    private String x;
    private String y;
    private String r;

    public static Error errorX = new Error("");
    public static Error errorY = new Error("");
    public static Error errorR = new Error("");

    private boolean correctX = false;
    private boolean correctY = false;
    private boolean correctR = false;

    public void submit() {
        long start = System.nanoTime();

        Double numericalX = validation.validateX(x);
        Double numericalY = validation.validateY(y);
        Integer numericalR = validation.validateR(r);

        correctX = (numericalX != null);
        correctY = (numericalY != null);
        correctR = (numericalR != null);

        if (correctX && correctY && correctR) {
            Shots shot = new Shots();
            shot.setX(numericalX.floatValue());
            shot.setY(numericalY.floatValue());
            shot.setR(numericalR);
            shot.setCurrentTime(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));
            shot.setDuration((System.nanoTime() - start) / 1000);
            shot.setResult(checkArea.hit(numericalX, numericalY, numericalR));
            dataAO.addShot(shot);
        }
    }

    public void submitClick() {
        long start = System.nanoTime();

        double numericalX = Double.parseDouble(x);
        double numericalY = Double.parseDouble(y);
        int numericalR = Integer.parseInt(r);

        Shots shot = new Shots();
        shot.setX((float) numericalX);
        shot.setY((float) numericalY);
        shot.setR(numericalR);
        shot.setCurrentTime(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));
        shot.setDuration((System.nanoTime() - start) / 1000);
        shot.setResult(checkArea.hit(numericalX, numericalY, numericalR));
        dataAO.addShot(shot);

    }

    public void resetHistory() {
        dataAO.clearTable();
    }

    public List<Shots> getHistory() {
        return dataAO.getShots();
    }


    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public boolean getCorrectX() {
        return correctX;
    }

    public boolean getCorrectY() {
        return correctY;
    }

    public Object getErrorR() {
        return errorR;
    }

    public Object getErrorY() {
        return errorY;
    }

    public Object getErrorX() {
        return errorX;
    }
}
