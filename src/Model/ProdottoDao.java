package Model;

import Controller.MyExceptionServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDao {

    public  ArrayList<Prodotto> getAllProdotti() {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Product;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String nome = rs.getString(2);
                double prezzo = rs.getDouble(3);
                String descrizione = rs.getString(4);
                int quantita = rs.getInt(5);
                prodotti.add(new Prodotto(id, nome, prezzo, descrizione,quantita));
            }

            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Prodotto> doFindProdottoAnteprima() {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Product order by id_product DESC limit 0,6;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String nome = rs.getString(2);
                double prezzo = rs.getDouble(3);
                String descrizione = rs.getString(4);
                int quantita = rs.getInt(5);
                prodotti.add(new Prodotto(id, nome, prezzo, descrizione, quantita));
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Prodotto> doFindProdottoNovita() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Product order by Id_product DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String nome = rs.getString(2);
                double prezzo = rs.getDouble(3);
                String descrizione = rs.getString(4);
                int quantita = rs.getInt(5);
                prodotti.add(new Prodotto(id, nome, prezzo, descrizione,quantita));
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Prodotto doFindProdottoConId(int idTmp) {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Product WHERE Id_product=?");
            ps.setInt(1, idTmp);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString(1);
                String nome = rs.getString(2);
                double prezzo = rs.getDouble(3);
                String descrizione = rs.getString(4);
                int quantita = rs.getInt(5);
                return new Prodotto(id, nome, prezzo, descrizione,quantita);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Prodotto> doFindProdottoCategoriaConId(int idTmp) {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
            PreparedStatement ps = con.prepareStatement("SELECT P.Id_product,P.name_product,P.price,P.short_descripton, C.category_name\n" +
                    "from Product as P join categoryprod on P.Id_product=categoryprod.Id_prod\n" +
                    "join category as C on C.Id_category=categoryprod.Id_Cat \n" +
                    "where C.Id_category=?;");
            ps.setInt(1, idTmp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String nome = rs.getString(2);
                double prezzo = rs.getDouble(3);
                String descrizione = rs.getString(4);
                int quantita = rs.getInt(5);
                prodotti.add(new Prodotto(id, nome, prezzo, descrizione,quantita));
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Categoria> doFindCategoriaConProdottoId(int idTmp) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Categoria> categoria = new ArrayList<Categoria>();
            PreparedStatement ps = con.prepareStatement(("select CS.Id_category,CS.category_name\n" +
                    "from Product as P join CategoryProd as C on P.Id_product=C.Id_prod\n" +
                    "join Category as CS on CS.Id_category=C.Id_Cat\n" +
                    "where P.Id_product=?;"));
            ps.setInt(1, idTmp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                String nome = rs.getString(2);
                Categoria cat = new Categoria(num, nome);
                categoria.add(cat);
            }
            return categoria;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel trovare le categorie");
        }
    }

    public static void doSaveCategorieProdotto(int idProdotto, int idCategorie) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO CategoryProd (`Id_prod`, `Id_Cat`) VALUES (?, ?);");
            ps.setInt(1, idProdotto);
            ps.setInt(2, idCategorie);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("errore nell' inserimento di relativi prodotti e categorie");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Prodotto> doFindProdottoConNome(String ricerca) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
            PreparedStatement ps = con.prepareStatement("SELECT P.Id_product,P.name_product,P.price,P.short_descripton\n" +
                    "from Product as P WHERE name_product like ?;");
            ps.setString(1, "%" + ricerca + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String nome = rs.getString(2);
                double prezzo = rs.getDouble(3);
                String descrizione = rs.getString(4);
                int quantita = rs.getInt(5);
                prodotti.add(new Prodotto(id, nome, prezzo, descrizione,quantita));
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doDelete(int parseInt) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE `Id_product` = ?;");
            ps.setInt(1, parseInt);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("errore nella cancellazione");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int doSave(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Product (`name_product`, `price`, `short_descripton`) VALUES (?, ?, ?);");
            ps.setString(1, prodotto.getNome());
            ps.setDouble(2, prodotto.getPrezzo());
            ps.setString(3, prodotto.getDesc());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("errore inserimento");
            }
                ps = con.prepareStatement("select Id_product \n" +
                        "from Product\n" +
                        "order by Id_product Desc\n" +
                        "limit 0,1;");
                ResultSet rs = ps.executeQuery();
                rs.next();
                int numTmp = rs.getInt(1);
                return numTmp;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doEdit(Prodotto prodotto){
        try(Connection con = ConPool.getConnection()){
                PreparedStatement ps = con.prepareStatement("UPDATE Product SET name_product=?, price=?, short_descripton=? WHERE Id_product=?");
                ps.setString(1,prodotto.getNome());
                ps.setDouble(2, prodotto.getPrezzo());
                ps.setString(3, prodotto.getDesc());
                ps.setString(4, prodotto.getIdprod());
                    if(ps.executeUpdate()!=1){
                        throw new RuntimeException("errore edit");
                    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doDeleteCategorieProdotto(int idProdotto, int idCategoria){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM `CategoryProd` WHERE `Id_prod`=? and`Id_Cat`=?;\n");
            ps.setInt(1,idProdotto);
            ps.setInt(2, idCategoria);
                if(ps.executeUpdate()!=1){
                    throw new RuntimeException("Errore nella cancellazione dei relativi prodotti e categorie");
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteWithID(int id_product)  {
                    try(Connection connection=ConPool.getConnection()) {
                        PreparedStatement ps= connection.prepareStatement("DELETE FROM Product WHERE Id_product=?");
                        ps.setInt(1,id_product);
                         if(ps.executeUpdate()!=1){
                                        throw new RuntimeException("Errore nella cancellazione del prodotto");
                         }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
    }

    public Prodotto doFindProdID(int id_prod){
                    try(Connection con=ConPool.getConnection()) {
                                    PreparedStatement ps= con.prepareStatement("SELECT distinct Id_product,name_product,short_descripton,price FROM Product WHERE  Id_product=?");
                                    ps.setInt(1,id_prod);
                                    ResultSet rs=ps.executeQuery();
                                    while(rs.next()){
                                            int id=rs.getInt(1);
                                            String nome=rs.getString(2);
                                            String desc=rs.getString(3);
                                            String path=rs.getString(4);
                                            double price= rs.getDouble(5);
                                            Prodotto prod= new Prodotto(path,nome,price,desc, id);
                                            return prod;
                                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
    }

    public List<Prodotto> getAllProdHome(){
                            List<Prodotto> home_list= new ArrayList<>();
                            PreparedStatement ps= null;
                            try(Connection connection=ConPool.getConnection()) {
                                    ps=connection.prepareStatement("SELECT Id_product,name_product,short_descripton,price,qty_product FROM Product order by price limit 0,4;");
                                    ResultSet rs=ps.executeQuery();
                                    while(rs.next()){
                                                int id=rs.getInt(1);
                                                String name=rs.getString(2);
                                                String desc= rs.getString(3);
                                                String img=rs.getString(4);
                                                double price=rs.getDouble(5);
                                                int qty=rs.getInt(6);
                                                Prodotto prodotto=new Prodotto(Integer.toString(id),name,price,desc,img,qty);
                                                home_list.add(prodotto);
                                    }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            return home_list;
    }


    public ArrayList<Prodotto> SearchProdByName(String name_prod){
                                    ArrayList<Prodotto> search_prod=new ArrayList<>();
                                    PreparedStatement ps=null;
                                    try(Connection connection=ConPool.getConnection()) {
                                                ps=connection.prepareStatement("SELECT Id_product,name_product,short_descripton,qty_product,price,bookmarked from Product where name_product=?;");
                                                ResultSet rs=ps.executeQuery();
                                                if(rs== null) throw new MyExceptionServlet("errore nella query di ricerca prodotto");
                                                else{
                                                            while (rs.next()){
                                                                int id=rs.getInt(1);
                                                                String name=rs.getString(2);
                                                                String desc=rs.getString(3);
                                                                String img=rs.getString(4);
                                                                int qty=rs.getInt(5);
                                                                double price=rs.getDouble(6);
                                                                Prodotto p=new Prodotto(id,name,desc,img,qty,price);
                                                                search_prod.add(p);
                                                            }
                                                }

                                    } catch (SQLException | MyExceptionServlet e) {
                                        e.printStackTrace();
                                    }
                                    return search_prod;
    }
}
