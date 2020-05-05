package com.lkf.web;

import com.lkf.pojo.Comment;
import com.lkf.pojo.User;
import com.lkf.service.BlogService;
import com.lkf.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author : lkf
 * @create : 2020-02-15 - 0:14
 * @describe:
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @Value("${admin.openid}")
    private String adminOpenid;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User loginStatus = (User) session.getAttribute("user");

        System.out.println("bbbbbbbbbbbbbbbb"+loginStatus);

            if(loginStatus!=null){
                comment.setAvatar(loginStatus.getAvatar());
                comment.setAdminComment(2);
                System.out.println("管理员身份登录111");
            }
        else {
            System.out.println("游客身份登录");
            comment.setAdminComment(0);
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

}
