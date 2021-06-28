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
    ENTREE (1, "Entree"),
    /**
     * Entree category.
     */
    PLAT (2, "Plat"),
    /**
     * Manager category.
     */
    DESSERT  (3, "Dessert");

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
            case "Entree": return this.ENTREE;
            case "Plat": return this.PLAT;
            case "Dessert": return this.DESSERT;
            default: return this.ENTREE;
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
            case 1: return ENTREE;
            case 2: return PLAT;
            case 3: return DESSERT;
            default: return ENTREE;
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

        lCategory.add(this.ENTREE);
        lCategory.add(this.PLAT);
        lCategory.add(this.DESSERT);

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
