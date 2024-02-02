package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {
    // bt.id, bt.content, bt.title, bt.created_at, bt.user_id, ut.username
    /*
    java.lang.Integer
    java.lang.String
    java.lang.String
    java.sql.Timestamp
    java.lang.Integer
    java.lang.String
    */
    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Timestamp createdAt;
        private Integer userId;
        private String username;
    }
}
