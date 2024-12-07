package com.spr.blogapp.entity;

import jakarta.persistence.AttributeConverter;

public class CommentStatusConverter
        implements AttributeConverter<Comment.Status, Character> {

    @Override
    public Character convertToDatabaseColumn(Comment.Status status) {
        return status.toString().charAt(0);
    }

    @Override
    public Comment.Status convertToEntityAttribute(Character code) {
        if (code == null) return null;
        switch (code) {
            case 'R': return Comment.Status.REVIEW;
            case 'O': return Comment.Status.OPEN;
            case 'C': return Comment.Status.CLOSE;
            default: throw new IllegalArgumentException("Unknown code: " + code);
        }
    }
}
