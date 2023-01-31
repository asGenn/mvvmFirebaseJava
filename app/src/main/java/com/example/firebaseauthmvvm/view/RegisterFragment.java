package com.example.firebaseauthmvvm.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseauthmvvm.R;
import com.example.firebaseauthmvvm.databinding.FragmentLoginBinding;
import com.example.firebaseauthmvvm.databinding.FragmentRegisterBinding;
import com.example.firebaseauthmvvm.model.User;
import com.example.firebaseauthmvvm.viewModel.LoginRegisterViewModel;


public class RegisterFragment extends Fragment {

    private LoginRegisterViewModel viewModel;
    private FragmentRegisterBinding binding;
    private static final String TAG = "RegisterFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = binding.text;
        Button button = binding.buttonlogout;
        if(getArguments() != null){
            String email = RegisterFragmentArgs.fromBundle(getArguments()).getEmail();
            textView.setText(email);
        }
        button.setOnClickListener(view1 -> signOut(view1));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginRegisterViewModel.class);

    }
    public void signOut(View view){
        viewModel.signOut();
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user.getUuid() == null){
                    Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                }
            }
        });
    }
}