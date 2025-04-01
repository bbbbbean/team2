package Controller;

import java.util.HashMap;
import java.util.Map;

public class FrontController{
	private Map<String,Controller> map = new HashMap<>();
	
	//싱글톤
	private static FrontController instance;
	public  FrontController() {
		System.out.println("[FC] FrontController init...");
		init();
	}
	public static FrontController getInstance() {
		if (instance == null) {
			instance = new FrontController();
		}
		return instance;
	}
	// 초기화
	private void init() {
		System.out.println("FrontController init");
		map.put("/reservation", new ReservationController());
		map.put("/rental", new RentalController());
		map.put("/book", new BookController());
		map.put("/user", new UserController());

		// 추가
	}
	
	// View로 부터 전달하는 요청 전달
	public Map<String,Object> execute(Map<String,Object>params){
		System.out.println("[FC] execute invoke...");
		String endPoint = (String)params.get("endpoint");
		Controller controller = map.get(endPoint);
		return controller.execute(params);
	}
	
}
