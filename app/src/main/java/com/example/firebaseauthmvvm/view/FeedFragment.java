package com.example.firebaseauthmvvm.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firebaseauthmvvm.R;
import com.example.firebaseauthmvvm.databinding.FragmentFeedBinding;
import com.example.firebaseauthmvvm.databinding.FragmentLoginBinding;


public class FeedFragment extends Fragment {

    private FragmentFeedBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}