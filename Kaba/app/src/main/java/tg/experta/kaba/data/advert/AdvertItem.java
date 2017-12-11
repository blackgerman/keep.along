package tg.experta.kaba.data.advert;

import java.util.ArrayList;
import java.util.List;

/**
 * By abiguime on 2017/12/8.
 * email: 2597434002@qq.com
 */

public class AdvertItem {

    public int _id;
    public String img_path;
    public String ad_code;
    public String ad_hash;

    public static List<AdvertItem> fakeList(int i) {

        List<AdvertItem> a = new ArrayList<>();
        for (int i1 = 0; i1 < i; i1++) {
            AdvertItem ad = new AdvertItem();
            a.add(ad);
        }
        return a;
    }
}
