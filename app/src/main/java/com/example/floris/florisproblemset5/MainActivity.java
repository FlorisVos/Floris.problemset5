package com.example.floris.florisproblemset5;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private DBManager dbManager;


    private ListView listView;

    private SimpleCursorAdapter adapter;



    EditText Add_Item_ET;
    final String[] from = new String[] { DataBaseHelper._ID,
            DataBaseHelper.NAME};
    // previously also .DESC

    final int[] to = new int[] { R.id.id, R.id.item };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Add_Item_ET = (EditText) findViewById(R.id.ETAddItem);


        setContentView(R.layout.activity_main);


        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.ListView);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_item, cursor, from, to, 0);
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
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView itemTextView = (TextView) view.findViewById(R.id.item);
//                TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String name = itemTextView.getText().toString();
//                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyItem.class);
                modify_intent.putExtra("name", name);
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
//            final String name = Add_Item_ET.getText().toString();
////                final String desc = descEditText.getText().toString();
//
//            dbManager.insert(name);
//
//        }
//        return super.onOptionsItemSelected(item);
//    }



    public void onClick(View view) {

        EditText Add_Item_ET = (EditText) findViewById(R.id.ETAddItem);
        String List_To_Add = Add_Item_ET.getText().toString();
        Log.d("test", List_To_Add);
        dbManager = new DBManager(this);
        dbManager.open();
        dbManager.insert(List_To_Add);
        Cursor cursor = dbManager.fetch();
        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_item, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        Add_Item_ET.setText("");

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
