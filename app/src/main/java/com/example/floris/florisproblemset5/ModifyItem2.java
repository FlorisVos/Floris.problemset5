package com.example.floris.florisproblemset5;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyItem2 extends Activity implements View.OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn;
//    private EditText descText;

    private long _id2;

    private DBManager dbManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_item2);

        dbManager = new DBManager(this);
        dbManager.open();

        titleText = (EditText) findViewById(R.id.subject_edittext2);
//        descText = (EditText) findViewById(R.id.description_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update2);
        deleteBtn = (Button) findViewById(R.id.btn_delete2);

        Intent intent = getIntent();
        String primKey = intent.getStringExtra("id");
        Log.d("INTENT ID", primKey);
        String item = intent.getStringExtra("item");
//        String desc = intent.getStringExtra("desc");

        _id2 = Long.parseLong(primKey);
        titleText.setText(item);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update2:
                Intent intent = getIntent();
                String name = intent.getStringExtra("item");
                String newname = titleText.getText().toString();
//                String desc = descText.getText().toString();

                dbManager.update2(_id2,newname);
                this.returnHome();
                break;

            case R.id.btn_delete2:
                dbManager.delete2(_id2);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), Main2Activity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Intent intent = getIntent();
        String listname = intent.getStringExtra("fetchid");
        String namelist = intent.getStringExtra("namelist");
        home_intent.putExtra("namelist",namelist);
        home_intent.putExtra("id",listname);
        startActivity(home_intent);
    }
}