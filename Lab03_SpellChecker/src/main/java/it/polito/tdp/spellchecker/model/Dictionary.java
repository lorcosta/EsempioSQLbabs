package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	List<String> dictionary= new ArrayList<String>();
	/**
	 * Carica il dizionario della lingua prescelta.
	 * @param language
	 */
	public void loadDictionary(String language) {
		String nomeFile="src/main/resources/"+language+".txt";
		try {
			FileReader fr= new FileReader(nomeFile);
			BufferedReader br= new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null) {
				//Aggiungere una parola alla struttura dati
				dictionary.add(word);
			}
			System.out.println("Caricato il dizionario");
			br.close();
		}catch(IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}
	/**
	 * Esegue il controllo ortografico sul testo in input, riceve una lista<String>
	 * di parole che devono essere controllate e restituisce una lista di {@link RichWord}
	 * con il parametro booleano che rappresenta la correttezza. Esegue la ricerca con
	 * modalità lineare all'interno di una {@link LinkedList}
	 * @param inputTextList
	 * @return List<RichWord>
	 */
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> correzioni= new LinkedList<RichWord>();
		for(String s: inputTextList) {
			//inizializzo la RichWord come falsa
			RichWord correzioni_element=new RichWord(s,false);
			//la aggiungo alla lista delle correzioni perchè in ogni caso dovrà essere aggiunta sia
			//che sia corretta e sia che non lo sia
			correzioni.add(correzioni_element);
			for(String word: dictionary) {
				if(word.equals(s)) {//se la parola inserita corrisponde ad una del dizionario allora la marco come corretta
					correzioni_element.setCorretta(true);
					//correzioni.add(new RichWord(s,true));
					break;
				}
				// questo gli farebbe ritornare per ogni parola inserita dall'utente
				// tante copie N quante sono le parole del dizionario, con solo 1 su N
				// true nel caso in cui sia corretta. O tutte false se non appare mai.
				// Cioè se hai 100 parole di vocabolario e 2 parole dell'utente avrai
				// che correzioni contiene 2x100 parole. E' ridondande, non serve.
				//-->correzioni.add(new RichWord(s,false));
			}	
		}
		return correzioni;
	}
}
