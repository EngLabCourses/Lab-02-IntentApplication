package it.englab.androidcourse.intentapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_DETAIL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("it.englab.androidcourse.justdrink.action.DETAIL");
                intent.putExtra("COCKTAIL_ID", "13427");
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("justdrinkapp://justdrink.androidcourse.englab.it/id/14107"));
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("it.englab.androidcourse.justdrink.action.DETAIL_FOR_RESULT");
                intent.putExtra("COCKTAIL_ID", "17177");
                startActivityForResult(intent, REQUEST_CODE_DETAIL);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_DETAIL) {
            if (resultCode == RESULT_OK) {
                findViewById(R.id.label_result).setVisibility(View.GONE);
                findViewById(R.id.text_view_result).setVisibility(View.GONE);
            } else if (resultCode == RESULT_CANCELED) {
                if (data != null) {
                    TextView textViewResult = (TextView) findViewById(R.id.text_view_result);
                    textViewResult.setText(data.getStringExtra("ERR_DESCRIPTION"));
                    textViewResult.setVisibility(View.VISIBLE);
                    findViewById(R.id.label_result).setVisibility(View.VISIBLE);
                }
            }
        }
    }

}

