package com.proyecto.kekema.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyecto.kekema.R;

public class RegisterFragment extends Fragment {
    private View view;
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        EditText editTextUsername = view.findViewById(R.id.edit_text_username_register);
        EditText editTextPassword = view.findViewById(R.id.edit_text_password_register);
        EditText editTextRepeatPassword = view.findViewById(R.id.edit_text_repeat_password_register);
        Button registerButton = view.findViewById(R.id.register_button);

        auth = FirebaseAuth.getInstance();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPassword(editTextPassword.getText().toString(), editTextRepeatPassword.getText().toString())
                        && checkFields(editTextUsername.getText().toString(), editTextPassword.getText().toString(), editTextRepeatPassword.getText().toString())) {
                    createUser(editTextUsername.getText().toString(), editTextPassword.getText().toString());
                }
            }
        });
        return view;
    }

    private boolean checkPassword(String password, String repeat) {
        if (!password.equals(repeat)) {
            Toast.makeText(getContext(), "Passwords have to be the same", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkFields(String user, String password, String repeat) {
        if (user.length() == 0 || password.length() == 0
                || repeat.length() == 0) {
            Toast.makeText(getContext(), "Please, fill all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void createUser(String user, String password) {
        auth.createUserWithEmailAndPassword(user, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            /*getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.tab_layout, new LoginFragment())
                                    .commit();*/
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /*private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }*/
}