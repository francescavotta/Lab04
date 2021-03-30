package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


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
			Connection conn = ConnectDB.getConnection(); //provo a connettermi al DB
			PreparedStatement st = conn.prepareStatement(sql); //creo la query statment, nel caso avessi parametri

			ResultSet rs = st.executeQuery(); //legge il db riga per riga

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso tempC = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(tempC);
			}

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
		
		String sql = "SELECT nome  FROM corso WHERE codins = ?";
		
		try {
			Connection conn = ConnectDB.getConnection(); //provo a connettermi al DB
			PreparedStatement st = conn.prepareStatement(sql); //creo la query statment, nel caso avessi parametri

			ResultSet rs = st.executeQuery();
			st.setString(1, corso.getCodins());
			
			while(rs.next()) {
				
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		List <Studente> studenti = new LinkedList <Studente>();
		final String sql ="SELECT distinct s.matricola, s.nome, s.cognome, s.CDS "+
				"FROM studente s, iscrizione i, corso c " +
				"WHERE s.matricola = i.matricola AND c.codins = i.codins AND c.codins=?";
		try {
			Connection conn = ConnectDB.getConnection(); //provo a connettermi al DB
			PreparedStatement st = conn.prepareStatement(sql); //creo la query statment, nel caso avessi parametri
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery(); //legge il db riga per riga

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("CDS");
				System.out.println("Matricola: " + matricola);
				// Crea un nuovo JAVA Bean Corso
				Studente tempS = new Studente(matricola, nome, cognome, cds);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studenti.add(tempS);
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
