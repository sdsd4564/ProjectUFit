package kr.co.team.LKLH.ufit;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016-08-01.
 */
public class CalendarFragment extends Fragment {
    private String title;
    private int page;

    /////프래그먼트가 리싸이클러뷰를 감싸는 코드 변수
    private RecyclerView mRecycler;
    private GridLayoutManager mGM;
    private int columnCount = 7;
    private DaysCellAdapter mAdapter;
    private View OldView;


    private RecyclerView memberListRecycler;
    //    private Calendar today = Calendar.getInstance(Locale.getDefault());


//    public CalendarFragment() {
//    }

    public static CalendarFragment newInstance(int year, int  month, int maximumDay, int startDay) {
        CalendarFragment calendarFragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putInt("ThisYear", year);
        args.putInt("ThisMonth", month);
        args.putInt("ThisMaximumDay", maximumDay);
        args.putInt("ThisStartDay", startDay);
        calendarFragment.setArguments(args);
        return calendarFragment;
    }

    public static CalendarFragment newInstance(int year, int  month, int maximumDay, int startDay, int today) {
        CalendarFragment calendarFragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putInt("today", today);
        args.putInt("ThisYear", year);
        args.putInt("ThisMonth", month);
        args.putInt("ThisMaximumDay", maximumDay);
        args.putInt("ThisStartDay", startDay);
        calendarFragment.setArguments(args);
        return calendarFragment;
    }

    public void circleToday(){

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        mRecycler = (RecyclerView)view.findViewById(R.id.recycler);
        memberListRecycler = (RecyclerView)getActivity().findViewById(R.id.ufit_manage_schedule_recycleview);

        final int maximumDay = super.getArguments().getInt("ThisMaximumDay");
        final int year = super.getArguments().getInt("ThisYear");
        final int month = super.getArguments().getInt("ThisMonth");
        final int startDay = super.getArguments().getInt("ThisStartDay");


        mGM = new GridLayoutManager(super.getContext(), columnCount, 1, false);
        mRecycler.setLayoutManager(new GridLayoutManager(super.getContext(), columnCount, 1, false));
        mRecycler.addItemDecoration(new DividerItemDecoration(super.getContext(), 1));
        mAdapter = new DaysCellAdapter(super.getContext(), maximumDay, startDay);
        mRecycler.setAdapter(mAdapter);



        final GestureDetector mGestureDetector = new GestureDetector(super.getContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        mRecycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = mRecycler.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mGestureDetector.onTouchEvent(e) && rv.getChildAdapterPosition(child) > startDay - 2) {
                    Log.i("클릭한 날짜", "" + (rv.getChildAdapterPosition(child) - startDay + 2));

                    //날짜를 클릭하며 이동할때 전에 잡아둔 올드뷰의 써클(날짜선택)을 취소시킬때
                    if(OldView != null){

                        OldView.findViewById(R.id.todayCircle).setBackgroundResource(0);
                    }
//                    child.setBackgroundColor(Color.parseColor("#FFFF00"));
//                    child.setBackgroundResource(R.drawable.today_circle);


                    // 작은 출석체크 점 표시.
                    ImageView test = (ImageView)child.findViewById(R.id.attend);
                    test.setImageResource(R.drawable.schedule_circle);

                    //큰 오늘 선택 써클 표시
                    child.findViewById(R.id.todayCircle).setBackgroundResource(R.drawable.today_circle);

                    TextView schedule_date = (TextView)getActivity().findViewById(R.id.today);
                    schedule_date.setText(year + ". " + (month + 1) + ". " + (rv.getChildAdapterPosition(child) - startDay + 2));
//                    R.drawable.today_circle

                    RecyclerView manage_schedule_recycleview = (RecyclerView) getActivity().findViewById(R.id.ufit_manage_schedule_recycleview);

                    ArrayList<UFitEntityObject> MemberList = new ArrayList<UFitEntityObject>();

//                  리싸이클러뷰 더미데이타 표현
                    /*
                    List<DummyDatePoooool> memberList = new ArrayList<>();
                    for(int i = 0; i < (rv.getChildAdapterPosition(child) - startDay + 2); i++){
                        DummyDatePoooool dummyDatePoooool = new DummyDatePoooool();
                        dummyDatePoooool.setData(R.drawable.pic, "오늘회원님" + (i + 1), "2:" + i*2 + "pm");
                        memberList.add(dummyDatePoooool);
                    }
                    MemberItemAdapter memberRecyclerViewAdapter = new MemberItemAdapter(memberList);
                    manage_schedule_recycleview.setAdapter(memberRecyclerViewAdapter);
                    */

                    //전에 클릭한 날짜를 지우기 위해 올드뷰를 종료전에 설정.

                    OldView = child;
                    new SelectedDayMemberList().execute();
                    return true;
                }
                return false;
            }

            class SelectedDayMemberList extends AsyncTask<String, Integer, ArrayList<UFitEntityObject>> {


                @Override
                protected ArrayList doInBackground(String... strings) {
                    Log.i("who am i?","" + UFitHttpConnectionHandler.mainlist());
                    return UFitHttpConnectionHandler.mainlist();
                }

                @Override
                protected void onPostExecute(ArrayList<UFitEntityObject> memberList) {
                    ArrayList<UFitEntityObject> memberListContainer = new ArrayList<>();
                    if (memberList != null && memberList.size() > 0) {
                        for (int i = 0; i < memberList.size(); i++) {
                            Log.i("안녕&main 전부다나와라", memberList + "");
                            Log.i("안녕 !!!", memberList.get(i) + "");
                            UFitEntityObject d = new UFitEntityObject();
                            d._mid = memberList.get(i)._mid;
                            Log.i("안녕 ! mid", memberList.get(i)._mid + "");
                            d._name = memberList.get(i)._name;
                            Log.i("안녕 ! name", memberList.get(i)._name + "");
                            d._time = memberList.get(i)._time;
                            Log.i("안녕 ! time", memberList.get(i)._time + "");
                            memberListContainer.add(d);
                        }
                    }

                    MemberItemAdapter memberRecyclerViewAdapter = new MemberItemAdapter(memberList);
                    memberListRecycler.setAdapter(memberRecyclerViewAdapter);

                    super.onPostExecute(memberList);
                }
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });



//        mRecycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//                Toast.makeText(getContext(), "adfdsf", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
        return view;
    }



}