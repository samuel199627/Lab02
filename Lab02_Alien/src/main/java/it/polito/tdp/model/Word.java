package it.polito.tdp.model;

public class Word {
	
	private String AlienWord;
	private String TransalteWord;
	
	
	public Word(String alienWord, String transalteWord) {
		super();
		AlienWord = alienWord;
		TransalteWord = transalteWord;
	}
	public String getAlienWord() {
		return AlienWord;
	}
	public void setAlienWord(String alienWord) {
		AlienWord = alienWord;
	}
	public String getTransalteWord() {
		return TransalteWord;
	}
	public void setTransalteWord(String transalteWord) {
		TransalteWord = transalteWord;
	}
	
	public boolean equals(String word) {
		if(this.AlienWord.equals(word)) {
			return true;
		}
		return false;
	}

}
