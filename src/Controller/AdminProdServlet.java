package Controller;

import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminProdServlet",urlPatterns = {"/AdmProdServ"})
@MultipartConfig
public class AdminProdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente usr = (Utente) session.getAttribute("UsrLog"); // controllo admin
        AdminDao admin= new AdminDao();
       Utente check=admin.FindAdminByPassAndName(usr.getNome(),usr.getPassword());
        ProdottoDao prodDAO = new ProdottoDao();
        CategoriaDao catDAO= new CategoriaDao();
        CatProdDao catprod= new CatProdDao();

        if(check==null) {
                    session.setAttribute("notify:","accesso non consentito, sarai reinderizzato");
                    response.sendRedirect("/WEB/Jsp/user.jsp"); // sostiuire con MyExceptionServlet
        }

        else {
            int hidden_id= Integer.parseInt(request.getParameter("prodID")); // Nella jsp impostare ad hidden il parametro id
            String modifica=request.getParameter("modifica");
            String rimuovi= request.getParameter("rimuovi");
            String aggiungi= request.getParameter("aggiungi");

            if(!rimuovi.isEmpty()) { // rimozione prodotto

                        prodDAO.doDeleteWithID(hidden_id);
                        request.setAttribute("notify","rimozione compiuta con successo");

            }

            if(!modifica.isEmpty()) { // valutare possibilita di modifica immagini Prodotto
                String long_desc = request.getParameter("desc_lunga");
                String short_desc = request.getParameter("desc_corta");
                String name_prod = request.getParameter("prodname");
                String weight = request.getParameter("peso");
                String height = request.getParameter("altezza");

                if (long_desc.isEmpty() && short_desc.isEmpty() && name_prod.isEmpty() && weight.isEmpty() && height.isEmpty()) {
                    throw new MyExceptionServlet("errore tutti i parametri sono vuoti impossibile continuare");
                }

                Prodotto prod = prodDAO.doFindProdID(hidden_id);// alternativa al costruire un nuovo prodotto rischiosa!!

                /*if (!long_desc.isEmpty() || !short_desc.isEmpty()) {
                    if (long_desc.isEmpty()) {
                        prod.setDesc(prod.getLdesc(), short_desc);
                    } else {
                        prod.setDesc(long_desc, prod.getSdesc());
                    }
                }*/
               /* if (Integer.parseInt(weight) < 0 || Integer.parseInt(height) < 0) {
                    throw new MyExceptionServlet("errori altezza e peso devono essere positivi");
                }*/

                if (name_prod.isEmpty())
                  prod.setNome(prod.getNome()); ; // conserva vecchio nome dato che l'user non ne ha inserito uno.

                //prod.setPhysicalAtt(weight, height);
                prod.setNome(name_prod);
                prodDAO.doDeleteWithID(hidden_id);
                ProdottoDao.doSave(prod);

                if (request.getParameter("addCat") != null) { // aggiunta nuova categoria

                    ArrayList<Categoria> catProd = catprod.getAllCategoriesFromProdID(hidden_id);
                    Categoria cat = new Categoria(Integer.parseInt(request.getParameter("catId")), request.getParameter("catname"));
                    catProd.add(cat);
                    CategoriaDao.doSave(cat.getNomeCategoria());
                    catprod.doSave(hidden_id,cat.getIdcategory());
                    ArrayList<Categoria> allCat = (ArrayList<Categoria>) getServletContext().getAttribute("category");
                    allCat.add(cat);
                    getServletContext().setAttribute("category", allCat);
                }

                if (request.getParameter("modCat") != null) { // modifica categoria
                    int id_sel_cat = 0;
                    String name = null;
                    int actual_id_cat = Integer.parseInt(request.getParameter("catId")); // trovare alterntiva per estrarre categoria
                    if (request.getParameter("catIDupd") != null)
                        id_sel_cat = Integer.parseInt(request.getParameter("catIDupd")); // catID  non sarà hidden nella pagina
                    if (request.getParameter("catnameupd") != null)
                        name = request.getParameter("catnameupd");
                    Categoria cat = catDAO.doFindByID(actual_id_cat);
                    catprod.doDeleteWithID(cat.getIdcategory(), hidden_id);
                    CategoriaDao.doDelete(actual_id_cat);
                    cat.setIdcategory(id_sel_cat);
                    cat.setNomeCategoria(name);
                    CategoriaDao.doSave(cat.getNomeCategoria());
                    catprod.doSave(cat.getIdcategory(), hidden_id); // si deve aggiorna la tabella CatProd con la nuova modifica
                    // aggiungere aggiornamento al servlet Context

                }


            }
                if(!aggiungi.isEmpty()){
                                        int new_ID= Integer.parseInt(request.getParameter("addID"));
                                        String name= request.getParameter("addName");
                                        String pathimg= request.getParameter("addImg");
                                        double price= Double.parseDouble(request.getParameter("addprice"));
                                        String desc= request.getParameter("addDesc");       // aggiungere attributo per la quantitità

                                        Prodotto prodotto= new Prodotto(pathimg,name,price,desc, new_ID);
                                        ArrayList<Prodotto> prodottos= prodDAO.getAllProdotti();
                                        for(Prodotto p:prodottos){
                                                    if(prodotto.getIdprod()==p.getIdprod())
                                                                throw new MyExceptionServlet("errori prodotto ID è un duplicato");
                                        }
                                        prodDAO.doSave(prodotto);

            }



        }

        RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/adminProd.jsp");
        dispatcher.forward(request,response);
    }
}
