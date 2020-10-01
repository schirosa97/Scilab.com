package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreferitiDao {

    public List<Prodotto> doRetriveByKey(int idCli){
        PreparedStatement ps = null;
        ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
            try(Connection con = ConPool.getConnection()){
                ps = con.prepareStatement("select Product.Id_product,Product.name_product,price,short_descripton,bookmarked from Product join Preference on Preference.id_prod=Product.Id_product join Utente on Preference.Id_client=Utente.Id_user where Preference.Id_client=?;");
                ps.setInt(1,idCli);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    Prodotto a = new Prodotto();
                    a.setIdprod(rs.getString("id_prodotto"));
                    a.setNome(rs.getString("nome"));
                    a.setPrezzo(rs.getDouble("prezzo"));
                    a.setDesc(rs.getString("descrizione"));
                    lista.add(a);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return lista;
    }

    public Prodotto doRetrieveByIdList(int id){
        PreparedStatement ps = null;
        try(Connection con = ConPool.getConnection()){
            ps= con.prepareStatement("select Id_product, name_product, short_descripton from Product where Product.Id_product=?;");
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
                if(res.next()){
                    Prodotto a = new Prodotto();
                    a.setIdprod(res.getString("id_prd"));
                    a.setNome(res.getString("nome"));
                    a.setPrezzo(res.getDouble("prezzo"));
                    a.setDesc(res.getString("descrizione"));
                    return a;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int doSave(int IdCli, int IdPrd){
        PreparedStatement ps = null;
        try(Connection con = ConPool.getConnection()){
            ps = con.prepareStatement("insert into Preference(Id_client,Id_prod) values(?,?); ");
            ps.setInt(1,IdCli);
            ps.setInt(2,IdPrd);
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void deleteProdotto(int idCliente, int idProdotto){
        PreparedStatement ps = null;
        try(Connection con = ConPool.getConnection()){
            ps = con.prepareStatement("delete  from Preference where Id_prod=? and Id_client=?;");
            ps.setInt(1,idProdotto);
            ps.setInt(2,idCliente);
                ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Prodotto> doRetrivePrefByUserId(int usr_id){
        ArrayList<Prodotto> lista_pref=new ArrayList<>();
        PreparedStatement ps= null;
            try (Connection con= ConPool.getConnection()){
                ps=con.prepareStatement("SELECT Id_product,name_product,short_descripton,predef_img,price,bookmarked from Product,Preference,Utente where Utente.Id_user=? and Preference.Id_prod=Product.Id_product and Utente.Id_user=Preference.Id_client and bookmarked=true ORDER BY name_product;");
                ps.setInt(1,usr_id);
                ResultSet rs= ps.executeQuery();
                while(rs.next()){
                            int id= rs.getInt(1);
                            String name=rs.getString(2);
                            String desc=rs.getString(3);
                            String imgpath=rs.getString(4);
                            double prezzo=rs.getDouble(5);
                            Prodotto prod=new Prodotto(imgpath,name,prezzo,desc, id);
                            lista_pref.add(prod);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return lista_pref;

    }

}
