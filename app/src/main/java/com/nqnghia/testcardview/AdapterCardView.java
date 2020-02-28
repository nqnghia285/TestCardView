package com.nqnghia.testcardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nqnghia.testcardview.R;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCardView extends RecyclerView.Adapter<AdapterCardView.ViewHolderCardView> {

    private ArrayList<ItemCardView> mItemList;

    private MutableLiveData<Integer> _event = new MutableLiveData<>();

    LiveData<Integer> event = _event;

    public static class ViewHolderCardView extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTitle;
        TextView mSubtitle;

        ViewHolderCardView(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_card_view);
            mTitle = itemView.findViewById(R.id.title_card_view);
            mSubtitle = itemView.findViewById(R.id.sub_title_card_view);
        }
    }

    public AdapterCardView(ArrayList<ItemCardView> itemList) {
        mItemList = itemList;
        _event.setValue(0);
    }

    @NonNull
    @Override
    public ViewHolderCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new ViewHolderCardView(li.inflate(R.layout.item_card_view , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCardView holder, final int position) {
        ItemCardView currentItem = mItemList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTitle.setText(currentItem.getTitle());
        holder.mSubtitle.setText(currentItem.getSubtitle());

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
