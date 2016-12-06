package com.example.mike.phonebook4.mListView;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.mike.phonebook4.R;

/**
 * Created by Mike on 11/18/2016.
 */

public class MyViewHolder implements View.OnLongClickListener,View.OnCreateContextMenuListener{

    TextView nameTxt,numberTxt;
    MyLongClickListener longClickListener;

    public MyViewHolder(View v){
        nameTxt=(TextView)v.findViewById(R.id.nametxt);
        numberTxt=(TextView)v.findViewById(R.id.numbertxt);
        v.setOnLongClickListener(this);
        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        this.longClickListener.onItemLongClick();
        return false;
    }
    public void setLongClickListener(MyLongClickListener longClickListener)
    {
        this.longClickListener=longClickListener;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        menu.setHeaderTitle("Action :");
        menu.add(0,0,0,"NEW");
        menu.add(0,1,0,"EDIT");
        menu.add(0,2,0,"DELETE");




    }
}
