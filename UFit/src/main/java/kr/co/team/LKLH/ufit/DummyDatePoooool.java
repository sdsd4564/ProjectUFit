package kr.co.team.LKLH.ufit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

/**
 * Created by Admin on 2016-07-29.
 */
public class DummyDatePoooool {
    String year;
    String title;
    String name;
    String time;
    int img;
    String chest, armfit, calf, thigh, waist;
    String centi = "cm";




    public void setData(String year, String title) {
        this.year = year;
        this.title = title;
    }
    public void setData(int img, String name, String time){
        this.img = img;
        this.name = name;
        this.time = time;
    }
    public void setData(int img, String name){
        this.img = img;
        this.name = name;
    }
    public void setData(String chest, String thigh, String calf, String armfit, String waist) {
        SpannableStringBuilder builder = new SpannableStringBuilder(centi);
        builder.setSpan(new ForegroundColorSpan(Color.RED),0,centi.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(builder);
        this.calf = calf + builder;
        this.chest = chest;
        this.thigh = thigh;
        this.armfit = armfit;
        this.waist = waist;
    }
}
