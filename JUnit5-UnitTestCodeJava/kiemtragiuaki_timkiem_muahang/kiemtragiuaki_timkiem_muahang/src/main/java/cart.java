public class cart {
    public int id;
    public String name;
    public String type;
    public int gia;
    public int sl;
//    product product = new product();
    public cart(int sl, product prod) {
        this.id = prod.id;
        this.name = prod.name;
        this.type = prod.type;
        this.gia = prod.gia;
        this.sl = sl;
    }
}
