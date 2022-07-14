package com.example.test.bl;

import android.content.res.AssetManager;

import com.example.test.MainActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class IOAccess {
//id,name,serving_size,calories
    public static List<Food> readFoodFromFile(){
       List<Food> foods=new LinkedList<>();
        BufferedReader br =null;
        String[] line;

        try{
            br= new BufferedReader(new FileReader("app/src/main/assets/nutrition.csv"));
            br.skip(1);
            while((br.readLine())!=null){
                int amount;
                double calories;
                String name;
                line=br.readLine().split(",");

                if(!line[2].contains("100 g"))
                {
                    if(!line[3].contains("100 g")) {
                       if(!line[4].contains("100g"))
                       {
                           name = line[1] + line[2]+line[3]+line[4];
                           name = name.replace("\"", "");
                           System.out.println(name);
                           amount = Integer.parseInt(line[5].replace(" g", ""));
                           calories = Double.parseDouble(line[6]);
                       }
                       else {
                           name = line[1] + line[2] + line[3];
                           name = name.replace("\"", "");
                           System.out.println(name);
                           amount = Integer.parseInt(line[4].replace(" g", ""));
                           calories = Double.parseDouble(line[5]);
                       }
                    }

                    else {
                        name = line[1] + line[2];
                        name = name.replace("\"", "");
                        System.out.println(name);
                        amount = Integer.parseInt(line[3].replace(" g", ""));
                        calories = Double.parseDouble(line[4]);
                    }

                }
                else {
                    name=line[1];
                    amount=Integer.parseInt(line[2].replace(" g", ""));
                    calories=Double.parseDouble(line[3]);
                }
                //foods.add(new Food(name,amount,calories));
            }
            br.close();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return foods;
    }

    public static void main(String[] args) {
        System.out.println(readFoodFromFile());
    }

}
