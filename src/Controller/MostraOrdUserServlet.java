package Controller;

import Model.Ordine;
import Model.OrdineDao;
import Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MostraOrdUserServlet",urlPatterns = {"/OrdusrServ"})
public class MostraOrdUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                            Utente usr=(Utente) request.getSession().getAttribute("Utente");
                            OrdineDao servord=new OrdineDao();
                            int id=usr.getIdUtente();
                            ArrayList<Ordine> orders=servord.getAllOrderByUsrId(id);
                            request.setAttribute("ListOrder",orders);
        RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/ordine.jsp");
                            dispatcher.forward(request,response);
    }

}
