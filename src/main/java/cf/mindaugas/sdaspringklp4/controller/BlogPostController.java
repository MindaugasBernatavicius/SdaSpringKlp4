package cf.mindaugas.sdaspringklp4.controller;

import cf.mindaugas.sdaspringklp4.model.BlogPost;
import cf.mindaugas.sdaspringklp4.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BlogPostController {
    // imitating a database
    //Map<String, List<String>> posts = new HashMap<>(){{
    //    put("1", Arrays.asList("Mindaugas", "Hello world blog post!"));
    //    put("2", Arrays.asList("Romas", "Karbauskio dienoraštis"));
    //    put("3", Arrays.asList("Bromas", "Karbauskio dienoraštis"));
    //}};

    @Autowired
    private BlogPostRepository bpr;

    //@RequestMapping(method = RequestMethod.GET, path = "/posts")
    @GetMapping(path = "/posts")
    public Iterable<BlogPost> getAllPosts(){
        return bpr.findAll();
    }

    // get single post :: http://localhost:8081/api/posts/1
    @RequestMapping(method = RequestMethod.GET, path="/posts/{id}")
    public BlogPost getPost(@PathVariable Integer id) {
        System.err.println("getPost /posts/{" + id + "} hit");
        //System.err.println(posts.get(id.toString()));
        return bpr.findById(id).get();
    }

    //// get single post :: http://localhost:8081/api/posts_query?id=4
    //@RequestMapping(method = RequestMethod.GET, path="/posts_query")
    //public Map getPost(@RequestParam String id) {
    //    System.err.println("getPost /posts/{" + id + "} hit");
    //    return new HashMap<String, List<String>>(){{
    //        put(id, posts.get(id)); }};
    //}

    // add post
    @RequestMapping(
            method = RequestMethod.POST,
            path="/posts",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<String> addPost(
                       @RequestParam("author") String author,
                       @RequestParam("post") String post) {
        bpr.save(new BlogPost(author, post));
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    //// delete post
    //@RequestMapping(method = RequestMethod.DELETE, path="/posts/{id}")
    //public Map deletePost(@PathVariable String id) {
    //    posts.remove(id);
    //    return posts;
    //}
    //
    //// update post
    //// Note the usage of @PathVariable instead of @RequestParam.
    //// ... In reality you would use either one or the other, this is just for illustration that they can both be used
    //@RequestMapping(
    //        method = RequestMethod.PUT,
    //        path="/posts/{id}",
    //        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    //)
    //public Map updatePost(@PathVariable String id,
    //                      @RequestParam("author") String author,
    //                      @RequestParam("post") String post) {
    //    posts.put(id, Arrays.asList(author, post));
    //    return posts;
    //}
}