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

//in questa seconda versione ho sempr tenuto che al massimo due parole potessero essere scritte per aggiungere al dizionario, ma per una
//parola aliena gia' inserita allora andiamo aggiungere la nuova traduzione anche alla precedente

public class FXMLController {
	
	//Quello a cui bisogna fare attenzione e' che io potrei volere un nuovo Dizionario senza necessariamente
    //aver cambiato controller, invece creando il Dizionario quando creo il controller, ci vincoliamo che fino a che
    //abbiamo quel controller non possiamo andare ad usufruire di un nuovo Dizionario. Quindi il dizionario non va creato
	//in inizialize() ma deve essere fatto in entryPoint dove cosi' il controller e il moddelo restsno separati.
	private AlienDictionary dictionary;

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
    	
    	String insertWord;
    	insertWord=insertArea.getText().toLowerCase();
    	
    	//devo controllare se la parola inserita contiene solo lettere alfabetiche
    	for(int c=0; c<insertWord.length();c++) {
    		if(!Character.isLetter(insertWord.charAt(c)) && !Character.isSpace((Character) insertWord.charAt(c))) {
    			System.out.println("\nLA STRINGA NON CONTIENE SOLO LETTERE!\n");
    			return;
    		}
    	}
    	
    	
    	//System.out.println(insertWord);
    	String[] insertWordSplitted=insertWord.split(" ");
    	if(insertWordSplitted.length==1 && !insertWordSplitted[0].equals("")) {
    		System.out.println("\nHAI INSERITO UNA PAROLA, PROCEDO NEL TRADURLA...\n");
    		List<String> stampaList=dictionary.translateWord(insertWordSplitted[0]);
    		String stampa="\n";
    		
    		if(stampaList!=null) {
    			for(String trans:stampaList) {
    				stampa=stampa+", "+trans;
    			}
    			translateArea.appendText(""+stampa+"\n");
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
    	else {
    		System.out.println("\nHAI INSERITO UN NUMERO ERRATO DI PAROLE, NON POSSO FARE NULLA!!\n");
    		return;
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

