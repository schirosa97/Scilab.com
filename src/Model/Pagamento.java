package Model;

import java.sql.Date;
import java.util.GregorianCalendar;

public class Pagamento  {
    private int idPagamento, idOrdine;
    private String tipoPagameno, username;
    private Date data;
    private double importoPagamento;

    public Pagamento(int idPagamento, int idOrdine, String tipoPagameno, String username, Date data, double importoPagamento) {
        super();
        data= new Date(data.getTime()); //da eliminare!!
        this.idPagamento= idPagamento;
        this.idOrdine=idOrdine;
        this.tipoPagameno=tipoPagameno;
        this.data=data;
        this.username=username;
        this.importoPagamento=importoPagamento;
    }

    public Pagamento() {

    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public double getImportoPagamento() {
        return importoPagamento;
    }

    public void setImportoPagamento(double importoPagamento) {
        this.importoPagamento = importoPagamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getTipoPagameno() {
        return tipoPagameno;
    }

    public void setTipoPagameno(String tipoPagameno) {
        this.tipoPagameno = tipoPagameno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", idOrdine=" + idOrdine +
                ", tipoPagameno='" + tipoPagameno + '\'' +
                ", username='" + username + '\'' +
                ", data=" + data +
                ", importoPagamento=" + importoPagamento +
                '}';
    }
}

