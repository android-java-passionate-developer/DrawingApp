package com.example.pujarani.drawingapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Puja.Rani on 26-12-2019.
 */

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    MyListAdapter adapter;
    ReadWriteClass io;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        io = ReadWriteClass.getInstance();

        String data = io.readFromFile(getContext());
        JSONArray array = null;
        try {
            array = new JSONArray(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new MyListAdapter(getContext(), array);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
