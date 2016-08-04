package kr.co.team.LKLH.ufit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 2016-07-29.
 */
public class UFitTrainerHistoryRCV extends Fragment {
    static UFitTrainerHistoryRCV newInstance() {
        UFitTrainerHistoryRCV f = new UFitTrainerHistoryRCV();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView)inflater.inflate(R.layout.ufit_fragment_recycler_view, container, false);
        UFitTrainerHistoryRCVAdapter rcv = new UFitTrainerHistoryRCVAdapter();
        rv.setLayoutManager(new LinearLayoutManager(UFitApplication.getUFitContext()));
        rv.setAdapter(rcv);
        DummyDatePoooool data = new DummyDatePoooool();
        DummyDatePoooool data1 = new DummyDatePoooool();
        DummyDatePoooool data2 = new DummyDatePoooool();
        data.setData("2013", "평차동계올림픽 참가");
        rcv.add(data);
        data1.setData("2014~2015", "한마음체육대회 우승");
        rcv.add(data1);
        data2.setData("2016", "T아카데미 참여");
        rcv.add(data2);

        return rv;
    }
    public static class UFitTrainerHistoryRCVAdapter extends RecyclerView.Adapter<UFitTrainerHistoryRCVAdapter.ViewHolder> {
        ArrayList<DummyDatePoooool> items = new ArrayList<DummyDatePoooool>();

        public void add(DummyDatePoooool t) {
            items.add(t);
            notifyDataSetChanged();
        }
        public static class ViewHolder extends RecyclerView.ViewHolder {
            public View rView;
            public TextView thYear, thTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                rView = itemView;
                thYear = (TextView)rView.findViewById(R.id.uf_trhistory_year);
                thTitle= (TextView)rView.findViewById(R.id.uf_trhistory_title);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.ufit_trainer_history_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            DummyDatePoooool t = items.get(position);
            holder.thYear.setText(t.year);
            holder.thTitle.setText(t.title);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
