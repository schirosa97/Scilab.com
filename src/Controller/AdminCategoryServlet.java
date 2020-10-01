package Controller;

import Model.AdminDao;
import Model.Categoria;
import Model.CategoriaDao;
import Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminCategoryServlet", urlPatterns = {"/adminCat"})
@MultipartConfig
public class AdminCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        Utente usr= (Utente) session.getAttribute("UsrLog");
        AdminDao admin_check= new AdminDao();
        CategoriaDao category= new CategoriaDao();
        int i;
        if(admin_check.FindAdminByPassAndName(usr.getNome(),usr.getPassword())==null)
                    throw new MyExceptionServlet("utente non admin accesso negato");

        else {
                    String rimuovi=request.getParameter("remove");
                    String modifica= request.getParameter("update");
                    String aggiungi= request.getParameter("addCat");
                    int select_id= Integer.parseInt(request.getParameter("catID"));

                    if(!rimuovi.isEmpty()){

                            category.doDeleteByID(select_id);
                        ServletContext context= this.getServletContext();
                        ArrayList<Categoria> conCat= (ArrayList<Categoria>) context.getAttribute("categorie");
                        for(Categoria cat:conCat){
                                        if(cat.getIdcategory()==select_id){
                                                    conCat.remove(cat);
                                                    break;
                                        }
                        }

                        request.setAttribute("notify","rimozione categoria riuscita!");
                        context.setAttribute("categorie",conCat);
                    }

                    else if(!modifica.isEmpty()){

                                    String name=request.getParameter("catname");
                                    String desc=request.getParameter("description");
                                    String image_path=request.getParameter("image");
                                    Categoria cat= category.doFindByID(select_id);
                                    if(cat.getNomeCategoria()==name) throw new MyExceptionServlet("Errore nome prodotto già esistente");
                                    if(!name.isEmpty())
                                         cat.setNomeCategoria(name);
                                    if(!desc.isEmpty())
                                            cat.setDescrizione(desc);
                                    if(!image_path.isEmpty() && !image_path.matches(cat.getPathimageicona()))
                                            cat.setPathimageicona(image_path);
                                    category.doDeleteByID(select_id);
                                    CategoriaDao.doSave(cat.getNomeCategoria());
                                    ServletContext context= this.getServletContext();
                            ArrayList<Categoria> conCat= (ArrayList<Categoria>) context.getAttribute("categorie");
                            for(i=0;i<conCat.size();i++){
                                        if(conCat.get(i).getIdcategory()==select_id){
                                                    conCat.remove(i);
                                                    conCat.add(i,cat);
                                                    break;
                                        }
                            }
                            this.getServletContext().setAttribute("categorie",conCat);
                            request.setAttribute("notify","aggiunta effettuata con successo");

                    }

                    else if(!aggiungi.isEmpty()){

                                    int id_add=Integer.parseInt(request.getParameter("idAdd"));
                                    String name= request.getParameter("catNameAdd");
                                    String desc=request.getParameter("descAdd");
                                    String image_path=request.getParameter("imageAdd");

                                    Categoria cat= new Categoria(id_add,name,desc,image_path);
                                    CategoriaDao.doSave(cat.getNomeCategoria());
                                  ArrayList<Categoria> categorias= (ArrayList<Categoria>) this.getServletContext().getAttribute("categoria");
                                  categorias.add(cat);
                                  this.getServletContext().setAttribute("categorie",categorias);
                                  request.setAttribute("notify","aggiunta effettuata con successo");
                    }


            RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/adminCat.jsp");
                        dispatcher.forward(request,response);



        }

    }
}
