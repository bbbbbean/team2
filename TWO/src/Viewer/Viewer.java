package Viewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;
import Domain.UserDto;

	public class Viewer {
		//사용자의 상태정보

			private Number Member_id;
			private String Member_name; 
			private String Member_identity;
			private String Member_grade;
			private String Member_addr;
			private String Member_phone;
			
			private Scanner sc = new Scanner(System.in);
	
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
				
				break;
			case 2:
				MemberInfoMenu();
				break;
			case 3:

				break;
			case 4:
						
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				System.exit(-1);
			}
		}
	}
	public void MemberInfoMenu() {
        while (true) {
            System.out.println("--------------------------");
            System.out.println("회원 정보 조회");
            System.out.println("--------------------------");
            System.out.println("1. 회원 단건 조회");
            System.out.println("2. 전체 회원 조회");
            System.out.println("3. 뒤로 가기");
            System.out.print("번호 선택: ");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    SingleMemberSearch();
                    break;
                case 2:
                    AllMembersSearch();
                    break;
                case 3:
                    return; // MainMenu()로 돌아감
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }
	
	//단건
	public void SingleMemberSearch() {
        System.out.println("--------------------------");
        System.out.println("회원 단건 조회");
        System.out.println("--------------------------");
        System.out.print("USER ID 입력: ");
        int id = sc.nextInt();

        // 요청 처리
        Map<String, Object> params = new HashMap<>();
        params.put("endPoint", "/user");
        params.put("serviceNo", 1); // 단건 조회 서비스 번호
        params.put("member_id", id);

        Map<String, Object> response = controller.execute(params);

        // 응답 출력
        for (String key : response.keySet()) {
            System.out.println(key + " : " + response.get(key));
        }
    }
	
	// 다건 조회 (전체 회원 목록 조회)
    public void AllMembersSearch() {
        System.out.println("--------------------------");
        System.out.println("전체 회원 조회");
        System.out.println("--------------------------");

        // 요청 처리
        Map<String, Object> params = new HashMap<>();
        params.put("endPoint", "/user");
        params.put("serviceNo", 2); // 전체 조회 서비스 번호

        Map<String, Object> response = controller.execute(params);

        // 응답 출력
        if (response.containsKey("data")) {
            List<UserDto> userList = (List<UserDto>) response.get("data");
            if (!userList.isEmpty()) {
                for (UserDto user : userList) {
                    System.out.println(user);
                }
            } else {
                System.out.println("등록된 회원이 없습니다.");
            }
        } else {
            System.out.println("조회 실패: " + response.get("message"));
        }
    }


}
