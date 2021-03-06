package com.example.weblab4.model;

import com.example.weblab4.POJO.Requests.CheckDotRequest;
import com.example.weblab4.entities.EntryEntity;
import com.example.weblab4.exceptions.NotIncludedInTheRangeException;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class AreaChecker {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public EntryEntity checkEntry(CheckDotRequest checkDotRequest) throws NotIncludedInTheRangeException {
        float x = checkDotRequest.getX();
        float y = checkDotRequest.getY();
        float r = checkDotRequest.getR();

        if (r> 5 || r < -3){
            throw new NotIncludedInTheRangeException("r in (-3;5)");
        }else if(y<-5 || y > 3){
            throw new NotIncludedInTheRangeException("y in (-3;3)");
        }else if(x> 5 || x < -3){
            throw new NotIncludedInTheRangeException("x in (-3;5)");
        }
        boolean entryValue = checkGetInto(checkDotRequest.getX(), checkDotRequest.getY(), checkDotRequest.getR());
        Date date = new Date();
        return new EntryEntity(checkDotRequest.getX(), checkDotRequest.getY(), checkDotRequest.getR(), entryValue, date);
    }


    public boolean checkGetInto(float x, float y, float r) {
        if (checkIntoTriangle(x, y, r) || checkIntoRectangle(x, y, r) || checkIntoCircle(x, y, r)) {
            return true;
        }
        return false;
    }

    public boolean checkIntoTriangle(float x, float y, float r) {
        if (x>=0 && y<=0){
            return x/2-r/2<=y;
        }
        return false;
    }

    public boolean checkIntoRectangle(float x, float y, float r) {
        if (x>= 0 && y>=0){
            return (x<=r && y<=r);
        }
        return false;
    }

    public boolean checkIntoCircle(float x, float y, float r) {
        if (x <= 0 && y <= 0){
            return (x*x + y*y <= (r/2)*(r/2));
        }
        return false;
    }
}
