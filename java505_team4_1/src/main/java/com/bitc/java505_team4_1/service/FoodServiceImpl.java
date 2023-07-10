package com.bitc.java505_team4_1.service;

import com.bitc.java505_team4_1.dto.food.FoodDB;
import com.bitc.java505_team4_1.dto.food.FoodItem;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{

//    수업 시간에 했던 예제를 거의 비슷하게 가져옴
    @Override
    public List<FoodItem> getItemListUrl(String strUrl) throws Exception {
        List<FoodItem> itemList = null;

        URL url = null;

        HttpURLConnection urlConn = null;

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
}
