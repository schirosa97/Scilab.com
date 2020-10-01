package Controller;

import Model.Spedizione;
import Model.SpedizioneDao;
import Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MostraSpedUsrServelt",urlPatterns = {"/SpedUsrServ"})
public class MostraSpedUsrServelt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                                            Utente usr=(Utente) request.getSession().getAttribute("UsrLog");
                                            if(usr==null) throw new MyExceptionServlet("Errore utente non loggato");
                                            else{
                                                ArrayList<Spedizione> list_sped;
                                               list_sped=SpedizioneDao.getAllUserSped(usr.getIdUtente());
                                               request.setAttribute("ListSped",list_sped);
                                               request.setAttribute("notify","Spedizioni mostrate correttamente");
                                                RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/Spedizioni.jsp");
                                                dispatcher.forward(request,response);
                                            }
    }
}
