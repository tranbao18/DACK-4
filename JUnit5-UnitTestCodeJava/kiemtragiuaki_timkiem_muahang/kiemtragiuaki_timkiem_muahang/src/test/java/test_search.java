import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_search {
    @Test
    public void testpay1() {
        product[] products = new product[5];
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);
        search test = new search();
        product[] result = test.searchName("TEE",products);
        assertEquals(products[0], result[0], "Sai test 1");
    }
    @Test
    public void testpay2() {
        product[] products = new product[5];
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);
        search test = new search();
        product[] result = test.searchName("TE",products);
        assertEquals(products[0], result[0], "Sai Test 2");
    }

    @Test
    public void testpay3() {
        product[] products = new product[5];
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);
        search test = new search();
        product[] result = test.searchName("TIE",products);
        assertEquals(null, result[0], "Sai Test 3");
    }

    @Test
    public void testpay4() {
        product[] products = new product[5];
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);
        search test = new search();
        product[] result = test.searchName(" TE",products);
        assertEquals(null, result[0], "Sai Test 4");
    }
    @Test
    public void testpay5() {
        product[] products = new product[5];
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);
        search test = new search();
        product[] result = test.searchType("HOODIES",products);
        assertEquals(products[1], result[0], "Sai test 5");
    }
    @Test
    public void testpay6() {
        product[] products = new product[5];
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);
        search test = new search();
        product[] result = test.searchType("PANTS",products);
        assertEquals(products[2], result[0], "Sai test 6");
    }

    @Test
    public void testpay7() {
        product[] products = new product[5];
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);
        search test = new search();
        product[] result = test.searchType("SHIRT",products);
        assertEquals(products[3], result[0], "Sai test 7");
    }

    @Test
    public void testpay8() {
        product[] products = new product[5];
        products[0] = new product(1,"TEE", "TEE",460000);
        products[1] = new product(2,"HOODIE", "HOODIES",850000);
        products[2] = new product(3,"PANTS", "PANTS",650000);
        products[3] = new product(1,"SHIRT", "SHIRT",750000);
        products[4] = new product(2,"SHORT", "SHORT",490000);
        search test = new search();
        product[] result = test.searchType("SHORT",products);
        assertEquals(products[4], result[0], "Sai test 8");
    }
}
