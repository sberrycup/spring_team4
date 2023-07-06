package com.bitc.java505_team4_1.service;

import com.bitc.java505_team4_1.dto.FoodMonthDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FoodMonthService {
    public List<FoodMonthDto> foodMonthList(@RequestParam("foodName") String foodName, @RequestParam("month") String month) throws Exception;
    public List<FoodMonthDto> foodNameNullMonthList(@RequestParam("month") String month) throws Exception;
}
