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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 2016-07-26.
 */
public class UFitMemberManagementRCV extends Fragment {
    public static UFitMemberManagementRCV newInstance(){
        UFitMemberManagementRCV f = new UFitMemberManagementRCV();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView)inflater.inflate(R.layout.ufit_fragment_recycler_view, container, false);
        rv.setLayoutManager(new LinearLayoutManager(UFitApplication.getUFitContext()));
        rv.setAdapter(new UFitMemberManagementRCVAdapter());

        return rv;
    }

    public static class UFitMemberManagementRCVAdapter
            extends RecyclerView.Adapter<UFitMemberManagementRCVAdapter.ViewHolder> {
        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final CircleImageView memberImg;
            public final TextView memName;
            public final TextView memberBirth;
            public final TextView memberCall;
            public final TextView memClassDate;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                memberImg = (CircleImageView)view.findViewById(R.id.uf_mem_mng_img);
                memName = (TextView) view.findViewById(R.id.uf_mem_mng_name);
                memberBirth = (TextView)view.findViewById(R.id.uf_mem_mng_birth);
                memberCall = (TextView)view.findViewById(R.id.uf_mem_mng_call);
                memClassDate = (TextView)view.findViewById(R.id.uf_mem_mng_date);
            }

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.ufit_mem_manage_carditem, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.memberImg.setImageResource(R.drawable.alf);
            holder.memName.setText("김비만");
            holder.memberBirth.setText("1940.07.15");
            holder.memberCall.setText("010.9932.3548");
            holder.memClassDate.setText("월, 금");
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
}
