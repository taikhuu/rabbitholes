package com.kms.challenges.rbh.repository;

import com.kms.challenges.rbh.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tkhuu.
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
