package com.bitc.java505_team4_1.mapper;

import com.bitc.java505_team4_1.dto.FoodMonthDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodMonthMapper {
    public List<FoodMonthDto> foodMonthList(FoodMonthDto foodMonthDto) throws Exception;
    public List<FoodMonthDto> foodNameNullMonthList(FoodMonthDto foodMonthDto) throws Exception;
}
