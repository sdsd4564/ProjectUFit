package kr.co.team.LKLH.ufit;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UFitTranerProfileActivity extends AppCompatActivity {

    boolean PROFIL_EDIT_FLAG;
    DrawerLayout drawLeft;
    Toolbar toolbar;
    ImageView toolbarLeft, toolbarRight;
    TextView toolbarHead;
    TextView trName, trBirth, trHistoryYear, trGetMem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ufit_trainer_profile);

        toolbar = (Toolbar) findViewById(R.id.uf_main_toolbar);
        setSupportActionBar(toolbar);
        toolbarLeft = (ImageView) findViewById(R.id.uf_toolbar_left);
        toolbarRight= (ImageView)findViewById(R.id.uf_toolbar_right);
        toolbarHead = (TextView)findViewById(R.id.uf_toolbar_head);
        trName = (TextView)findViewById(R.id.trainer_name);
        trBirth= (TextView)findViewById(R.id.trainer_birth);
        trHistoryYear=(TextView)findViewById(R.id.trainer_history_year);
        toolbarHead.setText(R.string.uf_profile);
        toolbarLeft.setImageResource(R.drawable.btn_back);
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        toolbarRight.setImageResource(android.R.drawable.ic_popup_sync);
        toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PROFIL_EDIT_FLAG) {
                    trName.setEnabled(true);
                    trName.setBackgroundResource(android.R.drawable.edit_text);
                    trBirth.setEnabled(true);
                    PROFIL_EDIT_FLAG = true;
                } else {
                    trName.setEnabled(false);
                    trBirth.setEnabled(false);
                    PROFIL_EDIT_FLAG = false;
                }


            }
        });


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.uf_trainer_history, UFitTrainerHistoryRCV.newInstance());
        ft.commit();
    }
}
