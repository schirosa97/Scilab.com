/*package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloDao {
  /*  public static ArrayList<Prodotto> doRetriveByClient(String idCliente) throws SQLException {
        PreparedStatement ps = null;
        ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
        try (Connection con = ConPool.getConnection()) {
            try {
                ps = con.prepareStatement("select  Shipping_cart.Id_sc, Shipping_cart.Id_usr, Product.Id_product, name_product, price, short_description, Category.Id_category, Category_name,Shipping_cart.qty_product\\r\\n\" + \n" +
                        "\t\t\t\t\t\"\t\t\t\t\t\t\tfrom Shipping_cart \\r\\n\" + \n" +
                        "\t\t\t\t\t\"\t\t\t\t\t\t\t\tjoin Product on Shipping_cart.Id_sc=Product.Id_shipcart \\r\\n\" + \n" +
                        "\t\t\t\t\t\"\t\t\t\t\t\t\t\t\twhere CategoryProd.Id_Cat=Category.Id_category and CategoryProd.Id_prod=Product.Id_product \\r\\n\" + \n" +
                        "\t\t\t\t\t\"\t\t\t\t\t                  and Shipping_cart.Id_usr=?;");
                int id;
                id = Integer.parseInt(idCliente);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) { //stampare il nome della categoria del prodotto mi serve il set che non ho
                    Prodotto a = new Prodotto();
                    a.setID(rs.getString("id_product"));
                    a.setNome(rs.getString("nome"));
                    a.setPrezzo(rs.getDouble("prezzo"));
                    a.setDescrizione(rs.getString("descrizione"));
                    a.setQuantProdotto(rs.getInt("quantità"));
                    lista.add(a);
                }
                return lista;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Prodotto doRetriveByCarrello(int idProdotto){
        PreparedStatement ps = null;
        Prodotto p = new Prodotto();
            try(Connection con = ConPool.getConnection()){
                ps = con.prepareStatement("select Product.Id_product, name_product, price, short_descripton,   Id_category,  from Product, CategoryProd where Id_product=?;");
                ps.setInt(1,idProdotto);
                    ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            Prodotto a = new Prodotto();
                                a.setID(rs.getString("id_product"));
                                a.setNome(rs.getString("nome"));
                                a.setPrezzo(rs.getDouble("prezzo"));
                                a.setDescrizione(rs.getString("descrizione"));
                                return a;
                        }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
    }

    public static void deleteProdotto(int idCliente, int idProdotto){
        PreparedStatement ps = null;
        try(Connection con = ConPool.getConnection()){
            ps=con.prepareStatement("delete from Shipping_cart where Id_prd=? and Id_usr=?;");
                ps.setInt(1,idProdotto);
                ps.setInt(2,idCliente);
                    ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProdottoOrdine(int idCliente){
        PreparedStatement ps = null;
        try(Connection con = ConPool.getConnection()){
            ps=con.prepareStatement("delete from Shipping_cart where Id_usr=?");
                ps.setInt(1,idCliente);
                    ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int UpdateQuantità(int idCliente, int idProdotto, int quantita){
        PreparedStatement ps = null;
        try(Connection con = ConPool.getConnection()){
            ps = con.prepareStatement("update Shipping_cart set qty_product=? where Id_prd=? and Id_usr=?;");
            ps.setInt(1,quantita);
            ps.setInt(2,idProdotto);
            ps.setInt(3,idCliente);
            int res = ps.executeUpdate();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int doSave(int idCliente, int idProdotto, int quantitaCarrello, int idCarrello){
        PreparedStatement ps = null;
        try(Connection con = ConPool.getConnection()){
            ps=con.prepareStatement("insert into Shipping_cart(Id_cat,Id_usr,Id_prd,qty_product) values(?,?,?,?)");
            ps.setInt(1,idCarrello);
            ps.setInt(2,idCliente);
            ps.setInt(3,idProdotto);
            ps.setInt(4,quantitaCarrello);
                int res = ps.executeUpdate();
                return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Carrello doRetrieveByUser(int idCliente){
        PreparedStatement ps = null;
        try(Connection con = ConPool.getConnection()){
            ps = con.prepareStatement("select Id_sc , Id_usr from Shipping_cart where  Id_usr=?;");
            ps.setInt(1,idCliente);
            ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Carrello cart = new Carrello();
                    cart.setIdcarrello(rs.getInt("id_carrello"));
                    cart.getIdprodotto(rs.getInt("id_cliente"));
                    return cart;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Carrello getCarrelloByUser(int idUtente){
        PreparedStatement ps;
        try(Connection con = ConPool.getConnection()){
            ps = con.prepareStatement("select Id_sc, Id_usr, Id_prd, name_product, price,short_description from Shipping_cart join Product on Shipping_cart.Id_prd = Product.Id_prd where Id_usr=?;");
            ps.setInt(1,idUtente);
            ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
            Carrello cart = new Carrello();
            ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Prodotto e = new Prodotto();
                    cart.setIdcarrello(rs.getInt("id_carrello"));
                    cart.setIdutente(rs.getInt("id_cliente"));
                    e.setNome(rs.getString(4));
                    e.setPrezzo(rs.getDouble(6));
                    e.setDescrizione(rs.getString(7));
                    listaProdotti.add(e);
                }
                cart.setListaProdotti(listaProdotti);
                return cart;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }



}*/
