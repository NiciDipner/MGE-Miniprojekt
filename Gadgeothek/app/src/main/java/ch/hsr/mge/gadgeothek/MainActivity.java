package ch.hsr.mge.gadgeothek;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    protected boolean authenticated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        loadFragment(new Fragment()); //TODO: Implement Home-Screen
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                makeSnack("Procced to login (TODO)");
                break;
            case R.id.registerLink:
                makeSnack("Procced to registration page (TODO)");
                break;
            default:
                makeSnack("No Action defined!");
                break;
        }
    }

    public void makeSnack(String text){
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fragment_container) , text, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public void loadFragment(Fragment fragment) {
        if (!authenticated) {
            fragment = new LoginFragment();
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}

