package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    public static List<Categoria> getAllCategoria(){
        try(Connection con = ConPool.getConnection()){
            ArrayList<Categoria> categorie = new ArrayList<Categoria>();
            PreparedStatement ps = con.prepareStatement("select Id_category,category_name,cat_description,img_category from category");
            ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    int id=rs.getInt(1);
                    String nome = rs.getString(2);
                    String descripton= rs.getString(3);
                    String pathimg=rs.getString(4);
                    categorie.add(new Categoria(id, nome,descripton, pathimg));
                }
                return categorie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doDelete(int idCategoria){
        try(Connection con = ConPool.getConnection()){
            ArrayList<Categoria> categorie = new ArrayList<Categoria>();
            PreparedStatement ps = con.prepareStatement("delete  from Category where Id_category=?");
            ps.setInt(1,idCategoria);
                if(ps.executeUpdate()!=1) {
                    throw new RuntimeException("errore nella cancellazione");
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int doSave(String nomeCategoria){
        try(Connection con = ConPool.getConnection()){
            ArrayList<Categoria> categorie = new ArrayList<Categoria>();
            PreparedStatement ps = con.prepareStatement("insert into Category ('category_name') values(?)");
            ps.setString(1,nomeCategoria);
                if(ps.executeUpdate()!=1){
                    throw new RuntimeException("errore inserimento");
                }
                ps=con.prepareStatement("select id_category from Category order by id_category desc ");
                ResultSet rs = ps.executeQuery();
                rs.next();
                int num=rs.getInt(1);
                return num;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Prodotto> doFindProdottoConIdCategoria(int idTmp){
        try(Connection con = ConPool.getConnection()){
            ArrayList<Prodotto> prod = new ArrayList<Prodotto>();
            PreparedStatement ps = con.prepareStatement("select P.Id_product,P.name_product,P.price,P.qty_product,P.bookmarked,P.height_prod,P.width_prod,P.short_descripton from Product as p,category,categoryprod where p.Id_product=categoryprod.Id_prod and category.Id_category=categoryprod.Id_Cat and category.Id_category=?");
            ps.setInt(1, idTmp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int num=rs.getInt(1);
                String nome=rs.getString(2);
                double prezzo=rs.getDouble(3);
                int quantita=rs.getInt(4);
                boolean preferiti=rs.getBoolean(5);
                double altezza=rs.getDouble(6);
                double larghezza=rs.getDouble(7);
                String piccola_dscr=rs.getString(8);
                Prodotto prd = new Prodotto(num, nome, prezzo, quantita, preferiti, altezza, larghezza, piccola_dscr );
                prod.add(prd);
            }
            return prod ;
        } catch (SQLException e) {
            throw new RuntimeException("Errore");
        }
    }

    public static List<Categoria> doFindNotInCategorie(int idTmp){
        try(Connection con = ConPool.getConnection()){
            ArrayList<Categoria> categorie = new ArrayList<Categoria>();
            PreparedStatement ps = con.prepareStatement("select *\n" +
                    "from Category \n" +
                    "where Category.Id_category not in(select CP.Id_Cat\n" +
                    "from Product as P join CategoryProd AS CP on P.Id_product=CP.Id_prod\n" +
                    "where P.Id_product=?)");
            ps.setInt(1, idTmp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int num = rs.getInt(1);
                String nome = rs.getString(2);
                Categoria categoria = new Categoria(num, nome);
                categorie.add(categoria);
            }
            return categorie;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Prodotto> doGetNotInProdotti(int idTmp){
        try(Connection con = ConPool.getConnection()){
            ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
            PreparedStatement ps = con.prepareStatement("select Product.Id_product,Product.name_product\n" +
                    "from Product \n" +
                    "where Product.Id_product not in(select CP1.Id_prod from Category as C join CategoryProd as CP1 on C.Id_category=CP1.Id_Cat where C.Id_category=?);");
            ps.setInt(1,idTmp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String numIdprodotto = rs.getString(1);
                String nomeProdotto = rs.getString(2);
                Prodotto prodotto = new Prodotto(numIdprodotto,nomeProdotto,0,null,0);
                prodotti.add(prodotto);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int doGetLastId() throws SQLException {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT Category.Id_category FROM Category ORDER BY Id_category DESC limit 0,1;");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

            public void doDeleteByID(int id_cat){
                                try(Connection connection=ConPool.getConnection()) {
                                                    PreparedStatement ps=connection.prepareStatement("DELETE FROM Category WHERE Id_category=?;");
                                                    ps.setInt(1,id_cat);
                                                    if(ps.executeUpdate()!=1)
                                                                throw new RuntimeException("errore nella cancellazione");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
            }

            public Categoria doFindByID(int id_cat) {
                try (Connection connection = ConPool.getConnection()) {
                    PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT Id_category Nome_cat,cat_description,img_category FROM Category where Id_category=?;");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String name = rs.getString(2);
                        String desc = rs.getString(3);
                        String image = rs.getString(4);
                        return new Categoria(id_cat, name, desc, image);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return null;
            }




            }




