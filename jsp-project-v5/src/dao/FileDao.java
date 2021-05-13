package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import vo.Board;

public class FileDao {
	DataSource ds = null;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	
	  public int upload(int post_id, String fileName, String FileRealName) throws Exception {
	      Connection connection = null;
	      int result = 0;

	      PreparedStatement stmt = null;
	      final String sqlInsert = "INSERT INTO file(post_id, fileName, fileRealName) VALUES(?, ?, ?)";
	      try {
	         // 커넥션 풀에서 Connection객체를 빌려온다
	         connection = ds.getConnection();
	         stmt = connection.prepareStatement(sqlInsert);
	         stmt.setint(1, post_id);
	         stmt.setString(2, fileName);
	         stmt.setString(3, FileRealName);
	         
//	         return stmt.executeUpdate();
	         result = stmt.executeUpdate();

	      } catch (Exception e) {
	         throw e;
	      } finally {
	         try {
	            if (stmt != null)
	               stmt.close();
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         try {
	            if (connection != null)
	               connection.close();
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         return -1;
	      }
	      return result;
	   }
}
