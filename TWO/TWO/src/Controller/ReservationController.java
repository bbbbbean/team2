package Controller;

import java.util.HashMap;
import java.util.Map;

import Domain.ReservationDto;
import Service.ReservationService;

public class ReservationController implements Controller {
	Map<String,Object> response = new HashMap<>();
	private ReservationService reserservice;
	public ReservationController() {
		try {
			reserservice = ReservationService.getInstance();
		} catch (Exception e) {
			exceptionalHandler(e);
		}
	}
	
	@Override
	public Map<String, Object> execute(Map<String, Object> params) {
		System.out.println("[SC] ReservationController excute invoke..!");
		//00
		response = new HashMap<>();
		Integer serviceNo = (Integer)params.get("serviceNo");
		if (serviceNo == null) {
			response.put("status", false);
			response.put("message", "유효하지 않은 서비스 요청입니다.");
			return response;
		}
		try {
			switch (serviceNo) {
			case 1: {
				//
			}
			case 2:{
				//
			}
			case 3:{
				//
			}
			case 4: {
				System.out.println("[SC] 예약서비스 삭제 요청 확인!");
				// 01 파라미터 받기
				Integer Rental_id = (params.get("RENTAL_ID") !=null)? (Integer)params.get("RENTAL_ID"):null;
				Integer Member_id = (params.get("Member_id") !=null)? (Integer)params.get("Member_id"):0;
				String Reserve_order = (params.get("Reserve_order") !=null)? (String)params.get("Reserve_order"):"";
				ReservationDto reserdto = new ReservationDto(Rental_id,Member_id,Reserve_order);
				
				// 유효성 검증
				boolean isokay = isValid(reserdto);
				boolean isDel = reserservice.delete_tbl(reserdto);
				if (isDel) {
					response.put("status", true);
					response.put("message", "삭제가 완료되었습니다.");
				}
				else {
					response.put("status", false);
					response.put("message", "삭제실패");
				}
				break;
			}
			default:
				System.out.println("[SC] 잘못된 서비스번호입니다.");
				response.put("status", false);
				response.put("message", "잘못된 서비스번호입니다.");
			}
		}
		catch(Exception e) {
			exceptionalHandler(e);
		}
		return response;
	}
	private boolean isValid(ReservationDto reserdto) {
		if (Integer.valueOf(reserdto.getRental_id()) == null) {
			response.put("error", "삭제한 Rental_id가 없습니다.");
			System.out.println("[INVALID] Rental_id가 없습니다.");
			return false;
		}
		
		return true;
	}
	
	// 예외처리 함수
	public Map<String,Object> exceptionalHandler(Exception e){
		if (response == null) {
			response = new HashMap<>();
		}
		response.put("status", false);
		response.put("message", e.getMessage());
		response.put("message", e);
		return response;
		
	}
	
}
