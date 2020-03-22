package it.polito.tdp.model;

import java.util.LinkedList;
import java.util.List;

public class AlienDictionary {
	
	
	List<Word> dictionary;
	
	public AlienDictionary() {
		this.dictionary=new LinkedList<>();
	}
	
	
	//questi due metodi qui sotto vengono entrambi richiamati dal controller in base alle azioni che questo fa
	
	public void addWord(String alienWord, String translation) {
		int ris=searchAlienWord(alienWord);
		if(ris==-1){ //la parola AlienWord non era ancora contenuta nel dizionario
			this.dictionary.add(new Word(alienWord,translation));
		}
		else {
			dictionary.get(ris).setTransalteWord(translation);
		}
	}
	
	public String translateWord(String alienWord) {
		int ris=searchAlienWord(alienWord);
		if(ris==-1) {
			return null;
		}
		else {
			return dictionary.get(ris).getTransalteWord();
		}
	}
	
	public int searchAlienWord(String alienWord) {
		int conta=0;
		for(Word w: this.dictionary) {
			if(w.getAlienWord().equals(alienWord)) {
				return conta;
			}
			conta++;
		}
		return -1;
	}

}
