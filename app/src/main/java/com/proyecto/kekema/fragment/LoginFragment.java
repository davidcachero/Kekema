package com.proyecto.kekema.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.proyecto.kekema.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyecto.kekema.activity.RecipeActivity;

public class LoginFragment extends Fragment {
    private View view;
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecipeActivity.class);
                startActivity(intent);
                getActivity().finish(); // Destroy activity so you have to close de application to get to it again
                // To use when firebase database is implemented
                /*if(currentUser != null){
                    currentUser.reload();
                }else{
                    Toast.makeText(getContext(), R.string.incorrectLogin, Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        return view;
    }
}