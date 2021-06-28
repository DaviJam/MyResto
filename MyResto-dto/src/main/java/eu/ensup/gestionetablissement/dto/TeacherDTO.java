package eu.ensup.gestionetablissement.dto;

import eu.ensup.gestionetablissement.business.Role;

/**
 * The type Teacher dto.
 */
public class TeacherDTO extends PersonDTO{

    private String subjectTaught;

    /**
     * Instantiates a new Teacher.
     *
     * @param lastname      the lastname
     * @param mailAddress   the mail address
     * @param address       the address
     * @param phoneNumber   the phone number
     * @param id            the id
     * @param firstname     the firstname
     * @param password      the password
     * @param subjectTaught the subject taught
     */
    public TeacherDTO(String lastname, String mailAddress, String address, String phoneNumber, int id, String firstname, String password, String subjectTaught) {
        super(lastname, mailAddress, address, phoneNumber, id, firstname, Role.TEACHER, password);
        this.subjectTaught = subjectTaught;
    }

    /**
     * Instantiates a new Teacher dto.
     *
     * @param lastname      the lastname
     * @param mailAddress   the mail address
     * @param address       the address
     * @param phoneNumber   the phone number
     * @param firstname     the firstname
     * @param password      the password
     * @param subjectTaught the subject taught
     */
    public TeacherDTO(String lastname, String mailAddress, String address, String phoneNumber, String firstname, String password, String subjectTaught) {
        super(lastname, mailAddress, address, phoneNumber, firstname, Role.TEACHER, password);
        this.subjectTaught = subjectTaught;
    }

    /**
     * Instantiates a new Teacher dto.
     */
    public TeacherDTO() {

    }

    /**
     * Get subject taught string.
     *
     * @return the string
     */
    public String getSubjectTaught(){
        return subjectTaught;
    }

    /**
     * Set subject taught.
     *
     * @param subjectTaught the subject taught
     */
    public void setSubjectTaught(String subjectTaught){
        this.subjectTaught = subjectTaught;
    }

    @Override
    public String toString() {
        String res = super.toString();
        res = res.replace("Person", "Teacher");
        res = res.substring(0, res.length()-1);
        res = res + ", subjectTaught=\" + subjectTaught + \"]";

        return res;
    }
}
