package com.example.myshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.myshop.R;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view)
    {
        switch(view.getId())  {
            case  R.id.button1:
                Intent intent1 = new Intent(this, LmActivity.class);
                startActivity(intent1);
                break;
            case  R.id.button2:
                Intent intent2 = new Intent(this, PurchasesActivity.class);
                startActivity(intent2);
                break;
            case  R.id.button3:
                Intent intent3 = new Intent(this, ListActivity.class);
                intent3.putExtra("current", 0);
                startActivity(intent3);
                break;
        }
    }
}
