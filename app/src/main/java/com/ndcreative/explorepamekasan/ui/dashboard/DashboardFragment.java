package com.ndcreative.explorepamekasan.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ndcreative.explorepamekasan.R;
import com.ndcreative.explorepamekasan.data.adapter.WisataAdapter;
import com.ndcreative.explorepamekasan.data.db.WisataItems;
import com.ndcreative.explorepamekasan.data.db.WisataResponse;
import com.ndcreative.explorepamekasan.data.network.ApiUrl;
import com.ndcreative.explorepamekasan.data.network.URLunicode;
import com.ndcreative.explorepamekasan.listener.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    View rootView;
    private static String TAG = DashboardFragment.class.getSimpleName();
    private List<WisataItems> wisataItemsList = new ArrayList<>();
    private WisataAdapter wisataAdapter;

    @BindView(R.id.rv_wisata)
    RecyclerView rvWisata;
    @BindView(R.id.loading_layout)
    RelativeLayout loadingLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        getDataWisata();
        return rootView;
    }

    private void initView() {
        wisataAdapter = new WisataAdapter(getActivity(), wisataItemsList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rvWisata.setLayoutManager(layoutManager);
        rvWisata.setItemAnimator(new DefaultItemAnimator());
        rvWisata.setAdapter(wisataAdapter);
        rvWisata.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvWisata, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                WisataItems data = wisataItemsList.get(position);
                Toast.makeText(getActivity(), "Clicked -> " + data.getNama_wisata(), Toast.LENGTH_SHORT).show();
                /*Intent goDetail = new Intent(getApplicationContext(), DetailWisataActivity.class);
                goDetail.putExtra("nama_wisata", data.getNama_wisata());
                goDetail.putExtra("alamat", data.getAlamat());
                goDetail.putExtra("kabupaten", data.getKabupaten());
                goDetail.putExtra("description", data.getDescription());
                goDetail.putExtra("latitude", data.getLatitude());
                goDetail.putExtra("longitude", data.getLongitude());
                goDetail.putExtra("photos", data.getPhotos());
                startActivity(goDetail);*/
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void getDataWisata() {
        loadingLayout.setVisibility(View.VISIBLE);
        rvWisata.setVisibility(View.GONE);
        URLunicode urLunicode;
        urLunicode = ApiUrl.getData();
        urLunicode.loadWisata("pamekasan").enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(@NonNull Call<WisataResponse> call, @NonNull Response<WisataResponse> response) {
                if (response.isSuccessful()) {
                    loadingLayout.setVisibility(View.GONE);
                    rvWisata.setVisibility(View.VISIBLE);

                    Log.d(TAG, response.toString());

                    wisataItemsList = response.body().getData();
                    wisataAdapter = new WisataAdapter(getActivity(), wisataItemsList);
                    rvWisata.setAdapter(wisataAdapter);
                }
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                loadingLayout.setVisibility(View.GONE);
                rvWisata.setVisibility(View.GONE);
                Log.d("Error", t.getMessage());
            }
        });
    }
}