package kr.co.team.LKLH.ufit;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;


public class UFitMainActivity extends AppCompatActivity {

    DrawerLayout drawLeft;
    Toolbar toolbar;
    ImageView toolbarLeft, toolbarRight;
    ImageView manageSchedule;
    TextView toolbarHead, calendarDate;
    Intent intent;
    Calendar calendar = Calendar.getInstance();
    int currentPosition;
    int this_year = calendar.get(Calendar.YEAR);
    int this_month= calendar.get(Calendar.MONTH);
    int this_today= calendar.get(Calendar.DATE);
    int this_maxday=calendar.getActualMaximum(calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ufit_activity_main);

        drawLeft = (DrawerLayout)findViewById(R.id.uf_left_drawer);

        toolbar = (Toolbar) findViewById(R.id.uf_main_toolbar);
        setSupportActionBar(toolbar);

        toolbarHead = (TextView)findViewById(R.id.uf_toolbar_head);
        toolbarHead.setText("김근육");
        toolbarLeft = (ImageView)findViewById(R.id.uf_toolbar_left);
        toolbarRight= (ImageView)findViewById(R.id.uf_toolbar_right);
        toolbarLeft.setImageResource(R.drawable.btn_menu);
        toolbarRight.setImageResource(R.drawable.btn_menu2);

        drawLeft.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawLeft.openDrawer(GravityCompat.START);
            }
        });
        toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawLeft.openDrawer(GravityCompat.END);
            }
        });

        ViewPager viewPager = (ViewPager)findViewById(R.id.uf_schedule_viewpager);
        viewPager.setAdapter(new UFitScheduleRCVFragm.UFitSchedulePagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(this_today + 25000);
        currentPosition = viewPager.getCurrentItem();
        calendarDate = (TextView)findViewById(R.id.uf_main_date);
        calendarDate.setText(this_year + "." + (this_month+1) + "." + this_today);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int mCurrentPosition;
            private int mScrollState;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (currentPosition < position) {
                    if(this_today == this_maxday) {
                        this_today = 1;
                        this_month++;
                    }

                    else
                        ++this_today;
                } else if(currentPosition > position){
                    if(this_today == 1) {
                        this_today = this_maxday;
                        this_month--;
                    }
                    else
                        --this_today;
                }
                currentPosition = position;

                calendarDate.setText(this_year + "." + (this_month+1) + "." + this_today);

            }


            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.uf_right_menu, UFitRightDrawRCV.newInstance());
        ft.commit();

        //*//*트레이너 메뉴 프로필
        final LinearLayout menuTrProfile = (LinearLayout) findViewById(R.id.uf_left_menu_profil);
        menuTrProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UFitMainActivity.this, UFitTranerProfileActivity.class);
                startActivity(intent);
                drawLeft.closeDrawer(GravityCompat.START);
            }
        });
        final LinearLayout menuTrManageSchedule = (LinearLayout)findViewById(R.id.uf_left_menu_schedule);
        menuTrManageSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UFitMainActivity.this, UFitManageSchedule.class);
                startActivity(intent);
                drawLeft.closeDrawer(GravityCompat.START);
            }
        });
        //트레이너 메뉴 고객관리
        final LinearLayout menuMemManage = (LinearLayout) findViewById(R.id.uf_left_menu_mem);
        menuMemManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UFitMainActivity.this, UFitMemberManageActivity.class);
                startActivity(intent);
                drawLeft.closeDrawer(GravityCompat.START);
            }
        });
        //트레이너 메뉴 메세지
        final LinearLayout menuMessage = (LinearLayout)findViewById(R.id.uf_left_menu_message);
        menuMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UFitMainActivity.this, UFitMessageActivity.class);
                startActivity(intent);
                drawLeft.closeDrawer(GravityCompat.START);
            }
        });
        // 트레이너 메뉴 세팅
        final LinearLayout menuSetting = (LinearLayout)findViewById(R.id.uf_left_menu_setting);
        menuSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UFitMainActivity.this, UFitSettingActivity.class);
                startActivity(intent);
                drawLeft.closeDrawer(GravityCompat.START);
            }
        });
        // 플로팅 액션 버튼 -> 스케쥴 관리
        manageSchedule = (ImageView)findViewById(R.id.uf_schedule_fab) ;
        manageSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UFitMainActivity.this, UFitManageSchedule.class);
                startActivity(intent);
            }
        });
    }


}


