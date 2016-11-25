package ui.view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.view.client.ClientBasicInfoController;
import ui.view.client.ClientBrowseHotelController;
import ui.view.client.ClientEnrollVIPController;
import ui.view.client.ClientEvaluateHotelController;
import ui.view.client.ClientOverviewController;
import ui.view.client.ClientSearchHotelController;
import ui.view.order.ClientBrowseOrderController;
import ui.view.user.LoginController;
import ui.view.user.LoginOverviewController;
import ui.view.user.RegistController;

public class Main extends Application {
	private Stage stage;
	private Scene scene;
	private final double MINIMUM_WINDOW_WIDTH = 400.0;
	private final double MINIMUM_WINDOW_HEIGHT = 250.0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Hotel");
		stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
		stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
		stage.setResizable(false);
		initUI();
		stage.show();
	}

	// 初始界面
	public void initUI() {
		try {
			LoginOverviewController loginOverviewController = (LoginOverviewController) replaceSceneContent(
					"user/LoginOverview.fxml");
			loginOverviewController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到登录界面
	public void gotoLogin() {
		try {
			LoginController loginController = (LoginController) replaceSceneContent("user/Login.fxml");
			loginController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到注册界面
	public void gotoRegist() {
		try {
			RegistController registController = (RegistController) replaceSceneContent("user/Regist.fxml");
			registController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到客户主界面
	public void gotoClientOverview() {
		try {
			ClientOverviewController controller = (ClientOverviewController) replaceSceneContent(
					"client/ClientOverview.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户查看基本信息
	public void gotoClientBasicInfo() {
		try {
			ClientBasicInfoController clientBasicInfoController = (ClientBasicInfoController) replaceSceneContent(
					"client/ClientBasicInfo.fxml");
			clientBasicInfoController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户浏览酒店
	public void gotoClientBrowseHotel() {
		try {
			ClientBrowseHotelController controller = (ClientBrowseHotelController) replaceSceneContent(
					"client/ClientBrowseHotel.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户浏览订单
		public void gotoClientBrowseOrder() {
			try {
				ClientBrowseOrderController controller = (ClientBrowseOrderController) replaceSceneContent(
						"order/ClietnBrowseOrder.fxml");
				controller.setMain(this);
			} catch (Exception e) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		
	// 客户注册会员
	public void gotoClientEnrollVIP() {
		try {
			ClientEnrollVIPController controller = (ClientEnrollVIPController) replaceSceneContent(
					"client/ClientEnrollVIP.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户评价酒店
	public void gotoClientEvaluateHotel() {
		try {
			ClientEvaluateHotelController controller = (ClientEvaluateHotelController) replaceSceneContent(
					"client/ClientEvaluateHotel.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户搜索酒店
	public void gotoClientSearchHotel() {
		try {
			ClientSearchHotelController controller = (ClientSearchHotelController) replaceSceneContent(
					"client/ClientSearchHotel.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 界面跳转主要方法
	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(fxml));
		AnchorPane pane = (AnchorPane) loader.load();
		this.scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.centerOnScreen();
		stage.setResizable(false);
		return (Initializable) loader.getController();
	}

	public Stage getPrimaryStage() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
