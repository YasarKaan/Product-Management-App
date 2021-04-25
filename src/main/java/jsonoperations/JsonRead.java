package jsonoperations;


import data.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import products.*;
import status.Complete;
import status.IStatus;
import status.InProgress;
import status.NotStarted;
import users.User;
import users.UserPriorities;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonRead {
    public static ArrayList<User> readEmployee(){
        JSONParser parser = new JSONParser();
        ArrayList<User> arrayList=new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader("employee.json"));
            JSONArray employeeList = (JSONArray) obj;
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ,arrayList) );
        } catch (ParseException | IOException e) {
            System.out.println(e.getMessage());
        }
        return arrayList;
    }
    public static ArrayList<Product> readProduct(){
        JSONParser parser = new JSONParser();
        ArrayList<Product> arrayList=new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader("product.json"));
            JSONArray productList = (JSONArray) obj;
            productList.forEach( prd -> parseProductObject( (JSONObject) prd ,arrayList) );
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return arrayList;
    }
    public static ArrayList<Part> readPart(){
        JSONParser parser = new JSONParser();
        ArrayList<Part> arrayList=new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader("part.json"));
            JSONArray productList = (JSONArray) obj;
            productList.forEach( prd -> parsePartObject( (JSONObject) prd ,arrayList) );
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    public static ArrayList<Assembly> readAssembly(){
        JSONParser parser = new JSONParser();
        ArrayList<Assembly> arrayList=new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader("assembly.json"));
            JSONArray assemblyList = (JSONArray) obj;
            assemblyList.forEach( prd -> parseAssemblyObject( (JSONObject) prd ,arrayList) );
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    private static void parseAssemblyObject(JSONObject assemblyObject, ArrayList<Assembly> arrayList) {
        int id=Integer.parseInt(String.valueOf(assemblyObject.get("Id")));
        String name = (String)assemblyObject.get("Name");
        String username=(String) assemblyObject.get("Created Manager Name");
        String parent =(String) assemblyObject.get("Parent material");
        String status=(String) assemblyObject.get("Status");
        Assembly assembly = new Assembly();
        assembly.setId(id);
        assembly.setTitle(name);
        assembly.setStatus(statusHelper(status));
        assembly.setParent(parentHelper(parent,assembly));
        assembly.setCreatedManager(getUser(username));
        arrayList.add(assembly);
    }

    private static void parsePartObject(JSONObject partObject, ArrayList<Part> arrayList){
        int id=Integer.parseInt(String.valueOf(partObject.get("Id")));
        String name = (String) partObject.get("Name");
        String userName=(String) partObject.get("Created Manager Name");
        String employeeName =(String) partObject.get("Responsible Employee");
        String status=(String) partObject.get("Status");
        String parent=(String) partObject.get("Parent material");
        Part part = new Part();
        part.setId(id);
        part.setCreatedManager(getUser(userName));
        part.setResponsibleEmployee(getUser(employeeName));
        part.setParent(parentHelper(parent,part));
        part.setTitle(name);
        part.setStatus(statusHelper(status));
        arrayList.add(part);
    }

    private static void parseProductObject(JSONObject productObject, ArrayList<Product> arrayList){
        int id=Integer.parseInt(String.valueOf(productObject.get("Id")));
        String name = (String) productObject.get("Name");
        String adminName = (String) productObject.get("Created Admin Name");
        String managerName = (String) productObject.get("Responsible Manager Name");
        String status=(String) productObject.get("Status");
        Product product = new Product();
        product.setId(id);
        product.setTitle(name);
        product.setCreatedAdmin(getUser(adminName));
        product.setResponsibleManager(getUser(managerName));
        product.setStatus(statusHelper(status));
        arrayList.add(product);
    }

    private static void parseEmployeeObject(JSONObject employeeObject, ArrayList<User> arrayList) {
        int id=Integer.parseInt(String.valueOf(employeeObject.get("Id")));
        String name = (String) employeeObject.get("Name");
        String password = (String)employeeObject.get("Password");
        String userType=(String) employeeObject.get("User Type");
        if (userType.equals("ADMIN")) {
            User user = new User(id, name, password, UserPriorities.ADMIN);
            arrayList.add(user);
        } else if (userType.equals("MANAGER")) {
            User user = new User(id, name, password, UserPriorities.MANAGER);
            arrayList.add(user);
        } else {
            User user = new User(id, name, password, UserPriorities.EMPLOYEE);
            arrayList.add(user);
        }

    }
    private static Warehouse parentHelper(String parent,ICombined combinedObj){
        for(Product product : Data.productList){
            if(product.getTitle().equals(parent)){
                product.getCombinedList().add(combinedObj);
                return product;
            }
        }
        for(Assembly assembly: Data.assembliesList){
            if(assembly.getTitle().equals(parent)){
                assembly.getProducedList().add(combinedObj);
                return assembly;
            }
        }
        return null;
    }
    private static User getUser(String userName) {
        for (User user : Data.staffList) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }


    private static IStatus statusHelper(String status){
        if (status.equals("NotStarted")) {
            return new NotStarted();
        } else if (status.equals("InProgress")) {
            return new InProgress();
        } else {
            return new Complete();
        }
    }

}