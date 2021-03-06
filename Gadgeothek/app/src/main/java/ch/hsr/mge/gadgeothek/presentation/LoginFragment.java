package ch.hsr.mge.gadgeothek.presentation;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import ch.hsr.mge.gadgeothek.R;

public class LoginFragment extends Fragment implements View.OnClickListener{
    private FragmentController activity;
    private EditText passwordField;
    private EditText emailField;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        root.findViewById(R.id.sign_in_button).setOnClickListener(this);
        root.findViewById(R.id.registerLink).setOnClickListener(this);
        this.passwordField = (EditText) root.findViewById(R.id.password);
        this.emailField = (EditText) root.findViewById(R.id.email);
        return root;
    }

    @Override
    public void onAttach (Context activity){
        super.onAttach(activity);
        if (activity instanceof FragmentController) {
            this.activity = (FragmentController) activity;

        } else {
            throw new AssertionError("Activity must implement FragmentController!");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerLink){
            activity.loadRegisterFragment();
        }
        if (view.getId() == R.id.sign_in_button){
            activity.attemptLogin(emailField.getText().toString(), passwordField.getText().toString());
        }
    }

    public interface FragmentController{
        void loadRegisterFragment();
        void attemptLogin(String email, String password);
    }
}
