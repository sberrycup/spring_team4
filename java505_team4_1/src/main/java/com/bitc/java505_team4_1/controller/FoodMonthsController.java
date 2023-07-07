package com.bitc.java505_team4_1.controller;

import com.bitc.java505_team4_1.dto.FoodMonthDto;
import com.bitc.java505_team4_1.dto.food.FoodItem;
import com.bitc.java505_team4_1.dto.food.FoodItems;
import com.bitc.java505_team4_1.service.FoodMonthService;
import com.bitc.java505_team4_1.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class FoodMonthsController {

    @Autowired
    FoodMonthService foodMonthService;

    @Autowired
    FoodService foodService;

    @RequestMapping(value = "foodMonths.do", method = RequestMethod.GET)
    public String doGetFoodMonths() throws Exception{
        return "foodM/fmMain";
    }

//    ajax할시 @ResponseBody 반드시 필요
    @ResponseBody
    @RequestMapping(value = "foodMonths.do", method = RequestMethod.POST)
//    public Object doPostFoodMonths(@RequestParam(value = "foodName", required = false) String foodName, @RequestParam("month") String month) throws Exception{
//    위와 같은 방법으로 false,true를 줘서 하는 방법이 있음
    public Object doPostFoodMonths(@RequestParam("fd_Nm") String foodName, @RequestParam("foodMMonth") String month, String Page_No, String Page_Size) throws Exception{

        List<FoodMonthDto> foodMonthDtoList = null;

//        trim을 써서 이상한 것 날리고 null인지 확인한다.
        if (foodName.trim().equals("")) {
            foodMonthDtoList = foodMonthService.foodNameNullMonthList(month);
        }
        else {
//            foodMonthDtoList = foodMonthService.foodMonthList(foodName, month);
            foodMonthDtoList = foodMonthService.foodMonthList(foodName);
        }
//        return foodMonthDtoList;

        String url = "https://apis.data.go.kr/1390802/AgriFood/FdFoodCkryImage/getKoreanFoodFdFoodCkryImageList";
        String serviceKey = "?serviceKey=";
        String key = "CW2CKvXPaOkFQSGvnI%2B5ly3MDBk%2BDuFxAdXMeAoP9T2EXQOeOi5P%2F8m95VWOpC1WcGdtFZ4%2FmfKHSNz76%2FetSA%3D%3D";
//        %2FrJ0lubv%2B4MV1XYApYfL%2Fw93lueywJYc3Gjo7PuKxSNOhJzMFusJmj0D6ZLDKzD6cMkRG8tyC8ZTDbnVYZXKJQ%3D%3D (내 키)
//        yde%2BbfwnvOmXEL6VOY1zviYmunzq3TPYLBJG2fzC9XisbSxP6xIHPaMgz3Q9xjJhbHDDN%2BhD7pw2HjMDvz4Lpw%3D%3D (규호씨 키)
        String serviceType = "&service_Type=xml";
        String opt1 = "&Page_No=";
        String opt2 = "&Page_Size=";
        String opt3 = "&food_Name=";
        String opt4 = "&ckry_Name=";
        String opt5 = "조리";

//        foodName = URLEncoder.encode(foodName, "UTF-8");
        opt5 = URLEncoder.encode(opt5, "UTF-8");

        List<List<FoodItem>> foodItemList = new ArrayList<>();

        for (int i = 0; i < foodMonthDtoList.size(); i++) {
            foodName = URLEncoder.encode(foodMonthDtoList.get(i).getFoodMName(), "UTF-8");
            List<FoodItem> foodList = foodService.getItemListUrl(url + serviceKey + key + serviceType + opt1 + Page_No + opt2 + Page_Size + opt3 + foodName + opt4 + opt5);
            foodItemList.add(foodList);
        }

        //        return itemList;

        Map<String, Object> data = new HashMap<>();
        data.put("foodDBList", foodMonthDtoList);
        data.put("foodAPIList", foodItemList);

        return data;
    }
}

