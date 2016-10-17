package ch.hsr.mge.gadgeothek.presentation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class LoginActivity extends Activity implements LoginFragment.FragmentController, RegisterFragment.FragmentController {

    protected boolean authenticated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LibraryService.setServerAddress("http://mge7.dev.ifs.hsr.ch/public");
        setContentView(R.layout.activity_login);
        loadFragment(new LoginFragment());

        if (LibraryService.isLoggedIn()) {
            startMainActivity();
        }else{
            attemptLogin("ndipner@hsr.ch","12345");
        }
    }

    protected void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void makeSnack(String text) {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fragment_container), text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

    @Override
    public void loadRegisterFragment() {
        loadFragment(new RegisterFragment());
    }

    public void attemptLogin(String email, String password) {

        LibraryService.login(email, password, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean success) {
                if (success) {
                    startMainActivity();
                } else {
                    makeSnack("Couldn't log in!");
                }
            }

            @Override
            public void onError(String message) {
                makeSnack(message);
            }
        });
    }


    @Override
    public void register(String email, String password, String name, String matriculationNumber) {
        LibraryService.register(email, password, name, matriculationNumber, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean success) {
                if (success) {
                    startMainActivity();
                } else {
                    makeSnack("Couldn't be registered");
                }
            }

            @Override
            public void onError(String message) {
                makeSnack(message);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

}

