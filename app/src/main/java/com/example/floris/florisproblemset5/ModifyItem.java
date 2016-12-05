package com.example.floris.florisproblemset5;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyItem extends Activity implements View.OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn, newPageBtn;
//    private EditText descText;

    private long _id;

    private DBManager dbManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_item);

        dbManager = new DBManager(this);
        dbManager.open();

        titleText = (EditText) findViewById(R.id.subject_edittext);
//        descText = (EditText) findViewById(R.id.description_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        newPageBtn = (Button) findViewById(R.id.btn_newpage);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.d("INTENT ID", id);
        String name = intent.getStringExtra("name");
//        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        titleText.setText(name);
//        descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name = titleText.getText().toString();
//                String desc = descText.getText().toString();

                dbManager.update(_id, name);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

    public void onNewPageClick(View view) {

        Intent intent = getIntent();
        String name2 = intent.getStringExtra("id");
        String namelist = intent.getStringExtra("name");
        Intent newpageintent = new Intent(this, Main2Activity.class);
        newpageintent.putExtra("id", name2);
        newpageintent.putExtra("namelist", namelist);
        startActivity(newpageintent);

    }
}