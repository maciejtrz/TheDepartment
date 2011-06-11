package events;

public class Ticket {

    private Event owner;

    public Ticket (Event owner) {
        this.owner = owner;
    }

    public Event getOwner() {
        return owner;
    }

    public void donate(Event newOwner) {

        newOwner.addOneTicket(this);
        owner = newOwner;
    }
}
