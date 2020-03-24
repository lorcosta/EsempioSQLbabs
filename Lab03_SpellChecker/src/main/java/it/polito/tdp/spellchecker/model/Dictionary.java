package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	/**
	 * Carica il dizionario della lingua prescelta.
	 * @param language
	 */
	public void loadDictionary(String language) {
	
		try {
			FileReader fr= new FileReader("");
			BufferedReader br= new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null) {
				//Aggiungere una parola alla struttura dati
			}
			br.close();
		}catch(IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}
	/**
	 * Esegue il controllo ortografico sul testo in input, riceve una lista<String>
	 * di parole che devono essere controllate e restituisce una lista di {@link RichWord}
	 * con il parametro booleano che rappresenta la correttezza.
	 * @param inputTextList
	 * @return List<RichWord>
	 */
	public List<RichWord> spellCheckText(List<String> inputTextList){
		return null;
	}
}
