package cf.mindaugas.sdaspringklp4;

import cf.mindaugas.sdaspringklp4.model.BlogPost;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Controller
@SpringBootApplication
public class SdaSpringKlp4Application {
    //@RequestMapping("/") // localhost:8080/
    //public @ResponseBody String root() { // @ResponseBody convert string to HTTP response
    //    return "<h1 style='color: red'>Hello world!</h1>";
    //}

    public static void main(String[] args) {
        SpringApplication.run(SdaSpringKlp4Application.class, args);

        //BlogPost bp = new BlogPost(1, "Jonas", "Labai geras postas!");
        //System.out.println(bp.getPost());
        //bp.setPost("Labai blogas blogpostas!");
        //System.out.println(bp.getPost());
        //System.out.println(bp);
        //
        //BlogPost bp2 = new BlogPost();
        //System.out.println(bp2.getPost());
        //bp2.setPost("Labai blogas blogpostas!");
        //System.out.println(bp2.getPost());
    }
}