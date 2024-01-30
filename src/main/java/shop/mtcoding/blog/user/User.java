package shop.mtcoding.blog.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity // ?
@Table(name = "user_tb")
public class User {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private int id;

    @Column(unique = true) // UNIQUE
    private String username;

    @Column(length = 60, nullable = false) // >60, NOT NULL
    private String password;
    private String email;

    @CreationTimestamp // 쿼리문 전송시에 자동 삽입
    private LocalDateTime createdAt;
}
