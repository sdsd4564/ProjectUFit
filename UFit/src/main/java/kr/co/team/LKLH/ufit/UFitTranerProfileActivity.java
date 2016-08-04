package kr.co.team.LKLH.ufit;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UFitTranerProfileActivity extends AppCompatActivity {

    DrawerLayout drawLeft;
    Toolbar toolbar;
    ImageView toolbarLeft, toolbarRight;
    TextView toolbarHead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ufit_trainer_profile);

        toolbar = (Toolbar) findViewById(R.id.uf_main_toolbar);
        setSupportActionBar(toolbar);
        toolbarLeft = (ImageView) findViewById(R.id.uf_toolbar_left);
        toolbarRight= (ImageView)findViewById(R.id.uf_toolbar_right);
        toolbarHead = (TextView)findViewById(R.id.uf_toolbar_head);
        toolbarHead.setText(R.string.uf_profile);
        toolbarLeft.setImageResource(R.drawable.btn_back);
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbarRight.setImageResource(android.R.drawable.ic_popup_sync);


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.uf_trainer_history, UFitTrainerHistoryRCV.newInstance());
        ft.commit();
    }
}
