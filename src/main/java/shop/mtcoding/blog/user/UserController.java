package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



/**
 * 1. 요청받기 (URL)
 * 2. http body는 어떻게? (DTO)
 * 3. 기본 mime 전략 : x-www-form-urlencoded (username=ssar&password=1234)
 * 4. 유효성 검사하기 (body 데이터가 있다면)
 * 5. 클라이언트가 view만 원하는지? 혹은 db처리 후 view도 원하는지?
 * 6. view만 원하면 view를 응답하면 끝
 * 7. DB처리를 원하면 Model(DAO)에게 위임 후 view를 응답하면 끝
 */

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/login") // login만 예외로 post 요청
    public String login(UserRequest.LoginDTO requestDTO) {
        System.out.println(requestDTO);
        // 1. 유효성 검사
        if (requestDTO.getUsername().length() < 3)
            return "error/400";

        // 2. model 연결 (위임)
        User user = userRepository.findByUsernameAndPassword(requestDTO);
        if (user == null)
            return "error/401";
        else {
            session.setAttribute("sessionUser", user); // hash 구조
            return "redirect:/";
        }
        // 3. 응답
    }

    @PostMapping("/join") // mvc pattern
    public String join(UserRequest.JoinDTO requestDTO) {
        System.out.println(requestDTO);
        // 1. 유효성 검사
        if (requestDTO.getUsername().length() < 3)
            return "error/400";

        // 2. 동일 username 체크
        User user = userRepository.findByUsername(requestDTO.getUsername());
        if (user == null){
            // 3. model에 위임하기
            userRepository.save(requestDTO);
        } else {
            return "error/400";
        }
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm") // cv
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm") // cv
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm") // cv
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout") // cv
    public String logout() {
        // session.setAttribute(); 클라이언트가 키를 버린다.
        session.invalidate(); // 클라이언트는 키를 가지고 있으나, 서랍 자체를 없애버린다.
        return "redirect:/";
    }
}
