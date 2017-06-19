package projet.supercalcmobile;

import android.os.Parcel;
import android.os.Parcelable;

class User implements Parcelable {
    private String name;
    private String emailAddress;
    private String password;

    public User(String name, String emailAddress, String password) {
        super();
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }


    public User(Parcel in)
    {
        //Same order as in writeToParcel
        this.name = in.readString();
        this.emailAddress = in.readString();
        this.password = in.readString();
    }
@Override
    public int describeContents()
    {
        return 0;
    }

@Override
    public void writeToParcel(Parcel dest, int flags)
    {
        //Same order as in User(Parcel in)
        dest.writeString(name);
        dest.writeString(emailAddress);
        dest.writeString(password);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel source)
        {
            return new User(source);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };
    public static Parcelable.Creator<User> getCreator()
    {
        return CREATOR;
    }

    String getName()
    {
        return name;
    }

    String getEmailAddress()
    {
        return emailAddress;
    }

    String getPassword()
    {
        return password;
    }
}
