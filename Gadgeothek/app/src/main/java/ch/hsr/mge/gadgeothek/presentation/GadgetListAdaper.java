package ch.hsr.mge.gadgeothek.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Gadget;

public class GadgetListAdaper extends RecyclerView.Adapter<GadgetListAdaper.Viewholder> {
    private List<Gadget> gadgets;
    private ItemSelectionListener selectionListener;

    public GadgetListAdaper(List<Gadget> gadgets, ItemSelectionListener selectionListener) {
        this.gadgets = gadgets;
        this.selectionListener = selectionListener;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView gadgetName;

        public Viewholder(View itemView, TextView gadgetNameView) {
            super(itemView);
            this.gadgetName = gadgetNameView;
        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rowlayout_gadgetlist, parent, false);
        TextView gadgetName = (TextView) view.findViewById(R.id.gadgetName);
        return new Viewholder(view, gadgetName);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        final Gadget gadget = gadgets.get(position);
        holder.gadgetName.setText(gadget.getName());

    }

    @Override
    public int getItemCount() {
        return gadgets.size();
    }
}
