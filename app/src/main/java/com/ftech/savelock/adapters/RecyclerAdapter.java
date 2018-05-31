package com.ftech.savelock.adapters;

/**
 * Created by Frederick on 5/19/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ftech.savelock.data.ListData;
import com.ftech.savelock.R;
import com.ftech.savelock.security_type.SecurityConnector;
import com.ftech.savelock.utils.Constants;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<ListData> mListData;
    //individual item's icon color
    String[] colorArray = {"#9c0a90","#f64c74","#20d2cc","#4495ff","#6145a3","#d11515"};
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle, mDesc, mLetter;
        public RelativeLayout mItemBg;

        public MyViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title);
            mDesc = (TextView) view.findViewById(R.id.desc);
            mLetter = (TextView) view.findViewById(R.id.letter);
            mItemBg = (RelativeLayout) view.findViewById(R.id.itemBg);
        }
    }


    public RecyclerAdapter(Context context, List<ListData> listData) {
        this.mContext = context;
        this.mListData = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListData listData = mListData.get(position);
        holder.mTitle.setText(listData.getmTitle());
        holder.mDesc.setText(listData.getmDesc());
        holder.mLetter.setText(listData.getmLetter());
        //set different color from colorArray
        holder.mLetter.setTextColor(Color.parseColor(colorArray[position]));
        //get the position
        final int pos = position;
        holder.mItemBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, SecurityConnector.class);
                intent.putExtra(Constants.NAV_HEADER_COLOR_TAG,colorArray[pos]);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }
}
