package com.nqnghia.testcardview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private AdapterCardView mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemCardView> mItemList;

    private Button insert;
    private Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createItemList();
        buildRecyclerView();

        insert = findViewById(R.id.insert_btn);
        remove = findViewById(R.id.remove_btn);

        insert.setOnClickListener(this);
        remove.setOnClickListener(this);

        findViewById(R.id.favorite_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Favorite!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createItemList() {
        mItemList = new ArrayList<>();
        mItemList.add(new ItemCardView(R.drawable.ic_android, "Title 01", "Subtitle 01"));
        mItemList.add(new ItemCardView(R.drawable.ic_favorite, "Title 02", "Subtitle 02"));
        mItemList.add(new ItemCardView(R.drawable.ic_android, "Title 03", "Subtitle 03"));
        mItemList.add(new ItemCardView(R.drawable.ic_favorite, "Title 04", "Subtitle 04"));
        mItemList.add(new ItemCardView(R.drawable.ic_android, "Title 05", "Subtitle 05"));
        mItemList.add(new ItemCardView(R.drawable.ic_favorite, "Title 06", "Subtitle 06"));
        mItemList.add(new ItemCardView(R.drawable.ic_android, "Title 07", "Subtitle 07"));
        mItemList.add(new ItemCardView(R.drawable.ic_favorite, "Title 08", "Subtitle 08"));
        mItemList.add(new ItemCardView(R.drawable.ic_android, "Title 09", "Subtitle 09"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recycle_list_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterCardView(mItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.event.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Snackbar.make(mRecyclerView, integer.toString(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void insertItem() {
        openDialog();
    }

    private void removeItem(int position) {
        if (mItemList.size() > position && position > -1) {
            Snackbar.make(mRecyclerView, mAdapter.event.getValue().toString(), Snackbar.LENGTH_LONG).show();
            mItemList.remove(position);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void openDialog() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View v = inflater.inflate(R.layout.insert_item_dialog, null);
        final EditText imgResource = v.findViewById(R.id.image_resource);
        final EditText title = v.findViewById(R.id.title_edit_text);
        final EditText subtitle = v.findViewById(R.id.subtitle_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insert Item");
        builder.setMessage("Insert item card view into list view");
        builder.setView(v);
        builder.setIcon(R.drawable.ic_assignment_late);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!imgResource.getText().toString().equals("") &&
                        !title.getText().toString().equals("") &&
                        !subtitle.getText().toString().equals("")) {
                    switch (Integer.parseInt(imgResource.getText().toString())) {
                        case 0:
                            mItemList.add(new ItemCardView(R.drawable.ic_android, title.getText().toString(), subtitle.getText().toString()));
                            mAdapter.notifyDataSetChanged();
                            break;
                        case 1:
                            mItemList.add(new ItemCardView(R.drawable.ic_favorite, title.getText().toString(), subtitle.getText().toString()));
                            mAdapter.notifyDataSetChanged();
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert_btn:
                insertItem();
                break;
            case R.id.remove_btn:
                removeItem(mAdapter.event.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mRecyclerView.setAdapter(null);
        super.onDestroy();
    }
}
