import java.sql.Array;

public class payment {
    public int ThanhToan (cart[] cart) {
        int tongtien = 0;
        for (int i = 0; cart.length > i ;i++) {
            tongtien += cart[i].gia * cart[i].sl;
        }
        return tongtien;
    }
}
