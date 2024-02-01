package shop.mtcoding.blog;

import org.junit.jupiter.api.Test;
import shop.mtcoding.blog._core.Constant;

public class PagingTest {
    @Test
    public void count() {
        int totalCount = 7;
        int pagingCount = 3;

        // 1. 나머지 여부 확인
        int remainCount = totalCount % pagingCount;
        // System.out.println(remainCount);
        int totalPageCount = totalCount / pagingCount;
        // 2. 나머지가 있다면
        if (remainCount > 0) {
            totalPageCount = totalPageCount + 1;
        }
        System.out.println("토탈페이지 : " + totalPageCount);
    }
}
