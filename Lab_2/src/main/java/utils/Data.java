package utils;

import org.kopitubruk.util.json.JSONUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Data implements Serializable {
    private double x;
    private double y;
    private double r;
    private String currentTime;
    private String duration;
    private String result;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String dataToJSON(){
        Map<String, String> dataFields = new HashMap<>();
        dataFields.put("x", String.valueOf(x));
        dataFields.put("y", String.valueOf(y));
        dataFields.put("r", String.valueOf(r));
        dataFields.put("currentTime", currentTime);
        dataFields.put("duration", duration);
        dataFields.put("result", result);
        return JSONUtil.toJSON(dataFields);
    }
}
