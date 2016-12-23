package ui.util;

/**
 * 抽象地址相关的字面值常量
 * @author Ferriswheel
 *
 */
public class LocationUtil {
	private static final String[] CITIES = {"南京","上海","北京"};
	private static final int CITY_NUM = CITIES.length;
	private static final String[] NANJING = {"新街口","仙林中心","学则路","玄武区"};
	private static final String[] BEIJING  = {"王府井","崇文门","CBD"};
	private static final String[] SHANGHAI = {"南京西路","陆家嘴","淮海路"};
	private static final int BA_NUM = NANJING.length;
	public static String[] getCities(){
		return CITIES;
	}
	
	public static String[] getBusinessAddress(String city) {
		String[][] businessaddresses = new String[CITY_NUM][BA_NUM];
		for(int col = 0; col<BA_NUM;col++){
			if (col<NANJING.length) {
				businessaddresses[0][col] = NANJING[col] ;	
			}
			if (col<SHANGHAI.length) {
				businessaddresses[1][col] = SHANGHAI[col];	
			}
			if (col<BEIJING.length) {
				businessaddresses[2][col] = BEIJING[col];	
			}
			
		}
		for(int row=0;row<CITY_NUM;row++){
			if (CITIES[row].equals(city)) {
				return businessaddresses[row];
			}
		}
		return null;
	}
}
