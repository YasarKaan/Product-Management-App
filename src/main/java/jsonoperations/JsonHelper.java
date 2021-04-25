package jsonoperations;

import data.Data;

public class JsonHelper {
    public static void read() {
        Data.staffList = JsonRead.readEmployee();
        Data.productList = JsonRead.readProduct();
        Data.assembliesList = JsonRead.readAssembly();
        Data.partsList = JsonRead.readPart();

    }

    public static void write(){
        JsonWrite.writeProduct();
        JsonWrite.writeAssembly();
        JsonWrite.writeEmployee();
        JsonWrite.writeParts();
    }
}
