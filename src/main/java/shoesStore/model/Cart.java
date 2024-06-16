package shoesStore.model;

public class Cart {
    private int id;
    private int userId;
    private int productId;
    private String name; // Assuming this is the product name in the cart
    private String color; // Assuming this is the product color in the cart
    private String size; // Assuming this is the product size in the cart
    private int quantity;

    // Constructors, getters, and setters

    public Cart(int id, int userId, int productId, String name, String color, String size, int quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.name = name;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
