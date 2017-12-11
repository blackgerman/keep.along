package tg.experta.kaba.data.Food;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * By abiguime on 2017/12/4.
 * email: 2597434002@qq.com
 */

public class Food_Tag implements Parcelable{

    public String name;
    public int state = 1; // 1 selected // 0 unselected


    public Food_Tag (String name) {
        this.name = name.toUpperCase();
    }

    protected Food_Tag(Parcel in) {
        name = in.readString();
        state = in.readInt();
    }

    public static final Creator<Food_Tag> CREATOR = new Creator<Food_Tag>() {
        @Override
        public Food_Tag createFromParcel(Parcel in) {
            return new Food_Tag(in);
        }

        @Override
        public Food_Tag[] newArray(int size) {
            return new Food_Tag[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(state);
    }

    public void switchState() {
        switch (state) {
            case 0:
                state = 1;
                break;
            case 1:
                state = 0;
                break;
        }
    }
}
