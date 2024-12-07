package com.spr.blogapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity

@Getter
@Setter

@Table(name = "post")
public class Post {
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto tăng id
    @Column(name = "id")
    private Long id;

    @Column(name = "title",length = 100,nullable = false)
    private String title;

    @Column(name = "description",length = 100,nullable = false)
    private String description;

    @Column(name = "content", length = 200,nullable = false)
    private String content;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> comments;

}
