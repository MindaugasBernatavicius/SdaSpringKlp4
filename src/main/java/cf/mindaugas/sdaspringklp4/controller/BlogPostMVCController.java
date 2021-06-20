package cf.mindaugas.sdaspringklp4.controller;

import cf.mindaugas.sdaspringklp4.model.BlogPost;
import cf.mindaugas.sdaspringklp4.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mvc")
public class BlogPostMVCController {

    @Autowired
    private BlogPostRepository bpr;

    // Returns the hello.html, not a string.
    // ... this is the difference between @RestContoller and @Controller
    @RequestMapping("/hello")
    public String greeting(Model model, @RequestParam(required = false) String name){
        model.addAttribute("name_in_view", name);
        //return "hello.html"; // return the view by name
         return "hello"; // return the view by name
    }

    @GetMapping(path = "/posts")
    public String getAllPosts(Model model, @RequestParam(value = "author_name", required = false) String authorName) {
        List<BlogPost> posts;
        if(authorName == null)
            posts = bpr.findAll();
        else
            posts = bpr.findBlogPostsByAuthorContainsOrderByPost(authorName).get();
        model.addAttribute("posts", posts);
        return "blogposts/blogposts";
    }
}
