package Viewer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;

public class Viewer {

		private Scanner sc = new Scanner(System.in);

		// 싱글톤 패턴의 FrontController 연결
		private FrontController controller;
		public Viewer() throws Exception {
			controller = FrontController.getInstance();
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
					// 각부분 추가
					break;
				case 2:
					// 각부분 추가
					break;
				case 3:
					System.out.println("--------------------------");
					System.out.println("대여 도서 정보 수정");
					System.out.println("--------------------------");
					System.out.print("RentalId : ");
					String rentalId = sc.next();
					System.out.print("BookCode : ");
					String bookCode = sc.next();
					System.out.print("MemberId : ");
					String memberId = sc.next();
					
					// 요청처리
					Map<String,Object> params = new HashMap();
					params.put("endPoint", "/rental");
					params.put("serviceNo", 3);
					
					params.put("rentalId",rentalId);
					params.put("bookCode",bookCode);
					params.put("memberId",memberId);
					
					// frontController로 params 던지기
					Map<String,Object> response = controller.excute(params);
					
					for(String key : response.keySet())
						System.out.println(key + " : " + response.get(key));
					break;
				case 4:
					// 각부분 추가
					break;
				case 5:
					System.out.println("프로그램을 종료합니다.");
					System.exit(-1);
				}
			}
		}
	
	
}
