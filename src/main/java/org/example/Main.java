package org.example;

import com.google.gson.Gson;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;





public class Main {

    public static JSONArray getJsonObjectOrJsonArray(Object object){
        JSONArray jsonArray = new JSONArray();
        if (object instanceof Map){
            JSONObject jsonObject = new JSONObject();
            jsonObject.putAll((Map)object);
            jsonArray.add(jsonObject);
        }
        else if (object instanceof List){
            jsonArray.addAll((List)object);
        }
        return jsonArray;
    }

    public static void main(String[] args) {

        try {
            ArrayList<CatalogItem> catalogItemsArrayList = new ArrayList<CatalogItem>();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("itm00267.hst");
            Scanner myReader = new Scanner(is);
            while (myReader.hasNextLine()) {
                // Read nextline
                String data = myReader.nextLine();
                // split each segment of data
                String[] dataArray = data.split(" ");
                System.out.println(data);
                CatalogItem cat = new CatalogItem();

                String catalogName = dataArray[0];
                //set the product name
                cat.setCatalogName(catalogName);
                String skuId = dataArray[1];
                cat.setSkuId(skuId);
                String displayName = dataArray[2];
                cat.setDisplayName(displayName);
                String description = dataArray[3];
                cat.setDescription(description);
                String groupName = dataArray[4];
                cat.setGroupName(groupName);
                String uom = dataArray[5];
                cat.setUOM(uom);
                //boolean cat.set
                boolean discountable = Boolean.parseBoolean(dataArray[6]);
                cat.setDiscountable(discountable);
                boolean canBeReturned = Boolean.parseBoolean(dataArray[7]);
                cat.setCanBeReturned(canBeReturned);
                boolean isEBTFoodAllowed = Boolean.parseBoolean(dataArray[8]);
                cat.setIsEBTFoodAllowed(isEBTFoodAllowed);
                boolean isSurchargeEligible = Boolean.parseBoolean(dataArray[9]);
                cat.setIsSurchargeEligible(isSurchargeEligible);
                //Attribute Array
                cat.addToAttributes("RETURN_LIMIT_DAYS", "30");
                String key1 = dataArray[8];
                String value1 = dataArray[9];

                String ageToBuy = dataArray[10];
                cat.setAgeToBuy(ageToBuy);
                String taxes = dataArray[11];
                cat.addToProductTaxCodes(taxes);

                String version = dataArray[13];
                cat.setVersion(version);
                catalogItemsArrayList.add(cat);

            }
                myReader.close();
                JSONArray catalogJsonObj = getJsonObjectOrJsonArray(catalogItemsArrayList.get(0));
                FileWriter file = new FileWriter("catalogItems.json");
                //String json = catalogJsonObj.toJSONString();
                 Gson gson = new Gson();
                String json = gson.toJson(catalogItemsArrayList);

                System.out.println(json);
                file.write(json);
                file.close();


        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        };




    }
}