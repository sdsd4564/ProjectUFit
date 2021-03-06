package kr.co.team.LKLH.ufit;

import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 2016-07-27.
 */
public class UFitRightDrawRCV extends Fragment {


    public static UFitRightDrawRCV newInstance() {
        UFitRightDrawRCV f = new UFitRightDrawRCV();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView)inflater.inflate(R.layout.ufit_fragment_recycler_view, container, false);
        UFitRightdrawRCVAdapter rcv = new UFitRightdrawRCVAdapter();
        rv.setLayoutManager(new LinearLayoutManager(UFitApplication.getUFitContext()));
        rv.setAdapter(rcv);


        DummyDatePoooool data1 = new DummyDatePoooool();
        DummyDatePoooool data2 = new DummyDatePoooool();
        DummyDatePoooool data3 = new DummyDatePoooool();
        DummyDatePoooool data4 = new DummyDatePoooool();
        DummyDatePoooool data5 = new DummyDatePoooool();
        DummyDatePoooool data6 = new DummyDatePoooool();
        DummyDatePoooool data7 = new DummyDatePoooool();
        DummyDatePoooool data8 = new DummyDatePoooool();

        data1.setData(R.drawable.pic, "황비만");
        rcv.add(data1);
        data2.setData(R.drawable.pic, "김허약");
        rcv.add(data2);
        data3.setData(R.drawable.pic, "독고비만");
        rcv.add(data3);
        data4.setData(R.drawable.pic, "김모델");
        rcv.add(data4);
        data5.setData(R.drawable.pic, "나는햄버거");
        rcv.add(data5);
        data6.setData(R.drawable.pic, "나는사람");
        rcv.add(data6);
        data7.setData(R.drawable.pic, "나도햄버거");
        rcv.add(data7);
        data8.setData(R.drawable.pic, "안드보이");
        rcv.add(data8);
        return rv;
    }

    public static class UFitRightdrawRCVAdapter extends RecyclerView.Adapter<UFitRightdrawRCVAdapter.ViewHolder> {
        ArrayList<DummyDatePoooool> items = new ArrayList<DummyDatePoooool>();

        public void add(DummyDatePoooool dt) {
            items.add(dt);
            notifyDataSetChanged();
        }
       public static class ViewHolder extends RecyclerView.ViewHolder {
           public final View mView;
           public final CircleImageView memImg;
           public final TextView memName;
           public final ImageView moreMenu;
           public final LinearLayout popupLayout;

           public ViewHolder(View itemView) {

               super(itemView);
               mView = itemView;
               memImg = (CircleImageView) mView.findViewById(R.id.uf_right_mem_img);
               memName= (TextView)mView.findViewById(R.id.uf_right_mem_name);
               moreMenu=(ImageView) mView.findViewById(R.id.uf_right_mem_more);
               popupLayout=(LinearLayout)mView.findViewById(R.id.uf_rd_popup);
           }
       }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.ufit_rightdraw_mem_item, parent, false);
            return new ViewHolder(view);
        }

        View mView = null;
        int lastClickPosition;
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            DummyDatePoooool d = items.get(position);
            holder.memImg.setImageResource(d.img);
            holder.memName.setText(d.name);

            holder.moreMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastClickPosition = position;
                    Log.i("Question", lastClickPosition + "");
                    if (mView != null) {
                        if (mView == holder.popupLayout) {
                            mView.setVisibility(View.INVISIBLE);
                            mView = null;
                        } else {
                            mView.setVisibility(View.INVISIBLE);
                            holder.popupLayout.setVisibility(View.VISIBLE);
                            mView = holder.popupLayout;
                        }
                    } else {
                        mView = holder.popupLayout;
                        holder.popupLayout.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
