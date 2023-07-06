package com.bitc.java505_team4.service;

import com.bitc.java505_team4.dto.food.Food;
import com.bitc.java505_team4.dto.food.FoodItem;
import com.bitc.java505_team4.dto.food2.Food2;
import com.bitc.java505_team4.dto.food2.Food2Item;

import java.util.List;

public interface FoodService {
    List<FoodItem> getItemListUrl(String url) throws Exception;

    List<Food2> getItem2ListUrl(String url) throws Exception;
}
