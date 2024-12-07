package com.spr.blogapp.specification;

import com.spr.blogapp.entity.Comment;
import com.spr.blogapp.form.CommentFilterForm;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.LinkedList;

public class CommentSpecification {
    public static Specification<Comment> buildSpec(CommentFilterForm form) {
        return form == null ? null : (Specification<Comment>) (root, query, builder) -> {
            var predicates = new LinkedList<Predicate>();

            var search = form.getSearch();
            if (StringUtils.hasText(search)) {
                var pattern = "%" + search.trim() + "%";
                var predicate = builder.like(root.get("body"), pattern);
                predicates.add(predicate);
            }

            var postId = form.getPostId();
            if (postId != null) {
                var predicate = builder.equal(root.get("post").get("id"), postId);
                predicates.add(predicate);
            }

            var status = form.getStatus();
            if (status != null) {
                var predicate = builder.equal(root.get("status"), status);
                predicates.add(predicate);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
