package ui.view.user;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class RegistController implements Initializable {
	private Main main;
	private String type;
	public RegistController(){
		
	}
	public void setMain(Main main,String type){
		this.main = main;
		this.type = type;
	}

	@FXML
	private void cancelRegist(ActionEvent event){
		main.gotoLogin(type);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根
		
	}
}
