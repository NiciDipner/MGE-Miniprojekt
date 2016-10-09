package ch.hsr.mge.gadgeothek;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RegisterFragment extends Fragment implements View.OnClickListener{
    private FragmentController activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        root.findViewById(R.id.registerButton).setOnClickListener(this);
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

        ((FragmentController) getActivity()).register();
    }

    public interface FragmentController{
        public void register();
    }
}
