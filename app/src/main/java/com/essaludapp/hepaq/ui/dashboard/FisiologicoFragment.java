package com.essaludapp.hepaq.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.essaludapp.hepaq.R;

public class FisiologicoFragment extends Fragment {

    public FisiologicoFragment() {
        // Required empty public constructor
    }

    public static FisiologicoFragment newInstance(String param1, String param2) {
        FisiologicoFragment fragment = new FisiologicoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fisiologico, container, false);
    }
}