package com.example.socketdemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document
public class User {
    @Id
    public String id;
    public String name;
    public Integer age;

    public User( String name, Integer age) {

        this.name = name;
        this.age = age;
    }
}
