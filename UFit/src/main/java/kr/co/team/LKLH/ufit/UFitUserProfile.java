package kr.co.team.LKLH.ufit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class UFitUserProfile extends AppCompatActivity {

    Toolbar toolbar;
    TextView tbHead;
    ImageView tbLeft, tbRight;
    CircleImageView profileImg;
    Intent intent;
    String name;
    int img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ufit_user_profile);

        intent = getIntent();
        name = intent.getExtras().getString("name");
        img = intent.getExtras().getInt("img");

        toolbar = (Toolbar)findViewById(R.id.uf_main_toolbar);
        setSupportActionBar(toolbar);
        tbHead = (TextView) findViewById(R.id.uf_toolbar_head);
        tbHead.setText(name);
        tbLeft = (ImageView)findViewById(R.id.uf_toolbar_left);
        tbLeft.setImageResource(R.drawable.btn_topleft);
        tbLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tbRight= (ImageView)findViewById(R.id.uf_toolbar_right);
        tbRight.setVisibility(View.INVISIBLE);

        profileImg = (CircleImageView) findViewById(R.id.uf_user_profile_img);
        profileImg.setImageResource(img);

        final LinearLayout userCircle = (LinearLayout)findViewById(R.id.uf_user_circleprogress);
        userCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UFitUserProfile.this, UFitUserProfile_Detail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
