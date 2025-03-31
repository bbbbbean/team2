package Service;

import Domain.RentalDAO;
import Domain.RentalDTO;

public class RentalService {
	private RentalDAO rentalDao;
	
	private static RentalService instance;
	private RentalService() throws Exception {
		// rentalDao
		rentalDao = RentalDAO.getInstance();
	}
	public static RentalService getInstance() throws Exception {
		if(instance == null)
			instance = new RentalService();
		return instance;
	}
	
	public boolean RentalUpdate(RentalDTO rentalDto) throws Exception {
		return rentalDao.update(rentalDto)>0;
		
	}
}
