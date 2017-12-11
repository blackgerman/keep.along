package tg.experta.kaba.data.Food;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * By abiguime on 2017/11/25.
 * email: 2597434002@qq.com
 */

public class Restaurant_Menu_FoodEntity implements Parcelable {


    public int _id;
    public String title;
    public String price;
    public String food_pic;
    // for more details
    public String food_description;
    public List<String> food_details_pictures;
    public List<Food_Tag> food_tags;

    public Restaurant_Menu_FoodEntity () {
        food_tags = new ArrayList<>();
        food_details_pictures = new ArrayList<>();
    }


    protected Restaurant_Menu_FoodEntity(Parcel in) {
        _id = in.readInt();
        title = in.readString();
        price = in.readString();
        food_pic = in.readString();
        food_description = in.readString();
        food_details_pictures = in.createStringArrayList();
        food_tags = in.createTypedArrayList(Food_Tag.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(food_pic);
        dest.writeString(food_description);
        dest.writeStringList(food_details_pictures);
        dest.writeTypedList(food_tags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Restaurant_Menu_FoodEntity> CREATOR = new Creator<Restaurant_Menu_FoodEntity>() {
        @Override
        public Restaurant_Menu_FoodEntity createFromParcel(Parcel in) {
            return new Restaurant_Menu_FoodEntity(in);
        }

        @Override
        public Restaurant_Menu_FoodEntity[] newArray(int size) {
            return new Restaurant_Menu_FoodEntity[size];
        }
    };
}
