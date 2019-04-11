package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> corseSwitch;

    @FXML
    private Button searchbtn;

    @FXML
    private TextField TxtInput1;

    @FXML
    private CheckBox CheckBtn;

    @FXML
    private TextField TxtInput2;

    @FXML
    private TextField TxtInput3;

    @FXML
    private Button SearchCorsi;

    @FXML
    private Button IscrivitiBtn;

    @FXML
    private TextArea TxtResult;

    @FXML
    private Button resetBtn;
   
    @FXML
    void doComplete(ActionEvent event) {
    	int matricola;
    	try {
    		matricola=Integer.parseInt(TxtInput1.getText());
    		}
    	catch(NumberFormatException e) {
    		TxtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
    		return;
    	}
    	
    	if(model.checkStudenti(matricola)!= null){
    		TxtInput2.appendText(model.checkStudenti(matricola).getNome());
    		TxtInput3.appendText(model.checkStudenti(matricola).getCognome());
    		
    	}else {
    		TxtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
    		return;
    	}
    	

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    	String nomec=corseSwitch.getValue(); 							// PRENDO IL NOME DEL CORSO CHE HA SELEZIONATO NEL MENU A TENDINA
    	Corso corso=model.getCorso(nomec);
    	int matricola;

    	if(TxtInput1.getText()!=null) {
    		if(corso==null) {
    			TxtResult.appendText("Errore. Scegliere un corso.");
    			return;
    		}
    		else{
    			try {
    				matricola=Integer.parseInt(TxtInput1.getText());
    			}
    			catch(NumberFormatException e) {
    				TxtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
    				return;
    			}
    			if(model.getEIscritto(matricola, corso)) {
    				TxtResult.appendText("Studente già iscritto a questo corso.\n");

    			}else {
    				model.iscriviStudente(matricola,corso);
    			}
    		}
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	TxtResult.clear();
    	TxtInput1.clear();
    	TxtInput2.clear();
    	TxtInput3.clear();
    	
    }

    

    @FXML
    void doSearchCorsi(ActionEvent event) {
    	int matricola;
    	try {
    		matricola=Integer.parseInt(TxtInput1.getText());
    		}
    	catch(NumberFormatException e) {
    		TxtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
    		return;
    	}
    	
    	if(model.checkStudenti(matricola)!=null) {
    		TxtResult.appendText(model.getCorsi(matricola));
    	}
    	
    }

    @FXML
    void doSearchIscritti(ActionEvent event) {
    
    	String nomec=corseSwitch.getValue(); 							// PRENDO IL NOME DEL CORSO CHE HA SELEZIONATO NEL MENU A TENDINA
    	Corso corso=model.getCorso(nomec);	
    	
    	if(TxtInput1.getText().equals("")) {
    		if(corso==null) {
        		TxtResult.appendText("Errore. Scegliere un corso.");
        	}
        	else{
        		TxtResult.appendText(model.getStudenti(corso));
        	}
    	}
    	
    		int matr=0;
        	try {
        		matr=Integer.parseInt(TxtInput1.getText());}
        	catch(NumberFormatException e) {
        		TxtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
        		return;
        	}
        	if(model.getEIscritto(matr, corso)==true)
    			TxtResult.appendText("Studente già iscritto a questo corso.\n");
    		else {
    			TxtResult.appendText("Studente non iscritto a questo corso.\n");
    		}
    	}   	
    

    @FXML
    void initialize() {
        assert corseSwitch != null : "fx:id=\"corseSwitch\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert searchbtn != null : "fx:id=\"searchbtn\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert TxtInput1 != null : "fx:id=\"TxtInput1\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert CheckBtn != null : "fx:id=\"CheckBtn\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert TxtInput2 != null : "fx:id=\"TxtInput2\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert TxtInput3 != null : "fx:id=\"TxtInput3\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert SearchCorsi != null : "fx:id=\"SearchCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert IscrivitiBtn != null : "fx:id=\"IscrivitiBtn\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert TxtResult != null : "fx:id=\"TxtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert resetBtn != null : "fx:id=\"resetBtn\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
        
        
        
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	List<String> corsi=new LinkedList<String>(model.getNomeCorsi()); 		// IL MODEL MI DA I CORSI
    	corsi.add(""); // -> SE NON VUOLE SELEZIONARE NESSUN CORSO
    	corseSwitch.getItems().addAll(corsi);
    	
    }
    	
}
