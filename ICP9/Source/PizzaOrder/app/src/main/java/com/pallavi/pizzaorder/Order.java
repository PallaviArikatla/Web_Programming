package com.akhil.pizzaorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dhairya.pizzaorder.R;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        TextView sum = (TextView)findViewById(R.id.sum);
        final String summary = getIntent().getStringExtra("SUMMARY");
        final String Name = getIntent().getStringExtra("NAME");
        sum.setText(getIntent().getStringExtra("SUMMARY"));
        Button or_btn = (Button)findViewById(R.id.or_sum);
        or_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String subject  = "Order details for: " + Name;

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pizza@abc.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent.putExtra(Intent.EXTRA_TEXT,summary);

                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,"Choose Email Client"));
            }
        });
    }
}
