package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String x = request.getParameter("x_value");
        String y = request.getParameter("y_value");
        String r = request.getParameter("r_value");

        RequestDispatcher requestDispatcher;
        if (x != null && y != null && r != null){
            requestDispatcher = request.getRequestDispatcher("/checkHit");
        } else requestDispatcher = request.getRequestDispatcher("/index.jsp");

        requestDispatcher.forward(request, response);
    }
}
