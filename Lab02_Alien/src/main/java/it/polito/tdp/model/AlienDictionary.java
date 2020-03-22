package it.polito.tdp.model;

import java.util.LinkedList;
import java.util.List;

public class AlienDictionary {
	
	
	List<WordEnhanced> dictionary;
	
	public AlienDictionary() {
		this.dictionary=new LinkedList<>();
	}
	
	
	//questi due metodi qui sotto vengono entrambi richiamati dal controller in base alle azioni che questo fa
	
	public void addWord(String alienWord, String translation) {
		int ris=searchAlienWord(alienWord);
		if(ris==-1){ //la parola AlienWord non era ancora contenuta nel dizionario
			this.dictionary.add(new WordEnhanced(alienWord));
			//tanto vengono inserite in ordine nella lista quindi devo andare ad aggiungere nell'ultima creata la prima traduzione
			this.dictionary.get(this.dictionary.size()-1).addTransalteWord(translation);
		}
		else {
			//aggiungiamo la nuova traduzione
			dictionary.get(ris).addTransalteWord(translation);
		}
	}
	
	public List translateWord(String alienWord) {
		int ris=searchAlienWord(alienWord);
		if(ris==-1) {
			return null;
		}
		else {
			//ritorniamo la lista di traduzioni
			return dictionary.get(ris).getTransalteWord();
		}
	}
	
	public int searchAlienWord(String alienWord) {
		int conta=0;
		for(WordEnhanced w: this.dictionary) {
			if(w.getAlienWord().equals(alienWord)) {
				return conta;
			}
			conta++;
		}
		return -1;
	}

}
