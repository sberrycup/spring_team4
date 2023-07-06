package com.bitc.java505_team4_1.service;

import com.bitc.java505_team4_1.dto.FoodMonthDto;
import com.bitc.java505_team4_1.mapper.FoodMonthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodMonthServiceImpl implements FoodMonthService {

    @Autowired
    FoodMonthMapper foodMonthMapper;

    @Override
    public List<FoodMonthDto> foodMonthList(String foodName, String month) throws Exception {
        List<FoodMonthDto> a = new ArrayList<>();
        foodMonthMapper.foodMonthList(foodName, month);
        return a;
    }

    @Override
    public List<FoodMonthDto> foodNameNullMonthList(String month) throws Exception {
        return null;
    }
}
