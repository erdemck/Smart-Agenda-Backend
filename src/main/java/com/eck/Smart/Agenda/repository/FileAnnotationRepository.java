package com.eck.Smart.Agenda.repository;

import com.eck.Smart.Agenda.entity.FileAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileAnnotationRepository extends JpaRepository<FileAnnotation, UUID> {
    
    List<FileAnnotation> findByFileIdOrderByPageNumber(UUID fileId);
    
    void deleteByFileId(UUID fileId);
}
