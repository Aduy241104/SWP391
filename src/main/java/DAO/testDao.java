package DAO;

public class testDAO {
    public static void main(String[] args) {
        userDAO dao = new userDAO();
        
        int userId = 1; // Thay đổi ID phù hợp
        String oldPassword = "password1";
        String newPassword = "654321";
        
        boolean result = dao.changePassword(userId, oldPassword, newPassword);
        if (result) {
            System.out.println("Mật khẩu đã được thay đổi thành công!");
        } else {
            System.out.println("Thay đổi mật khẩu thất bại. Kiểm tra lại thông tin.");
        }
    }
}
