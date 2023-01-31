package com.example.firebaseauthmvvm.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebaseauthmvvm.R;
import com.example.firebaseauthmvvm.databinding.FragmentLoginBinding;
import com.example.firebaseauthmvvm.model.User;
import com.example.firebaseauthmvvm.viewModel.LoginRegisterViewModel;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginRegisterViewModel viewModel;
    private NavController navController;
    private EditText email,pass;
    private Button signInButton;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        email = binding.emailLogin;
        pass = binding.passLogin;
        signInButton = binding.singInButton;
        signInButton.setOnClickListener(view1 -> {
            girisYap(view1);
        });
        alreadyLogged(view);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginRegisterViewModel.class);



    }
    public void girisYap(View view) {

        String getEmail = email.getText().toString();
        String getPass = pass.getText().toString();
        if (getEmail.length() > 0 && pass.length() > 0) {
            viewModel.signIn(getEmail, getPass);
            viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), user -> {

                LoginFragmentDirections.ActionLoginFragmentToRegisterFragment action =
                        LoginFragmentDirections.actionLoginFragmentToRegisterFragment();
                action.setEmail(user.getEmail());
                Navigation.findNavController(view).navigate(action);



            });
        }
    }
    public void alreadyLogged(View view){
            viewModel.loggedIn();
            viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), user -> {
                if(user.isLogged() ==true) {
                    LoginFragmentDirections.ActionLoginFragmentToRegisterFragment action =
                            LoginFragmentDirections.actionLoginFragmentToRegisterFragment();
                    action.setEmail(user.getEmail());
                    Navigation.findNavController(view).navigate(action);
                }
            });


    }

}