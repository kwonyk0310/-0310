package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SuperDAO {
	protected Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String id = "proman";
//	private String password = "oracle";
	private String id = "lomue";
	private String password = "w12270502w";
	
	public SuperDAO() {
		try {
			Class.forName(this.driver) ;
			this.conn = this.getConnection();
			if (conn!=null) {
				System.out.println("접속 성공");
			} else {
				System.out.println("접속 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() {
		// 커넥션 객체 생성
		try {			 
			return DriverManager.getConnection(url, id, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	public void closeConnection(){
		//커넥션 객체 닫기
		
			try {
				if(conn != null){conn.close();} 
			} 
			catch (Exception e) {
				e.printStackTrace();		
				}
			
		} 
	}
