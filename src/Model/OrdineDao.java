package Model;


import Controller.MyExceptionServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class OrdineDao {
    public static void addOrdine(int idProdotto,String pagamento, int quantità, double prezzo, int idOrdine ){
        try(Connection con = ConPool.getConnection()){
            GregorianCalendar dataTmp = new GregorianCalendar(); //modifica perchè nel db è stato adottato il tipo date
            int giorno=dataTmp.get(GregorianCalendar.DAY_OF_MONTH);
            int mese=dataTmp.get(GregorianCalendar.MONTH);
            int anno=dataTmp.get(GregorianCalendar.YEAR);
            String data = giorno+"/"+mese+"/"+anno;

                PreparedStatement ps = con.prepareStatement("INSERT INTO OrderProd(Id_order,Id_prod)\n"+
                        "VALUES(?,?)");
                ps.setInt(1,idOrdine);
                ps.setInt(2, idProdotto);
                    if(ps.executeUpdate()==0){
                        throw new RuntimeException("errore nel collegamento tra prodotto e ordine");
                    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Ordine> getAllOrdine(int idUtente){
        try(Connection con = ConPool.getConnection()){
            int tmp=-1;
            double prezzoTotale=0.0;
            Ordine ordine = null;
            int indice=-1;
                ArrayList<Ordine> listOrdine = new ArrayList<Ordine>();
                PreparedStatement ps = con.prepareStatement("Select Orders.Id_orders,Product.Id_product,Product.name_product,Product.price\n" +
                        "from Utente join Orders on Utente.Id_user=Orders.Id_us\n" +
                        "join OrderProd on Orders.Id_orders=OrderProd.Id_order\n" +
                        "join Product on Product.Id_product=OrderProd.Id_prod\n"+
                        "where Utente.Id_user=?;");
                ps.setInt(1,idUtente);
                ResultSet rs = ps.executeQuery();

                    while (rs.next()){
                        String id_prodotto = rs.getString(1);
                        String nome = rs.getString(2);
                        double prezzo = rs.getDouble(3);
                            Prodotto prodotto = new Prodotto(id_prodotto,nome,prezzo,null,0);
                            prodotto.setIdprod(""+id_prodotto+"");

                                double subTot = rs.getDouble(3);
                                int quantita = rs.getInt(2);
                                    Prodotto prodottoquantita = new Prodotto();
                                    prodottoquantita.setSubTotale(subTot);

                                int idOrdine = rs.getInt(1);
                                    if(idOrdine!=tmp){
                                        String statusOrdine = rs.getString(5);
                                        Date dataEmissione = rs.getDate(6);
                                            prezzoTotale=0.0;
                                            prezzoTotale += quantita * prezzo;

                                                ordine= new Ordine();
                                                    ordine.setStatusOrdine(statusOrdine);
                                                    ordine.setDataEmissione(dataEmissione);
                                                    ordine.setIdOrdine(idOrdine);
                                                    ordine.addProdotti(prodottoquantita);
                                                    ordine.setPrezzoTotale(prezzoTotale);
                                                        listOrdine.add(ordine);

                                                            indice++;
                                                            tmp=idOrdine;
                                    }else{
                                        prezzoTotale += quantita * prezzo;
                                        listOrdine.get(indice).setPrezzoTotale(prezzoTotale);
                                        listOrdine.get(indice).addProdotti(prodottoquantita);
                                    }
                    }
                    return listOrdine;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getOrdine(int idutente){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into 'Orders'('Id_us') values (?);");
            ps.setInt(1,idutente);

                int idOrdine=0;
                if(ps.executeUpdate()==0){
                    throw new RuntimeException("errore di inserimento dell' ordine");
                }
                ps = con.prepareStatement("select Id_orders from Orders where Id_us=? order by Id_orders desc limit 0,1");
                ps.setInt(1,idutente);
                ResultSet rs = ps.executeQuery();
                rs.next();
                idOrdine = rs.getInt(1);
                    return idOrdine;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        public void doSave(int usr_id,double tot_ord,String status,Date data){
                            try(Connection con= ConPool.getConnection()) {
                                            String name_ordine="Ordine dell'utente con id:"+usr_id;
                                            PreparedStatement ps= con.prepareStatement("INSERT INTO Orders('Id_us',orders_name,orders_date,status_order,tot_price) values(?,?,?,?,?);");
                                            ps.setInt(1,usr_id);
                                            ps.setString(2,name_ordine);
                                            ps.setDate(3, (java.sql.Date) data);
                                            ps.setString(4,status);
                                            ps.setDouble(5,tot_ord);
                                            if(ps.executeUpdate()!=1)
                                                throw new MyExceptionServlet("errore nel salvattaggio del ordine");
                            } catch (SQLException | MyExceptionServlet e) {
                                e.printStackTrace();
                            }
        }


        public ArrayList<Ordine> getAllOrderByUsrId(int id_usr){
                    ArrayList<Ordine> list_usr= new ArrayList<>();
                    PreparedStatement ps= null;
                    double subprice=0.0;
                    double tot_price=0.0;
                    int check_ord=-1;
                    int index=0;
                    try(Connection connection=ConPool.getConnection()) {
                                        ps=connection.prepareStatement("SELECT orders_name,orders_date,Id_orders,status_order,Id_us,Id_product,name_product,price,predef_img,short_descripton,Product.qty_product from Orders,Product,OrderProd,Utente where Id_product=Id_prod and Id_order=Id_orders and Id_user=Id_us and Id_user=?;");
                                        ps.setInt(1,id_usr);
                                        ResultSet rs=ps.executeQuery();
                                        if(rs==null) throw  new MyExceptionServlet("Errore nella query");
                                        while(rs.next()){
                                                    int id_order=rs.getInt(3);
                                                    String name=rs.getString(1);
                                                    Date date=rs.getDate(2);
                                                    if(check_ord!=id_order){
                                                                        String status=rs.getString(4);
                                                                        int id_user=rs.getInt(5);
                                                                        Ordine order= new Ordine();
                                                                        order.setDataEmissione(date);
                                                                        order.setIdOrdine(id_order);
                                                                        order.setNomeOrdine(name);
                                                                        order.setStatusOrdine(status);
                                                                        int id_prod=rs.getInt("Id_product");
                                                                        String name_p=rs.getString("name_product");
                                                                        subprice=rs.getDouble("price");
                                                                        String pathimg=rs.getString("predef_img");
                                                                        String desc=rs.getString(10);
                                                                        int qty_prod=rs.getInt(11);
                                                                        Prodotto prod= new Prodotto();
                                                                        prod.setNome(name_p);
                                                                        prod.setDesc(desc);
                                                                        prod.setIdprod(Integer.toString(id_prod));
                                                                        prod.setPathimageprodotto(pathimg);
                                                                        prod.setQuantProdotto(qty_prod);
                                                                        tot_price+= subprice*qty_prod;
                                                                        order.setPrezzoTotale(tot_price);
                                                                        order.addProdotti(prod);
                                                                        check_ord=id_order;
                                                                        list_usr.add(order);
                                                                        index++;


                                                    }
                                                    else{
                                                        int id_prod=rs.getInt("Id_product");
                                                        String name_p=rs.getString("name_product");
                                                        subprice=rs.getDouble("price");
                                                        String pathimg=rs.getString("predef_img");
                                                        String desc=rs.getString(10);
                                                        int qty_prod=rs.getInt(11);
                                                        Prodotto prod= new Prodotto();
                                                        prod.setNome(name_p);
                                                        prod.setDesc(desc);
                                                        prod.setIdprod(Integer.toString(id_prod));
                                                        prod.setPathimageprodotto(pathimg);
                                                        prod.setQuantProdotto(qty_prod);
                                                        tot_price+= subprice*qty_prod;
                                                        list_usr.get(index).addProdotti(prod);
                                                        list_usr.get(index).setPrezzoTotale(tot_price);

                                                    }


                                        }
                    } catch (SQLException | MyExceptionServlet e) {
                        e.printStackTrace();
                    }

                    return list_usr;
        }

        public static void doSaveOrdProd(int id_usr,int id_prod){
                    try(Connection con=ConPool.getConnection()) {
                        PreparedStatement ps1=con.prepareStatement("INSERT INTO OrderProd(Id_order, Id_prod) values (?,?);");
                        PreparedStatement ps2=con.prepareStatement("SELECT distinct Id_orders from Orders where Id_us=?;");
                        ps2.setInt(1,id_usr);
                        ResultSet rs2= ps2.executeQuery();
                        int id_order=rs2.getInt(1);
                        ps1.setInt(1,id_order);
                        ps1.setInt(2,id_prod);
                        if(ps1.executeUpdate()!=1) throw new MyExceptionServlet("Errore nel salvataggio Prodotto-ordine");

                    } catch (SQLException | MyExceptionServlet e) {
                        e.printStackTrace();
                    }
        }

}
