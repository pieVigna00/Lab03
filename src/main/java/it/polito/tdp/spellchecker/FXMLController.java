
 package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonSpellCheck;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextArea txtIniziale;

    @FXML
    private TextField txtNumeroErrori;

    @FXML
    private TextArea txtSbagliate;

    @FXML
    private TextField txtSecondi;

	private Dictionary dizionario;

    @FXML
    void doClear(ActionEvent event) {
    	txtIniziale.clear();
    	txtSbagliate.clear();
    	txtNumeroErrori.clear();

    }
    LinkedList<String> paroleInput=new LinkedList<>();
    @FXML
    
    void doSpellCheck(ActionEvent event) {
    	long inizio=System.nanoTime();
    	String input=txtIniziale.getText();
    	if(input==null)
    		return;
    	input=input.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]\n", "");
    	String[] parolaSingola= input.split(" ");
    	for(int i=0;i<parolaSingola.length;i++) {
    	paroleInput.add(parolaSingola[i]);
    	}
    	String linguaSelezionata = comboBox.getValue();
    	if(linguaSelezionata==null)
    		return;
    	List<RichWord> richWord=new LinkedList<>();
  
      	if(linguaSelezionata.compareTo("Italiano")==0) {
    		richWord= dizionario.spellCheckTextItalianoDichotomic(paroleInput);
    		
    	}
    	else {
        if(linguaSelezionata.compareTo("Inglese")==0) {
        	richWord=dizionario.spellCheckTextIngleseDichotomic(paroleInput);
    	}
    	}
      	for(RichWord r: richWord) {
			txtSbagliate.appendText(r.getParola()+"\n");
		}
		txtNumeroErrori.setText("Il testo contiene "+richWord.size()+" errori");
    	long fine=System.nanoTime();
    	long ris=fine-inizio;
    	txtSecondi.setText("Tempo impiegato: "+Double.toString(ris/1E9));

    }
    public void setModel(Dictionary model) {
    	this.dizionario=model;
    	this.dizionario.loadDictionaries();
    }
    @FXML
    void initialize() {
        assert buttonClear != null : "fx:id=\"buttonClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonSpellCheck != null : "fx:id=\"buttonSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        this.comboBox.getItems().addAll("Inglese","Italiano");
        assert txtIniziale != null : "fx:id=\"txtIniziale\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumeroErrori != null : "fx:id=\"txtNumeroErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSbagliate != null : "fx:id=\"txtSbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSecondi != null : "fx:id=\"txtSecondi\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
