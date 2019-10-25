package com.ndcreative.explorepamekasan.data.network;

/**
 * Created by Umar Fadil on 25,Oct,2019
 * ND Creative Solution
 * id.ndcreativesolution@gmail.com
 */

public class ApiUrl {

    public static final String BASE_URL = "https://script.google.com/macros/";

    public static URLunicode getData() {
        return RetrofitClient.getClient(BASE_URL).create(URLunicode.class);
    }
}
