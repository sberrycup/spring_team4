package com.bitc.java505_team4.controller;

import com.bitc.java505_team4.dto.food.Food;
import com.bitc.java505_team4.dto.food.FoodItem;
import com.bitc.java505_team4.dto.food2.Food2;
import com.bitc.java505_team4.dto.food2.Food2Item;
import com.bitc.java505_team4.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/foodUrl")
    public String getFoodUrl() throws Exception {
        return "foodUrl";
    }

    @ResponseBody
    @PostMapping("/foodUrl")
    public Object getFoodUrl(String Page_No, String Page_Size, String fd_Nm) throws Exception {

        String url = "http://apis.data.go.kr/1390802/AgriFood/FdFoodImage/getKoreanFoodFdFoodImageList";
        String serviceKey = "?serviceKey=";
        String key = "0m0EyVE7TywORA8Zu4qti6NOPFYY6D89BGTNGTz7%2BT4uU6Cp%2B8fCsQDYypUZD5ML4Q%2BwGxBsnH2lJg0yD2Ei9g%3D%3D";
        String serviceType = "&service_Type=xml";
        String opt1 = "&Page_No=";
        String opt2 = "&Page_Size=";
        String opt3 = "&food_Name=";
        fd_Nm = URLEncoder.encode(fd_Nm, "UTF-8");

        List<FoodItem> itemList = foodService.getItemListUrl(url + serviceKey + key + serviceType + opt1 + Page_No + opt2 + Page_Size + opt3 + fd_Nm);

        return itemList;
    }

//    @ResponseBody
//    @PostMapping("/foodUrl1")
//    public Object getUrl(String Page_No, String Page_Size, String fd_Nm) throws Exception {
//
//        String url = "http://apis.data.go.kr/1390802/AgriFood/FdFoodImage/getKoreanFoodFdFoodImageList";
//        String serviceKey = "?serviceKey=";
//        String key = "0m0EyVE7TywORA8Zu4qti6NOPFYY6D89BGTNGTz7%2BT4uU6Cp%2B8fCsQDYypUZD5ML4Q%2BwGxBsnH2lJg0yD2Ei9g%3D%3D";
//        String serviceType = "&service_Type=xml";
//        String opt1 = "&Page_No=";
//        String opt2 = "&Page_Size=";
//        String opt3 = "&food_Name=";
//        fd_Nm = URLEncoder.encode(fd_Nm, "UTF-8");
//
//        List<Food> itemList = foodService.getListUrl(url + serviceKey + key + serviceType + opt1 + Page_No + opt2 + Page_Size + opt3 + fd_Nm);
//
//        return itemList;
//    }


    @ResponseBody
    @PostMapping("/foodUrl2")
    public Object getFood2Url(String foodCd) throws Exception {
        String url = "http://apis.data.go.kr/1390802/AgriFood/MzenFoodNutri/getKoreanFoodIdntList";
        String serviceKey = "?serviceKey=";
        String key = "0m0EyVE7TywORA8Zu4qti6NOPFYY6D89BGTNGTz7%2BT4uU6Cp%2B8fCsQDYypUZD5ML4Q%2BwGxBsnH2lJg0yD2Ei9g%3D%3D";
        String opt1 = "&food_Code=";

        List<Food2>item2List = foodService.getItem2ListUrl(url + serviceKey + key + opt1 + foodCd);

        return item2List;
    }
}
