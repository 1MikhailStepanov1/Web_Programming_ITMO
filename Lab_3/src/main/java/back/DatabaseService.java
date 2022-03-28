package back;

import dao.DataAO;
import entity.Shots;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public class DatabaseService {
    private final DataAO dataAO = new DataAO();

    public void initializeTable(){
        try{
            dataAO.initializeTable();
        } catch (ServiceException exception){
            System.out.println("Something went wrong. Check connection to DB one more time.");
        }
    }

    public List<Shots> getShots(){
        try{
            return dataAO.getShots();
        } catch (ServiceException exception){
            return null;
        }
    }

    public void deleteShots(){
        try {
            dataAO.clearTable();
        } catch (ServiceException exception){
            exception.printStackTrace();
        }
    }

    public void addShot(Shots shot){
        try {
            dataAO.addShot(shot);
        } catch (ServiceException exception){
            exception.printStackTrace();
        }
    }
}
