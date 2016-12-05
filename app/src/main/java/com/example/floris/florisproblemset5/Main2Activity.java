package com.example.floris.florisproblemset5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;



    EditText Add_Item_ET;
    final String[] from = new String[] {DataBaseHelper._ID, DataBaseHelper.ITEM};
    // previously also .DESC

    final int[] to = new int[] {R.id.id2, R.id.item2 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView titletext = (TextView) findViewById(R.id.ToDoListTextView);

        Intent intent = getIntent();
        String listname = intent.getStringExtra("id");
        String namelist = intent.getStringExtra("namelist");
        titletext.setText(namelist + " list");

        SharedPreferences sharedPreferences;


        Add_Item_ET = (EditText) findViewById(R.id.ETAddItem2);
        dbManager = new DBManager(this);
        dbManager.open();
//        Cursor cursor = dbManager.fetch2(listname);
        Cursor cursor = dbManager.fetch2(listname);

        listView = (ListView) findViewById(R.id.ListView2);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_item2, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        String cursortest = DatabaseUtils.dumpCursorToString(cursor);

        Log.d("CURSOR",cursortest);
        String test = "Hello";
        Log.d("success", test);






        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id2);
                TextView itemTextView = (TextView) view.findViewById(R.id.item2);
//                TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String item = itemTextView.getText().toString();

                Intent intent = getIntent();
                String listname = intent.getStringExtra("id");
                String namelist = intent.getStringExtra("namelist");

                Intent modify_intent = new Intent(getApplicationContext(), ModifyItem2.class);
                modify_intent.putExtra("fetchid",listname);
                modify_intent.putExtra("item", item);
                modify_intent.putExtra("namelist",namelist);
//                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);
                startActivity(modify_intent);
            }
        });


    }




//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.add_record) {
//
//            Intent add_mem = new Intent(this, AddItem.class);
//            startActivity(add_mem);
//
//        }
//        return super.onOptionsItemSelected(item);
//    }



    public void onClick2(View view) {

        EditText Add_Item_ET = (EditText) findViewById(R.id.ETAddItem2);
        String Item_To_Add = Add_Item_ET.getText().toString();
        Log.d("test", Item_To_Add);
        dbManager = new DBManager(this);
        dbManager.open();
        Intent intent = getIntent();
        String listname = intent.getStringExtra("id");
        Log.d("INTENT-ID", listname);
        dbManager.insert2(Item_To_Add, listname);
        Cursor cursor = dbManager.fetch2(listname);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_item2, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        Add_Item_ET.setText("");
    }

    public void onCheckBoxClick(View view) {
//        final int position = adapter.getCursor().getPosition()
        int position = listView.getPositionForView(view);
        Log.d("Position", " VALUE " + position);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox2);
        boolean checked = checkBox.isChecked();
        Log.d("CHECKBOX STATUS: ", "Status" + checked);
        long checkboxid = listView.getItemIdAtPosition(position);
        Log.d("ID@pos:", "ID= " + checkboxid);
//        if(checkboxinlist.isChecked()) {
//            Log.d("CHECKBOX is CHECKED", "if true");
//            dbManager.updateStatus(checkboxid, 1);
//        }
//        else{
//            Log.d("CHECKBOX is NOT CHECKED", "if false");
//            dbManager.updateStatus(checkboxid, 0);
//        }
    }

//    public void onCheckBoxClick(View view) {
//        switch(view.getId()) {
//            case R.id.checkBox:
//                boolean checked = ((CheckBox) view).isChecked();
//                PreferenceManager.getDefaultSharedPreferences(this).edit()
//                        .putBoolean("checkBox", checked).apply();
//                break;
//        }

}
