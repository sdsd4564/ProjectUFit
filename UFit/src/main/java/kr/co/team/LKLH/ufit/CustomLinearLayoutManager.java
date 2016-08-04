package kr.co.team.LKLH.ufit;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by Encho on 2016-08-01.
 */
public class CustomLinearLayoutManager extends LinearLayoutManager {
    public boolean isScrollEnabled = true;
    public CustomLinearLayoutManager(Context context) {
        super(context);
    }
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
