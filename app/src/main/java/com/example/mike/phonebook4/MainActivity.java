package com.example.mike.phonebook4;

import android.app.Dialog;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mike.phonebook4.mDataBase.DBAdapter;
import com.example.mike.phonebook4.mDataObject.Spacecraft;
import com.example.mike.phonebook4.mListView.CustomAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lv;
    EditText nameEditText,numberEditText;
    Button save,retrive;
    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    CustomAdapter adapter;
    Boolean forUpdate=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.lv);
        adapter=new CustomAdapter(this,spacecrafts);
        this.getSpacecrafts();
        // lv.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog(false);
            }
        });
    }

    private  void displayDialog (Boolean forUpdate)
    {
        Dialog d=new Dialog(this);
        d.setTitle("SQLite DATA");
        d.setContentView(R.layout.dialog_layout);

        nameEditText= (EditText) d.findViewById(R.id.nameEditTxt);
        numberEditText=(EditText)d.findViewById(R.id.numberEditTxt);
        save=(Button)d.findViewById(R.id.saveBtn);


        if(!forUpdate) {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        save(nameEditText.getText().toString(),numberEditText.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } else {

            // set selected text
//            nameEditText.setText(adapter.getSelectedItemID());
//          numberEditText.setText(adapter.getSelectedItemID());
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    update(nameEditText.getText().toString(),numberEditText.getText().toString());
                }
            });
        }

        d.show();
    }


    //SAVE
    private void save(String name , String number) throws Exception {

        EncDec  encDec = new EncDec();
        String a =encDec.encrypt(name);
        String b =encDec.encrypt(number);
        DBAdapter db= new DBAdapter(this);
        db.openDB();
        boolean saved=db.add(a,b);

        if(saved){
            nameEditText.setText("");
            numberEditText.setText("");
            getSpacecrafts();
        }else {
            Toast.makeText(this,"Unable to save",Toast.LENGTH_SHORT).show();
        }
    }
    //Retrive or GetSpaceCrafts
   private void getSpacecrafts(){
        spacecrafts.clear();
        DBAdapter db= new DBAdapter(this);
        db.openDB();
        Cursor c = db.retrieve();
        Spacecraft spacecraft=null;


       while (c.moveToNext()){
            int id =c.getInt(0);
            String name=c.getString(1);
            String number=c.getString(2);

            spacecraft=new Spacecraft();
            spacecraft.setId(id);
            spacecraft.setName(name);
            spacecraft.setNumber(number);


            spacecrafts.add(spacecraft);
        }
        db.closeDB();
        lv.setAdapter(adapter);
    }

    //Update or Edit
    private void update(String newName , String newNumber)
    {
        //Get Id of SpaceCraft
        int id=adapter.getSelectedItemID();


        //Update in DB
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        boolean updated=db.update(newName,newNumber,id);
        db.closeDB();


        if(updated){

            nameEditText.setText(newName);
            numberEditText.setText(newNumber);
            getSpacecrafts();
        }else {
            Toast.makeText(this,"unable to update",Toast.LENGTH_SHORT).show();
        }
    }
    private void  delete()
    {
        //Get id
        int id = adapter.getSelectedItemID();

        //delete from db
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        boolean deleted = db.delete(id);
        db.closeDB();

        if(deleted)
        {
            getSpacecrafts();
        }else {
            Toast.makeText(this,"unable to delete",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();
        if(title=="NEW")
        {
            displayDialog(!forUpdate);

        }else if (title=="EDIT")
        {
            displayDialog(forUpdate);


        }else if (title=="DELETE"){
            delete();
        }
        return super.onContextItemSelected(item);
    }
}
