package Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import Domain.UserDto;
import Service.UserService;

public class UserController implements Controller {

    private UserService userService;
    Map<String, Object> response;

    public UserController() {
        try {
            userService = UserService.getInstance();
        } catch (Exception e) {
            exceptionHandler(e);
        }
    }

    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        System.out.println("[SC] UserController execute invoke..!");

        response = new HashMap<>();

        Integer serviceNo = (Integer) params.get("serviceNo");
        if (serviceNo == null) {
            response.put("status", false);
            response.put("message", "유효하지 않은 서비스 요청입니다.");
            return response;
        }

        try {
            switch (serviceNo) {
                case 1: // R - 회원 단건 조회
                    System.out.println("[SC] 회원 단건 조회 요청 확인");

                    // 01. 파라미터 받기
                    Number member_id = (params.get("member_id") != null) ? (Number) params.get("member_id") : null;

                    if (member_id == null) {
                        response.put("status", false);
                        response.put("message", "회원 ID가 필요합니다.");
                        return response;
                    }

                    // 02. 회원 조회 실행
                    UserDto userDto = userService.getUserById(member_id.intValue());

                    // 03. 결과 처리 및 응답
                    if (userDto != null) {
                        response.put("status", true);
                        response.put("message", "회원 조회 성공!");
                        response.put("data", userDto);
                    } else {
                        response.put("status", false);
                        response.put("message", "해당 ID의 회원을 찾을 수 없습니다.");
                    }
                    break; // 🔥 switch 종료 추가

                case 2: // R - 전체 회원 조회
                    System.out.println("[SC] 전체 회원 조회 요청 확인");

                    // 01. 회원 목록 조회 실행
                    List<UserDto> userList = userService.getAllUsers();

                    // 02. 결과 처리 및 응답
                    if (userList != null && !userList.isEmpty()) {
                        response.put("status", true);
                        response.put("message", "전체 회원 조회 성공!");
                        response.put("data", userList);
                    } else {
                        response.put("status", false);
                        response.put("message", "등록된 회원이 없습니다.");
                    }
                    break; 

                default: 
                    System.out.println("[SC] 잘못된 서비스 번호 요청");
                    response.put("status", false);
                    response.put("message", "잘못된 서비스 요청입니다.");
                    break;
            }
        } catch (Exception e) {
            exceptionHandler(e);
        }

        return response;
    }

    private boolean isValid(UserDto userDto) { // 🔥 switch 문 밖으로 이동 (잘못된 위치 수정)
        if (userDto.getMember_id() == null || ((CharSequence) userDto.getMember_id()).length() <= 4) {
            response.put("error", "userid의 길이는 최소 5자 이상이어야 합니다.");
            System.out.println("[INVALID] userid의 길이는 최소 5자 이상이어야 합니다.");
            return false;
        }
        return true;
    }

    public Map<String, Object> exceptionHandler(Exception e) {
        if (response == null)
            response = new HashMap<>();

        response.put("status", false);
        response.put("message", e.getMessage());
        response.put("exception", e);

        return response;
    }
}


