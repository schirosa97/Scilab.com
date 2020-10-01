package Model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Prodotto {


    private int num, quantita;
    private boolean preferiti;
    private double larghezza;
    private String piccola_descr;

    public Prodotto(int num, String nome, double prezzo, int quantita, boolean preferiti, double altezza, double larghezza, String piccola_dscr) {
        this.num=num;
        this.nome=nome;
        this.prezzo=prezzo;
        this.quantita=quantita;
        this.preferiti=preferiti;
        this.altezza=altezza;
        this.larghezza=larghezza;
        this.piccola_descr=piccola_dscr;
    }

    public String getIdprod() {
        return idprod;
    }

    public void setIdprod(String idprod) {
        this.idprod = idprod;
    }

    private String idprod,desc,descrizione_dettagliata,pathimageprodotto,nome;
    private double prezzo, altezza, peso;
    private int quantProdotto;
    private double subTotale;

    public Prodotto(String nome , double prezzo, String descrizione, String pathimageprodotto, String descrizione_dettagliata, double altezza, double peso) {
        this.nome=nome;
        this.desc=descrizione;
        this.prezzo=prezzo;
        this.pathimageprodotto=pathimageprodotto;
        this.descrizione_dettagliata=descrizione_dettagliata;
        this.altezza=altezza;
        this.peso=peso;
    }

    public Prodotto(String id_prodotto, String nome, double prezzo, String descrizione, int quantProdotto) {
        this.idprod=id_prodotto;
        this.nome=nome;
        this.prezzo=prezzo;
        this.desc=descrizione;
        this.quantProdotto=quantProdotto;
        this.subTotale=0.0;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Prodotto(String id_prodotto, String nome, double price, String descrizione, String pathimg) {
                this.idprod=id_prodotto;
                this.desc=descrizione;
                this.pathimageprodotto=pathimg;
                this.nome=nome;
                this.prezzo=price;

    }

    public Prodotto() {

    }

    public Prodotto(int id, String name, String desc, String img, int qty, double price) {
        this.idprod=Integer.toString(id);
        this.nome=name;
        this.desc=desc;
        this.pathimageprodotto=img;
        this.quantProdotto=qty;
        this.prezzo=price;

    }

    public Prodotto(String id, String name, double price, String desc, String img, int qty) {
                    this.idprod=id;
                    this.nome=name;
                    this.prezzo=price;
                    this.desc=desc;
                    this.pathimageprodotto=img;
                    this.quantProdotto=qty;
    }



    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public double getPrezzo(){
        return prezzo;
    }
    public void setPrezzo(double prize){
        this.prezzo=prize;
    }
    public String getPathimageprodotto(){
        return pathimageprodotto;
    }
    public void setPathimageprodotto(String path){
        this.pathimageprodotto=path;
    }
    public String getDescrizione_dettagliata(){
        return descrizione_dettagliata;
    }
    public void setDescrizione_dettagliata(String des_dett){
        this.descrizione_dettagliata=des_dett;
    }
    public double getAltezza(){
        return altezza;
    }
    public void setAltezza(double altezza){
        this.altezza=altezza;
    }
    public double getPeso(){
        return peso;
    }
    public void setPeso(double peso){
        this.peso=peso;
    }

    public int getQuantProdotto(){
        return quantProdotto;
    }

    public void setQuantProdotto(int quantProdotto){
        this.quantProdotto=quantProdotto;
    }

    public String getSubTotale(){
        Locale locale=new Locale("ITALIAN");
        String pattern = "###.##";

        DecimalFormat decimalFormat =(DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        String format = decimalFormat.format(subTotale);
        return format;
    }
    public void  setSubTotale(double subTotale){
        this.subTotale=subTotale;
    }

}
