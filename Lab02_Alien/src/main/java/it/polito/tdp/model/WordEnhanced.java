package it.polito.tdp.model;

import java.util.LinkedList;
import java.util.List;

public class WordEnhanced {
	
	private String AlienWord;
	private List<String> translateWord;
	
	
	public WordEnhanced(String alienWord) {
		super();
		AlienWord = alienWord;
		this.translateWord = new LinkedList<>();
	}
	public String getAlienWord() {
		return AlienWord;
	}
	public void setAlienWord(String alienWord) {
		AlienWord = alienWord;
	}
	public List getTransalteWord() {
		return translateWord;
	}
	public void addTransalteWord(String translateWord) {
		this.translateWord.add(translateWord);
	}
	
	public boolean equals(String word) {
		if(this.AlienWord.equals(word)) {
			return true;
		}
		return false;
	}

}
