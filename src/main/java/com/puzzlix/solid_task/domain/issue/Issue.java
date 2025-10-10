package com.puzzlix.solid_task.domain.issue;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    // 추후 연관관계 필드
    private Long projectId;
    // 누가 요청(보고)
    private Long reporterId;
    // 담당자(누군가에게 할당 되어 처리 됩니다)
    private Long assigneeId;
}
