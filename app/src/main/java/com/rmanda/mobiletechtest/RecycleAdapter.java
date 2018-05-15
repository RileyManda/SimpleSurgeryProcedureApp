    package com.rmanda.mobiletechtest;

    /**
     * Created by
     * RMB on 2/21/18.
     */
    import android.content.Context;
    import android.content.Intent;
    import android.support.v7.widget.CardView;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;
    import com.bumptech.glide.Glide;
    import java.util.ArrayList;
    import java.util.HashMap;


    public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

        private ArrayList<HashMap<String, String>> mDataset;

        public RecycleAdapter(ArrayList<HashMap<String, String>> mDataset) {
            this.mDataset = mDataset;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup Adapter, int viewType) {
            View v = LayoutInflater.from(Adapter.getContext()).inflate(R.layout.activity_procedure, Adapter, false);
            return new ViewHolder(v);
            //activity_procedure
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,final int position) {
            holder.getId().setText(mDataset.get(position).get("id"));
            holder.getItemname().setText(mDataset.get(position).get("name"));
            holder.getItemname().setText(mDataset.get(position).get("name"));
            holder.getItemname().setText(mDataset.get(position).get("name"));
            Glide.with(holder.getIvImage())
                .load(mDataset.get(position).get("icon")).into(holder.getIvImage());

            holder.getIvImage().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ProcedureDetail.class);
                    intent.putExtra(ProcedureDetail.KEY_DETAIL , mDataset.get(position));
                    view.getContext().startActivity(intent);
                }
            });
    //        holder.getIvImage().setBackground(mDataset.get(position).get("icon"));

        }

        @Override
        public int getItemCount() {
            return mDataset.size();

        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView itemId,tvName;
            private ImageView ivImage;


            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                itemId = itemView.findViewById(R.id.tvId);
                tvName = itemView.findViewById(R.id.itemName);
                ivImage = itemView.findViewById(R.id.thumbimage);
            }

            public TextView getId() {
                return itemId;
            }

            public TextView getItemname() {
                return tvName;
            }

            public ImageView getIvImage() {
                return ivImage;
            }





            @Override
            public void onClick(View v ) {


                }


            }
        }

