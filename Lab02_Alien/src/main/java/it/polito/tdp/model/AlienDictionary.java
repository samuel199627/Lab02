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
		
		//questa e' creata per l'altro caso del punto interrogativo in cui potrei avere piu' lista da ritornare
		List<List<String>> lista=new LinkedList<>();
		
		if(ris==-1) {
			return null;
		}
		else {
			//ritorniamo la lista di traduzioni
			lista.add(dictionary.get(ris).getTransalteWord());
			return lista;
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
	
	public List translateWordQ(String alienWord, int index) {
		int[] ris=searchAlienWordQ(alienWord, index);
		boolean corrispondenza=false;
		
		List<List<String>> lista=new LinkedList<>();
		
		for(int i=0;i<ris.length;i++) {
			if(ris[i]!=-1) {
				corrispondenza=true;
				lista.add(dictionary.get(ris[i]).getTransalteWord());
			}
		}
		
		if(corrispondenza==true) {
			return lista;
		}
		
		return null;
		
		
	}
	
	public int[] searchAlienWordQ(String alienWord, int index) {
		int conta=0;
		int[] ritorna=new int[this.dictionary.size()];
		
		//int riscontri=0;
		boolean confronto;
		
		for(WordEnhanced w: this.dictionary) {
			ritorna[conta]=-1;
			confronto=true;
			if(w.getAlienWord().length()==alienWord.length()) {
				for(int c=0; c<w.getAlienWord().length();c++) {
					if(w.getAlienWord().charAt(c)!=alienWord.charAt(c) && c!=index) {
						confronto=false;
					}
				}
				if(confronto==true) {
					//riscontri++;
					ritorna[conta]=conta;
				}
			}
			conta++;
		}
		/*
		if(riscontri>1) {
			System.out.println("\nPiu' di un riscontro trovato");
		}
		*/
		return ritorna;
	}


}
