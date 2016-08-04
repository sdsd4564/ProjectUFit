package kr.co.team.LKLH.ufit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UFitSettingActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView tbLeft, tbRight;
    TextView tbHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ufit_setting);

        toolbar = (Toolbar)findViewById(R.id.uf_main_toolbar);
        setSupportActionBar(toolbar);

        tbLeft = (ImageView)findViewById(R.id.uf_toolbar_left);
        tbRight= (ImageView)findViewById(R.id.uf_toolbar_right);
        tbHead = (TextView) findViewById(R.id.uf_toolbar_head);
        tbLeft.setImageResource(R.drawable.btn_topleft);
        tbRight.setVisibility(View.INVISIBLE);
        tbLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UFitSettingActivity.this, UFitMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
