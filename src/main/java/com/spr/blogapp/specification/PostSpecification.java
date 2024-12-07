package com.spr.blogapp.specification;

import com.spr.blogapp.entity.Post;
import com.spr.blogapp.form.PostFilterForm;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class PostSpecification {
    public static Specification<Post> buildSpec(PostFilterForm form) {
        return form == null ? null : (Specification<Post>) (root, query, builder) -> {
            var predicates = new LinkedList<Predicate>();
            var search = form.getSearch();
            //WHERE title LIKE "%search%"
            if (StringUtils.hasText(search)) {
                var pattern = "%" + search.trim() + "%";
                var predicate = builder.like(root.get("title"), pattern);
                predicates.add(predicate);
            }

            var minCreatedDate = form.getMinCreatedDate();
            if (minCreatedDate != null) {
                var minCreatedAt = LocalDateTime.of(minCreatedDate, LocalTime.MIN);
                var predicate = builder.greaterThanOrEqualTo(root.get("createdAt"), minCreatedAt);
                predicates.add(predicate);
            }

            var maxCreatedDate = form.getMaxCreatedDate();
            if (maxCreatedDate != null) {
                var maxCreatedAt = LocalDateTime.of(maxCreatedDate, LocalTime.MAX);
                var predicate = builder.lessThanOrEqualTo(root.get("createdAt"), maxCreatedAt);
                predicates.add(predicate);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
