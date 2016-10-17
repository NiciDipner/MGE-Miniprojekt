package ch.hsr.mge.gadgeothek.presentation;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.hsr.mge.gadgeothek.R;

public class RegisterFragment extends Fragment implements View.OnClickListener{
    private FragmentController activity;
    private EditText passwordField;
    private EditText passwordConfirmationField;
    private EditText emailField;
    private EditText firstNameField;
    private EditText lastNameField;
    private EditText matriculationNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        root.findViewById(R.id.registerButton).setOnClickListener(this);
        this.passwordField = (EditText) root.findViewById(R.id.password);
        this.passwordConfirmationField = (EditText) root.findViewById(R.id.passwordConfirmation);
        this.emailField = (EditText) root.findViewById(R.id.email);
        this.firstNameField = (EditText) root.findViewById(R.id.firstName);
        this.lastNameField = (EditText) root.findViewById(R.id.lastName);
        this.matriculationNumber = (EditText) root.findViewById(R.id.matriculationNumber);
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
        if (validateRegistrationForm()) {
            ((FragmentController) getActivity()).register(
                    emailField.getText().toString(),
                    passwordField.getText().toString(),
                    firstNameField.getText().toString() + " " + lastNameField.getText().toString(),
                    matriculationNumber.getText().toString()
            );
        }
    }

    public interface FragmentController{
        public void register(String email, String password, String name, String matriculationNumber);
    }

    boolean validateRegistrationForm(){
        boolean emptyFieldsFound = false;
        for (EditText field: new ArrayList<EditText>(Arrays.asList(
                firstNameField,
                lastNameField,
                emailField,
                passwordField,
                matriculationNumber
        ))) {
            field.setError(null);
            if (!emptyFieldsFound && fieldIsEmpty(field)){
                field.requestFocus();
                field.setError("Please fill out this Field!");
            }
        }
        if (emptyFieldsFound){
            return false;
        }

        if (!isValidEmail(emailField.getText().toString())){
            emailField.setError("Invalid email address");
            return false;
        }

        if (!passwordField.getText().toString().equals(passwordConfirmationField.getText().toString())){
            passwordConfirmationField.setError("Confirmation does not match password");
            return false;
        }
        return true;
    }


    static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    static boolean fieldIsEmpty(EditText field){
        return  (field.getText().toString().isEmpty());
    }
}
