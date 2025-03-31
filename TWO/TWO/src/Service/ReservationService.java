package Service;

import java.sql.SQLException;

import Domain.ReservationDao;
import Domain.ReservationDto;

public class ReservationService {
	private static ReservationDao reservationdao;
	
	
	// 싱글톤 패턴
	private static ReservationService instance;
	private ReservationService() throws ClassNotFoundException, SQLException {
		reservationdao = ReservationDao.getInstance();
		System.out.println("ReservationDao is init...");
	}
	public static ReservationService getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new ReservationService();
		}
		return instance;
	}
	
	// 삭제
	public boolean delete_tbl(ReservationDto reserdto) {
		boolean isdel = false;
		try {
			isdel = reservationdao.delete(reserdto) >0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return isdel;
	}
	
}
