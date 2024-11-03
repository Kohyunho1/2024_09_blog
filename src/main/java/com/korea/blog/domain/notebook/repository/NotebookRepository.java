package com.korea.blog.domain.notebook.repository;

import com.korea.blog.domain.notebook.entity.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {
}
