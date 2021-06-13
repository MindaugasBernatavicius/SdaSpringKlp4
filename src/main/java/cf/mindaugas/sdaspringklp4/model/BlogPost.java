package cf.mindaugas.sdaspringklp4.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
//@AllArgsConstructor
@Slf4j
public class BlogPost {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private @NonNull String author;
    private @NonNull String post;

    //public BlogPost(){
    //    //log.error("Inside the constructor");
    //}
}

