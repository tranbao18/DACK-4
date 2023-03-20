public class search {
    String name;
    String type;
    public product[] searchName (String name,product[] prod) {
        this.name = name;
        product[] result = new product[5];
        int i = 0;
        for (product product: prod) {
            if (product.name.toLowerCase().contains(this.name.toLowerCase())) {
                result[i] = product;
                i++;
            }
        }
        return result;
    }
    public product[] searchType (String type, product[] prod) {
        this.type = type;
        product[] result = new product[5];
        int i = 0;
        for (product product: prod) {
            if (product.type == this.type) {
                result[i] = product;
                i++;
            }
        }
        return result;
    }
}
