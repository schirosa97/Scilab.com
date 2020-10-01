package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Model.*;
@WebServlet(name = "InitServlet",urlPatterns = {"/InitServ"},loadOnStartup = 0)
public class InitServlet extends HttpServlet {
// inizializza il sito con estraendo tutte le categorie dal DB
    @Override
    public void init() throws ServletException {
                List<Categoria> categories=CategoriaDao.getAllCategoria();
                getServletContext().setAttribute("categorie",categories);
        super.init();
    }
}
