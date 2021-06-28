package eu.ensup.myresto.business;

/**
 * The type Product.
 */
public class Product{
    private int id;
    private String name;
    private String description;
    private double price;
    private String allergen;
    private String image;
    private int stock;
    private Category category;

    public Product()
    {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.price = 0;
        this.allergen = "";
        this.image = "";
        this.stock = 0;
        this.category = Category.ENTREE;
    }
    public Product(int id, String name, String description, double price, String allergen, String image, int stock, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergen = allergen;
        this.image = image;
        this.stock = stock;
        this.category = category;
    }

    public Product(String name, String description, double price, String allergen, String image, int stock, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergen = allergen;
        this.image = image;
        this.stock = stock;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", allergen='" + allergen + '\'' +
                ", image='" + image + '\'' +
                ", stock=" + stock +
                ", category=" + category +
                '}';
    }
}
