package com.eck.Smart.Agenda.repository;

import com.eck.Smart.Agenda.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID> {
    
    List<FileEntity> findByProjectIdAndParentIsNullOrderByTypeDescNameAsc(UUID projectId);
    
    List<FileEntity> findByParentIdOrderByTypeDescNameAsc(UUID parentId);
    
    List<FileEntity> findByProjectId(UUID projectId);
}
