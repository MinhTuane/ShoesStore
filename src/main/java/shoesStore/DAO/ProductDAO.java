package shoesStore.DAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shoesStore.database.DatabaseConnector;
import shoesStore.model.Product;

public class ProductDAO implements InterfaceDAO<Product> {
	static DatabaseConnector db = new DatabaseConnector();
	
	public List<Product> getTenPopular() {
		List<Product> products = new ArrayList<>();
		String sql = "Select * from products order by sold desc limit 10";
		
		try(Connection conn = db.getConnection();
				PreparedStatement ppst = conn.prepareStatement(sql);
				ResultSet rs = ppst.executeQuery()){
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double cost = rs.getDouble("cost");
				byte[] image = rs.getBytes("image");
				Date date = rs.getDate("date");
				long sold = rs.getLong("sold");
				double rate = rs.getDouble("rate");
				Product product = new Product(id,name,cost,image,date,sold,rate);
				products.add(product);
			}
			
		} catch (SQLException e) {
			
		}
		return products;
	}
	
	public List<Product> getTenRating() {
		List<Product> products = new ArrayList<>();
		String sql = "Select * from products order by rate desc limit 10";
		
		try(Connection conn = db.getConnection();
				PreparedStatement ppst = conn.prepareStatement(sql);
				ResultSet rs = ppst.executeQuery()){
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double cost = rs.getDouble("cost");
				byte[] image = rs.getBytes("image");
				Date date = rs.getDate("date");
				long sold = rs.getLong("sold");
				double rate = rs.getDouble("rate");
				Product product = new Product(id,name,cost,image,date,sold,rate);
				products.add(product);
			}
			
		} catch (SQLException e) {
			
		}
		return products;
	}
	
	@Override
	public List<Product> getAll() {
		List<Product> products = new ArrayList<>();
		String sql = "Select * FROM products";
		

		try (Connection conn = db.getConnection();
				PreparedStatement ppst = conn.prepareStatement(sql);
				ResultSet rs = ppst.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double cost = rs.getDouble("cost");
				byte[] image = rs.getBytes("image");
				Date date = rs.getDate("date");
				long sold = rs.getLong("sold");
				double rate = rs.getDouble("rate");
				Product product = new Product(id,name,cost,image,date,sold,rate);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product detail(int id) {
		String sql = "SELECT * FROM products WHERE id = ?";
		Product product = null;

		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					double cost = rs.getDouble("cost");
					byte[] image = rs.getBytes("image");
					java.sql.Date date = rs.getDate("date");
					long sold = rs.getLong("sold");
					double rate = rs.getDouble("rate");
					product = new Product(id,name,cost,image,date,sold,rate);
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}

	@Override
	public boolean insert(Product t) {
		String sql = "INSERT INTO products (name, cost, image, date,sold,rate) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, t.getName());
			pstmt.setDouble(2, t.getCost());
			pstmt.setBytes(3, t.getImage());
			pstmt.setDate(4, t.getDate());
			pstmt.setLong(5, t.getSold());
			pstmt.setDouble(6, t.getRate());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean edit(Product t) {
		String sql = "UPDATE products SET name = ?, cost = ?, image = ?, date = ?,sold = ?, rate = ? WHERE id = ?";

		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, t.getName());
			pstmt.setDouble(2, t.getCost());
			pstmt.setBytes(3, t.getImage());
			pstmt.setDate(4, t.getDate());
			pstmt.setLong(5, t.getSold());
			pstmt.setDouble(6, t.getRate());
			pstmt.setInt(7, t.getId());

			pstmt.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM products WHERE id = ?";

		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public byte[] readImageAsByteArray(String imagePath) {
        byte[] imageBytes = null;
        Path path = Paths.get(imagePath);

        try {
            imageBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBytes;
    }
    public int getIdByName(String name) {
    	String sql = "SELECT * FROM products WHERE name = ?";
		int id=-1;
		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, name);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					id = rs.getInt("id");		
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
    }
}
