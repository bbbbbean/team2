package Domain;

public class BookDTO {
	private int bookCode;
	private int classificationId;
	private String bookAuthor;
	private String bookName;
	private String publisher;
	private int isreserve;
	//생성자
	public BookDTO() {}



	public BookDTO(int bookCode, int classificationId, String bookAuthor, String bookName, String publisher,
			int isreserve) {
		super();
		this.bookCode = bookCode;
		this.classificationId = classificationId;
		this.bookAuthor = bookAuthor;
		this.bookName = bookName;
		this.publisher = publisher;
		this.isreserve = isreserve;
	}



	public int getIsreserve() {
		return isreserve;
	}



	public void setIsreserve(int isreserve) {
		this.isreserve = isreserve;
	}



	public int getBookCode() {
		return bookCode;
	}
	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public int getClassificationId() {
		return classificationId;
	}
	public void setClassificationId(int classificationId) {
		this.classificationId = classificationId;
	}



	@Override
	public String toString() {
		return "BookDTO [bookCode=" + bookCode + ", classificationId=" + classificationId + ", bookAuthor=" + bookAuthor
				+ ", bookName=" + bookName + ", publisher=" + publisher + ", isreserve=" + isreserve + "]";
	}





	
	


	//1 BookDaoImpl 생성
	//2 BookDaoImpl insert 작업 TEST
	//3 table명 : tbl_book

}
