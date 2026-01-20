package com.eck.Smart.Agenda.repository;

import com.eck.Smart.Agenda.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    
    List<Task> findByUserIdOrderByOrderIndexAsc(UUID userId);
    
    List<Task> findByUserIdAndStatus(UUID userId, String status);
    
    List<Task> findByUserIdAndProjectId(UUID userId, UUID projectId);
}
