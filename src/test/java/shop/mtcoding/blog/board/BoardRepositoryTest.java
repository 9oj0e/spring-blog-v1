package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardRepository.class)
// 메모리에 띄워준다.
@DataJpaTest
// jpa가 DB관련된 애들을 띄워준다.
public class BoardRepositoryTest {
    @Autowired
    // 생성자 주입도 자동
    private BoardRepository boardRepository;
    @Test
    public void findById_test(){

    }
}
