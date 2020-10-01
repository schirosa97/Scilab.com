package Controller;

import Model.AdminDao;
import Model.Utente;
import Model.UtenteDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletLogin", urlPatterns = {"/LogServ"})
public class ServletLogin extends HttpServlet {
            private static String User_regex= "^[^\\s]{5,30}$";
            private static String hashpass_regex="^[a-zA-Z0-9 && [^\\s]]{6,40}$";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                     HttpSession session= request.getSession();
                     UtenteDao user=  new UtenteDao();
                     AdminDao user_admin= new AdminDao();
                     String address= null;

                     if(request.getParameter("username")==null || request.getParameter("hashpass")==null){
                                    throw new MyExceptionServlet("Riempire i form");
                     }

                     else{
                            if(request.getParameter("username").matches(User_regex) && request.getParameter("hashpash").matches(hashpass_regex)){
                                        if(user_admin.getAdminById(Integer.parseInt(request.getParameter("userId")))!=null){
                                                                 address= "/WEB-INF/jsp/admin.jsp";  // migliorare costruendo jsp in base al livello di privilegi del admin
                                            RequestDispatcher dispatcher=request.getRequestDispatcher(address);
                                                                dispatcher.forward(request,response);
                                        }

                                        else{
                                                if(user.doFindByName(request.getParameter("username"))!=null){
                                                                 address="/WEB-INF/jsp/user.jsp";
                                                                synchronized (session) {
                                                                    session = request.getSession(true);
                                                                    session.setAttribute("usrlog", user);
                                                                }
                                                                RequestDispatcher dispatcher=request.getRequestDispatcher(address);
                                                                dispatcher.forward(request,response);
                                                }

                                                else{
                                                        address=request.getHeader("referer");
                                                        if(address.contains("login")|| address.trim().isEmpty()){
                                                                    address=".";
                                                                    RequestDispatcher dispatcher=request.getRequestDispatcher(address);
                                                                    dispatcher.forward(request,response);

                                                        }

                                                        else {
                                                                response.sendRedirect(address);
                                                        }

                                                }
                                        }


                            }

                     }


    }
}
