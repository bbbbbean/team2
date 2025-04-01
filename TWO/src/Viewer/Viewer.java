package Viewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;
import Domain.UserDto;

public class Viewer {

		private Scanner sc = new Scanner(System.in);

		// 싱글톤 패턴의 FrontController 연결
		private FrontController controller;
		public Viewer() throws Exception {
			controller = FrontController.getInstance();
		}

		public void MainMenu() {
			Map<String,Object> params;
			Map<String,Object> response;
			
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
					System.out.println("--------------------------");
					System.out.println("도서추가하기");
					System.out.println("--------------------------");
					System.out.print("도서코드 : ");
					String code = sc.next();
					System.out.print("분류번호 : ");
					String ClfID = sc.next();
					System.out.print("저자 : ");
					String author = sc.next();
					System.out.print("도서이름 : ");
					String name = sc.next();
					System.out.print("출판사 : ");
					String psher = sc.next();
					System.out.print("예약상태 : ");
					String isrv = sc.next();

					//View에서 요청정보 담기
					params = new HashMap();
					params.put("endpoint", "/book");//endPoint
					params.put("serviceNo", 1);	//ServiceNo
					//도서등록 - 인자전달
					params.put("bookCode", code);
					params.put("classificationId", ClfID);
					params.put("bookAuthor", author);
					params.put("bookName", name);
					params.put("publisher", psher);
					params.put("isreserve", isrv);

					
					//요청하기
					response = controller.execute(params);
					
					//응답확인
					
					for(String key : response.keySet()) {
						System.out.println(key + " : " + response.get(key));
						
					}
					break;
				case 2:
					// 각부분 추가
					MemberInfoMenu();
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
					params = new HashMap();
					params.put("endpoint", "/rental");
					params.put("serviceNo", 3);
					
					params.put("rentalId",rentalId);
					params.put("bookCode",bookCode);
					params.put("memberId",memberId);
					
					
					// frontController로 params 던지기
					response = controller.execute(params);
					
					for(String key : response.keySet())
						System.out.println(key + " : " + response.get(key));
					break;
				case 4:
					// 각부분 추가

					System.out.println("--------------------------");
					System.out.println("예약 도서 삭제"); //
					System.out.println("--------------------------");
					System.out.print("Rental_id: ");
					int rentalid = Integer.parseInt(sc.next());
					
					//요청처리
					params = new HashMap<>();
					params.put("endpoint", "/reservation");
					params.put("serviceNo", 4);
					params.put("RENTAL_ID",  rentalid);
					
					response = controller.execute(params);
					for(String key : response.keySet()) {
						System.out.println(key + " : " + response.get(key));;
					}
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
        params.put("endpoint", "/user");
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
        params.put("endpoint", "/user");
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

