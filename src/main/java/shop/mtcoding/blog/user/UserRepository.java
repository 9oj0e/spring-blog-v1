package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class UserRepository {
    private EntityManager em;

    public UserRepository(EntityManager em) {
        // entitymanager는 메모리에 띄운적이없는데 왜 생성이 가능한가?
        // JPM라이브러리를 쓰면, 메모리에 그냥 뜬다.
        // 앞으로 UserRepository는 EntityManager에 의존한다. (의존성 주입)
        this.em = em;
    }
    @Transactional
    public void save(UserRequest.JoinDTO requestDTO){
        // 기본기
        Query query = em.createNativeQuery("INSERT INTO user_tb (username, password, email) VALUES (?, ?, ?)");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());

        query.executeUpdate();
    }

    @Transactional
    public void saveV2(UserRequest.JoinDTO requestDTO){
        // with hibernate. 아직 때가 아니다.
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());

        em.persist(user);
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb WHERE username=? and password=?", User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());

        // 500 NoResultException 버그 수정
        try {
            User user = (User)query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    public User findByUsername(String username) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb WHERE username=?", User.class);
        query.setParameter(1, username);

        try {
            User user = (User)query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
