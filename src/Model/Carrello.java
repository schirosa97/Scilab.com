package Model;

import java.util.ArrayList;

public class Carrello {
    private int idcarrello, idprodotto, idutente;
    private ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();

	/*iniziaizzo il  costruttore vuoto
	Carrello() {
	}
*/

    public int getIdcarrello() {
        return idcarrello;
    }


    public void setIdcarrello(int idcarrello) {
        this.idcarrello = idcarrello;
    }


    public int getIdprodotto(int id_cliente) {
        return idprodotto;
    }


    public void setIdprodotto(int idprodotto) {
        this.idprodotto = idprodotto;
    }


    public int getIdutente() {
        return idutente;
    }


    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }


    public ArrayList<Prodotto> getListaProdotti() {
        return listaProdotti;
    }


    public void setListaProdotti(ArrayList<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }



    public void addProd(Prodotto prod){listaProdotti.add(prod);}


    public int getSize() {
        return listaProdotti.size();
    }


    @Override
    public String toString() {
        return "Carrello [idcarrello=" + idcarrello + ", idprodotto=" + idprodotto + ", idutente=" + idutente
                + ", listaProdotti=" + listaProdotti + "]";
    }


}
