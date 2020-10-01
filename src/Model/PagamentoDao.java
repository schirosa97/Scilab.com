/*package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class PagamentoDao {
    public  static  int doSave(int id_pagamento, int id_ordine, String nome_pagamento, String tipo_pagamento, Date data_pagamento, double totale_pagamento){
        try(Connection con = ConPool.getConnection();){
            data_pagamento=new Date(new GregorianCalendar().getTimeInMillis());
           PreparedStatement ps = con.prepareStatement("insert into Payment(Id_payment, Id_ord, name_pay, type_pay, date_pay, total_pay) values(?,?,?,?,?,?)");
            ps.setInt(1,id_pagamento);
            ps.setInt(2,id_ordine);
            ps.setString(3, nome_pagamento);
            ps.setString(4, tipo_pagamento);
            ps.setDate(5, data_pagamento);

            int rs = ps.executeUpdate();
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Pagamento doRetrieveByIdOrd(int IDord) {
        PreparedStatement ps = null;
        Pagamento a =new Pagamento();

        try (Connection con = ConPool.getConnection();) {
            ps = con.prepareStatement("select * from Payment where Id_ord=?");
            ps.setInt(1, IDord);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                a.setIdPagamento(rs.getInt("id_pagamento"));
                a.setIdOrdine(rs.getInt("id_ordine"));
                a.setUsername(rs.getString("nome_pagamento"));
                a.setTipoPagameno(rs.getString("tipo_pagamento"));
                a.setData(rs.getDate("data_pagamento"));
                a.setImportoPagamento(rs.getDouble("totale_pagamento"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
}*/
