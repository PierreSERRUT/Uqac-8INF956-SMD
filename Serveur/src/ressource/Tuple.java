package ressource;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import javax.print.attribute.standard.DateTimeAtCompleted;

// Faut géré l'histoire du temps pour delete les clients qui reste trop lgt en dispo 

public class Tuple<Client, Date> { 
    public final Client client; 
    public final Date date; 
    
    public Tuple(Client client, Date date) {
    	
        this.client = client; 
        this.date = date; 
    }

    // Useless ?
    
    @Override
    public String toString() {
        return "(" + client + "," + date + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tuple)){
            return false;
        }

        Tuple<Client,Date> other_ = (Tuple<Client,Date>) other;

        // this may cause NPE if nulls are valid values for x or y. The logic may be improved to handle nulls properly, if needed.
        return other_.client.equals(this.client) && other_.date.equals(this.date);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }
}
