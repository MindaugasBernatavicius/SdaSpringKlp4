package cf.mindaugas.sdaspringklp4.controller;

import cf.mindaugas.sdaspringklp4.model.BlogPost;
import cf.mindaugas.sdaspringklp4.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class BlogPostRESTController {

    // imitating a database
    //Map<String, List<String>> posts = new HashMap<>(){{
    //    put("1", Arrays.asList("Mindaugas", "Hello world blog post!"));
    //    put("2", Arrays.asList("Romas", "Karbauskio dienoraštis"));
    //    put("3", Arrays.asList("Bromas", "Karbauskio dienoraštis"));
    //}};

    @Autowired
    private BlogPostRepository bpr;

    // get all posts or some by author name :: http://localhost:8081/api/posts?author_name=Jonas
    //@RequestMapping(method = RequestMethod.GET, path = "/posts")
    @GetMapping(path = "/posts")
    public Iterable<BlogPost> getAllPosts(@RequestParam(value = "author_name", required = false) String authorName) {
        return authorName == null
                ? bpr.findAll()
                : bpr.findBlogPostsByAuthorContainsOrderByPost(authorName).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // get single post :: http://localhost:8081/api/posts/1
    @RequestMapping(method = RequestMethod.GET, path = "/posts/{id}")
    public BlogPost getPost(@PathVariable Integer id) {
        //System.err.println("getPost /posts/{" + id + "} hit");
        //System.err.println(posts.get(id.toString())); // no error handling
        return bpr.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)); // 404
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
            path = "/posts",
            //consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE // author=svsv&text=sgdgd
            consumes = MediaType.APPLICATION_JSON_VALUE // { "author": "svsv" , "text" : "sgdgd" }
    )
    // Because we changed the media type, we now need to get the
    // ... data from @RequestBody (... or use Jackson library
    // ... with @JsonField("first") to deserialize the incoming request)
    // ... see more: https://stackoverflow.com/questions/7312436/spring-mvc-how-to-get-all-request-params-in-a-map-in-spring-controller
    public ResponseEntity<String> addPost(
            //@RequestParam("author") String author,    // ... if APPLICATION_FORM_URLENCODED_VALUE
            //@RequestParam("post") String post)        // ... if APPLICATION_FORM_URLENCODED_VALUE
            //@RequestBody BlogPost post)               // ... if APPLICATION_JSON_VALUE
            @RequestBody Map<String, String> params) {
        //bpr.save(new BlogPost(author, post));                     // ... if APPLICATION_FORM_URLENCODED_VALUE
        //bpr.save(post);                                           // ... if APPLICATION_JSON_VALUE
        bpr.save(new BlogPost(params.get("author"), params.get("post"))); // ... if APPLICATION_JSON_VALUE + param map
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    //// delete post
    //@RequestMapping(method = RequestMethod.DELETE, path="/posts/{id}")
    //public Map deletePost(@PathVariable String id) {
    //    posts.remove(id);
    //    return posts;
    //}

    //@RequestMapping(method = RequestMethod.DELETE, path="/posts/{id}")
    @DeleteMapping(path = "/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        try {
            bpr.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

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

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/posts/{id}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<Void> updatePost(
            @PathVariable Integer id,
            @RequestParam("author") String author,
            @RequestParam("post") String post) {
        BlogPost blogPost = bpr.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)); // 404
        blogPost.setAuthor(author);
        blogPost.setPost(post);
        bpr.save(blogPost);
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }
}