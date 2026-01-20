package com.eck.Smart.Agenda.repository;

import com.eck.Smart.Agenda.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    
    List<Project> findByUserIdOrderByCreatedAtDesc(UUID userId);
    
    @Query("SELECT COUNT(f) FROM FileEntity f WHERE f.project.id = :projectId")
    long countFilesByProjectId(UUID projectId);
}
