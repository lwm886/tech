package com.tech.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.UnaryOperator;

/**
 * @author lw
 * @since 2023-04-06
 */
public class AtomicReferenceFieldUpdaterRunner {
    static AtomicReferenceFieldUpdater ato=AtomicReferenceFieldUpdater.newUpdater(Student.class,String.class,"name");

    public static void main(String[] args) {
        Student student = new Student("A");
        boolean b = ato.compareAndSet(student, "A", "a");
        System.out.println(b);
        System.out.println(ato.get(student));
        ato.getAndSet(student,"1");
        System.out.println(ato.get(student));

        UnaryOperator<String> uo=k->{
            System.out.println("k="+k);
            return "aaaaaa";
        };
        Object o = ato.getAndUpdate(student, uo);
        System.out.println("v="+o);
        System.out.println(ato.get(student));
    }
}

@AllArgsConstructor
@Data
class Student{
    public volatile String name;
}
