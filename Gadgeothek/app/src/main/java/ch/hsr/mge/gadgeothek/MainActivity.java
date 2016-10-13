package ch.hsr.mge.gadgeothek;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class MainActivity extends Activity implements LoginFragment.FragmentController, RegisterFragment.FragmentController {

    protected boolean authenticated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LibraryService.setServerAddress("http://mge7.dev.ifs.hsr.ch/public");
        setContentView(R.layout.main_activity);
        loadFragment(new Fragment()); //TODO: Implement Home-Screen
    }

    public void makeSnack(String text){
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fragment_container) , text, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public void loadFragment(Fragment fragment) {
        if (!authenticated && !(fragment instanceof RegisterFragment)) {
            fragment = new LoginFragment();
        }

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

    public void attemptLogin(String email,String password){

        LibraryService.login(email, password, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean success) {
                if(success) {
                    makeSnack("Success");
                } else {
                    makeSnack("Fail!");
                }
            }
            @Override
            public void onError(String message) {
                makeSnack(message);
            }
        });
    }


    @Override
    public void register(String email, String password,  String name, String matriculationNumber ) {
        makeSnack(email);
//        LibraryService.register("dummy@test.ch","12345","Dummy","12345",new Callback<Boolean>(){
//            @Override
//            public void onCompletion(Boolean success) {
//                if(success) {
//                    makeSnack("Success");
//                } else {
//                    makeSnack("Couldn't be registered");
//                }
//            }
//            @Override
//            public void onError(String message) {
//                makeSnack(message);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if ( getFragmentManager().getBackStackEntryCount() <= 1 ) {
            finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

}

