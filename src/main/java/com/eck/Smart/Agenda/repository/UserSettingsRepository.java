package com.eck.Smart.Agenda.repository;

import com.eck.Smart.Agenda.entity.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, UUID> {
    
}
