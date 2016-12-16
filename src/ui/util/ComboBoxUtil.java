package ui.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ComboBoxUtil {

	public static  void initialize(ComboBox<String> box){
		box.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> param) {
				final ListCell<String> cell = new ListCell<String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setText(item);
					}
				};
				return cell;
			}
		});
	}
}
