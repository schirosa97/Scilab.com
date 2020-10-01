package Model;

import Controller.MyExceptionServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao {
    public static Utente doFind(String userName, String password) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            System.out.println(userName);
            System.out.println(password);
            PreparedStatement ps = con.prepareStatement("SELECT Nome,Cognome,Id_user  FROM Utente WHERE username= ?  AND passwordhash=?");
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idUtente = rs.getInt(1);
                String nome = rs.getString(2);
                String cognome = rs.getString(3);
                String passworduser = rs.getString(4);
                Utente tmp = new Utente(idUtente, nome, cognome, passworduser);
                tmp.setIdUtente(idUtente);
                return tmp;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Utente addUtente(String userName, String password, String nome, String cognome, String email, Boolean admin) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO `Utente`\n" +
                            "(`Nome`, `Cognome`, `email`, `username`, `passwordhash`) VALUES (?, ?, ?, ?, ?, ?);");
            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, email);
            ps.setString(4, userName);
            ps.setString(5, password);
            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Errore nel creare l'account");
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Utente> findAll() {
        try (Connection con = ConPool.getConnection()) {
            ArrayList<Utente> listaUtente = new ArrayList<Utente>();
            Utente utente = null;
            PreparedStatement ps = con
                    .prepareStatement("SELECT * FROM Utente");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idUtente = rs.getInt(1);
                String nome = rs.getString(2);
                String cognome = rs.getString(3);
                String username = rs.getString(4);
                String password = rs.getString(5);
                String email = rs.getString(6);
                String indirizzo = rs.getString(7);
                String via = rs.getString(8);
                int nc = rs.getInt(9);
                String piazza = rs.getString(10);
                String provincia = rs.getString(11);
                int CAP = rs.getInt(12);
                String regione = rs.getString(13);
                String stato = rs.getString(14);
                String CF = rs.getString(15);
                String pathimage = rs.getString(16);
                utente = new Utente(idUtente, nome, cognome, username, password, email, indirizzo, via, nc, piazza, provincia, CAP, regione, stato, CF, pathimage);
                utente.setIdUtente(idUtente);
                listaUtente.add(utente);
            }
            return listaUtente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Utente doFindByName(String name) {
        try (Connection connection = ConPool.getConnection()) {
            Utente user = null;
            PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT Nome,Cognome,Id_user,password FROM Utente WHERE Nome=?;");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String nome = rs.getString(1);
                    String cognome = rs.getString(2);
                    int id = rs.getInt(3);
                    String pass = rs.getString(4);
                    user= new Utente(id,nome,cognome,pass);
                    return user;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void doSave(Utente usr){
                    int id_usr=usr.getIdUtente();
                    String nome= usr.getNome();
                    String cognome= usr.getCognome();
                    String pass= usr.getPassword();

                    try(Connection connection= ConPool.getConnection()) {
                        PreparedStatement ps= connection.prepareStatement("INSERT into Utente \n"+
                                "('Id_user','Nome','Cognome','Password') VALUES (?,?,?,?);");
                        ps.setInt(1,id_usr);
                        ps.setString(2,nome);
                        ps.setString(3,cognome);
                        ps.setString(4,pass);
                        if(ps.executeUpdate()!=1)
                                    throw new RuntimeException("errore nell'inserimento");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

    }


    public void doDeleteByID(int id_search){
                        try(Connection connection= ConPool.getConnection()) {
                                    PreparedStatement ps= connection.prepareStatement("DELETE FROM Utente WHERE Id_user=?; ");
                                    ps.setInt(1,id_search);
                                    if(ps.executeUpdate()!=1)
                                                throw new RuntimeException("errore nella eliminazione");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
    }


    public Utente doFindByID(int id_usr) {
        try (Connection connection = ConPool.getConnection()) {
            Utente user = null;
            PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT Nome,Cognome,Id_user,password FROM Utente WHERE Id_user=?;");
            ps.setInt(1, id_usr);
            ResultSet rs = ps.executeQuery();
               while (rs.next()) {
                    String nome = rs.getString(1);
                    String cognome = rs.getString(2);
                    int id = rs.getInt(3);
                    String pass = rs.getString(4);
                    user= new Utente(id,nome,cognome,pass);
                    return user;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}