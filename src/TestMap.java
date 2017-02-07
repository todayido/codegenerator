import java.util.HashMap;
import java.util.Map;


public class TestMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("", "1");
		map.put(" ", "2");
		System.out.println(map.get(""));
		System.out.println(map.get(" "));
	}

}
