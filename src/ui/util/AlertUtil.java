package ui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 抽象弹窗显示
 * @author Ferriswheel
 *
 */
public class AlertUtil  {

	public static void showInformationAlert(String text){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(text);
		alert.show();
	}
	public static void showConfirmingAlert(String text){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(text);
		alert.show();
	}
	public static void showWarningAlert(String text){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText(text);
		alert.show();
	}
	
	public static void showErrorAlert(String text){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(text);
		alert.show();
	}
	
}
