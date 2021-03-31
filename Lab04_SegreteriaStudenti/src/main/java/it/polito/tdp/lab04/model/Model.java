package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.IscrizioneDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	private IscrizioneDAO iscrizioneDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
		iscrizioneDao = new IscrizioneDAO();
	}

	public List <Corso> getTuttiICorsi(){
		return corsoDao.getTuttiICorsi();
	}
	
	public Studente getStudente(int matricola) {
		return studenteDao.getStudenteByMatricola(matricola);
	}
	
	public List <Studente> getStudentiCorso(Corso corso){
		return corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List <Corso> getCorsiStudente(int matricola){
		return this.studenteDao.getCorsiByMatricola(matricola);
	}
	
	public boolean getAssociazione(int matricola, Corso corso) {
		return this.studenteDao.cercaAssociazione(matricola, corso);
	}
	
	public int iscrizione(int matricola, Corso c) {
		return iscrizioneDao.upload(matricola, c);
	}
}
