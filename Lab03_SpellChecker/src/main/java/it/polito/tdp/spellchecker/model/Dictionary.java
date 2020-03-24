package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	HashSet<String> dictionary= new HashSet<String>();
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
	 * con il parametro booleano che rappresenta la correttezza.
	 * @param inputTextList
	 * @return List<RichWord>
	 */
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> correzioni= new ArrayList<RichWord>();
		for(String s: inputTextList) {
			if(dictionary.contains(s))
				correzioni.add(new RichWord(s,true));
			else correzioni.add(new RichWord(s,false));
				
		}
		return correzioni;
	}
}
