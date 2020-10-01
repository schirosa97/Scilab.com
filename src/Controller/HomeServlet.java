package Controller;

import Model.Categoria;
import Model.Prodotto;
import Model.ProdottoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet",urlPatterns = {"/HomeServ"})
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                           List<Categoria> categorie= (List<Categoria>) getServletContext().getAttribute("categorie");
                           ProdottoDao service= new ProdottoDao();
                           List<Prodotto> product= service.getAllProdHome();
                         HttpSession session= request.getSession();
                        session.setAttribute("prodotti",product);
                      RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/jsp/Home.jsp");
                      dispatcher.forward(request,response);


    }
}
