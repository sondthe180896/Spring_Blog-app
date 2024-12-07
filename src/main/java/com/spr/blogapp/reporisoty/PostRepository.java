package com.spr.blogapp.reporisoty;

import com.spr.blogapp.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
// Gồm CRUD

//Spring data JPA
//Cách 1: Method names
//Nhược điểm: tên phương thức dài và phải gõ đúng từng ksy tự theo quy tắc
//findBy, countBy, existBy, deleteBy + property của entity(title,description,...)
    void deleteByTitle(String title);
    boolean existsByTitle(String title);

    Page<Post> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);

//Cách 2: @Query annotation
//Hibernate Query Language: HQL, câu lệnh truy xuất theo đối tượng java
//    @Query("DELETE * FROM Post WHERE description = ?1 AND id = ?2")
//    void deleteByDescAndId(Long id, String description);
//    @Query("DELETE * FROM Post WHERE description = : description")
//    void deleteByDesc(@Param("description") String description);

//    Structured Query Languege: SQL
    @Query(value = "DELETE FROM post WHERE description = ?1", nativeQuery = true)
    @Modifying
    void deleteByDesc(String description);
//    Khi custom query làm thay đổi dữ liệu cần thêm 2 annotation
//    @Modifying     (Repository)
//    @Transactional (Service)
}
