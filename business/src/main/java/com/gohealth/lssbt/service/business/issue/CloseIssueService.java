package com.gohealth.lssbt.service.business.issue;

import com.gohealth.lssbt.service.business.issue.inbound.CloseIssueUseCase;
import com.gohealth.lssbt.service.business.issue.outbound.PersistIssuePort;
import com.gohealth.lssbt.service.business.issue.outbound.QueryIssuePort;
import com.gohealth.lssbt.service.domain.issue.command.CloseIssueCommand;
import com.gohealth.lssbt.service.domain.issue.entity.ImmutableIssueEntity;
import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.domain.issue.query.ImmutableFindIssueByIdQuery;
import lssbt.service.domain.shared.issue.IssueStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloseIssueService implements CloseIssueUseCase {

  private final PersistIssuePort persistIssuePort;
  private final QueryIssuePort queryIssuePort;

  @Autowired
  public CloseIssueService(PersistIssuePort persistIssuePort, QueryIssuePort queryIssuePort) {
    this.persistIssuePort = persistIssuePort;
    this.queryIssuePort = queryIssuePort;
  }

  @Override
  public void execute(CloseIssueCommand command) {
    final IssueEntity entity;
    try {
      entity = queryIssuePort.findOne(ImmutableFindIssueByIdQuery.of(command.id()));
    } catch (RuntimeException e) {
      // TODO: throw exception and move `System.out.printf` to UI Controller
      System.out.printf("\nIssue with id '%s' was not found.\n", command.id());
      return;
    }

    persistIssuePort.close(
        ImmutableIssueEntity.builder()
            .id(entity.id())
            .description(entity.description())
            .parentId(entity.parentId())
            .status(IssueStatus.CLOSED.getValue())
            .creationTimestampt(entity.creationTimestampt())
            .link(entity.link())
            .build());

    // TODO: throw exception and move `System.out.printf` to UI Controller
    System.out.printf(
        "\nCongratulations, issue with id '%s' was successfully closed.\n", command.id());
  }
}
