package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;
    public Board findById(int id){
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        // 객체 지향 쿼리를 작성하게 해준다. 실제 쿼리는 아니지만 쿼리처럼 작동한다.
        // join fetch == inner join, left/right join == outer join
        query.setParameter("id", id);
        Board board = (Board)query.getSingleResult();
        return board;
    }
}
