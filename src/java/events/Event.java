
package events;

import java.util.List;
import java.util.ArrayList;

public abstract class Event {

    private List<Ticket> tickets;

    public Event() {
        tickets = new ArrayList<Ticket>();
    }

    // Returns and removes one ticket from this Event object.
    // Should never be called when the object has only one or zero tickets
    // (it is the responsibility of a LotteryManager to ensure that.
    public Ticket getOneTicket() {
        Ticket ticket = null;
        int size = getNumOfTickets();
        if (size > 2) {
            // Get the first ticket
            ticket = tickets.remove(size - 1);
        }
        return ticket;
    }

    // Adds one ticket to the tickets lists of this object. Used when
    // donating tickets. Note, it is the responsibility of a ticket
    // object to donate itself properly.
    public void addOneTicket(Ticket t) {
        tickets.add(t);
    }

    public int getNumOfTickets() {
        return tickets.size();
    }

    // Invokes a given event.
    public abstract void invoke();

    // Returns the information about a given event.
    public abstract String getInfo();

    // Returns true if input name corresponds to the given event.
    public abstract boolean isEqual(String name);


}
