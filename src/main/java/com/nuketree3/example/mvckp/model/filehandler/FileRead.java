package com.nuketree3.example.mvckp.model.filehandler;

import java.io.*;

public class FileRead implements Readable{

    @Override
    public String readKey(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/main/java/com/nuketree3/example/model/key/"+fileName+".txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        String key = (String) ois.readObject();
        ois.close();
        fis.close();
        return key;
    }
}
