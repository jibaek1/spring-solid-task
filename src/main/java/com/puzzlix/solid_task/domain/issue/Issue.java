package com.puzzlix.solid_task.domain.issue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    private Long id;
    private String title;
    private String description;
    private IssueStatus issueStatus;

    // 추후 연관관계 필드
    private Long projectId;
    private Long reporterId;
    private Long assigneeId;
}
