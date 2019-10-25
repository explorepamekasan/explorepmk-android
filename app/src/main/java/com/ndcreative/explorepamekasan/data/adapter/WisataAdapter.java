package com.ndcreative.explorepamekasan.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ndcreative.explorepamekasan.R;
import com.ndcreative.explorepamekasan.data.db.WisataItems;
import com.ndcreative.explorepamekasan.utils.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder> {

    private Context context;
    private List<WisataItems> wisataItems;

    public WisataAdapter(Context context, List<WisataItems> wisataItemsList) {
        this.context = context;
        this.wisataItems = wisataItemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_wisata, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WisataItems result = wisataItems.get(position);
        holder.namaWisata.setText(result.getNama_wisata());
        String url = result.getPhotos();
        ViewUtils.showImageToView(holder.imgWisata, result.getPhotos());

        /*Glide.with(context).load(result.getPhotos())
                .placeholder(R.drawable.img_loading)
                .error(R.drawable.img_no_image)
                .crossFade()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgWisata);*/
    }

    @Override
    public int getItemCount() {
        return wisataItems == null ? 0 : wisataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_wisata)
        TextView namaWisata;
        @BindView(R.id.img_wisata)
        ImageView imgWisata;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
