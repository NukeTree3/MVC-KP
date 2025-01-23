package com.nuketree3.example.mvckp.model.filehandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Readable {
    String readKey(String fileName) throws IOException, ClassNotFoundException;
}
