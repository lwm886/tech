package com.tech.ot;

import com.tech.ot.view.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechOtApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechOtApplication.class, args);
        try{
            new NettyServer(8886).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
