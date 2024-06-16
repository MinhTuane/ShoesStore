package shoesStore.model;

public class Buy {
    private int id;
    private String name;
    private String color;
    private String size;
    private int quantity;
    private String status;
    private int userId;

    public Buy(int id, String name, String color, String size, int quantity, String status, int userId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.status = status;
        this.userId = userId;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
