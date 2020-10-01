package Controller;

import Model.Carrello;
import Model.OrdineDao;
import Model.Prodotto;
import Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "OrdineCheckoutServlet",urlPatterns = {"/ordCheckServ"})
public class OrdineCheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        Utente usr= (Utente) request.getSession().getAttribute("Usrlog");
                        if(usr==null)
                                throw new MyExceptionServlet("Utente non ha effetuato l'accesso");

                        String checkout=request.getParameter("checkout");
                        String infopay= request.getParameter("info");

                        if(infopay!=null){
                            RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/riepilogoOrdine.jsp");
                            dispatcher.forward(request,response);

                        }

                        if(checkout!=null){
                            OrdineDao order= new OrdineDao();
                                        Carrello cart= (Carrello) request.getSession().getAttribute("carrello");
                                        double tot=0.0;
                            ArrayList<Prodotto> products= cart.getListaProdotti();
                                        for(int i=0; i<products.size();i++){
                                                    Prodotto x=products.get(i);
                                                    OrdineDao.doSaveOrdProd(usr.getIdUtente(),Integer.parseInt(x.getIdprod()));
                                                    tot+=x.getPrezzo();
                                        }
                                        String tots=String.format("%2.f",tot);

                                         Date date= new Date(); // alternativa piu precisa utilizzo di calendar con date
                                        order.doSave(usr.getIdUtente(),tot,"Processato",date);
                                        HttpSession session= request.getSession();
                                        session.setAttribute("totalecarrello",tots);
                                        session.setAttribute("totaleprodotti",cart.getListaProdotti().size());
                                        session.setAttribute("notifica","totale carrello corretamente mostrato");
                        }
                        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/checkout.jsp");
                        dispatcher.forward(request,response);
    }
}
