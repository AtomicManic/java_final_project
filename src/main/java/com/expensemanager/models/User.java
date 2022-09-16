package com.expensemanager.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "user")
    private List<Category> categories;
}
