/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.InfoTeam;
import it.polito.tdp.PremierLeague.model.Model;
import it.polito.tdp.PremierLeague.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnClassifica"
    private Button btnClassifica; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbSquadra"
    private ComboBox<Team> cmbSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doClassifica(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	if(this.model.getGrafo() == null) {
    		this.txtResult.setText("Crea prima il grafo!");
    		return;
    	}
    	
    	Team t = this.cmbSquadra.getValue();
  
    	
    	if(t == null) {
    		this.txtResult.appendText("Selezionare una squadra!");
    		return;
    	}
    	
    	int punti = this.model.getPunti(t);
    	
    	List<InfoTeam> migliori = new LinkedList<>();
    	List<InfoTeam> peggiori = new LinkedList<>();
    	
    	List<InfoTeam> classifica = this.model.calcolaClassifica();
    	
    	for(InfoTeam i : classifica) {
    		if(!i.getTeam().equals(t) && i.getPunti() > punti) {
    			migliori.add(i);
    		}else if(!i.getTeam().equals(t) && i.getPunti() < punti){
    			peggiori.add(i);    
    		}
    	}
    	
    	this.txtResult.appendText("Squadre migliori: \n");
    	for(InfoTeam i : migliori) {
    		this.txtResult.appendText("\n" + i.getTeam() + " "+ -(punti-i.getPunti()));
    	}
    	
    	this.txtResult.appendText("\n\n");
    	
    	this.txtResult.appendText("Squadre peggiori: \n");
    	for(InfoTeam i : peggiori) {
    		this.txtResult.appendText("\n" + i.getTeam() + " "+ (punti-i.getPunti()));
    	}
    	
    	

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	this.model.creaGrafo();
    	
    	this.txtResult.appendText(this.model.infoGrafo());
    	
    	

    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClassifica != null : "fx:id=\"btnClassifica\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbSquadra != null : "fx:id=\"cmbSquadra\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbSquadra.getItems().addAll(this.model.listAllTeams());
    }
}
