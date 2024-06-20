package com.gohealth.lssbt.service.business.issue;

import com.gohealth.lssbt.service.business.issue.inbound.CreateIssueUseCase;
import com.gohealth.lssbt.service.business.issue.outbound.PersistIssuePort;
import com.gohealth.lssbt.service.business.issue.outbound.QueryIssuePort;
import com.gohealth.lssbt.service.domain.issue.command.CreateIssueCommand;
import com.gohealth.lssbt.service.domain.issue.entity.ImmutableIssueEntity;
import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.domain.issue.query.ImmutableFindIssueByIdQuery;
import com.gohealth.lssbt.service.domain.issue.query.ImmutableListIssuesQuery;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;
import java.time.OffsetDateTime;
import java.util.List;
import lssbt.service.domain.shared.issue.IssueStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateIssueService implements CreateIssueUseCase {

  private static final String IDENTIFICATION_DELIMITER = "I-";

  private final PersistIssuePort persistIssuePort;
  private final QueryIssuePort queryIssuePort;

  @Autowired
  public CreateIssueService(PersistIssuePort persistIssuePort, QueryIssuePort queryIssuePort) {
    this.persistIssuePort = persistIssuePort;
    this.queryIssuePort = queryIssuePort;
  }

  @Override
  @Transactional
  public IssueEntity execute(CreateIssueCommand command) {
    final String id = resolveIncrementedId();

    persistIssuePort.insert(
        ImmutableIssueEntity.builder()
            .id(id)
            .description(command.description())
            .parentId(command.parentId())
            .status(IssueStatus.OPEN.getValue())
            .creationTimestampt(OffsetDateTime.now())
            .link(command.link())
            .build());

    return queryIssuePort.findOne(ImmutableFindIssueByIdQuery.of(id));
  }

  // list all issues (even closed) so the new id is always unique.
  private String resolveIncrementedId() {
    final ListIssuesQuery query = ImmutableListIssuesQuery.of(false);
    final List<Integer> issueIds =
        queryIssuePort.list(query).stream()
            .map(issue -> issue.id().replace(IDENTIFICATION_DELIMITER, ""))
            .map(Integer::parseInt)
            .sorted()
            .toList();

    return IDENTIFICATION_DELIMITER + (!issueIds.isEmpty() ? issueIds.getLast() + 1 : 1);
  }
}
