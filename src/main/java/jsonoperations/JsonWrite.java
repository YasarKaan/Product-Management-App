package jsonoperations;


import data.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import products.Assembly;
import products.Part;
import products.Product;
import users.User;
import java.io.FileWriter;
import java.io.IOException;


public class JsonWrite {
    public static void writeEmployee(){
        JSONArray staffList = new JSONArray();
        for(User user: Data.staffList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id",user.getUserId());
            jsonObject.put("Name",user.getUserName());
            jsonObject.put("Password", user.getPassword());
            jsonObject.put("User Type", user.getUserType().name());
            staffList.add(jsonObject);
        }
        try (FileWriter file = new FileWriter("employee.json")) {
            file.write(staffList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeProduct(){
        JSONArray productList = new JSONArray();
        for(Product product: Data.productList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id",product.getId());
            jsonObject.put("Name",product.getTitle());
            jsonObject.put("Created Admin Name",product.getCreatedAdmin().getUserName());
            jsonObject.put("Responsible Manager Name", product.getResponsibleManager().getUserName());
            jsonObject.put("Status",product.getStatus().getClass().getSimpleName());
            productList.add(jsonObject);
        }
        try (FileWriter file = new FileWriter("product.json")) {
            file.write(productList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeParts(){
        JSONArray partList = new JSONArray();
        for(Part part: Data.partsList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id",part.getId());
            jsonObject.put("Name",part.getTitle());
            jsonObject.put("Responsible Employee", part.getResponsibleEmployee().getUserName());
            jsonObject.put("Created Manager Name",part.getCreatedManager().getUserName());
            jsonObject.put("Parent material",part.getParent().getTitle());
            jsonObject.put("Status",part.getStatus().getClass().getSimpleName());
            partList.add(jsonObject);
        }
        try (FileWriter file = new FileWriter("part.json")) {
            file.write(partList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeAssembly(){
        JSONArray assemblyList = new JSONArray();
        for(Assembly assembly: Data.assembliesList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id",assembly.getId());
            jsonObject.put("Name",assembly.getTitle());
            jsonObject.put("Created Manager Name",assembly.getCreatedManager().getUserName());
            jsonObject.put("Parent material",assembly.getParent().getTitle());
            jsonObject.put("Status",assembly.getStatus().getClass().getSimpleName());
            assemblyList.add(jsonObject);
        }
        try (FileWriter file = new FileWriter("assembly.json")) {
            file.write(assemblyList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}