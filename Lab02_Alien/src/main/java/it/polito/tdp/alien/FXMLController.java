package it.polito.tdp.alien;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.AlienDictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//in questa terza versione abbiamo la ricerca con wild card che contiene al massimo un solo carattere speciale ?

public class FXMLController {
	
	//Quello a cui bisogna fare attenzione e' che io potrei volere un nuovo Dizionario senza necessariamente
    //aver cambiato controller, invece creando il Dizionario quando creo il controller, ci vincoliamo che fino a che
    //abbiamo quel controller non possiamo andare ad usufruire di un nuovo Dizionario. Quindi il dizionario non va creato
	//in inizialize() ma deve essere fatto in entryPoint dove cosi' il controller e il moddelo restsno separati.
	private AlienDictionary dictionary;
	private int stampati;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField insertArea;

    @FXML
    private Button transBtn;

    @FXML
    private TextArea translateArea;

    @FXML
    private Button clearBtn;

    @FXML
    void doReset(ActionEvent event) {

    	//non viene specificato che cosa dovesse dare il pulsante ClearText allora gli faccio solo pulire l'area di testo
    	translateArea.clear();
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	//abbiamo premuto il pulsante Translate quindi dobbiamo controllare per prima cosa se sono state fornite due parole
    	//e quindi siamo nel caso in cui vogliamo aggiungere al dizionario, oppure se solo una parola è stata inserita e quindi
    	//dobbiamo solo tradurla
    	
    	stampati=0;
    	
    	String insertWord;
    	insertWord=insertArea.getText().toLowerCase();
    	
    	//System.out.println(insertWord);
    	String[] insertWordSplitted=insertWord.split(" ");
    	//conta i punti interrogativi
    	int contaQ=0;
    	int posizioneQ=0;
    	if(insertWordSplitted.length<3 && !insertWordSplitted[0].equals("")) {
    		
    		for(int i=0;i<insertWordSplitted.length;i++) {
    			/*
    			if(insertWordSplitted[i].contains("?") ) { //il punto interrogativo va bene solo quando devo tradurre
    				if(i==1 || insertWordSplitted.length==2) {
    					System.out.println("\nLA STRINGA CONTIENE PUNTO INTERROGATIVO, MA NON RISPETTA LE REGOLE!\n");
	    				return;
    				}
    				//DEVO ANCHE CONTROLLARE IL CASO IN CUI HO INSERITO UNA SOLA PAROLA MA CON PIU' DI UN PUNTO INTERROGATIVO
    				String[] verificaQ=insertWordSplitted[0].split("?");
    				
    				if(verificaQ.length!=2) {
    					System.out.println("\nLA STRINGA CONTIENE PIU' DI UN PUNTO INTERROGATIVO E NON RISPETTA LE REGOLE!\n");
	    				return;
    				}
    			}
    			*/
    			
	    		//devo controllare se la parola inserita contiene solo lettere alfabetiche
		    	for(int c=0; c<insertWordSplitted[i].length();c++) {
		    		
		    		if(!Character.isLetter(insertWordSplitted[i].charAt(c)) && !Character.isSpace((Character) insertWordSplitted[i].charAt(c))) {
		    			//eventualmente potrebbero esserci punti interrogativi, ma solo se ho una sola parola e se non ce ne sono piu' di uno
		    			if(insertWordSplitted[i].charAt(c)=='?') {
		    				posizioneQ=c;
		    				contaQ++;
		    				if(i==1 || insertWordSplitted.length==2) {
		    					System.out.println("\nLA STRINGA CONTIENE PUNTO INTERROGATIVO, MA NON RISPETTA LE REGOLE!\n");
			    				return;
		    				}
		    				if(contaQ>1) {
		    					System.out.println("\nLA STRINGA CONTIENE PIU' DI UN PUNTO INTERROGATIVO E NON RISPETTA LE REGOLE!\n");
			    				return;
		    				}
		    				
		    				//se ne contiene solo uno e c'e' solo una stringa passa tutto indenne
		    			}
		    			else {
		    				System.out.println("\nLA STRINGA NON CONTIENE SOLO LETTERE!\n");
		    				return;
		    			}
		    			
		    			
		    		}
		    		
		    		
		    	}
    		}
    		
    	
    	}
    	else {
    		System.out.println("\nHAI INSERITO UN NUMERO ERRATO DI PAROLE, NON POSSO FARE NULLA!!\n");
    		return;
    	}
    	
    	
    	
    	if(insertWordSplitted.length==1 && !insertWordSplitted[0].equals("")) {
    		System.out.println("\nHAI INSERITO UNA PAROLA, PROCEDO NEL TRADURLA...\n");
    		List<List<String>> stampaList;
    		
    		if(contaQ>0) {
    			System.out.println("\nATTENZIONE, RICERCA WILDCARD...\n");
    			stampaList=dictionary.translateWordQ(insertWordSplitted[0],posizioneQ);
    		}
    		else {
    			stampaList=dictionary.translateWord(insertWordSplitted[0]);
    		}
    		
    		
    		
    		if(stampaList!=null) {
    			for(List<String> lista:stampaList) {
    				System.out.println("\nCORRISPONDENZA STAMPA:\n");
    				String stampa="";
    				
	    			for(String trans:lista) {
	    				
	    				if(stampati==0) {
	    					stampa=trans;
	    					stampati++;
	    				}
	    				else {
	    					stampa=stampa+", "+trans;
	    				}
	    				
	    			}
	    			stampa=stampa+"\n";
	    			translateArea.appendText(""+stampa+"\n");
    			}
    			
    		}
    		else {
    			System.out.println("PAROLA NON ANCORA PRESENTE NEL DIZIONARIO, PERTANTO IMPOSSIBILE DA TRADURRE\n");
    		}
    		
    	}
    	else if(insertWordSplitted.length==2){
    		System.out.println("\nHAI INSERITO DUE PAROLE, PROCEDO NELL' AGGIUNGERLA...");
    		dictionary.addWord(insertWordSplitted[0], insertWordSplitted[1]);
    		System.out.println("AGGIUNTA!\n");
    	}

    }

    @FXML
    void initialize() {
        assert insertArea != null : "fx:id=\"insertArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert transBtn != null : "fx:id=\"transBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert translateArea != null : "fx:id=\"translateArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clearBtn != null : "fx:id=\"clearBtn\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    
  //ha senso quindi per quanto riguarda il dizionario andare a crearsi un metodo esterno che importa solamente il dizionario
    //e non lo va a creare.
    //Il modello lo andiamo a creare in Entry Point
    public void setModel(AlienDictionary dizionario) {
    	this.dictionary=dizionario;
    	
    }
}

