package eu.ensup.myresto.dto;

import java.util.ArrayList;
import java.util.List;

public enum StatusDTO {
    /**
     * En attente status.
     */
    ENATTENTE (1, "En attente"),
    /**
     * En cours status.
     */
    ENCOURS (2, "En cours"),
    /**
     * Terminé status.
     */
    TERMINE (3, "Terminé"),
    /**
     * Annulé status.
     */
    ANNULE  (4, "Annulé");

    private int    numStatus;
    private String name;

    private StatusDTO(int numStatus, String name)
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
    public StatusDTO getStatusByName(String name)
    {
        switch(name)
        {
            case "Enattente": return this.ENATTENTE;
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
    static public StatusDTO getStatusByNum(int num)
    {
        switch(num)
        {
            case 1: return ENATTENTE;
            case 2: return ENCOURS;
            case 3: return TERMINE;
            case 4: return ANNULE;
            default: return ENCOURS;
        }
    }

    /**
     * Gets all categories.
     *
     * @return the all categories
     */
    public List<StatusDTO> getAllCategories()
    {
        List<StatusDTO> lStatus = new ArrayList<StatusDTO>();

        lStatus.add(this.ENATTENTE);
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
