package Model;

public class Categoria {

    public Categoria(int id, String nome, String descripton) {
        this.idcategory=id;
        this.nome=nome;
        this.descrizione=descripton;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    private int idcategory ;
    private String nome, descrizione, pathimageicona;

    public Categoria(int catID, String nome){
        super();
        this.idcategory=catID;
        this.nome=nome;
    }
    public Categoria(String name,String descrizione, String pathimageicona){
        this.nome=name;
        this.descrizione=descrizione;
        this.pathimageicona=pathimageicona;
    }

    public Categoria(int id_add, String name, String desc, String image_path) {
        this.idcategory=id_add;
        this.nome=name;
        this.descrizione=desc;
        pathimageicona=image_path;
    }


    public String getNomeCategoria(){
        return nome;
    }
    public void setNomeCategoria(String name){
        this.nome=name;
    }
    public String getDescrizione(){
        return descrizione;
    }
    public void setDescrizione(String descrizione){
        this.descrizione=descrizione;
    }
    public String getPathimageicona(){
        return pathimageicona;
    }
    public void setPathimageicona(String path){
        this.pathimageicona=path;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + idcategory +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", pathimageicona='" + pathimageicona + '\'' +
                '}';
    }
}
