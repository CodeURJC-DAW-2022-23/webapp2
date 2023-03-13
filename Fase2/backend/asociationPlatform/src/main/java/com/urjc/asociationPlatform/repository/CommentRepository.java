package com.urjc.asociationPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.urjc.asociationPlatform.model.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
