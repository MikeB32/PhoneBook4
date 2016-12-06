package com.example.mike.phonebook4.mListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.mike.phonebook4.EncDec;
import com.example.mike.phonebook4.R;
import com.example.mike.phonebook4.mDataObject.Spacecraft;

import java.util.ArrayList;

/**
 * Created by Mike on 11/18/2016.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Spacecraft>spacecrafts;
    LayoutInflater inflater;
    Spacecraft spacecraft;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.model,parent,false);
        }
        //Bind Data
        MyViewHolder holder = new MyViewHolder(convertView);
        EncDec encDec = new EncDec();

        try {
            String a =encDec.decrypt(spacecrafts.get(position).getName());
          String  b = encDec.decrypt(spacecrafts.get(position).getNumber());
            holder.nameTxt.setText(a);
            holder.numberTxt.setText(b);
        } catch (Exception e) {
            e.printStackTrace();
        }



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c,spacecrafts.get(position).getName()+"\n"+ spacecrafts.get(position).getNumber(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.setLongClickListener(new MyLongClickListener() {
            @Override
            public void onItemLongClick() {
                spacecraft= (Spacecraft) getItem(position);
            }
        });
        return convertView;
    }

    //expose Name and Number and ID
    public  int getSelectedItemID(){
        return spacecraft.getId();
    }

    public String getSelectedItemName()
    {
        return spacecraft.getName();
    }
    public String getSelectedItemNumber(){
        return spacecraft.getNumber();
    }



}
