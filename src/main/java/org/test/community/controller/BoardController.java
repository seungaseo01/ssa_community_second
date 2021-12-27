package org.test.community.controller;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.test.community.model.Category;
import org.test.community.model.Comment;
import org.test.community.model.TotalBoard;
import org.test.community.model.User;
import org.test.community.repository.BoardRepository;
import org.test.community.repository.CategoryRepository;
import org.test.community.repository.CommentRepository;
import org.test.community.repository.LikesRepository;
import org.test.community.repository.UserRepository;
import org.test.community.service.LikesService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BoardRepository boardRepository;
    
    @Autowired
    LikesService likesService;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    LikesRepository likesRepository;
    
    @Autowired
    CommentRepository commentRepository;
    
    
//    카테고리 생성

    @PostMapping("/regCategory")
    public String regCategory(Category category){
        System.out.println("==============category==============="+category);
        categoryRepository.save(category);
        return "redirect:/";
    }

// 썸머 노트 file 컨트롤러
    @PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }



// 게시물 생성
    @PostMapping("/regBoard")
    @ResponseBody
    public String regBoard(TotalBoard board,Principal principal){
        System.out.println("==============category==============="+board);

   	 String username = principal.getName();
	 

   	 System.out.println("=============username================"+username);  	 
   	 int id =  userRepository.selectId(username); 
   	 
   	 User user = new User();
   	 user.setId(id);
   	 
   	 board.setUser(user);
        
     boardRepository.save(board);

        String test = "Success";
        return test;
    }


// 전체 게시물 리스트
    @GetMapping("/list")
    public String getList(Model model,
                          @PageableDefault Pageable pageable){
        int page = (pageable.getPageNumber()==0) ? 0 : (pageable.getPageNumber()-1);
        pageable = PageRequest.of(page, 15, Sort.by("bNo").descending());

        Page<TotalBoard> list = boardRepository.findAll(pageable);

        System.out.println("================list==============="+list);

        model.addAttribute("list", list);

        return "board/totalBoardList";
    }


// 게시물 상세페이지
    @GetMapping("/select")
    public String getBoardById(@RequestParam("bNo") int bNo, Model model){

    	//조회수 업데이트
        boardRepository.updateView(bNo);
        
        //게시글 조회
        System.out.println("======================="+bNo);
        Optional<TotalBoard> boardOne = boardRepository.findById(bNo);

        System.out.println("===============boardOne========"+boardOne.get());
        model.addAttribute("boardOne",boardOne.get());
        
        //좋아요 조회
        int likes = likesRepository.selectLikes(bNo);
        model.addAttribute("likes",likes);
        System.out.println("=======likes========"+ likes);
        
        //댓글 조회
        List<Comment> commentList = commentRepository.findByBNo(bNo);
        model.addAttribute("commentList",commentList);

        //Optional 에서 값을 빼올려면 get()써줘야함
        return "board/selectByBNo";
    }

// 수정 페이지
    @GetMapping("/update")
    public String goBoardUpdateForm(@RequestParam("bNo") int bNo, Model model){

        System.out.println("======================="+bNo);
        Optional<TotalBoard> boardOne = boardRepository.findById(bNo);

        System.out.println("===============boardOne========"+boardOne);
        model.addAttribute("boardOne",boardOne.get());

        //Optional 에서 값을 빼올려면 get()써줘야함
        return "board/updateBoardForm";
    }


// 게시물 수정
    @PostMapping("/updateBoard")
    public String boardUpdate(TotalBoard board,Principal principal){

        System.out.println("===========boardUpdate===========");

	  	 String username = principal.getName();
		 
	
	   	 System.out.println("=============username================"+username);  	 
	   	 int id =  userRepository.selectId(username); 
	   	 
	   	 User user = new User();
	   	 user.setId(id);
	   	 
	   	board.setUser(user);
        boardRepository.save(board);

        int bNo = board.getBNo();

        return "redirect:/board/select?bNo="+bNo;
    }


// 게시물 삭제
    @GetMapping("/delete")
    public String boardDelete(@RequestParam("bNo") int bNo){

        System.out.println("======================="+bNo);
        boardRepository.deleteById(bNo);

        //Optional 에서 값을 빼올려면 get()써줘야함
        return "redirect:/board/list";
    }
    
    


 // 좋아요
     @PostMapping("/likes")
     @ResponseBody
     public int  pushLikes(int bNo,Principal principal){
    	 
    	 System.out.println("=============bNo================"+bNo);
    	 String username = principal.getName();
    	 

    	 System.out.println("=============username================"+username);  	 
    	 int id =  userRepository.selectId(username); 
    	 System.out.println("=============id================"+id);
  	 
    	 likesService.pushLike(bNo, id);

         return id;
     }
     
     
 

     


}
