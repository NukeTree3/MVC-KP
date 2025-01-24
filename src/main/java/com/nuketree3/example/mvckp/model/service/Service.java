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
import java.util.ArrayList;
import java.util.Arrays;


@org.springframework.stereotype.Service
public class Service {
    private final PostgreSQLConnections connections;
    private final FileRead fileRead;
    private String keyFile;

    public Service(){
        connections = new PostgreSQLConnections("jdbc:postgresql://localhost:5432/MVCDB", "postgres", "12345");
        fileRead = new FileRead();
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
        ResultSet resultSet = getFromDB("SELECT count FROM storage WHERE product_id = " + nameOfProduct);
        assert resultSet != null;
        return resultSet.getInt("count");
    }

    public boolean findLoginUser(String login) throws SQLException {
        ResultSet resultSet = getFromDB("SELECT * FROM purchaser WHERE login = '" + login + "'");
        return resultSet != null;
    }

    public int addPurchaser(User user) throws SQLException {
        if(!findLoginUser(user.getEmail())){
            return updateDB("INSERT INTO purchaser (login, password, first_name, last_name, birthdate, phone_number) VALUES " + user.getEmail() +", "+
                    user.getPassword()+", " + user.getFirstName() +", "+ user.getLastName()+", "+user.getBirthday() +", "+user.getPhone());
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

    /*
    переделать storage(добавить каждому продукту тип)
    вытаскивать в методе пару значений: имя и тип
    передавать это все в getProduct
     */
    public ArrayList<String> productIdFromStorage() throws SQLException {
        ResultSet resultSet = getFromDB("SELECT product_id FROM storage");
        ArrayList<String> productIds = new ArrayList<>();
        if(resultSet!=null){
            while(resultSet.next()){
                productIds.add(resultSet.getString("product_id"));
            }
        }
        return productIds;
    }

//    public Product getProduct(String name) throws SQLException{
//        ResultSet resultSet = getFromDB("SELECT * FROM laptop WHERE laptop_name = '"+name+"' UNION ALL SELECT * FROM pc WHERE pc_name = '"+name+"';");
//        if(resultSet!=null){
//            while(resultSet.next()){
//                return new Product();
//            }
//        }
//    }

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
