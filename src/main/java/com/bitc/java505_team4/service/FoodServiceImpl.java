package com.bitc.java505_team4.service;

import com.bitc.java505_team4.dto.food.Food;
import com.bitc.java505_team4.dto.food.FoodDB;
import com.bitc.java505_team4.dto.food.FoodItem;
import com.bitc.java505_team4.dto.food.FoodItems;
import com.bitc.java505_team4.dto.food2.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{
    URL url = null;
    HttpURLConnection urlConn = null;

    @Override
    public List<FoodItem> getItemListUrl(String strUrl) throws Exception {
        List<FoodItem> itemList = null;

        try {
            url = new URL(strUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");

            JAXBContext jc = JAXBContext.newInstance(FoodDB.class);
            Unmarshaller um = jc.createUnmarshaller();

            FoodDB foodData = (FoodDB) um.unmarshal(url);
            itemList = foodData.getBody().getItems().getItemList();
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        return itemList;
    }


    @Override
    public List<Food2> getItem2ListUrl(String strUrl) throws Exception {
        List<Food2> food2List = null;

        try {
            url = new URL(strUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");

            JAXBContext jc = JAXBContext.newInstance(Food2DB.class);
            Unmarshaller um = jc.createUnmarshaller();

            Food2DB fullData = (Food2DB) um.unmarshal(url);
            Food2Body body = fullData.getBody();
            Food2Items items = body.getItems2();
            List<Food2Item> itemList = items.getItem2List();
            food2List = itemList.get(0).getFood2List();
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        return food2List;
    }
}
