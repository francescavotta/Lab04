package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class FXMLController {
	Model model = new Model();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private Button btnIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnVerificaMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matricola;
    	try {
    		if(txtMatricola.getText().length() != 6) {
    			txtRisultato.setText("Matricola non valida!");
    			return;
    		}

    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException nfe) {
    		txtRisultato.setText("Matricola non valida");
    		return;
    	}

    	Studente s = model.getStudente(matricola);
    	if(s==null)
    	{
    		txtRisultato.setText("La matricola inserita non è esistente!");
    		return;
    	}
    	txtCognome.setText(s.getCognome());
    	txtNome.setText(s.getNome());
    	
    	List <Corso> corsi = model.getCorsiStudente(matricola);
    	txtRisultato.setText(corsi.toString());
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	Corso tempC = this.cmbCorsi.getValue();
    	if(tempC.getNome().equals("--")) {
    		txtRisultato.setText("Selezionare un corso valido!");
    		return;
    	}
    	List <Studente> studenti = model.getStudentiCorso(tempC);
    	if(studenti.isEmpty()) {
    		txtRisultato.setText("Nessun iscritto al corso");
    		return;
    	}
    	txtRisultato.setText(studenti.toString());

    }

    @FXML
    void doIscrizione(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void doVerificaMatricola(ActionEvent event) {

    	int matricola;
    	try {
    		if(txtMatricola.getText().length() != 6) {
    			txtRisultato.setText("Matricola non valida!");
    			return;
    		}

    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException nfe) {
    		txtRisultato.setText("Matricola non valida");
    		return;
    	}

    	Studente s = model.getStudente(matricola);
    	if(s==null)
    	{
    		txtRisultato.setText("La matricola inserita non è esistente!");
    		return;
    	}
    	txtCognome.setText(s.getCognome());
    	txtNome.setText(s.getNome());

    }

    @FXML
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrittiCorso != null : "fx:id=\"btnIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVerificaMatricola != null : "fx:id=\"btnVerificaMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        
        this.btnVerificaMatricola.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        this.cmbCorsi.getItems().add(new Corso("",0,"--",0));
        this.cmbCorsi.getItems().addAll(model.getTuttiICorsi());
        
    }
    
    public void setModel(Model model) {
    	
    	this.model= model;
    }
}
