package com.ndcreative.explorepamekasan.data.network;

import com.ndcreative.explorepamekasan.data.db.WisataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface URLunicode {


    @GET("s/AKfycbzKjXBfv3gBOrqw1j6A48f_jlN-OSdppy42LAl6t24UmVngTL7x/exec?action=read&")
    Call<WisataResponse> loadWisata(@Query("table_name") String sheet_name);

}