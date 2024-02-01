package shop.mtcoding.blog._core;

public class PagingUtil {
    public static boolean isFirst(int currentPage) {
        return currentPage == 0 ? true : false;
    }
    public static boolean isLast(int currentPage, int totalCount){
        int totalPageCount = getTotalPageCount(totalCount);
        return currentPage == totalPageCount - 1 ? true : false;
    }
    // 책임 : 전체 페이지 개수 리턴
    public static int getTotalPageCount(int totalCount){

        // 1. 나머지 여부 확인
        int remainCount = totalCount % Constant.PAGING_COUNT;
        // System.out.println(remainCount);
        int totalPageCount = totalCount / Constant.PAGING_COUNT;

        // 2. 나머지가 있다면
        if (remainCount > 0) {
            totalPageCount = totalPageCount + 1;
        }
        return totalPageCount;
    }
}
