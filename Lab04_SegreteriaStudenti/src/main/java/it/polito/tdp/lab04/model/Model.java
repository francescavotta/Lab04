package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
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
}
