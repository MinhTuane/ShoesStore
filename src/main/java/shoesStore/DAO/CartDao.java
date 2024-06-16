package shoesStore.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shoesStore.database.DatabaseConnector;
import shoesStore.model.Cart;

public class CartDao implements InterfaceDAO<Cart> {
	static DatabaseConnector db = new DatabaseConnector();

	@Override
	public List<Cart> getAll() {
		List<Cart> carts = new ArrayList<>();
		String sql = "SELECT * FROM cart";

		try (Connection conn = db.getConnection();
				PreparedStatement ppst = conn.prepareStatement(sql);
				ResultSet rs = ppst.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int productId = rs.getInt("product_id");
				String name = rs.getString("name");
				String color = rs.getString("color");
				String size = rs.getString("size");
				int quantity = rs.getInt("quantity");
				Cart cart = new Cart(id, userId, productId, name, color, size, quantity);
				carts.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carts;
	}

	@Override
	public Cart detail(int id) {
		String sql = "SELECT * FROM cart WHERE id = ?";
		Cart cart = null;

		try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int userId = rs.getInt("user_id");
					int productId = rs.getInt("product_id");
					String name = rs.getString("name");
					String color = rs.getString("color");
					String size = rs.getString("size");
					int quantity = rs.getInt("quantity");
					cart = new Cart(id, userId, productId, name, color, size, quantity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cart;
	}

	@Override
	public boolean insert(Cart t) {
		String sql = "INSERT INTO cart (user_id, product_id, name, color, size, quantity) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, t.getUserId());
			pstmt.setInt(2, t.getProductId());
			pstmt.setString(3, t.getName());
			pstmt.setString(4, t.getColor());
			pstmt.setString(5, t.getSize());
			pstmt.setInt(6, t.getQuantity());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean edit(Cart t) {
		String sql = "UPDATE cart SET user_id=?, product_id=?, name=?, color=?, size=?, quantity=? WHERE id = ?";

		try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, t.getUserId());
			pstmt.setInt(2, t.getProductId());
			pstmt.setString(3, t.getName());
			pstmt.setString(4, t.getColor());
			pstmt.setString(5, t.getSize());
			pstmt.setInt(6, t.getQuantity());
			pstmt.setInt(7, t.getId());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM cart WHERE id = ?";

		try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Cart> getAllByUserId(int userId) {
		List<Cart> carts = new ArrayList<>();
		String sql = "SELECT * FROM cart WHERE user_id = ?";

		try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					int productId = rs.getInt("product_id");
					String name = rs.getString("name");
					String color = rs.getString("color");
					String size = rs.getString("size");
					int quantity = rs.getInt("quantity");
					Cart cart = new Cart(id, userId, productId, name, color, size, quantity);
					carts.add(cart);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return carts;
	}

	public boolean deleteByProductIdAndUserId(int productId, int userId) {
		String sql = "DELETE FROM cart WHERE product_id = ? AND user_id = ?";

		try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, productId);
			pstmt.setInt(2, userId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteByProductId(int productId) {
		// TODO Auto-generated method stub
		return false;
	}
}
