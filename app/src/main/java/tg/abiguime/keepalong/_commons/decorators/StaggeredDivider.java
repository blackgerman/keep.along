package tg.abiguime.keepalong._commons.decorators;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abiguime on 2016/12/9.
 */

public class StaggeredDivider extends RecyclerView.ItemDecoration {

    private final Drawable divider;


    public StaggeredDivider(Drawable drawable) {
        this.divider = drawable;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            // left
            outRect.right = divider.getIntrinsicHeight()/2;
        } else {
            // right
            outRect.left = divider.getIntrinsicHeight()/2;
        }
        if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1)
            return;
        // 两个视图之间的距离就是分划线的高度
        outRect.top = divider.getIntrinsicHeight();
    }


    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);

        /**
         * 计算绘图的范围
         */
        int divider_left = parent.getPaddingLeft();
        int divider_right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < parent.getChildCount()-1; i++) {
            View child = parent.getChildAt(i);
            // 更改本view 的大小参数
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int dividerTop = child.getBottom()+ params.bottomMargin;
            int dividerBottom = dividerTop + divider.getIntrinsicHeight();

            divider.setBounds(divider_left, dividerTop, divider_right, dividerBottom);
            divider.draw(canvas);
        }
    }
}