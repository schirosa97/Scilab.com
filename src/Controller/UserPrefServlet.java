package Controller;

import Model.PreferitiDao;
import Model.Prodotto;
import Model.ProdottoDao;
import Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/UserPrefServlet",urlPatterns = {"/UsrPref"})
public class UserPrefServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente usr = (Utente) request.getSession().getAttribute("Usrlog");
        String insert = request.getParameter("insertbtn");
        String delete = request.getParameter("deletebtn");

        if (usr != null) {

            if (insert != null) {
                                        /*request.setAttribute("addPref","true");
                                        request.setAttribute("idtoinsert",user_id); alternativa più pulita
                                RequestDispatcher dispatcher=request.getRequestDispatcher("/")*/
                String prod = request.getParameter("prod_idhide");
                PreferitiDao servpref = new PreferitiDao();
                List<Prodotto> pref_usr = servpref.doRetriveByKey(usr.getIdUtente());
                if (pref_usr == null) {
                    ProdottoDao prodottoDAO = new ProdottoDao();
                    Prodotto product = prodottoDAO.doFindProdID(Integer.parseInt(prod));
                    pref_usr.add(product);
                    PreferitiDao.doSave(Integer.parseInt(product.getIdprod()),usr.getIdUtente());
                    request.setAttribute("notifica", "aggiunta ai preferiti riuscita");
                    request.getSession().setAttribute("preferusr", pref_usr);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Successo.jsp");
                    dispatcher.forward(request, response);
                } else {
                    ProdottoDao prodottoDAO = new ProdottoDao();
                    Prodotto product = prodottoDAO.doFindProdID(Integer.parseInt(prod));
                        if(!pref_usr.contains(product)) {
                            pref_usr.add(product);
                            PreferitiDao.doSave(Integer.parseInt(product.getIdprod()),usr.getIdUtente());
                        }
                    request.setAttribute("notifica", "aggiunta ai preferiti riuscita");
                    request.getSession().setAttribute("preferusr", pref_usr);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Successo.jsp");
                    dispatcher.forward(request, response);
                }
                if (delete != null) {
                    String prod_id = request.getParameter("prod_id");
                    PreferitiDao.deleteProdotto(usr.getIdUtente(),Integer.parseInt(prod_id));
                    request.setAttribute("notifica", "rimozione dai preferiti riuscita");
                    List<Prodotto> prefers = servpref.doRetrivePrefByUserId(usr.getIdUtente());
                    HttpSession session = request.getSession();
                    session.setAttribute("preferusr", prefers);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Successo.jsp");
                    dispatcher.forward(request, response);

                }
            }
        }

        else    throw new MyExceptionServlet("Utente non registrato");



    }
}
