package eu.ensup.gestionetablissement.dto;

import eu.ensup.gestionetablissement.business.Role;

/**
 * The type Director dto.
 */
public class DirectorDTO extends ManagerDTO{
    /**
     * Instantiates a new Director.
     *
     * @param lastname    the lastname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param id          the id
     * @param firstname   the firstname
     * @param password    the password
     */
    public DirectorDTO(String lastname, String mailAddress, String address, String phoneNumber, int id, String firstname, String password) {
        super(lastname, mailAddress, address, phoneNumber, id, firstname, Role.DIRECTOR, password);
    }

    /**
     * Instantiates a new Director dto.
     *
     * @param lastname    the lastname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param firstname   the firstname
     * @param password    the password
     */
    public DirectorDTO(String lastname, String mailAddress, String address, String phoneNumber,  String firstname, String password) {
        super(lastname, mailAddress, address, phoneNumber, firstname, Role.DIRECTOR, password);
    }

    /**
     * Instantiates a new Director dto.
     */
    public DirectorDTO() {
    }

    @Override
    public String toString() {
        String res = super.toString();
        res = res.replace("Manager", "Director");

        return res;
    }
}
