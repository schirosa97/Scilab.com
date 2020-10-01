package Controller;

import Model.AdminDao;
import Model.Utente;
import Model.UtenteDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "/AdminUserServlet",urlPatterns = {"/adminUser"})
@MultipartConfig
public class AdminUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session= request.getSession();
        Utente usr= (Utente)session.getAttribute("UsrLog");
        AdminDao admin_chek= new AdminDao();
        UtenteDao usr_db= new UtenteDao();
        if(admin_chek.FindAdminByPassAndName(usr.getNome(),usr.getPassword())==null)
                    throw new MyExceptionServlet("errore utente non autorizzato");

        else{
             int id_usr= Integer.parseInt(request.getParameter("id_usr"));
             String rimuovi= request.getParameter("rimuovi");
             String modifica= request.getParameter("modifica");
             String aggiungi= request.getParameter("aggiungi");

             if(rimuovi.isEmpty() && modifica.isEmpty() && aggiungi.isEmpty())
                                throw new MyExceptionServlet("errore esegui una operazione");

             if(!rimuovi.isEmpty()){
                                usr_db.doDeleteByID(id_usr);
                                request.setAttribute("notify","rimozione avvenuta con successo");
             }

             if(!modifica.isEmpty()){
                            String name= request.getParameter("usrName");
                            String pass= request.getParameter("usrPass");
                            // aggiungere altri campi utente
                            Utente usr1= usr_db.doFindByID(id_usr);
                            usr_db.doDeleteByID(id_usr);
                            usr1.setNome(name);
                            usr1.setPassword(pass);
                            usr_db.doSave(usr1);
                            request.setAttribute("notify","modifica eseguita con successo");
             }


             if(!aggiungi.isEmpty()){
                 String name= request.getParameter("addusrName");
                 String pass= request.getParameter("addusrPass");
                 String surname=request.getParameter("addSurname");
                 int ids_usr= Integer.parseInt(request.getParameter("idUsr"));
                 Utente check;
                 check=usr_db.doFindByName(name);
                 if(check!=null){
                                    check.setNome(name);
                                    check.setCognome(surname);
                                    check.setPassword(pass);
                                    if(ids_usr>0)
                                    check.setIdUtente(ids_usr);
                                    else
                                        check.setIdUtente(id_usr);
                 }
                 else  throw new MyExceptionServlet("errore nome già esistente");

                 usr_db.doSave(check);
                 request.setAttribute("notify","aggiunta avvenuta con successo");
             }


            RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/adminUser.jsp");
                        dispatcher.forward(request,response);

        }
    }
}
