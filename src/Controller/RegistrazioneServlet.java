package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Model.*;

@WebServlet(name = "RegistrazioneServlet",urlPatterns = {"/RegServlet"})
public class RegistrazioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                              String name= request.getParameter("nome");
                              String surname=request.getParameter("cognome");
                              String cf=request.getParameter("Cf");
                              String hashpass= request.getParameter("password");
                              String email= request.getParameter("email");
                              String passConf= request.getParameter("passconf");
                              String cap= request.getParameter("cap");

                              if(hashpass.length()<=8 || hashpass.matches("! @")){
                                            throw new MyExceptionServlet("formato password non valido");
                              }
                              if(cap.length()<5)
                                            throw  new MyExceptionServlet("formato cap errato");
                              if(!hashpass.equals(passConf)){
                                  throw new MyExceptionServlet("Password e Conferma diversi");
                              }
                                if(cf.length()<16 || !(cf!= null && cf.matches("!")))
                                        throw new MyExceptionServlet("Codice fiscale non valido");

            if (!(email != null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")))
                                             throw new MyExceptionServlet("Email non valida.");

            UtenteDao service= new UtenteDao();
            Utente  new_user= new Utente();
            new_user.setNome(name);
            new_user.setCognome(surname);
            new_user.setPassword(hashpass);
            new_user.setCF(cf);
            new_user.setEmail(email);
            service.doSave(new_user);
            request.getSession().setAttribute("UsrLog",new_user);

        RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/Jsp/RegistrazioneSucesso.jsp");
        dispatcher.forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doPost(request, response);
    }
}
