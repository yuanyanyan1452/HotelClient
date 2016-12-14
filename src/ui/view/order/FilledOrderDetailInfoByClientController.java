package ui.view.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import ui.view.Main;

public class FilledOrderDetailInfoByClientController implements Initializable {
	private Main main;
	
	public FilledOrderDetailInfoByClientController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
