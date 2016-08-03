package kr.co.team.LKLH.ufit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    public static CalendarFragment newInstance(int year, int  month, int maximumDay, int startDay, boolean cal) {
        CalendarFragment calendarFragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putInt("ThisYear", year);
        args.putInt("ThisMonth", month);
        args.putInt("ThisMaximumDay", maximumDay);
        args.putInt("ThisStartDay", startDay);
        calendarFragment.setArguments(args);
        return calendarFragment;
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
                    if(OldView != null){

                        OldView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                    }
                    child.setBackgroundColor(Color.parseColor("#FFFF00"));
                    TextView day = (TextView)getActivity().findViewById(R.id.today);
                    day.setText(year + ". " + (month + 1) + ". " + (rv.getChildAdapterPosition(child) - startDay + 2));
                    OldView = child;
                    return true;
                }
                return false;
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