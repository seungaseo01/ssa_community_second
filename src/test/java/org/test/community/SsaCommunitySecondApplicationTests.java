package org.test.community;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.community.model.Category;
import org.test.community.model.TotalBoard;
import org.test.community.repository.BoardRepository;

@SpringBootTest
class SsaCommunitySecondApplicationTests {
	
	@Autowired
	BoardRepository boardRepository;

	@Test
	void contextLoads() {
	}
	
    @Test
    public void insertBoardTest(){

        for(int i=0; i<100; i++){
            TotalBoard board = new TotalBoard();
            
            Category category = new Category();

            category.setCNo(1);
            category.setCName("test");
            
            
            board.setCategory(category);
            board.setBDivide("");
            board.setBTitle("Sample title"+i);
            board.setBWriter("writer"+i);
            board.setBContent("Sample content"+i);

            boardRepository.save(board);
        }
}
}
