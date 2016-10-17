package ch.hsr.mge.gadgeothek.presentation;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Gadget;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

import static android.support.v7.appcompat.R.id.text;

public class GadgetListFragment extends Fragment {
    private ItemSelectionListener itemSelectionCallback = null;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private GadgetListAdaper gadgetListAdaper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_gadget_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.gadgetRecycleView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        Callback<List<Gadget>> displayGadgets = new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                System.out.print(input);
                gadgetListAdaper = new GadgetListAdaper(input, itemSelectionCallback);
                recyclerView.setAdapter(gadgetListAdaper);

                Toast.makeText(getActivity(), "Gadgets loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        };
        LibraryService.getGadgets(displayGadgets);

        return rootView;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        if (!(activity instanceof ItemSelectionListener)) {
            throw new IllegalStateException("Activity must implement ItemSelectionListener");
        }
        itemSelectionCallback = (ItemSelectionListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        itemSelectionCallback = null;
    }

}
