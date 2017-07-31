package com.example.butterknifedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidlibrary.ButterKnife.BindOnclick;
import com.example.androidlibrary.ButterKnife.BindView;
import com.example.androidlibrary.ButterKnife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text)
    private TextView text ;

    @BindView(R.id.button)
    private Button button ;

    private final String TAG = "MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Log.d(TAG, "onCreate: text : " + text.getText());
        Log.d(TAG, "onCreate: text : " + button.getText());

    }

    @BindOnclick(R.id.button)
    private void onClickButton(View v){
        Toast.makeText(this , "点击按钮" , Toast.LENGTH_SHORT).show();
    }

    @BindOnclick(R.id.text)
    private void onClickText(View v){

        ((TextView)v).setText("成功");

    }

}
