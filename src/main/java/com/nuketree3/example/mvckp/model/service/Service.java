package com.nuketree3.example.mvckp.model.service;

import com.nuketree3.example.mvckp.model.connections.PostgreSQLConnections;
import com.nuketree3.example.mvckp.model.filehandler.FileRead;
import com.nuketree3.example.mvckp.model.product.Laptop;
import com.nuketree3.example.mvckp.model.product.PersonalComputer;
import com.nuketree3.example.mvckp.model.product.Product;
import com.nuketree3.example.mvckp.model.user.User;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


@org.springframework.stereotype.Service
public class Service {
    private final PostgreSQLConnections connections;
    private final FileRead fileRead;
    private String keyFile;
//    private final ArrayList<String> tables;

    public Service(){
        connections = new PostgreSQLConnections("jdbc:postgresql://localhost:5432/MVCDB", "postgres", "12345");
        fileRead = new FileRead();
//        tables = new ArrayList<>();
//        tables.add("laptop");
//        tables.add("pc");
    }

    private int updateDB(String string) throws SQLException {
        Connection connection = connections.connect();
        int resultSet = 0;
        if(connection!=null){
            Statement statement = connection.createStatement();
            resultSet = statement.executeUpdate(string);
        }
        assert connection != null;
        connection.close();
        return resultSet;
    }

    public ArrayList<PersonalComputer>  getPersonalComputerFromDB() throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM pc");
        ArrayList<PersonalComputer> pc = new ArrayList<>();
        if(resultSet != null){
            while(resultSet.next()){
                pc.add(new PersonalComputer(resultSet.getString("pc_name"), resultSet.getString("producer"), Integer.getInteger(resultSet.getString("price")), resultSet.getString("processor_model"),
                        Integer.getInteger(resultSet.getString("RAM"))));
            }
        }
        connections.closeConnection();
        return pc;
    }

    public ArrayList<Laptop>  getLaptopsFromDB() throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM laptop");
        ArrayList<Laptop> laptops = new ArrayList<>();
        if(resultSet != null){
            while(resultSet.next()){
                laptops.add(new Laptop(resultSet.getString("laptop_name"), resultSet.getString("producer"), Integer.getInteger(resultSet.getString("price")
                ), resultSet.getString("processor_model"), Integer.getInteger(resultSet.getString("RAM"))));
            }
        }
        connections.closeConnection();
        return laptops;
    }

    public int getCountProductFromStorage(String nameOfProduct) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT count FROM storage WHERE product_name = '" + nameOfProduct+"'");
        assert resultSet != null;
        return resultSet.getInt("count");
    }

    public boolean findLoginUser(String login) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM purchaser WHERE login = '" + login + "'");
        assert resultSet != null;
        System.out.println(resultSet.next());
        return resultSet.next();
    }

    public int addPurchaser(User user) throws SQLException {
        if(!findLoginUser(user.getEmail())){
            System.out.println("INSERT INTO purchaser (login, password, first_name, last_name, birthdate, phone_number) VALUES ('" + user.getEmail() +"', '"+
                    user.getPassword()+"', '" + user.getFirstName() +"', '"+ user.getLastName()+"', '"+user.getBirthday() +"', '"+user.getPhone()+"');");
            return updateDB("INSERT INTO purchaser (login, password, first_name, last_name, birthdate, phone_number) VALUES ('" + user.getEmail() +"', '"+
                    user.getPassword()+"', '" + user.getFirstName() +"', '"+ user.getLastName()+"', '"+user.getBirthday() +"', '"+user.getPhone()+"');");
        }
        else return 0;
    }

    public boolean checkPassword(String login, String password) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM purchaser WHERE login = '" + login + "'");
        assert resultSet != null;
        return password.equals(resultSet.getString("password"));
    }

    private String getKeyFromFile() throws IOException, ClassNotFoundException {
        return fileRead.readKey(keyFile);
    }

    public boolean checkUser(User user) throws SQLException {
        return checkPassword(user.getPassword(), user.getPassword()) && !findLoginUser(user.getEmail());
    }

    public String crypt(String password) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(getKeyFromFile().getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(password.getBytes());
        return Arrays.toString(bytes);
    }

    public String decrypt(String password) throws IOException, ClassNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec key = new SecretKeySpec(getKeyFromFile().getBytes(), "AES");
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bytes = new byte[password.length()];
        Cipher decrypt =Cipher.getInstance("AES");
        decrypt.init(Cipher.DECRYPT_MODE,key);
        byte[] decriptBites = decrypt.doFinal(bytes);
        for (byte b : decriptBites){
            stringBuilder.append((char) b);
        }
        return stringBuilder.toString();
    }

    public User getUserFromDb(String email) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM purchaser WHERE login = '" + email + "'");
        assert resultSet != null;
        if(resultSet.next()){
            return new User(resultSet.getString("first_name"),resultSet.getString("last_name"),
                    resultSet.getString("login"),resultSet.getString("password"), LocalDate.parse(resultSet.getString("birthdate")),
                    resultSet.getString("phone_number"));
        }
        return null;
    }

    public String getPathPhotoFromDB(String nameOfProduct) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT path FROM photos WHERE product_id = '" + nameOfProduct+"'");
        if(resultSet != null){
            resultSet.getString("path");
        }
        return nameOfProduct;
    }
    
    public ArrayList<String> getName(ArrayList<ArrayList<String>> typeAndName){
        ArrayList<String> name = new ArrayList<>();
        for(ArrayList<String> f : typeAndName){
            name.add(f.get(0));
        }
        return name;
    }

    public ArrayList<String> getNames() throws SQLException{
        ArrayList<String> name = new ArrayList<>();
        ResultSet resultSet = getFromDB("SELECT product_name FROM storage");
        assert resultSet != null;
        if(resultSet.next()){
            while(resultSet.next()){
                name.add(resultSet.getString("product_name"));
            }
        }
        return name;
    }

    public String getImagePathFromDB(String nameOfProduct) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT path FROM photos WHERE product_id = '" + nameOfProduct+"'");
        assert resultSet != null;
        if(resultSet.next()){
            return resultSet.getString("path");
        }
        return null;
    }

    public ArrayList<Product> getProductList() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        for(String productName : getNames()){
            Product product = getProduct(productName);
            products.add(product);
        }
        return products;
    }


    public ArrayList<ArrayList<String>> productIdFromStorage() throws SQLException {
        ResultSet resultSet = getFromDB("SELECT product_name, type FROM storage");
        ArrayList<ArrayList<String>> productIds = new ArrayList<>();
        if(resultSet!=null){
            while(resultSet.next()){
                ArrayList<String> NameAndType = new ArrayList<>();
                NameAndType.add(resultSet.getString("product_name"));
                NameAndType.add(resultSet.getString("type"));
                productIds.add(NameAndType);
            }
        }
        return productIds;
    }

    public String getTypeFromDB(String nameOfProduct) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT type FROM storage WHERE product_name = '" + nameOfProduct+"'");
        assert resultSet != null;
        if(resultSet.next()) return resultSet.getString("type");
        return null;
    }

    public Product getProduct(String name) throws SQLException{
        String type = getTypeFromDB(name);
        ResultSet resultSet = getFromDB("SELECT * FROM " + type + " WHERE "+type+"_name = '" + name+"'");
        assert resultSet != null;
        if(resultSet.next()){
            if(type.equals("laptop")){
                Product product = new Laptop(name, resultSet.getString("producer"),
                        resultSet.getInt("price"),
                        resultSet.getString("processor_model"), resultSet.getInt("ram"));
                product.setImagePath(getImagePathFromDB(name));
                return product;
            }
            if(type.equals("pc")){
                Product product = new PersonalComputer(name, resultSet.getString("producer"),
                        resultSet.getInt("price"),
                        resultSet.getString("processor_model"), resultSet.getInt("ram"));
                product.setImagePath(getImagePathFromDB(name));
                return product;
            }
            else return null;
        }
        return null;
    }

    private ResultSet getFromDB(String query) throws SQLException {
        Connection connect = connections.connect();
        if(connect != null){
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(query);
            connections.getAllCloseConnectionResurses(connect, statement, result);
            return result;
        }
        else{
            return null;
        }
    }
}
