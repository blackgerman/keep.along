package tg.experta.kaba.data.Menu;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tg.experta.kaba.data.Food.Restaurant_Menu_FoodEntity;
import tg.experta.kaba.data.Restaurant.RestaurantEntity;

/**
 * By abiguime on 2017/12/3.
 * email: 2597434002@qq.com
 */

public class Restaurant_SubMenuEntity implements Parcelable {


    public int _id;
    public String title;
    public List<Restaurant_Menu_FoodEntity> foods;

    public Restaurant_SubMenuEntity () {

    }

    protected Restaurant_SubMenuEntity(Parcel in) {
        _id = in.readInt();
        title = in.readString();
        foods = in.createTypedArrayList(Restaurant_Menu_FoodEntity.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(title);
        dest.writeTypedList(foods);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Restaurant_SubMenuEntity> CREATOR = new Creator<Restaurant_SubMenuEntity>() {
        @Override
        public Restaurant_SubMenuEntity createFromParcel(Parcel in) {
            return new Restaurant_SubMenuEntity(in);
        }

        @Override
        public Restaurant_SubMenuEntity[] newArray(int size) {
            return new Restaurant_SubMenuEntity[size];
        }
    };

    public static Restaurant_SubMenuEntity fake() {
        Restaurant_SubMenuEntity tmp = new Restaurant_SubMenuEntity();
        tmp.title = "title";
        tmp._id = 0;
        tmp.foods  = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            Restaurant_Menu_FoodEntity fd = new Restaurant_Menu_FoodEntity();
            fd._id = 0;
            fd.title = "food";
            tmp.foods.add(fd);
        }
        return tmp;
    }
}
