package kr.co.team.LKLH.ufit;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ccei on 2016-08-02.
 */
public class MemberItemAdapter extends RecyclerView.Adapter<MemberItemAdapter.ViewHolder> {
    private List<DummyDatePoooool> memberList;
    private ArrayList<UFitEntityObject> memberListObject;

    public MemberItemAdapter(List<DummyDatePoooool> memberList) {
        this.memberList = memberList;
    }

    public MemberItemAdapter(ArrayList<UFitEntityObject> memberListObject) {
        this.memberListObject = memberListObject;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRoot = LayoutInflater.from(parent.getContext()).inflate(R.layout.ufit_manage_schedule_memberlist_item, parent, false);
        ViewHolder holder = new ViewHolder(itemRoot);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(memberList != null){
            DummyDatePoooool member = memberList.get(position);
            holder.profile_pic.setImageResource(member.getImg());
            holder.name.setText(member.getName());
            holder.time.setText(member.getTime());
        }
        else{
            UFitEntityObject member = memberListObject.get(position);
            holder.profile_pic.setImageResource(R.drawable.pic);
            holder.name.setText(member._name);
            holder.time.setText(member._name);
        }



    }


    @Override
    public int getItemCount() {
        if(memberList != null){
            return memberList.size();
        }
        else{
            return memberListObject.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name, time;
        public CircleImageView profile_pic;

        public ViewHolder(View itemView) {
            super(itemView);
            profile_pic = (CircleImageView)itemView.findViewById(R.id.manage_schedule_member_profile_pic);
            name = (TextView)itemView.findViewById(R.id.manage_schedule_member_name);
            time = (TextView)itemView.findViewById(R.id.manage_schedule_training_time);

        }
    }

}
