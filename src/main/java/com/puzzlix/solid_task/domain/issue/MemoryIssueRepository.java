package com.puzzlix.solid_task.domain.issue;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository // 이 클래스가 데이터 저장소 역할을 하는 스프링 빈임을 선언 함
public class MemoryIssueRepository implements IssueRepository{

    // 동시성 문제를 방지하기 위해 ConcurrentHashMap 사용 멀티 스레드를 안정적이게 사용 하기위한 자료구조
    private static Map<Long,Issue> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(0);

    @Override
    public Issue save(Issue issue) {
        // save 요청시 Issue에 상태값 id가 없는 상태이다.
        if (issue.getId() == null) {
            // -> 1 변경 하고 issue 객체에 상태값 id를 1로 할당
            issue.setId(sequence.incrementAndGet());
            // sequence -> 1 로 결정됨
            // sequence -> 2 로 결정됨
        }
        store.put(issue.getId(),issue);
        return issue;
    }

    @Override
    public Optional<Issue> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Issue> findAll() {
        return new ArrayList<>(store.values());
    }
}
