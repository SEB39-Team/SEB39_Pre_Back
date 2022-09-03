package com.codestates.pre047.answer.entity;


import com.codestates.pre047.post.entity.Post;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    
    /* member 추가 후 멤버 추가*/
}
