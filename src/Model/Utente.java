package Model;



public class Utente {
    private String username, password, nome, cognome, email, indirizzo, via, piazza, provincia, regione, stato, pathimage, CF;
    private int nc, CAP,idUtente;
    private boolean admin;
    public Utente(int idUtente,String username, String password, String nome, String cognome, boolean admin){
        this.idUtente=idUtente;
        this.username=username;
        this.password=password;
        this.nome=nome;
        this.cognome=cognome;
        this.admin=admin;
    }

    public Utente(int idUtente,String name,String surname,String password){
            this.idUtente=idUtente;
            this.nome=name;
            this.cognome=surname;
            this.password=password;
    }


    public Utente(int idUtente, String nome, String cognome, String username, String password, String email, String indirizzo, String via, int nc, String piazza, String provincia, int cap, String regione, String stato, String cf, String pathimage) {
        super();
        this.idUtente=idUtente;
        this.nome=nome;
        this.cognome=cognome;
        this.username=username;
        this.password=password;
        this.email=email;
        this.indirizzo=indirizzo;
        this.via=via;
        this.nc=nc;
        this.piazza=piazza;
        this.provincia=provincia;
        this.CAP=CAP;
        this.regione=regione;
        this.stato=stato;
        this.CF=CF;
        this.pathimage=pathimage;
        this.admin=admin;
        this.idUtente=idUtente;
    }

    public Utente() {

    }

    public int getIdUtente(){return idUtente;}
    public void setIdUtente(int idUtente){ this.idUtente=idUtente;}
    public boolean getAdmin(){return admin;}
    public void setAdmin(boolean admin){this.admin=admin; }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public String getCognome(){
        return cognome;
    }
    public void setCognome(String cognome){
        this.cognome=cognome;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public  String getIndirizzo(){
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo){
        this.indirizzo=indirizzo;
    }
    public String getVia(){
        return via;
    }
    public void setVia(String via){
        this.via=via;
    }
    public int getNc(){
        return nc;
    }
    public void setNc(int nc){
        this.nc=nc;
    }
    public String getPiazza(){
        return piazza;
    }
    public void setPiazza(String piazza){
        this.piazza=piazza;
    }
    public String getProvincia(){
        return provincia;
    }
    public void setProvincia(String provincia){
        this.provincia=provincia;
    }
    public int getCAP(){
        return CAP;
    }
    public void setCAP(int cap){
        this.CAP=cap;
    }
    public String getRegione(){
        return regione;
    }
    public void setRegione(String regione){
        this.regione=regione;
    }
    public String getStato(){
        return stato;
    }
    public void setStato(String stato){
        this.stato=stato;
    }
    public String getPathimage(){
        return pathimage;
    }
    public  void setPathimage(String pathimage){
        this.pathimage=pathimage;
    }
    public String getCF(){
        return CF;
    }
    public void setCF(String cf){
        this.CF=cf;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", via='" + via + '\'' +
                ", piazza='" + piazza + '\'' +
                ", provincia='" + provincia + '\'' +
                ", regione='" + regione + '\'' +
                ", stato='" + stato + '\'' +
                ", pathimage='" + pathimage + '\'' +
                ", CF='" + CF + '\'' +
                ", nc=" + nc +
                ", CAP=" + CAP +
                '}';
    }
}
