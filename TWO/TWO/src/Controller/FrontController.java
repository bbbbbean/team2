package Controller;

import java.util.HashMap;
import java.util.Map;

public class FrontController {

	private Map<String,Controller> map = new HashMap();
	
	// 싱글톤
	private static FrontController instance;
	private FrontController() {
		System.out.println("FrontController init");
		init();
	}
	public static FrontController getInstance() {
		if(instance == null)
			instance = new FrontController();
		return instance;
	}
	
	// 초기화
	private void init() {
		System.out.println("FrontController init");
		map.put("/rental", new RentalController());
		map.put("/book", new BookController());
		// 추가
	}
	
	public Map<String, Object> excute (Map<String, Object> params){
		System.out.println("excute invoke");
		String endPoint = (String)params.get("endPoint");
		Controller controller = map.get(endPoint);
		return controller.execute(params);
	}
}



