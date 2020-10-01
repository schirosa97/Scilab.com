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
import java.util.List;

@WebServlet(name = "RicercaServlet", urlPatterns = {"/RicServ"})
public class RicercaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                            String name_prod= request.getParameter("nomeProd");
                            ProdottoDao prod= new ProdottoDao();
                            List<Prodotto> products= prod.SearchProdByName(name_prod);
                            request.setAttribute("showprodsearch",products);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/showprodsearch.jsp");
                        dispatcher.forward(request,response);
    }
}
