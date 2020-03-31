/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import it.polito.tdp.spellchecker.model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class FXMLController{
	ObservableList<String> list=FXCollections.observableArrayList();
	Dictionary d= new Dictionary();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceDictionary;
    
    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Text txtNumberError;

    @FXML
    private Button btnClearText;

    @FXML
    private Text txtTime;
    
    @FXML
    void doChooseLanguage() {//Carico il dizionario corretto
    	String language=choiceDictionary.getValue();
    	if(language==null)
    		txtOutput.setText("Scegliere quale dizionario utilizzare");
    	else d.loadDictionary(language);
    }

    @FXML
    void doClearText(ActionEvent event) {
    	txtInput.clear();
    	txtOutput.clear();
    	txtNumberError.setText("");
    	txtTime.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	int wrongWords=0;//conta le parole sbagliate
    	this.doChooseLanguage();
    	txtOutput.clear();
    	long start = System.nanoTime();
    	//Prendere tutte le parole inserite e trasformarle in una lista
    	String inputText=txtInput.getText().replaceAll("\\p{P}", "").toLowerCase();
    	List<String> inputTextList=new ArrayList<String>();
    	for(String s: inputText.split(" ")) {
    		inputTextList.add(s);
    	}
    	//Passare le parole inserite al model per poterle controllare 
    	//Ricevere la correzione delle parole
    	List<RichWord> correzioni = new ArrayList<RichWord>(d.spellCheckText(inputTextList));
    	//Mostrare le parole scorrette
    	for(RichWord w:correzioni) {
    		if(!w.isCorretta()) {
    			txtOutput.appendText(w.getParola()+"\n");
    			wrongWords++;
    		}
    	}
    	//Mostrare quante parole sono state sbagliate    	
    	txtNumberError.setText("The text contains "+wrongWords+" errors");
    	//Definire il tempo di esecuzione dello spell check
    	long time = System.nanoTime()-start;
    	double t = time/1e9;
    	txtTime.setText("Spell check completed in "+(t)+"seconds");
    }

    private void loadData() {
    	String italian="Italian";
    	String english="English";
    	list.addAll(italian,english);
    	choiceDictionary.getItems().addAll(list);
    }
    @FXML
    void initialize() {
    	loadData();
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumberError != null : "fx:id=\"txtNumberError\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}


