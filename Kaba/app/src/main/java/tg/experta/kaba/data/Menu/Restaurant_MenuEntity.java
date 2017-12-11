package tg.experta.kaba.data.Menu;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import tg.experta.kaba.data.Restaurant.RestaurantEntity;

/**
 * By abiguime on 2017/11/25.
 * email: 2597434002@qq.com
 */

public class Restaurant_MenuEntity implements Parcelable {


    public int _id;
    public String restaurant_unique_id;
    public List<Restaurant_SubMenuEntity> sub_menus;


    public Restaurant_MenuEntity() {

    }

    protected Restaurant_MenuEntity(Parcel in) {
        _id = in.readInt();
        restaurant_unique_id = in.readString();
        sub_menus = in.createTypedArrayList(Restaurant_SubMenuEntity.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(restaurant_unique_id);
        dest.writeTypedList(sub_menus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Restaurant_MenuEntity> CREATOR = new Creator<Restaurant_MenuEntity>() {
        @Override
        public Restaurant_MenuEntity createFromParcel(Parcel in) {
            return new Restaurant_MenuEntity(in);
        }

        @Override
        public Restaurant_MenuEntity[] newArray(int size) {
            return new Restaurant_MenuEntity[size];
        }
    };
}
