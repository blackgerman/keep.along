package tg.abiguime.keepalong._core;

import android.app.Application;
import android.graphics.Typeface;

/**
 * By abiguime on 24/12/2016.
 * email: 2597434002@qq.com
 */

public class PockiApplication extends Application {

    Typeface footstep = null;

    Typeface dailyQuote = null;


    @Override
    public void onCreate() {
        super.onCreate();
        initFonts();
    }

    private void initFonts() {
        if (footstep == null || dailyQuote == null) {
            footstep = Typeface.createFromAsset(this.getAssets(), "fonts/plastik_comics.ttf");
            dailyQuote = Typeface.createFromAsset(this.getAssets(), "fonts/smart_kid.ttf");
        }
    }

    public enum PockiTypeFace {
        footstep, dailyQuote
    }

    public Typeface getTypeFace (PockiTypeFace tf) {
        if (tf.equals(PockiTypeFace.footstep)){
                return footstep;
        } else if (tf.equals(PockiTypeFace.dailyQuote))
            return dailyQuote;
        return null;
    }

}
