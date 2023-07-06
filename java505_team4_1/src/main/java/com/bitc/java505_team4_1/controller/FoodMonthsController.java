package com.bitc.java505_team4_1.controller;

import com.bitc.java505_team4_1.service.FoodMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class FoodMonthsController {

    @Autowired
    FoodMonthService foodMonthService;

    @RequestMapping(value = "foodMonths.do", method = RequestMethod.GET)
    public String doGetFoodMonths() throws Exception{
        return "foodM/fmMain";
    }

    @ResponseBody
    @RequestMapping(value = "foodMonths.do", method = RequestMethod.POST)
//    public Object doPostFoodMonths(@RequestParam(value = "foodName", required = false) String foodName, @RequestParam("month") String month) throws Exception{
    public Object doPostFoodMonths(@RequestParam("foodName") String foodName, @RequestParam("month") String month) throws Exception{

//        trim을 써서 이상한 것 날리고 null인지 확인한다.
        if (foodName.trim().equals("")) {
            foodMonthService.foodMonthList(foodName, month);
        }
        else {
            foodMonthService.foodNameNullMonthList(month);
        }

        return true;
    }
}
