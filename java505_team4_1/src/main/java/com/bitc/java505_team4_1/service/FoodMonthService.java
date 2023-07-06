package com.bitc.java505_team4_1.service;

import com.bitc.java505_team4_1.dto.FoodMonthDto;

import java.util.List;

public interface FoodMonthService {
//    List<FoodMonthDto> foodMonthList(String foodName, String month) throws Exception;
    List<FoodMonthDto> foodMonthList(String foodName) throws Exception;

    List<FoodMonthDto> foodNameNullMonthList(String month) throws Exception;
}
