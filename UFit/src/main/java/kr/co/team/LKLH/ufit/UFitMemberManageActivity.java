package kr.co.team.LKLH.ufit;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class UFitMemberManageActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView tbLeft, tbRight;
    TextView tbHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ufit_mem_manage);

        toolbar = (Toolbar)findViewById(R.id.uf_main_toolbar);
        setSupportActionBar(toolbar);
        tbHead = (TextView)findViewById(R.id.uf_toolbar_head);
        tbHead.setText("회원관리");
        tbLeft = (ImageView)findViewById(R.id.uf_toolbar_left);
        tbLeft.setImageResource(R.drawable.btn_topleft);
        tbLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tbRight = (ImageView)findViewById(R.id.uf_toolbar_right);
        tbRight.setImageResource(R.drawable.btn_edit);
        tbRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                UFitMemberManagementRegister ufd = new UFitMemberManagementRegister().newInstance();
                ft.add(ufd, "member_add").addToBackStack("member_add").commit();*/
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                UFitMemberManagementRegister ufd = new UFitMemberManagementRegister().newInstance();
                ft.add(ufd, "member_add").addToBackStack("member_add").commit();
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.uf_mem_manage_rcv, UFitMemberManagementRCV.newInstance());
        ft.commit();
    }
}
