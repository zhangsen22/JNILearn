package com.example.nativecdemo.base.viewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nativecdemo.base.interfaces.IBaseRecyclerItemClickListener;

import butterknife.ButterKnife;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-16 15:39
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.base.viewHolder
 */
public class BaseClickViewHolder extends RecyclerView.ViewHolder {

    public BaseClickViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setDataSource(final int position, final IBaseRecyclerItemClickListener mIBaseRecyclerItemClickListener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempFlag = getTempFlag(v, position);
                if (mIBaseRecyclerItemClickListener != null) {
                    mIBaseRecyclerItemClickListener.itemClickBack(v, tempFlag,false);
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int tempFlag = getTempFlag(v, position);
                if (mIBaseRecyclerItemClickListener != null) {
                    mIBaseRecyclerItemClickListener.itemClickBack(v, tempFlag,true);
                }
                return false;
            }
        });
    }

    private int getTempFlag(View v, int position) {
        int tempFlag = 0;
        Object viewTag = v.getTag();
        if (viewTag instanceof Integer) {
            int tag = (int) viewTag;
            if (tag >= 0) {
                tempFlag = tag;
            } else {
                tempFlag = position;
            }
        } else {
            tempFlag = position;
        }
        return tempFlag;
    }

}
