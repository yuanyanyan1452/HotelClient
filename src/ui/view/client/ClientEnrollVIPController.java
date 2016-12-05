package ui.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import ui.view.Main;

public class ClientEnrollVIPController implements Initializable {
	private Main main;
	@FXML
	private RadioButton normalButton;
	
	@FXML
	private RadioButton companyButton;
	
	@FXML
	private TextField infoTextField;
	
	@FXML
	private void close(){
		main.closeExtraStage();
	}
	
	@FXML
	private void enroll(){
		//TODO
	}
	
	@FXML
	private void initialize(){
		
	}
	
	public ClientEnrollVIPController() {
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main){
		this.main = main;
		final ToggleGroup group = new ToggleGroup();
		normalButton.setToggleGroup(group);
		companyButton.setToggleGroup(group);
	}
}
