package kr.co.team.LKLH.ufit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Admin on 2016-08-01.
 */
public class DaysCellAdapter extends RecyclerView.Adapter<DaysCellAdapter.ViewHolder>{
    int maximumDay;
    int day_counter;
    int startDay;
    Context mContext;

    public DaysCellAdapter(Context mContext, int maximumDay, int startDay){
        this.mContext = mContext;
        this.maximumDay = maximumDay;
        this.startDay = startDay;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRoot = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_grid_cell, parent, false);
        ViewHolder holder = new ViewHolder(itemRoot);
        return holder;
    }

    @Override
    public int getItemCount() {
        return maximumDay + startDay - 1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position >= (startDay - 1)) {
            holder.day.setText(String.valueOf(++day_counter));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView day;
        public ViewHolder(View itemView) {
            super(itemView);
            day = (TextView)itemView.findViewById(R.id.day);
        }
    }


}