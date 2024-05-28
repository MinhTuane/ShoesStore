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
import shoesStore.model.HeaderImage;
import shoesStore.model.Product;

public class HeaderDAO implements InterfaceDAO<HeaderImage>{
	static DatabaseConnector db = new DatabaseConnector();
	@Override
	public List<HeaderImage> getAll() {
		List<HeaderImage> headerImages = new ArrayList<>();
		String sql = "Select * FROM header";
		

		try (Connection conn = db.getConnection();
				PreparedStatement ppst = conn.prepareStatement(sql);
				ResultSet rs = ppst.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id_header");
				String name = rs.getString("name");
				byte[] image = rs.getBytes("image");
				HeaderImage header = new HeaderImage(id, name, image);
				headerImages.add(header);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return headerImages;
	}

	@Override
	public HeaderImage detail(int id) {
		String sql = "SELECT * FROM header WHERE id_header = ?";
		HeaderImage header = null;

		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					byte[] image = rs.getBytes("image");
					header = new HeaderImage(id, name, image);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return header;
	}

	@Override
	public boolean insert(HeaderImage t) {
		String sql = "INSERT INTO header (name, image) VALUES (?, ?)";

		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, t.getName());
			pstmt.setBytes(2, t.getImage());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean edit(HeaderImage t) {
		String sql = "UPDATE header SET name = ?, image = ? WHERE id_header = ?";

		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, t.getName());

			pstmt.setBytes(2, t.getImage());

			pstmt.setInt(3, t.getId());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM header WHERE id_header = ?";

		try (Connection conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			pstmt.executeUpdate();
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

}
