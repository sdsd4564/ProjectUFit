package kr.co.team.LKLH.ufit;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class UFitManageSchedule extends AppCompatActivity {

    //날짜계산을 해보자
    Calendar calender = Calendar.getInstance(Locale.getDefault());
    Toolbar toolbar;
    ImageView tbLeft, tbRight;
    TextView tbHead;

    int this_year = calender.get(Calendar.YEAR);
    int this_month = calender.get(Calendar.MONTH);
    int this_maximum_day = calender.getActualMaximum(calender.DAY_OF_MONTH);

    int currentPosition;

    private Context mContext;
    TextView textView;
    GridView gridView;
    TextView clickedDate;

    ViewPager pager;
    CalendarFragmentPagerAdapter mFragmentAdapter = new CalendarFragmentPagerAdapter(getSupportFragmentManager(), this_year, this_month);

    static final String[] monday_to_sunday = new String[]{"S", "M", "T", "W", "T", "F", "S"};

    //선택날짜의 회원리스트를 리싸이클러뷰로 표현해보자
    private List<DummyDatePoooool> memberList = new ArrayList<>();
    private MemberItemAdapter memberRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.ufit_manage_schedule_sub);

        textView = (TextView) findViewById(R.id.tv_date);
        gridView = (GridView) findViewById(R.id.grid_view_monday_to_sunday);
        clickedDate = (TextView) findViewById(R.id.today);


        textView.setText(this_year + " . " + (this_month + 1));
        clickedDate.setText(this_year + " . " + (this_month + 1) + ". " + (calender.get(Calendar.DAY_OF_MONTH) + 1));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, monday_to_sunday);
        gridView.setAdapter(adapter);


        pager = (ViewPager) findViewById(R.id.viewpager1);
        pager.setAdapter(mFragmentAdapter);
        pager.setCurrentItem(this_month + 20736);
        currentPosition = pager.getCurrentItem();
        Log.e("메인에서의 포지션", "" + currentPosition);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageSelected(int position) {
                if (currentPosition < position) {
                    if (this_month == 11) {
                        this_month = 0;
                        this_year++;
                    } else
                        this_month++;
                } else if (currentPosition > position) {
                    if (this_month == 0) {
                        this_month = 11;
                        this_year--;
                    } else
                        this_month--;
                }
                currentPosition = position;

                textView.setText(this_year + " . " + (this_month + 1));

            }

        });

        toolbar = (Toolbar)findViewById(R.id.uf_main_toolbar);
        setSupportActionBar(toolbar);
        tbHead = (TextView)findViewById(R.id.uf_toolbar_head);
        tbHead.setText(R.string.uf_schedule);
        tbLeft = (ImageView)findViewById(R.id.uf_toolbar_left);
        tbLeft.setImageResource(R.drawable.btn_topleft);
        tbLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tbRight= (ImageView)findViewById(R.id.uf_toolbar_right);
        tbRight.setVisibility(View.INVISIBLE);


//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.manage_schedule_rcv, UFitRightDrawRCV.newInstance());
//        ft.commit();
        RecyclerView rv = (RecyclerView)findViewById(R.id.ufit_manage_schedule_recycleview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);

        for(int i = 0; i < calender.get(Calendar.DAY_OF_MONTH); i++){
            DummyDatePoooool dummyDatePoooool = new DummyDatePoooool();
            dummyDatePoooool.setData(R.drawable.pic, "오늘회원님" + (i + 1), "2:" + i*2 + "pm");
            memberList.add(dummyDatePoooool);
        }

        memberRecyclerViewAdapter = new MemberItemAdapter(memberList);
        rv.setAdapter(memberRecyclerViewAdapter);


//        private List<Movie> moviesList;
    }
}