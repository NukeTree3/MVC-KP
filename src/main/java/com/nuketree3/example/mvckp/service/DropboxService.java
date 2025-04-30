package com.nuketree3.example.mvckp.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class DropboxService {

    @Autowired
    private DbxClientV2 client;

    @Value("${dropbox.imgPath}")
    private String DROPBOX_FOLDER;

    public byte[] downloadFile(String dropboxPath) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            System.out.println(DROPBOX_FOLDER + dropboxPath);
            client.files().download(DROPBOX_FOLDER + dropboxPath).download(outputStream);
            return outputStream.toByteArray();
        }
        catch (IOException | DbxException e) {
            return new byte[0];
        }
    }
}
