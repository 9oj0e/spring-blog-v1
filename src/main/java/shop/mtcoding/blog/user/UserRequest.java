package shop.mtcoding.blog.user;

import lombok.Data;

/**
 * 1. 요청 DTO = Data Transfer Object
 */
    // 왜 UserRequest내부에다 static class 선언하나.
    // 나중에 이름 관리하기 편하다.
    // UserRequest.JoinDTO
    // UserJoinRequestDTO 로 개별적으로 호출하는 것
public class UserRequest {
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;
    }
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

}
