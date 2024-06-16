package shoesStore.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shoesStore.database.DatabaseConnector;
import shoesStore.model.Product;
import shoesStore.model.user.User;

public class UserService {

		
		static DatabaseConnector db = new DatabaseConnector();
		public User isAuthorize(String username, String password) {
		    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		    User user = null;
		    try (Connection conn = db.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, username);
		        pstmt.setString(2, password);
		        
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                int id = rs.getInt("id");
		                String role = rs.getString("role");
		                user = new User(id, username, password, role);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return user;
		}

		
		public List<Product> getCart(int userId) {
			List<Product> cart = new ArrayList<>();
			String sql = "SELECT p.* FROM Products p INNER JOIN User_Cart uc ON p.product_id = uc.product_id WHERE uc.user_id = ?";
	        try (Connection conn = db.getConnection();
	        		PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            
	            stmt.setInt(1, userId);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	int id = rs.getInt("id");
	            	String name = rs.getString("name");
					double cost = rs.getDouble("cost");
					byte[] image = rs.getBytes("image");
					java.sql.Date date = rs.getDate("date");
					long sold = rs.getLong("sold");
					double rate = rs.getDouble("rate");
					Product product = new Product(id,name,cost,image,date,sold,rate);
	                cart.add(product);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return cart;
		}
		
		public List<Product> getLiked(int userId) {
			List<Product> liked = new ArrayList<>();
			String sql = "SELECT p.* FROM Products p INNER JOIN User_Liked ul ON p.product_id = ul.product_id WHERE ul.user_id = ?";
	        try (Connection conn = db.getConnection();
	        		PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            
	            stmt.setInt(1, userId);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	int id = rs.getInt("id");
	            	String name = rs.getString("name");
					double cost = rs.getDouble("cost");
					byte[] image = rs.getBytes("image");
					java.sql.Date date = rs.getDate("date");
					long sold = rs.getLong("sold");
					double rate = rs.getDouble("rate");
					Product product = new Product(id,name,cost,image,date,sold,rate);
	                liked.add(product);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return liked;
		}
		
		public void deleteUserLiked(int userId, int productId) {
	        try (Connection conn = db.getConnection()) {
	            String sql = "DELETE FROM User_Liked WHERE user_id = ? AND product_id = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, userId);
	            stmt.setInt(2, productId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void deleteUserCart(int userId, int productId) {
	        try (Connection conn = db.getConnection()) {
	            String sql = "DELETE FROM User_Cart WHERE user_id = ? AND product_id = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, userId);
	            stmt.setInt(2, productId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void insertUserLiked(String userId, int productId) {
	        try (Connection conn = db.getConnection()) {
	            String sql = "INSERT INTO User_Liked (user_id, product_id) VALUES (?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, userId);
	            stmt.setInt(2, productId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void insertUserCart(String userId, int productId) {
	        try (Connection conn = db.getConnection()) {
	            String sql = "INSERT INTO User_Cart (user_id, product_id) VALUES (?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, userId);
	            stmt.setInt(2, productId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public boolean createUser(String username, String password) {
	        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
	        try (Connection conn = db.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, username);
	            pstmt.setString(2, password);
	            pstmt.setString(3, "user");
	            int rowsInserted = pstmt.executeUpdate();
	            return rowsInserted > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public boolean createAdmin(String username, String password) {
	        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
	        try (Connection conn = db.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, username);
	            pstmt.setString(2, password);
	            pstmt.setString(3, "admin");
	            int rowsInserted = pstmt.executeUpdate();
	            return rowsInserted > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
