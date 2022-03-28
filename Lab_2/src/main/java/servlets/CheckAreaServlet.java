package servlets;

import utils.Data;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

public class CheckAreaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long start = System.nanoTime();
        String currentTime = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime());

        double x;
        double y;
        double r;
        try {
            x = Double.parseDouble(request.getParameter("x_value"));
            y = Double.parseDouble(request.getParameter("y_value"));
            r = Double.parseDouble(request.getParameter("r_value"));
            if (x < -5 || x > 5 || y < -3 || y > 5 || r < 1 || r > 5) {
                response.setStatus(422);
                return;
            }
        } catch (NumberFormatException exception) {
            response.setStatus(422);
            return;
        }
        boolean hit = hit(x, y, r);
        String result = hit ? "TRUE" : "FALSE";

        long duration = (System.nanoTime()- start)/1000;

        ServletContext context = getServletContext();
        Object rawHistory = context.getAttribute("history");
        LinkedList<Data> history;
        if (rawHistory != null) {
            if (rawHistory instanceof LinkedList
                    && !((LinkedList<?>) rawHistory).isEmpty()
                    && ((LinkedList<?>) rawHistory).getFirst() instanceof Data) {
                history = (LinkedList<Data>) rawHistory;
            } else {
                System.out.println("Attempt`s history can't be matched.");
                history = new LinkedList<>();
            }
        } else {
            history = new LinkedList<>();
        }
        Data data = new Data();
        data.setX(x);
        data.setY(y);
        data.setR(r);
        data.setCurrentTime(currentTime);
        data.setDuration(Long.toString(duration));
        data.setResult(result);
        history.add(data);

        context.setAttribute("history", history);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(data.dataToJSON());
    }

    private boolean hit(double x, double y, double r) {
        if (x >= 0) {
            if (y >= 0) {
                return (x <= r && y <= r);
            } else {
                return (y > 0.5 * x - r / 2);
            }
        } else {
            if (y >= 0) {
                return (x * x + y * y <= r/2 * r/2);
            } else return false;
        }
    }
}
