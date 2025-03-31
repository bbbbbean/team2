package Domain;

public class RentalDTO {
	String rentalId;
	String bookCode;
	String memberId;
	
	// 생성자
	public RentalDTO() {}

	public RentalDTO(String rentalId, String bookCode, String memberId) {
		super();
		this.rentalId = rentalId;
		this.bookCode = bookCode;
		this.memberId = memberId;
	}

	// getter and setter
	public String getRentalId() {
		return rentalId;
	}

	public void setRentalId(String rentalId) {
		this.rentalId = rentalId;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	// toString
	@Override
	public String toString() {
		return "RentalDTO [rentalId=" + rentalId + ", bookCode=" + bookCode + ", memberId=" + memberId + "]";
	}
	
}
