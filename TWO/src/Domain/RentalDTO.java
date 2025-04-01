package Domain;

public class RentalDTO {
	private int rentalId;
	private int bookCode;
	private int memberId;
	
	// 생성자
	public RentalDTO() {}

	public RentalDTO(int rentalId, int bookCode, int memberId) {
		super();
		this.rentalId = rentalId;
		this.bookCode = bookCode;
		this.memberId = memberId;
	}

	// getter and setter
	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	// toString
	@Override
	public String toString() {
		return "RentalDTO [rentalId=" + rentalId + ", bookCode=" + bookCode + ", memberId=" + memberId + "]";
	}

	
	
}
