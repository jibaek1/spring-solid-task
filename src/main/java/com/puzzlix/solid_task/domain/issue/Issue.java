package com.puzzlix.solid_task.domain.issue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
