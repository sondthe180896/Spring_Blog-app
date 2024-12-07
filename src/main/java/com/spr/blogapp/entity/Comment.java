package com.spr.blogapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity

@Table(name = "comment")

@Getter
@Setter
public class Comment {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "comment_id_generator", type = CommentIdGenerator.class)
    @GeneratedValue(generator = "comment_id_generator")
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 80, nullable = false)
    private String email;

    @Column(name = "body",length = 200,nullable = false)
    private String body;

    @Column(name = "status",nullable = false)
//    @Enumerated(value = EnumType.ORDINAL) luu theo thu tu 0 1 2 ...
//    @Enumerated(value = EnumType.STRING)
    @Convert(converter = CommentStatusConverter.class)
    private Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    public void setStatus(String status){
        this.status = Status.valueOf(status);
    }

    public enum Status {
//        0 1 2
        REVIEW,OPEN,CLOSE
    }
}
