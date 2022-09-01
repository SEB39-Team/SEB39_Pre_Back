package com.codestates.pre047.post.entity;

import com.codestates.pre047.baseEntity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;
    

    public Post(long postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
