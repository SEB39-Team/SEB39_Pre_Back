package com.codestates.pre047.member.entity;

import com.codestates.pre047.answer.entity.Answer;
import com.codestates.pre047.baseEntity.BaseTimeEntity;
import com.codestates.pre047.post.entity.Post;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Password can't be empty")
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "invalid email")
    @NotBlank(message = "Email can't be empty")
    private String email;

    private String roles;
    private String providerId;

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @Builder
    public Member(Long id, String username, String password, String email, String roles, String providerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.providerId = providerId;
    }

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    @OneToMany(mappedBy = "post")
    private List<Answer> answers;


}
