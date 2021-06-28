package eu.ensup.myresto.business;

/**
 * The type Users.
 */
public class User {
    private int id;
    private String surname;
    private String firstname;
    private Role role;
    private String email;
    private String password;
    private String address;

    public User(){
        this.id = 0;
        this.surname = "";
        this.firstname = "";
        this.role = Role.CLIENT;
        this.email = "";
        this.password = "";
        this.address = "";
    }

    public User(int id, String surname, String firstname, Role role, String email, String password, String address) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.role = role;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", surename='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
