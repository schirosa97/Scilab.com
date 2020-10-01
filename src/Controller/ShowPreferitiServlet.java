package Controller;

import Model.PreferitiDao;
import Model.Prodotto;
import Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/ShowPreferitiServlet")
public class ShowPreferitiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


                                  Utente user = (Utente) request.getSession().getAttribute("UsrLog");
                                  if(user==null) throw new MyExceptionServlet("Utente non registrato");
                                  PreferitiDao servicePref = new PreferitiDao();
                                  List<Prodotto> preference = servicePref.doRetrivePrefByUserId(user.getIdUtente());
                                  request.setAttribute("preferusr", preference);
                                  request.setAttribute("notifica", "i preferiti sono mostrati correttamente");
                                  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/preferiti.jsp");
                                  dispatcher.forward(request, response);



    }
}
