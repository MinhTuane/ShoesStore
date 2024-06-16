package shoesStore.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shoesStore.database.DatabaseConnector;
import shoesStore.model.Buy;

public class BuyDao implements InterfaceDAO<Buy> {
    static DatabaseConnector db = new DatabaseConnector();
    
    // New method to get buys for a specific user
    public List<Buy> getAllByUserId(int userId) {
        List<Buy> buys = new ArrayList<>();
        String sql = "SELECT * FROM buy WHERE user_id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement ppst = conn.prepareStatement(sql)) {
             
            ppst.setInt(1, userId);
            try (ResultSet rs = ppst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String size = rs.getString("size");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    Buy buy = new Buy(id, name, color, size, quantity, status, userId);
                    buys.add(buy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buys;
    }

    @Override
    public List<Buy> getAll() {
        List<Buy> buys = new ArrayList<>();
        String sql = "SELECT * FROM buy";

        try (Connection conn = db.getConnection();
             PreparedStatement ppst = conn.prepareStatement(sql);
             ResultSet rs = ppst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String color = rs.getString("color");
                String size = rs.getString("size");
                int quantity = rs.getInt("quantity");
                String status = rs.getString("status");
                int userId = rs.getInt("user_id");
                Buy buy = new Buy(id, name, color, size, quantity, status, userId);
                buys.add(buy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buys;
    }

    @Override
    public Buy detail(int id) {
        String sql = "SELECT * FROM buy WHERE id = ?";
        Buy buy = null;

        try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String size = rs.getString("size");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");
                    int userId = rs.getInt("user_id");
                    buy = new Buy(id, name, color, size, quantity, status, userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return buy;
    }

    @Override
    public boolean insert(Buy t) {
        String sql = "INSERT INTO buy (name, color, size, quantity, status, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getName());
            pstmt.setString(2, t.getColor());
            pstmt.setString(3, t.getSize());
            pstmt.setInt(4, t.getQuantity());
            pstmt.setString(5, t.getStatus());
            pstmt.setInt(6, t.getUserId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(Buy t) {
        String sql = "UPDATE buy SET name=?, color=?, size=?, quantity=?, status=?, user_id=? WHERE id = ?";

        try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getName());
            pstmt.setString(2, t.getColor());
            pstmt.setString(3, t.getSize());
            pstmt.setInt(4, t.getQuantity());
            pstmt.setString(5, t.getStatus());
            pstmt.setInt(6, t.getUserId());
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
        String sql = "DELETE FROM buy WHERE id = ?";

        try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
