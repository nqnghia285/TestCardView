package com.nqnghia.testcardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCardView extends RecyclerView.Adapter<AdapterCardView.ViewHolderCardView> {
    private ArrayList<ItemCardView> mItemList;
    private MutableLiveData<Integer> _event = new MutableLiveData<>();
    LiveData<Integer> event = _event;
    private OnItemClickListener mListener;

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    static class ViewHolderCardView extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTitle;
        TextView mSubtitle;
        CheckBox mCheckBox;

        ViewHolderCardView(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_card_view);
            mTitle = itemView.findViewById(R.id.title_card_view);
            mSubtitle = itemView.findViewById(R.id.sub_title_card_view);
            mCheckBox = itemView.findViewById(R.id.checked_box);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    AdapterCardView(ArrayList<ItemCardView> itemList) {
        mItemList = itemList;
        _event.setValue(0);
    }

    void setSelectedItem() {
        _event.setValue(0);
    }

    @NonNull
    @Override
    public ViewHolderCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new ViewHolderCardView(li.inflate(R.layout.item_card_view , parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderCardView holder, final int position) {
        final ItemCardView currentItem = mItemList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTitle.setText(currentItem.getTitle());
        holder.mSubtitle.setText(currentItem.getSubtitle());
        holder.mCheckBox.setChecked(currentItem.getmCheckBox());
        if (currentItem.getmCheckBox()) {
            holder.itemView.setBackgroundResource(R.drawable.item_blue_purple_background);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.item_white_background);
        }

        final CheckBox checkBox = holder.itemView.findViewById(R.id.checked_box);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.setmCheckBox(checkBox.isChecked());
                if (checkBox.isChecked()) {
                    holder.itemView.setBackgroundResource(R.drawable.item_blue_purple_background);
                } else {
                    holder.itemView.setBackgroundResource(R.drawable.item_white_background);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _event.setValue(position);
            }

        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}
