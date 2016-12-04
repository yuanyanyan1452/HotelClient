package ui.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ui.view.Main;

public class ClientBasicInfoController implements Initializable {
	private Main main;
	
	@FXML
	private TextField nameTextField;
	
	@FXML
	private TextField contactTextField;

	@FXML
	private Label vipTypeLabel;
	
	@FXML
	private Label infoNameLabel;
	
	@FXML
	private Label infoLabel;
	
	@FXML
	private Label creditLabel;
	
	@FXML
	private TableView<String> creditTable;
	
	@FXML
	private TableColumn< String, String> timeColumn;
	
	@FXML
	private TableColumn< String, String> reasonColumn;
	
	@FXML
	private TableColumn< String, String> resultColumn;

	@FXML
	private void update() {
		// TODO
	}

	@FXML
	private void initialize() {

	}

	public ClientBasicInfoController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
