package template.taxi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import template.taxi.R;
import template.taxi.model.RideClass;

import java.util.ArrayList;
import java.util.List;

public class RideClassListAdapter extends RecyclerView.Adapter<RideClassListAdapter.ViewHolder> {

    private Context ctx;
    private List<RideClass> items = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, RideClass obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView class_name;
        public TextView price;
        public TextView pax;
        public TextView duration;
        public View lyt_parent;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            class_name = (TextView) v.findViewById(R.id.class_name);
            price = (TextView) v.findViewById(R.id.price);
            pax = (TextView) v.findViewById(R.id.pax);
            duration = (TextView) v.findViewById(R.id.duration);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }

    }

    public RideClassListAdapter(Context ctx, List<RideClass> items) {
        this.ctx = ctx;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_taxi_item_ride_class, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final RideClass r = items.get(position);
        Picasso.with(ctx).load(r.image).into(holder.image);
        holder.class_name.setText(r.class_name);
        holder.price.setText(r.price);
        holder.pax.setText(r.pax);
        holder.duration.setText(r.duration);

        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, r, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}