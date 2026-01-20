package com.eck.Smart.Agenda.repository;

import com.eck.Smart.Agenda.entity.LectureScript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LectureScriptRepository extends JpaRepository<LectureScript, UUID> {
    
    Optional<LectureScript> findByFileIdAndLength(UUID fileId, String length);
    boolean existsByFileIdAndLength(UUID fileId, String length);
}
