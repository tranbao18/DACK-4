import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_payment {
    static public product[] products = new product[5];
    static public cart[] cart = new cart[3];

    @Test
    public void testpay1() {
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);

        cart[0] = new cart(3, products[0]);
        cart[1] = new cart(2, products[1]);
        cart[2] = new cart(1, products[2]);
        payment test = new payment();
        int tongtien = test.ThanhToan(cart);
        assertEquals(3730000, tongtien, "Sai test 1");
    }
    @Test
    public void testpay2() {
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);

        cart[0] = new cart(1, products[0]);
        cart[1] = new cart(2, products[1]);
        cart[2] = new cart(3, products[2]);
        payment test = new payment();
        int tongtien = test.ThanhToan(cart);
        assertEquals(4110000, tongtien, "Sai test 2");
    }

    @Test
    public void testpay3() {
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);

        cart[0] = new cart(1, products[0]);
        cart[1] = new cart(1, products[1]);
        cart[2] = new cart(1, products[2]);
        payment test = new payment();
        int tongtien = test.ThanhToan(cart);
        assertEquals(1960000, tongtien, "Sai test 3");
    }
    @Test
    public void testpay4() {
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);

        cart[0] = new cart(2, products[0]);
        cart[1] = new cart(2, products[1]);
        cart[2] = new cart(2, products[2]);
        payment test = new payment();
        int tongtien = test.ThanhToan(cart);
        assertEquals(3920000, tongtien, "Sai test 4");
    }
}
