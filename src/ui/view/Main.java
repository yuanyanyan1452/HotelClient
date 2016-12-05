package ui.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objects.Hotel;
import ui.view.client.ClientBasicInfoController;
import ui.view.client.ClientBrowseHotelController;
import ui.view.client.ClientEnrollVIPController;
import ui.view.client.ClientEvaluateHotelController;
import ui.view.client.ClientOverviewController;
import ui.view.client.ClientSearchHotelController;
import ui.model.HotelModel;
import ui.view.market.*;
import ui.view.hotel.*;
import ui.view.manager.ManagerOverviewController;
import ui.view.market.CreditChargeController;
import ui.view.market.MarketStrategyController;
import ui.view.order.*;
import ui.view.user.LoginController;
import ui.view.user.LoginOverviewController;
import ui.view.user.RegistController;
import ui.view.user.UpdatePasswordController;

public class Main extends Application {
	// 主窗口
	private Stage stage;

	// 弹窗
	private Stage extraStage;

	// 内部窗口
	private SplitPane rootLayout;

	private Scene scene;
	private final double MINIMUM_WINDOW_WIDTH = 400.0;
	private final double MINIMUM_WINDOW_HEIGHT = 250.0;

	/*
	 * an observable list of hotels.
	 */
	private ObservableList<HotelModel> hotelData = FXCollections.observableArrayList();

	public Main() {
		// hotelData.add(new HotelModel("ww","新街口","xx广场xx号",3,4.5));
		// hotelData.add(new HotelModel("ss","马群","xx广场xx号",4,4.7));
		// hotelData.add(new HotelModel("ww","仙林","xx广场xx号",1,4.0));
		// hotelData.add(new HotelModel("xx","旧街口","xx广场xx号",2,4.2));
	}

	public ObservableList<HotelModel> getHotelData() {
		return hotelData;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		// stage.initStyle(StageStyle.UNDECORATED);
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
	public void gotoLogin(String type) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("user/Login.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			this.scene = new Scene(pane);
			LoginController loginController = (LoginController) loader.getController();
			loginController.setMain(this, type);

			// 当非客户用户登录时，隐藏注册按钮
			if (type != "client") {
				loginController.getRegistButton().setVisible(false);
			}

			stage.setScene(scene);
			stage.sizeToScene();
			stage.centerOnScreen();
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到注册界面
	public void gotoRegist(String type) {
		try {
			RegistController registController = (RegistController) replaceSceneContent("user/Regist.fxml");
			registController.setMain(this, type);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到修改密码界面
	public void gotoUpdatePassword(String type) {
		try {
			UpdatePasswordController controller = (UpdatePasswordController) replaceSceneContent(
					"user/UpdatePassword.fxml");
			controller.setMain(this, type);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到客户主界面
	public void gotoClientOverview() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("client/ClientOverview.fxml"));
			rootLayout = (SplitPane) fxmlLoader.load();
			rootLayout.setPrefSize(1000, 600);
			rootLayout.setDividerPositions(0.3f);
			ClientOverviewController controller = (ClientOverviewController) fxmlLoader.getController();
			controller.setMain(this);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户查看基本信息
	public void gotoClientBasicInfo() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("client/ClientBasicInfo.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			ClientBasicInfoController controller = (ClientBasicInfoController) fxmlLoader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户浏览酒店
	public void gotoClientBrowseHotel() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("client/ClientBrowseHotel.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			ClientBrowseHotelController controller = (ClientBrowseHotelController) fxmlLoader.getController();
			controller.setMain(this);
			stage.centerOnScreen();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户浏览订单
	public void gotoClientBrowseOrder() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("order/ClientBrowseOrder.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			ClientBrowseOrderController controller = (ClientBrowseOrderController) fxmlLoader.getController();
			controller.setMain(this);
			stage.centerOnScreen();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户注册会员
	public void gotoClientEnrollVIP() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("client/ClientEnrollVIP.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			ClientEnrollVIPController controller = (ClientEnrollVIPController) fxmlLoader.getController();
			controller.setMain(this);
			stage.centerOnScreen();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户评价酒店
	public void gotoClientEvaluateHotel() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("client/ClientEvaluateHotel.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();

			ClientEvaluateHotelController controller = (ClientEvaluateHotelController) fxmlLoader.getController();
			controller.setMain(this);

			extraStage = new Stage(StageStyle.UNDECORATED);
			extraStage.setScene(new Scene(insidePane));
			extraStage.setAlwaysOnTop(true);
			extraStage.centerOnScreen();
			extraStage.show();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户搜索酒店
	public void gotoClientSearchHotel() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("client/ClientSearchHotel.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			ClientSearchHotelController controller = (ClientSearchHotelController) fxmlLoader.getController();
			controller.setMain(this);
			stage.centerOnScreen();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户查看酒店详细信息
	public void gotoHotelDetailInfo(HotelModel hotel) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("hotel/HotelDetailInfo.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();

			HotelDetailInfoController controller = (HotelDetailInfoController) fxmlLoader.getController();
			controller.setMain(this,hotel);

			extraStage = new Stage(StageStyle.UNDECORATED);
			extraStage.setScene(new Scene(insidePane));
			extraStage.centerOnScreen();
			extraStage.show();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户生成订单
	public void gotoGenerateOrder(HotelModel hotel) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("order/ClientGenerateOrder.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();

			ClientGenerateOrderController controller = (ClientGenerateOrderController) fxmlLoader.getController();
			controller.setMain(this, hotel);

			extraStage = new Stage(StageStyle.UNDECORATED);
			extraStage.setScene(new Scene(insidePane));
			extraStage.centerOnScreen();
			extraStage.show();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到酒店主界面
	public void gotoHotelOverview() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("hotel/HotelOverview.fxml"));
			rootLayout = (SplitPane) fxmlLoader.load();
			rootLayout.setPrefSize(1000, 600);
			rootLayout.setDividerPositions(0.3f);
			HotelOverviewController controller = (HotelOverviewController) fxmlLoader.getController();
			controller.setMain(this);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 酒店工作人员管理酒店信息
	public void gotoHotelBasicInfo() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("hotel/HotelBasicInfo.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			HotelBasicInfoController controller = (HotelBasicInfoController) fxmlLoader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 酒店可用房间管理
	public void gotoHotelRoomManage() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("hotel/HotelRoomManage.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			HotelRoomManageController controller = (HotelRoomManageController) fxmlLoader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 酒店订单浏览
	public void gotoHotelBrowseOrder() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("order/HotelBrowseOrder.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			HotelBrowseOrderController controller = (HotelBrowseOrderController) fxmlLoader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 酒店订单执行
	public void gotoHotelExecuteOrder() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("order/HotelExecuteOrder.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			HotelExecuteOrderController controller = (HotelExecuteOrderController) fxmlLoader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 酒店房间信息更新
	public void gotoHotelCheckIn() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("hotel/HotelCheckIn.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			HotelCheckInController controller = (HotelCheckInController) fxmlLoader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 酒店销售策略管理
	public void gotoHotelStrategyManage() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("hotel/HotelStrategy.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			HotelStrategyController controller = (HotelStrategyController) fxmlLoader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到网站营销人员主界面
	public void gotoMarketOverview() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("market/MarketOverview.fxml"));
			rootLayout = (SplitPane) fxmlLoader.load();
			rootLayout.setPrefSize(1000, 600);
			rootLayout.setDividerPositions(0.3f);
			MarketOverviewController controller = (MarketOverviewController) fxmlLoader.getController();
			controller.setMain(this);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 网站销售策略管理
	public void gotoWebStrategyManage() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("market/MarketStrategy.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			MarketStrategyController controller = (MarketStrategyController) fxmlLoader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 网站营销人员浏览异常订单
	public void gotoMarketAbnormalOrder() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("order/MarketBrowseAbnormalOrder.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			MarketBrowseAbnormalOrderController controller = (MarketBrowseAbnormalOrderController) fxmlLoader
					.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 信用充值
	public void gotoMarketCreditCharge() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("market/CreditCharge.fxml"));
			AnchorPane insidePane = (AnchorPane) fxmlLoader.load();
			insidePane.setPrefSize(700, 600);
			rootLayout.getItems().set(1, insidePane);
			CreditChargeController controller = (CreditChargeController) fxmlLoader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到网站管理人员主界面
	public void gotoManagerOverview() {
		try {
			ManagerOverviewController ManagerOverviewController = (ManagerOverviewController) replaceSceneContent(
					"manager/ManagerOverview.fxml");
			ManagerOverviewController.setMain(this);
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

	// 关闭弹窗
	public void closeExtraStage() {
		extraStage.hide();
	}

	public Stage getPrimaryStage() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
