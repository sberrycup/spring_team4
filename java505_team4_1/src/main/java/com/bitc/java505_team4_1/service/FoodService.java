package com.bitc.java505_team4_1.service;

import com.bitc.java505_team4_1.dto.food.FoodItem;

import java.util.List;

public interface FoodService {
    List<FoodItem> getItemListUrl(String url) throws Exception;
}
