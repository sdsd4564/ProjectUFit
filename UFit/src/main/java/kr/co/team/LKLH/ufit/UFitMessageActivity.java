package kr.co.team.LKLH.ufit;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UFitMessageActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView tbLeft, tbRight;
    TextView tbHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ufit_message);

        toolbar = (Toolbar)findViewById(R.id.uf_main_toolbar);
        setSupportActionBar(toolbar);

        tbLeft = (ImageView)findViewById(R.id.uf_toolbar_left);
        tbRight= (ImageView)findViewById(R.id.uf_toolbar_right);
        tbHead = (TextView) findViewById(R.id.uf_toolbar_head);
        tbLeft.setImageResource(R.drawable.btn_topleft);
        tbRight.setVisibility(View.INVISIBLE);
        tbHead.setText("트레이너");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.uf_message_rcv, UFitMessageRCV.newInstance());
        ft.commit();
    }
}
