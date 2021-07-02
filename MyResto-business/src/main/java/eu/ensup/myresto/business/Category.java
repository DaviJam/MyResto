package eu.ensup.myresto.business;

import java.util.ArrayList;
import java.util.List;

/**
 * The enum Category.
 */
public enum Category
{
    /**
     * Entree category.
     */
    MENU (1, "Menu"),
    /**
     * Entree category.
     */
    BURGER (2, "Burger"),
    /**
     * Manager category.
     */
    SUPPLEMENT  (3, "Supplément"),
    /**
     * Manager category.
     */
    DRINK  (4, "Boisson");

    private int    numCategory;
    private String name;

    private Category(int numCategory, String name)
    {
        this.numCategory = numCategory;
        this.name = name;
    }

    /**
     * Gets num.
     *
     * @return the num
     */
    public int getNum()  { return this.numCategory; }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() { return this.name; }

    /**
     * Gets category by name.
     *
     * @param name the name
     * @return the category by name
     */
    public Category getCategoryByName(String name)
    {
        switch(name)
        {
            case "Menu": return this.MENU;
            case "Burger": return this.BURGER;
            case "Supplément": return this.SUPPLEMENT;
            case "Boisson": return this.DRINK;
            default: return this.MENU;
        }
    }

    /**
     * Gets category by num.
     *
     * @param num the num
     * @return the category by num
     */
    static public Category getCategoryByNum(int num)
    {
        switch(num)
        {
            case 1: return MENU;
            case 2: return BURGER;
            case 3: return SUPPLEMENT;
            case 4: return DRINK;
            default: return MENU;
        }
    }

    /**
     * Gets all categories.
     *
     * @return the all categories
     */
    public List<Category> getAllCategories()
    {
        List<Category> lCategory = new ArrayList<Category>();

        lCategory.add(this.MENU);
        lCategory.add(this.BURGER);
        lCategory.add(this.SUPPLEMENT);
        lCategory.add(this.DRINK);

        return lCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "numCategory=" + numCategory +
                ", name='" + name + '\'' +
                '}';
    }
}
