package tg.experta.kaba.data.Restaurant;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import tg.experta.kaba.data.Menu.Restaurant_MenuEntity;
import tg.experta.kaba.data._OtherEntities.Contact;

/**
 * By abiguime on 2017/11/25.
 * email: 2597434002@qq.com
 */

public class RestaurantEntity implements Parcelable {


    public int _id;

    public String restaurant_unique_id;

    public String restaurant_logo;

    public String restaurant_theme_image;

    public String restaurant_name;

    public String restaurant_description;

    public Contact restaurant_contact;

    public Restaurant_MenuEntity menu;

    public String last_update;


    public RestaurantEntity () {

    }


    protected RestaurantEntity(Parcel in) {
        _id = in.readInt();
        restaurant_unique_id = in.readString();
        restaurant_logo = in.readString();
        restaurant_theme_image = in.readString();
        restaurant_name = in.readString();
        restaurant_description = in.readString();
        restaurant_contact = in.readParcelable(Contact.class.getClassLoader());
        menu = in.readParcelable(Restaurant_MenuEntity.class.getClassLoader());
        last_update = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(restaurant_unique_id);
        dest.writeString(restaurant_logo);
        dest.writeString(restaurant_theme_image);
        dest.writeString(restaurant_name);
        dest.writeString(restaurant_description);
        dest.writeParcelable(restaurant_contact, flags);
        dest.writeParcelable(menu, flags);
        dest.writeString(last_update);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RestaurantEntity> CREATOR = new Creator<RestaurantEntity>() {
        @Override
        public RestaurantEntity createFromParcel(Parcel in) {
            return new RestaurantEntity(in);
        }

        @Override
        public RestaurantEntity[] newArray(int size) {
            return new RestaurantEntity[size];
        }
    };

    public static List<RestaurantEntity> fakeList(int i) {

        List<RestaurantEntity> res = new ArrayList<>();

        for (int i1 = 0; i1 < i; i1++) {
            res.add(new RestaurantEntity());
        }
        return res;
    }
}
