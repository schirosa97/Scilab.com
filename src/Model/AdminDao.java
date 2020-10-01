package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
            public Utente FindAdminByPassAndName(String name,String password){
                            try(Connection connection= ConPool.getConnection()) {
                                PreparedStatement ps=connection.prepareStatement("SELECT DISTINCT admin_user,adminpasshash,id_admn FROM Admin WHERE admin_user=? and adminpasshash=?; ");
                                ps.setString(1,name);
                                ps.setString(2,password);
                                ResultSet rs=ps.executeQuery();
                                while (rs.next()){
                                            String name_found=rs.getString(1);
                                            String pass_found= rs.getString("Pass_adm");
                                            int id_found=rs.getInt(3);
                                    return new Utente(id_found,name_found,null,pass_found);
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                return null;
            }

            public Utente getAdminById(int id_adm){
                                try (Connection con=ConPool.getConnection()){
                                            PreparedStatement ps=con.prepareStatement("SELECT distinct id_admn,admin_user,adminpasshash from Admin where id_admn=?;");
                                            ps.setInt(1,id_adm);
                                            ResultSet rs=ps.executeQuery();
                                            if(rs.next()){
                                                        int id_adms=rs.getInt(1);
                                                        String user_adm=rs.getString(2);
                                                        String pass=rs.getString(3);
                                                        Utente usr= new Utente();
                                                        usr.setIdUtente(id_adms);
                                                        usr.setNome(user_adm);
                                                        usr.setPassword(pass);
                                                        return usr;
                                            }



                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                return null;
            }

}
