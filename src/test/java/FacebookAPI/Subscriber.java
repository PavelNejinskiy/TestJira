package FacebookAPI;

public class Subscriber {

    public Subscriber(String name, String lastName, String ID) {
        this.name = name;
        this.lastName = lastName;
        this.ID = ID;
    }

    public Subscriber(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    String name;
    String lastName;
    String ID;


}
