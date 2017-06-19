package projet.supercalcmobile;

import android.os.Parcel;
import android.os.Parcelable;

class User {
    private String name;
    private String emailAddress;
    private String password;

    public User(String name, String emailAddress, String password) {
        super();
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }


    String getName()
    {
        return name;
    }

    void setName(String name) { this.name = name;}

    String getEmailAddress()
    {
        return emailAddress;
    }

    void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    String getPassword()
    {
        return password;
    }

    void setPassword(String password) { this.password = password;}
}
