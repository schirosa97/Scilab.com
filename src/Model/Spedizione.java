package Model;

import java.util.Arrays;
import java.util.Random;

public class Spedizione {
    private int IdSpedizione, IdOrdine;
    private String nomeSpedizione;
    private String[] tipoSpedizione ={"Bartolini", "DHL", "SDA", "GLS", "Fedex", "UPS","simoncelli"};
    private Random r;


    public Spedizione(){
        super();
        r= new Random();
        int j = r.nextInt(7);
        String nomeSpedizione = tipoSpedizione[j];

    }

    public int getIdSpedizione(){
        return IdSpedizione;
    }
    public void setIdSpedizione(int idSpedizione){
        this.IdSpedizione=idSpedizione;
    }

    public String getNomeSpedizione(){
        return nomeSpedizione;
    }
    public void setNomeSpedizione(String nomeSpedizione){
        this.nomeSpedizione=nomeSpedizione;
    }

    public String[] getTipoSpedizione(){
        return tipoSpedizione;
    }

    public void setTipoSpedizione(String[] tipoSpedizione) {
        this.tipoSpedizione = tipoSpedizione;
    }

    public int getIdOrdine() {
        return IdOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        IdOrdine = idOrdine;
    }

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "Spedizione{" +
                "IdSpedizione=" + IdSpedizione +
                ", IdOrdine=" + IdOrdine +
                ", nomeSpedizione='" + nomeSpedizione + '\'' +
                ", tipoSpedizione=" + Arrays.toString(tipoSpedizione) +
                ", r=" + r +
                '}';
    }
}
