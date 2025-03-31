package Domain;

public class ReservationDto {
	private int RENTAL_ID;
	private int Member_id;
	private String Reserve_order;
	
	public ReservationDto() {
		super();
	}

	public ReservationDto(int rental_id, int member_id, String reserve_order) {
		super();
		RENTAL_ID = rental_id;
		Member_id = member_id;
		Reserve_order = reserve_order;
	}

	public int getRental_id() {
		return RENTAL_ID;
	}

	public void setRental_id(int rental_id) {
		RENTAL_ID = rental_id;
	}

	public int getMember_id() {
		return Member_id;
	}

	public void setMember_id(int member_id) {
		Member_id = member_id;
	}

	public String getReserve_order() {
		return Reserve_order;
	}

	public void setReserve_order(String reserve_order) {
		Reserve_order = reserve_order;
	}

	@Override
	public String toString() {
		return "BookDto [Rental_id=" + RENTAL_ID + ", Member_id=" + Member_id + ", Reserve_order=" + Reserve_order
				+ "]";
	}
	
	
	
}
