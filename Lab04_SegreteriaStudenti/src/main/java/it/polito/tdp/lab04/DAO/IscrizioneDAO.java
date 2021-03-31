package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class IscrizioneDAO {
	
	public int upload(int matricola, Corso c) {
		final String sql = "INSERT INTO iscrizione(matricola, codins) "
				+ "VALUES(?, ?)";
		
		try {
			Connection conn = ConnectDB.getConnection(); //provo a connettermi al DB
			PreparedStatement st = conn.prepareStatement(sql); //creo la query statment, nel caso avessi parametri
			st.setInt(1, matricola);
			st.setString(2, c.getCodins());
			
			int rs = st.executeUpdate(); //legge il db riga per riga
			conn.close();
			return rs;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
