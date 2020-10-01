package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SpedizioneDao {
    public SpedizioneDao(){
        super();
    };

    public void doSave(String tipoSpedizione, int IdOrdine){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert  into Shipment(  Type_ship , Id_orderi) value(?,?); ");
                ps.setString(1,tipoSpedizione);
                ps.setInt(2,IdOrdine);
                ps.executeUpdate();
        } catch (SQLException e) {
            throw  new  RuntimeException(e);
        }
    }

    public static ArrayList<Spedizione> getAllUserSped(int id_usr){
                ArrayList<Spedizione> list_sped= new ArrayList<>();
                    try(Connection con=ConPool.getConnection()){
                                    PreparedStatement  ps=con.prepareStatement("SELECT Id_orderi,Id_ship,time_ship from Shipment,Utente where Id_usr=Id_user and Id_user=? order by Id_usr;");
                                    ps.setInt(1,id_usr);
                            ResultSet rs=ps.executeQuery();

                            while(rs.next()){
                                        int id_ordine=rs.getInt(1);
                                        int id_ship=rs.getInt(2);
                                        String type=rs.getString(4);
                                String name_sped="Spedizione del utente con id:"+id_usr+"con id_ordine relativo:";
                                        name_sped+=id_ordine;
                                        Spedizione sped= new Spedizione();
                                        sped.setNomeSpedizione(name_sped);
                                        sped.setIdOrdine(id_ordine);
                                        sped.setIdSpedizione(id_ship);
                                        list_sped.add(sped);
                            }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

        return list_sped;
    }

}
