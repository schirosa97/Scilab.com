package Controller;

import Model.Prodotto;
import Model.ProdottoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProdottiServlet",urlPatterns = {"/PrdServ"})
public class ProdottiServlet  extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numStr=request.getParameter("idcat");
        System.out.println(numStr);
        if(numStr!=null)
        {
            int idTMP=Integer.parseInt(numStr);
            Prodotto prodotto=ProdottoDao.doFindProdottoConId(idTMP);
            request.setAttribute("prodotto", prodotto);
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("WEB-INF/jsp/Prodotto.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
