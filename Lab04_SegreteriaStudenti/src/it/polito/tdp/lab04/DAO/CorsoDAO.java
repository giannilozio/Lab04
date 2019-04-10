package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				corsi.add(c);
				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

			}
			rs.close();
			conn.close();

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	
		public void getCorso(Corso corso) {
			// TODO
		}

	
	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		String cod=corso.getCodins();
		final String sql =	"SELECT matricola, cognome, nome, CDS FROM studente WHERE matricola IN (SELECT matricola FROM iscrizione WHERE codins=?)";
		
		List<Studente>result = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, cod);								// USO QUESTO PER PRENDERE L INPUT ( 1 -> CORRISP. AL ? ( PERIODO ) , 2 -> NOME ; ECC
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
				result.add(s);
			}
			rs.close();
			conn.close();
			return result;
			
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
		
	}
	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		int matr=studente.getMatricola();
		String codcorso= corso.getCodins();
	
		final String sql =	"INSERT INTO iscrizione"+"VALUES(?,?)";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,matr);
			st.setString(2, codcorso);								
			ResultSet rs = st.executeQuery();
			
			rs.close();
			conn.close();
			return true;
		// ritorna true se l'iscrizione e' avvenuta con successo
		
	}catch (SQLException e) {
		throw new RuntimeException(e);
		
	}
		
	}

	public Studente checkS(int matricola) {
		final String sql =	"SELECT * FROM studente WHERE matricola IN (SELECT matricola FROM iscrizione WHERE matricola =?)";
		try {
			Studente temp=null;
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);								// USO QUESTO PER PRENDERE L INPUT ( 1 -> CORRISP. AL ? ( PERIODO ) , 2 -> NOME ; ECC
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(matricola,rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
				temp=s;
			}
			rs.close();
			conn.close();
			return temp;
			
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
		
	}

	public  List<Corso> getCorsi(int matricola) {
		final String sql =	"SELECT * FROM corso WHERE codins IN (SELECT codins FROM iscrizione WHERE matricola=?)";
		try {
			List<Corso> corsi = new LinkedList<Corso>();
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);								// USO QUESTO PER PRENDERE L INPUT ( 1 -> CORRISP. AL ? ( PERIODO ) , 2 -> NOME ; ECC
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				corsi.add(c);
			}
			rs.close();
			conn.close();
			return corsi;
			
	} catch (SQLException e) {
		throw new RuntimeException(e);
		}
	}
}
