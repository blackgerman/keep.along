package tg.experta.kaba.data._OtherEntities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * By abiguime on 2017/11/25.
 * email: 2597434002@qq.com
 */

public class Contact implements Parcelable{

    public int _id;
    public int restaurant_id;
    public String[] phone;
    public String address;

    public Contact (){}

    protected Contact(Parcel in) {
        _id = in.readInt();
        restaurant_id = in.readInt();
        phone = in.createStringArray();
        address = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_id);
        parcel.writeInt(restaurant_id);
        parcel.writeStringArray(phone);
        parcel.writeString(address);
    }
}
