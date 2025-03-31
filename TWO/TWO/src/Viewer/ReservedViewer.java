package Viewer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;




public class ReservedViewer {
	
	private int Rental_id;
	private int Member_id;
	private String Reserve_order;
	
	private FrontController controller;
	Scanner sc = new Scanner(System.in);
	
	public ReservedViewer() {
		controller = new FrontController().getInstance();
	}
	
	public void MainMenu() {
		
		while (true) {
			System.out.println("--------------------------");
			System.out.println("MAIN");
			System.out.println("--------------------------");
			System.out.println("1 도서 추가");
			System.out.println("2 회원 정보 조회");
			System.out.println("3 대여 도서 정보 수정");
			System.out.println("4 예약 도서 삭제");
			System.out.println("5 종료");
			System.out.print("번호 : ");
			int num = sc.nextInt();
			
			switch (num) {
			case 1:
				
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:
				delMenu();
						
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				System.exit(-1);
			}
		}
	}
	public void delMenu() {
		System.out.println("--------------------------");
		System.out.println("예약 도서 삭제"); //
		System.out.println("--------------------------");
		System.out.print("Rental_id: ");
		int rentalId = Integer.parseInt(sc.next());
		
		//요청처리
		Map<String,Object> params = new HashMap<>();
		params.put("endpoint", "/reservation");
		params.put("serviceNo", 4);
		params.put("RENTAL_ID",  rentalId);
		
		Map<String,Object> response = controller.execute(params);
		for(String key : response.keySet()) {
			System.out.println(key + " : " + response.get(key));;
		}
	}
}
