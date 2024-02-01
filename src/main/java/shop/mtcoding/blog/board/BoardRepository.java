package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll(int page) {
        final int COUNT = 3; // 상수 선언 규칙 : 대문자
        int value = page * COUNT;
        Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?, ?", Board.class);
        query.setParameter(1, value);
        query.setParameter(2, COUNT);

        List<Board> boardList = query.getResultList();
        return boardList;
    }
    public int count() {
        Query query = em.createNativeQuery("select count(*) from board_tb");
        int count = ((Number)query.getSingleResult()).intValue();
        // 페이지 개수 리턴
        return count;
    }
}
