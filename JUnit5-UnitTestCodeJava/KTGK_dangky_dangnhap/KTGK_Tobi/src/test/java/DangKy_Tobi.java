import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DangKy_Tobi {
    @Test
    public void TestDK(){
        System.out.print("Test DK 1: ");
        DangKy_Tobi_GK test = new DangKy_Tobi_GK();
        boolean kt = test.DangKy("Nguyễn", "Phát", "asasasa@gmail.com", "12345678");
        assertTrue(kt, "Thành công");
    }
    @Test
    public void TestDK2(){
        System.out.print("Test DK 2: ");
        DangKy_Tobi_GK test = new DangKy_Tobi_GK();
        boolean kt = test.DangKy("Nguy@ễn", "Ph@át", "asasasa@gmail.com", "12345678");
        assertFalse(kt, "Name ko có ký tự đặc biệt");
    }
    @Test
    public void TestDK3(){
        System.out.print("Test DK 3: ");
        DangKy_Tobi_GK test = new DangKy_Tobi_GK();
        boolean kt = test.DangKy("Nguyễn", "Phát", "asasasa@gmailcom", "12345678");
        assertFalse(kt, "Email sai định dạng");
    }
    @Test
    public void TestDK4(){
        System.out.print("Test DK 4: ");
        DangKy_Tobi_GK test = new DangKy_Tobi_GK();
        boolean kt = test.DangKy("Nguyễn", "Phát", "asasasa@gmail.com", "1234");
        assertFalse(kt, "Password tối thiểu 5 ký tự");
    }

    @Test
    public void TestDN1(){
        System.out.print("Test DN 1: ");
        DangNhap_Tobi_GK test = new DangNhap_Tobi_GK();
        boolean kt = test.DangNhap("nguyenphat@gmail.com", "12345678");
        assertTrue(kt, "Đăng nhập thành công");
    }
    @Test
    public void TestDN2(){
        System.out.print("Test DN 2: ");
        DangNhap_Tobi_GK test = new DangNhap_Tobi_GK();
        boolean kt = test.DangNhap("nguyenphat@gmailcom", "12345678");
        assertFalse(kt, "Email sai định dạng");
    }
    @Test
    public void TestDN3(){
        System.out.print("Test DN 3: ");
        DangNhap_Tobi_GK test = new DangNhap_Tobi_GK();
        boolean kt = test.DangNhap("nguyenphat@gmail.com", "123456789");
        assertFalse(kt, "Sai mật khẩu");
    }
    @Test
    public void TestDN4(){
        System.out.print("Test DN 4: ");
        DangNhap_Tobi_GK test = new DangNhap_Tobi_GK();
        boolean kt = test.DangNhap("ngu32yenphat@gmail.com", "12345678");
        assertFalse(kt, "Tài khoản không tồn tại");
    }
}
