package ui.util;

/**
 * 抽象酒店房间类型的相关字面值常量
 * @author Ferriswheel
 *
 */
public class RoomTypeUtil {
	private static final String[] ALL_TYPES = {"标准间",
			"大床房",
			"双人房",
			"商务房",
			"豪华房",
			"海景房"};

	public static String[] getAllRoomType(){
		return ALL_TYPES;
	}

}
