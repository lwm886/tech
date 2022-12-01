package com.tech.jvm;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class TechJvmApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TechJvmApplication.class, args);
        BigDecimal b1=new BigDecimal(1.5).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal b2=new BigDecimal(11.1).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal b3=new BigDecimal(1.3).setScale(2,BigDecimal.ROUND_HALF_UP);
        User u1 = new User(b1);
        User u2 = new User(b2);
        User u3 = new User(b3);
        List<User> list=new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);
        System.out.println(list);
        list.sort(Comparator.comparing(User::getB,Comparator.nullsFirst(Comparator.naturalOrder())));
        System.out.println(list);
    }

}
@Data
class User{
    BigDecimal b;

    public User(BigDecimal b) {
        this.b = b;
    }
}