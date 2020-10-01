/*package Controller;

import Model.Carrello;
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
import java.util.ArrayList;

@WebServlet(name = "/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente usr;
        HttpSession session = request.getSession();
        String id_prod, qty_prod;
        if ((id_prod = request.getParameter("id_product")) == null) {  // utente vuole solo visulizzare il carrello
            if ((Utente) session.getAttribute("usrlog") != null) { // loggato
                CarrelloDAO cartDAO = new CarelloDAO();
                Carrello cart = cartDAO.doRetriveCartByUsrId(usr.getId());
                if(cart==null) cart=new Carrello();
                session.setAttribute("carrello", cart);
                session.setAttribute("notifica", "Carrello del utente loggato mostrato correttamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
                dispatcher.forward(request, response);
            } else {
                Carrello cart = (Carrello) session.getAttribute("carrello"); // non loggato
                if (cart == null) {
                    cart = new Carrello();
                    session.setAttribute("carrello",cart);
                    session.setAttribute("notifica","Carrello utente non loggato mostrato correttamente");
                    RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
                    dispatcher.forward(request,response);
                }

                else {
                    session.setAttribute("notifica","Carrello utente non loggato mostrato correttamente");
                    RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
                    dispatcher.forward(request,response);
                }



            }

        }

        else{
                    if((usr=(Utente) session.getAttribute("usrlog"))!=null){ // utente vuole aggiungere prodotto al carello
                                int num_prod= Integer.parseInt(request.getParameter("numProd")); //utente loggato
                                qty_prod= request.getParameter("quntProd");
                                CarelloDAO carelloDAO= new CarrelloDAO();
                                Carrello cart= carelloDAO.getCartbyUsrId(usr.getId());
                                ArrayList<Prodotto> products=cart.getAllProd();
                                boolean check=false;
                                int id_prods=Integer.parseInt(id_prod);
                                int qty= Integer.parseInt(qty_prod);
                                for(Prodotto p:products){
                                                if(p.getID()==id_prod){
                                                            check=true;
                                                            qty+=num_prod;
                                                            p.setQty(qty);
                                                            carelloDAO.updateQtyProd(qty,id_prods,usr.getId());
                                                }
                                }

                                if(check==false){
                                                    Prodotto p=prodottoDAO.getProdbyId(id_prods);
                                                    p.updatyQty(num_prod);
                                                    products.add(p);
                                                    carelloDAO.doUpdateCartbyIdUsr(usr.getID(),p);
                                                    cart.setProdList(products);
                                                    session.setAttribute("carrello",cart);
                                                    session.setAttribute("notifica","Aggiunta prodotto  riuscita");
                                                    RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
                                                    dispatcher.forward(request,response);

                                }

                    }

                    else{// utente non loggato
                            Carrello cart= session.getAttribute("carrello");
                            String numProdotto= request.getParameter("numProd");
                            ProdottoDao prod= new ProdottoDao();
                            Prodotto p=prod.doFindProdID(Integer.parseInt(id_prod));
                                    if(cart!=null){
                                            boolean check=false;
                                        ArrayList<Prodotto> products=cart.getAllProd();

                                            for(int i=0; i<products.size();i++){
                                                        Prodotto x=products.get(i);
                                                           if(p.getId()==x.getId()){
                                                              check=true;
                                                              int qty=products.get(i).getQtyCart();
                                                              qty+=Integer.parseInt(numProdotto);
                                                              products.get(i).setQtyCart(qty);
                                                           }
                                            }

                                            if(check==false){
                                                p.setQtyCart(Integer.parseInt(numProdotto));
                                                products.add(p);
                                                cart.setProdList(products);
                                            }
                                            session.setAttribute("carrello",cart);
                                            session.setAttribute("notifica","Aggiunta prodotto utente non loggato ha avuto sucesso");
                                            RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
                                            dispatcher.forward(request,response);

                                    }

                                    else {
                                            Carrello cart= new Carrello();
                                            p.setQtyCart(Integer.parseInt(numProdotto));
                                            ArrayList<Prodotto> prodottos=cart.getAllProd();
                                            prodottos.add(p);
                                            cart.setProdList(prodottos);
                                            session.setAttribute("carrello",cart);
                                            session.setAttribute("notifica","Aggiunta a carello vuoto per usr non log effettuata");
                                            RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
                                            dispatcher.forward(request,response);
                                    }


                    }
        }
    }
}
*/