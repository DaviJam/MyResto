package eu.ensup.myresto.business;

import java.util.ArrayList;
import java.util.List;

public enum Status {
    /**
     * En cours status.
     */
    ENCOURS (1, "Encours"),
    /**
     * Terminé status.
     */
    TERMINE (2, "Terminé"),
    /**
     * Annulé status.
     */
    ANNULE  (3, "Annulé");

    private int    numStatus;
    private String name;

    private Status(int numStatus, String name)
    {
        this.numStatus = numStatus;
        this.name = name;
    }

    /**
     * Gets num.
     *
     * @return the num
     */
    public int    getNum()  { return this.numStatus; }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() { return this.name; }

    /**
     * Gets Status by name.
     *
     * @param name the name
     * @return the Status by name
     */
    public Status getStatusByName(String name)
    {
        switch(name)
        {
            case "Encours": return this.ENCOURS;
            case "Terminé": return this.TERMINE;
            case "Annulé": return this.ANNULE;
            default: return this.ENCOURS;
        }
    }

    /**
     * Gets Status by num.
     *
     * @param num the num
     * @return the Status by num
     */
    static public Status getStatusByNum(int num)
    {
        switch(num)
        {
            case 1: return ENCOURS;
            case 2: return TERMINE;
            case 3: return ANNULE;
            default: return ENCOURS;
        }
    }

    /**
     * Gets all categories.
     *
     * @return the all categories
     */
    public List<Status> getAllCategories()
    {
        List<Status> lStatus = new ArrayList<Status>();

        lStatus.add(this.ENCOURS);
        lStatus.add(this.TERMINE);
        lStatus.add(this.ANNULE);

        return lStatus;
    }

    @Override
    public String toString() {
        return "Status{" +
                "numStatus=" + numStatus +
                ", name='" + name + '\'' +
                '}';
    }
}
