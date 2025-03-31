package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.BookDTO;
import Service.BookService;

public class BookController implements Controller {
	
	//BookService
	private BookService bookService;
	
	Map<String, Object> response ;
	
	public BookController() {
		try {
			bookService = BookService.getInstance();
		}catch(Exception e) {
//			exceptionHandler(e);
		    System.out.println("[BookController] 예외 발생: " + e.getMessage());
	        e.printStackTrace(); // 꼭 있어야 문제 원인을 볼 수 있음
		}
	}
	
	//C(1)R(2)U(3)D(4)
	@Override
	public Map<String,Object> execute (Map<String,Object> params){
		System.out.println("[SC] BookController execute invoke..!");
		
		//컨트롤러할 일.
		//00
		response = new HashMap();
		Integer serviceNo = (Integer)params.get("serviceNo");
		if(serviceNo == null) {
			response.put("status", false);
			response.put("message", "유효하지 않은 서비스 요청입니다.");
			return response;
		}
		
		try {
			
		BookDTO bookDto = null;
		boolean isOk = false;
		boolean isSuccess = false;
		switch(serviceNo) {
		case 1:				//C = 도서등록(ROLE-사서)
			System.out.println("[SC] 도서등록 요청 확인");
			//01 요청 파라미터받기
			Integer bookCode = params.get("bookCode") != null ? Integer.parseInt((String)params.get("bookCode")) : null;

			Integer classificationId = params.get("classificationId") != null? Integer.parseInt((String) params.get("classificationId")): null;			
			String bookAuthor = (params.get("bookAuthor")!=null)?(String)params.get("bookAuthor"):null;
			String bookName = (params.get("bookName")!=null)?(String)params.get("bookName"):null;
			String publisher = (params.get("publisher")!=null)?(String)params.get("publisher"):null;
			Integer isreserve = params.get("isreserve") != null? Integer.parseInt((String) params.get("isreserve")): null;			
			
			bookDto = new BookDTO(bookCode, classificationId, bookAuthor, bookName, publisher, isreserve );
			
			//02 유효성검증(Validation)
			isOk = isValid(bookDto);
			System.out.println("[N0-1 도서등록] : 유효성 검증 확인 : " + isOk );
			if(!isOk) {
				response.put("status", false);
				return response;
			}
			//03 관련 서비스 실행
			System.out.println("[BookController] 도서등록 서비스 호출 시도");
			isSuccess = bookService.bookRegistration(bookDto);
			System.out.println("[BookController] 도서등록 서비스 호출 완료, 결과: " + isSuccess);

			//04 뷰로 이동(or 내용전달)
			if (isSuccess) {
			    response.put("status", true);
			    response.put("message", "도서등록 성공!");
			} else {
			    response.put("status", false);
			    response.put("message", "도서등록 실패!");
			}
			break;
		case 2:				//R = 도서조회(ROLE-회원,사서,관리자)
		    System.out.println("[SC] 도서조회 요청 확인");
		    //01 요청 파라미터받기
		    bookCode = params.get("bookCode") != null ? Integer.parseInt((String)params.get("bookCode")) : null;
		    bookDto = new BookDTO();
		    bookDto.setBookCode(bookCode);
			//02 유효성검증(Validation)
		    //
		    //03 관련 서비스 실행
		    BookDTO result = bookService.findBook(bookDto);
		    //04 뷰로 이동(or 내용전달)
		    if (result != null) {
		        response.put("status", true);
		        response.put("book", result);
		    } else {
		        response.put("status", false);
		        response.put("message", "도서를 찾을 수 없습니다.");
		    }
		    break;
		case 3:				//R = 도서전체조회(ROLE-회원,사서,관리자)
			System.out.println("[SC] 도서전체조회 요청 확인");
			//01 요청 파라미터받기
			//없음
			//02 유효성검증(Validation)
			//
			//03 관련 서비스 실행
			List<BookDTO> bookList = bookService.findAllBooks();
			//04 뷰로 이동(or 내용전달)
			response.put("status", true);
			response.put("list", bookList);
			break;
		case 4:
			System.out.println("[SC] 도서수정 요청 확인");
			//01 요청 파라미터받기
		    bookCode = params.get("bookCode") != null ? Integer.parseInt((String)params.get("bookCode")) : null;
		    classificationId = params.get("classificationId") != null ? Integer.parseInt((String)params.get("classificationId")) : null;
		    bookAuthor = (String)params.get("bookAuthor");
		    bookName = (String)params.get("bookName");
		    publisher = (String)params.get("publisher");
		    isreserve = params.get("isreserve") != null ? Integer.parseInt((String)params.get("isreserve")) : null;
		    
		    bookDto = new BookDTO(bookCode, classificationId, bookAuthor, bookName, publisher, isreserve);
			//02 유효성검증(Validation)
		    isOk = isValid(bookDto);
		    if (!isOk) {
		        response.put("status", false);
		        return response;
		    }
			//03 관련 서비스 실행
		    isSuccess = bookService.updateBook(bookDto);
			//04 뷰로 이동(or 내용전달)
		    response.put("status", isSuccess);
		    response.put("message", isSuccess ? "도서 수정 성공!" : "도서 수정 실패!");
			break;
		case 5:
			System.out.println("[SC] 도서삭제 요청 확인");
		    // 01 요청 파라미터받기
			bookCode = params.get("bookCode") != null ? Integer.parseInt((String) params.get("bookCode")) : null;

		    bookDto = new BookDTO();
		    bookDto.setBookCode(bookCode);

		    // 02 유효성검증(Validation)
		    if (bookDto.getBookCode() == 0) {
		        response.put("status", false);
		        response.put("message", "도서코드는 필수입니다.");
		        break;
		    }

		    // 03 관련 서비스 실행
		    isSuccess = bookService.deleteBook(bookDto);

		    // 04 뷰로 이동(or 내용전달)
		    if (isSuccess) {
		        response.put("status", true);
		        response.put("message", "도서 삭제 성공!");
		    } else {
		        response.put("status", false);
		        response.put("message", "도서 삭제 실패!");
		    }
			break;
		default :
			System.out.println("[SC] 잘못된 서비스번호 요청 확인");
			response.put("status", false);
			response.put("message", "잘못된 서비스번호 요청입니다.");
		}
		}catch(Exception e) {
			System.out.println("[BookController] 도서등록 중 예외 발생: " + e.getMessage());
			e.printStackTrace();
		    response.put("status", false);
		    response.put("message", "예외 발생: " + e.getMessage());
		}
		
		return response;
	}

	private boolean isValid(BookDTO bookDto) {
		String codeStr = String.valueOf(bookDto.getBookCode());
		if(codeStr.length() != 8) {
			response.put("error", "bookCode의 길이는 8자리만 허용합니다.");
			System.out.println("[INVALID] bookCode의 길이는 8자리만 허용합니다.");
			return false;
		}
		if(bookDto.getBookName() == null || bookDto.getBookName().length()>255) {
			response.put("error", "bookName의 길이는 255자를 넘기지 않아야 합니다.");
			System.out.println("[INVALID] bookName의 길이는 255자를 넘기지 않아야 합니다.");
			return false;
		}
		return true;
	}
	
	// 예외처리함수
		public Map<String, Object> exceptionHandler(Exception e) {

			if (response == null)
				response = new HashMap();

			response.put("status", false);
			response.put("message", e.getMessage());
			response.put("exception", e);

			return response;
		}
	

}
