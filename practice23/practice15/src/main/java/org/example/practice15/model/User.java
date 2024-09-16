package org.example.practice15.model;

import javax.persistence.*;

@Entity
@Table(name = "users") // Название таблицы, чтобы избежать конфликта с зарезервированным словом 'user'
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Уникальное имя пользователя
    private String username;

    private String password;

    // Конструкторы

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    // Нет сеттера для id, так как оно генерируется автоматически

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Переопределение методов equals и hashCode (опционально)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
