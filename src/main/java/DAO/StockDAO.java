package DAO;

import Model.Stock;
import Utils.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    private Connection connection;

    public StockDAO() {
        try {
            connection = new DBContext().getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Thêm giao dịch mới vào bảng Stock (Tính luôn totalPrice)
    public boolean addStockTransaction(int productID, int userID, int quantity, String type) {
        String query = "INSERT INTO Stock (productID, userID, quantity, type, totalPrice) VALUES (?, ?, ?, ?, ?)";
        double totalPrice = calculateTotalPrice(productID, quantity);

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, productID);
            ps.setInt(2, userID);
            ps.setInt(3, quantity);
            ps.setString(4, type);
            ps.setDouble(5, totalPrice);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Lấy tất cả giao dịch tồn kho
    public List<Stock> getAllStockTransactions() {
        return getStockByType(null);
    }

    // ✅ Lấy thông tin giao dịch theo ID
    public Stock getStockById(int stockID) {
        String query = "SELECT * FROM Stock WHERE stockID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, stockID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Stock(
                            rs.getInt("stockID"),
                            rs.getInt("productID"),
                            rs.getInt("userID"),
                            rs.getInt("quantity"),
                            rs.getString("type"),
                            rs.getTimestamp("timestamp"),
                            rs.getDouble("totalPrice")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Lọc giao dịch theo loại (Import hoặc Export)
    public List<Stock> getStockByType(String type) {
        List<Stock> stockList = new ArrayList<>();
        String query = "SELECT * FROM Stock";
        if (type != null) {
            query += " WHERE type = ?";
        }

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            if (type != null) {
                ps.setString(1, type);
            }
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    stockList.add(new Stock(
                            rs.getInt("stockID"),
                            rs.getInt("productID"),
                            rs.getInt("userID"),
                            rs.getInt("quantity"),
                            rs.getString("type"),
                            rs.getTimestamp("timestamp"),
                            rs.getDouble("totalPrice")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockList;
    }

    // ✅ Lấy danh sách giao dịch nhập hàng (Import)
    public List<Stock> getImportStock() {
        return getStockByType("import");
    }

    // ✅ Lấy danh sách giao dịch xuất hàng (Export)
    public List<Stock> getExportStock() {
        return getStockByType("export");
    }

    // ✅ Kiểm tra sản phẩm có tồn tại trong kho không
    public boolean isProductInStock(int productID) {
        String query = "SELECT COUNT(*) FROM Stock WHERE productID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, productID);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Xóa một giao dịch khỏi bảng Stock theo ID
    public boolean deleteStockTransaction(int stockID) {
        String query = "DELETE FROM Stock WHERE stockID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, stockID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Tính tổng giá trị của một giao dịch dựa trên productID và quantity
    private double calculateTotalPrice(int productID, int quantity) {
        String query = "SELECT price FROM Products WHERE productID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, productID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("price") * quantity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    // ✅ Tính tổng giá trị của tất cả giao dịch nhập hàng (import)

    public double calculateTotalImportValue() {
        String query = "SELECT SUM(totalPrice) FROM Stock WHERE type = 'import'";
        try ( PreparedStatement ps = connection.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1); // Lấy tổng giá trị import
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

// ✅ Tính tổng giá trị của tất cả giao dịch xuất hàng (export)
    public double calculateTotalExportValue() {
        String query = "SELECT SUM(totalPrice) FROM Stock WHERE type = 'export'";
        try ( PreparedStatement ps = connection.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1); // Lấy tổng giá trị export
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // ✅ Tính tổng chênh lệch giữa xuất hàng (export) và nhập hàng (import)
    public double calculateStockDifference() {
        String query = "SELECT "
                + "(SELECT COALESCE(SUM(totalPrice), 0) FROM Stock WHERE type = 'export') - "
                + "(SELECT COALESCE(SUM(totalPrice), 0) FROM Stock WHERE type = 'import') AS stockDifference";
        try ( PreparedStatement ps = connection.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("stockDifference");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

}
