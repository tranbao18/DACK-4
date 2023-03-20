import java.util.regex.*;
public class DangKy_Tobi_GK {
    String TB="";
    Customer[] KH = new Customer[2];
    public boolean DangKy(String FirstName, String LastName, String Email, String Password){

         if(FirstName==""){
             TB+="First Name không được bỏ trống";
         } else if(testcach(FirstName)==false){
             TB+="First Name không được chứa dấu cách ở đầu ";
         } else if(testso(FirstName)==false){
             TB+="First Name không được có số ";
         } else if(testspec(FirstName)==false){
             TB+="First Name không được chứa ký tự đặc biệt ";
         }

        if(LastName==""){
            TB+="Last Name không được bỏ trống";
        } else if(testcach(LastName)==false){
            TB+="Last Name không được chứa dấu cách ở đầu ";
        } else if(testso(LastName)==false){
            TB+="Last Name không được có số ";
        } else if(testspec(LastName)==false){
            TB+="Last Name không được chứa ký tự đặc biệt ";
        }

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
        } else if(Password.length()<5){
            TB+="Password tối thiểu 5 ký tự ";
        }
        if(TB==""){
            KH[0]=new Customer(FirstName, LastName, Email, Password);
            System.out.println("Success");
            return true;
        }
        else{
            System.out.println(TB);
            return false;
        }
    }
    public boolean testso(String chu){
        boolean flag = true;
        String[] arrchu=chu.split("",-1);
        for (int i=0; i< arrchu.length-1; i++){
            for(int j=0; j<10; j++){
                if(arrchu[i].equals(Integer.toString(j))){
                    flag=false;
                }
            }
        }
        return flag;
    }

    public boolean testcach(String chu){
        String[] arrchu=chu.split("",-1);
        if(arrchu[0].equals(" ")){
            return false;
        }
        return true;
    }

    public boolean testspec(String chu){
        boolean flag = true;
        String[] arrchu=chu.split("",-1);
        String[] arrspec= {".", ",", "<", ">", "?", "/", ":", ";", "'", "{", "}", "[", "]", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+"};
        for (int i=0; i< arrchu.length-1; i++){
            for(int j=0; j<arrspec.length; j++){
                if(arrchu[i].equals(arrspec[j])){
                    flag = false;
                }
            }
        }
        return flag;
    }

    public boolean testvalid(String chu){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(chu);
        return matcher.matches();
    }
}
