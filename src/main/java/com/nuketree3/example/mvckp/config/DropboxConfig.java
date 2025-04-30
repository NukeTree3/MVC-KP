package com.nuketree3.example.mvckp.config;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DropboxConfig {

    @Value("${dropbox.accessToken}")
    private String accessToken;

    @Value("${dropbox.dropboxName}")
    private String dropboxName;

    @Bean
    public DbxRequestConfig dbxRequestConfig() {
        return DbxRequestConfig.newBuilder(dropboxName).build();
    }

    @Bean
    public DbxClientV2 dbxClient(DbxRequestConfig dbxRequestConfig) {
        return new DbxClientV2(dbxRequestConfig, accessToken);
    }

}
