package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudenteByMatricola(int matricola) {
		
		final String sql = "SELECT * FROM studente WHERE matricola=?";

		try {
			Connection conn = ConnectDB.getConnection(); //provo a connettermi al DB
			PreparedStatement st = conn.prepareStatement(sql); //creo la query statment, nel caso avessi parametri
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery(); //legge il db riga per riga

			while (rs.next()) {

				int matricol = rs.getInt("matricola");
				if(matricol==matricola) {
					String nome = rs.getString("nome");
					String cognome = rs.getString("cognome");
					String cds = rs.getString("CDS");
					Studente s = new Studente (matricol, nome, cognome, cds);
					
					return s;
				}
			}
			conn.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return null;
		
	}
	
	public List <Corso> getCorsiByMatricola(int matricola){
		List <Corso> corsi = new LinkedList<Corso>();
		
		final String sql = "SELECT c.codins, c.nome, c.crediti, c.pd  "
				+ "FROM corso c, studente s, iscrizione i "
				+ "WHERE c.codins = i.codins AND s.matricola = i.matricola AND s.matricola = ?";
		
		try {
			Connection conn = ConnectDB.getConnection(); //provo a connettermi al DB
			PreparedStatement st = conn.prepareStatement(sql); //creo la query statment, nel caso avessi parametri
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery(); //legge il db riga per riga

			while (rs.next()) {

				String codins = rs.getString("codins");
				String nome = rs.getString("nome");
				int crediti = rs.getInt("crediti");
				int pd = rs.getInt("pd");

				Corso c = new Corso(codins, crediti, nome, pd);
				corsi.add(c);
			}
			conn.close();		
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return corsi;
	}

}
