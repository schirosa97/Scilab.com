package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Ordine {
    private double prezzoTotale;
    private ArrayList<Prodotto> prodotti;
    private int idOrdine;
    private String nomeOrdine, statusOrdine;
    private Date dataEmissione;
    public Ordine(){
        prezzoTotale=0;
        prodotti= new ArrayList<Prodotto>();
        dataEmissione = new Date();
    }



    public ArrayList<Prodotto> getProdotti(){
        return prodotti;
    }
    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public void addProdotti(Prodotto prod){
        prodotti.add(prod);
    }

    public int getIdOrdine(){
        return idOrdine;
    }
    public void setIdOrdine(int idOrdine){
        this.idOrdine=idOrdine;
    }

    public String getNomeOrdine(){
        return nomeOrdine;
    }
    public void setNomeOrdine(String nomeOrdine){
        this.nomeOrdine=nomeOrdine;
    }

    public String getStatusOrdine(){
        return statusOrdine;
    }
    public void setStatusOrdine(String statusOrdine){
        this.statusOrdine=statusOrdine;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public Date getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(Date dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "prezzoTotale=" + prezzoTotale +
                ", prodotti=" + prodotti +
                ", idOrdine=" + idOrdine +
                ", nomeOrdine='" + nomeOrdine + '\'' +
                ", statusOrdine='" + statusOrdine + '\'' +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}
