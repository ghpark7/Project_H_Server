package ureca.team5.handicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ureca.team5.handicine.dto.CommentDTO;
import ureca.team5.handicine.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/board/{post_id}/comments")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 특정 게시글의 댓글 전체 조회
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllCommentsForPost(@PathVariable("post_id") Long post_id) {
        List<CommentDTO> comments = commentService.getCommentsByPostId(post_id);
        return ResponseEntity.ok(comments);
    }

    // 댓글 작성
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@PathVariable("post_id") Long post_id, @RequestBody CommentDTO commentDTO) {
    	CommentDTO createdComment = commentService.createComment(post_id, commentDTO);
        return ResponseEntity.ok(createdComment);
    }

    // 댓글 수정
    @PatchMapping("/{comment_id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("comment_id") Long comment_id, @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.updateComment(comment_id, commentDTO);
        return ResponseEntity.ok(updatedComment);
    }

    // 댓글 삭제
    @DeleteMapping("/{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("comment_id") Long comment_id) {
        commentService.deleteComment(comment_id);
        return ResponseEntity.noContent().build();
    }
}