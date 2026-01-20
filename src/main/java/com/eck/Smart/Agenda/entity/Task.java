package com.eck.Smart.Agenda.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    @Builder.Default
    private String status = "TODO";

    @Column(nullable = false)
    @Builder.Default
    private String priority = "MEDIUM";

    @Column(nullable = false)
    @Builder.Default
    private String urgency = "MEDIUM";

    @Column(name = "due_date")
    private OffsetDateTime dueDate;

    @Column(name = "completed_at")
    private OffsetDateTime completedAt;

    private String recurrence;

    @Type(StringArrayType.class)
    @Column(columnDefinition = "text[]")
    private String[] tags;

    @Column(name = "order_index")
    @Builder.Default
    private Integer orderIndex = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;
}
