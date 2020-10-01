package Model;

import Controller.MyExceptionServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatProdDao {
                public void doSave(int id_prod,int id_cat){
                        try(Connection con= ConPool.getConnection()) {
                            PreparedStatement ps=con.prepareStatement("INSERT INTO CategoryProd ('Id_prod','Id_Cat') \n"+
                                    "VALUES (?,?)");
                            ps.setInt(1,id_prod);
                            ps.setInt(2,id_cat);
                            if(ps.executeUpdate()!=1)
                                throw new RuntimeException("errore nell'inserimento");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                }

                public ArrayList<Categoria> getAllCategoriesFromProdID(int id_prod){
                                ArrayList<Categoria> cat_prod= new ArrayList<>();
                                try(Connection con= ConPool.getConnection()) {
                                    PreparedStatement ps=con.prepareStatement("SELECT DISTINCT category_name,cat_description,img_category,Id_category \n"+
                                            "FROM Category,CategoryProd,Product WHERE Id_product=? and Id_category=Id_Cat and Id_product=Id_prod"+
                                            " ORDER BY category_name ");
                                    ps.setInt(1,id_prod);
                                    ResultSet rs=ps.executeQuery();
                                    while (rs.next()){
                                                        Categoria cat= new Categoria(rs.getInt("Id_category"),rs.getString("category_name"),rs.getString(2),rs.getString(3));
                                                        cat_prod.add(cat);
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                return cat_prod;
                }

                public void doDeleteWithID(int id_catmint , int id_prod){
                                try(Connection connection=ConPool.getConnection()) {
                                                    PreparedStatement ps= connection.prepareStatement("DELETE FROM CategoryProd where Id_Cat=? and Id_prod=?");
                                                    ps.setInt(1,id_catmint);
                                                    ps.setInt(2,id_prod);
                                                    if(ps.executeUpdate()!=1)
                                                                throw new MyExceptionServlet("errore nella cancellazione");
                                } catch (SQLException | MyExceptionServlet e) {
                                    e.printStackTrace();
                                }
                }


}
