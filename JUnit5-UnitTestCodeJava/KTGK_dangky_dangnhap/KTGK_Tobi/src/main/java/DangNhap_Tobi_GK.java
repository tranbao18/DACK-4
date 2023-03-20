import java.util.regex.*;
public class DangNhap_Tobi_GK {
    String TB="";
    Customer[] KHT = new Customer[2];
    public boolean DangNhap(String Email, String Password){
        KHT[0]=new Customer("Nguyễn", "Phát", "nguyenphat@gmail.com", "12345678");
        if(Email==""){
            TB+="Email không được bỏ trống";
        } else if(testcach(Email)==false){
            TB+="Email không được chứa dấu cách ở đầu ";
        } else if(testvalid(Email)==false){
            TB+="Email không hợp lệ ";
        }

        if(Password==""){
            TB+="Password không được bỏ trống";
        } else if(testcach(Password)==false){
            TB+="Password không được có dấu cách ở đầu ";
        } else if(Password.length()<8){
            TB+="Password tối thiểu 8 ký tự ";
        }

        if(KHT[0]!=null){
            if(TB=="" && Email.equals(KHT[0].getEmail()) && Password.equals(KHT[0].getPassword())){
                System.out.println("Đăng nhập thành công");
                return true;
            }
            else if(TB=="" && !Email.equals(KHT[0].getEmail())){
                System.out.println("Tài khoản không tồn tại. Email không đúng.");
                return false;
            }
            else if(TB=="" && Email.equals(KHT[0].getEmail()) && !Password.equals(KHT[0].getPassword())){
                System.out.println("Sai mật khẩu");
                return false;
            }
            else{
                System.out.println(TB);
                return false;
            }
        }
        else{
            System.out.println("Chưa có tài khoản trên hệ thống");
            return false;
        }
    }

    public boolean testcach(String chu){
        String[] arrchu=chu.split("");
        if(arrchu[0]==" "){
            return false;
        }
        return true;
    }

    public boolean testvalid(String chu){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(chu);
        return matcher.matches();
    }
}
