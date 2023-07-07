package com.bitc.java505_team4_1.controller;

import com.bitc.java505_team4_1.dto.FoodMonthDto;
import com.bitc.java505_team4_1.service.FoodMonthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class FoodMonthsController {

    @Autowired
    FoodMonthService foodMonthService;

    @RequestMapping(value = "foodMonths.do", method = RequestMethod.GET)
    public String doGetFoodMonths() throws Exception{
        return "foodM/fmMain";
    }

//    ajax할시 @ResponseBody 반드시 필요
    @ResponseBody
    @RequestMapping(value = "foodMonths.do", method = RequestMethod.POST)
//    public Object doPostFoodMonths(@RequestParam(value = "foodName", required = false) String foodName, @RequestParam("month") String month) throws Exception{
//    위와 같은 방법으로 false,true를 줘서 하는 방법이 있음
    public Object doPostFoodMonths(@RequestParam("foodMName") String foodName, @RequestParam("foodMMonth") String month) throws Exception{

        List<FoodMonthDto> foodMonthDtoList = null;

//        trim을 써서 이상한 것 날리고 null인지 확인한다.
        if (foodName.trim().equals("")) {
            foodMonthDtoList = foodMonthService.foodNameNullMonthList(month);
        }
        else {
//            foodMonthDtoList = foodMonthService.foodMonthList(foodName, month);
            foodMonthDtoList = foodMonthService.foodMonthList(foodName);
        }

        return foodMonthDtoList;
    }
}
