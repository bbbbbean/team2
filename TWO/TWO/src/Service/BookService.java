package Service;


import java.util.List;

import Domain.BookDAO;
import Domain.BookDTO;



public class BookService {
	
	//
	private BookDAO bookDao;
	
	//싱클톤 패턴
	private static BookService instance;
	private BookService() throws Exception{
		bookDao = BookDAO.getInstance();
		System.out.println("[SERVICE] BookServiceImpl init...");
	}
	public static BookService getInstance() throws Exception{
		if(instance==null)
			instance = new BookService();
		return instance ;
	}

	// TX처리필요 + 비즈니스 유효성검사
	public boolean bookRegistration(BookDTO bookDto) throws Exception{
		
	    System.out.println("[BookService] 도서등록 실행: " + bookDto);
	    try {
	        int result = bookDao.insert(bookDto);
	        System.out.println("[BookService] insert 결과: " + result);
	        return result > 0;
	    } catch (Exception e) {
	        System.out.println("[BookService] 예외 발생: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}
	
	// 단건 조회
	public BookDTO findBook(BookDTO dto) throws Exception {
	    return bookDao.select(dto);
	}

	// 전체 조회
	public List<BookDTO> findAllBooks() throws Exception {
	    return bookDao.selectAll();
	}

	// 도서 수정
	public boolean updateBook(BookDTO dto) throws Exception {
	    return bookDao.update(dto) > 0;
	}

	// 도서 삭제
	public boolean deleteBook(BookDTO dto) throws Exception {
	    return bookDao.delete(dto) > 0;
	}
}
