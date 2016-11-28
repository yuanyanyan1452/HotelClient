package ui.view.user;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class UpdatePasswordController implements Initializable{
	private Main main;
	private String type;
	@FXML
	public void back(){
		main.gotoLogin(type);
	}
	
	public UpdatePasswordController() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO 自动生成的方法存根
		
	}
	
	public void setMain(Main main,String type){
		this.main = main;
		this.type = type;
	}
}
