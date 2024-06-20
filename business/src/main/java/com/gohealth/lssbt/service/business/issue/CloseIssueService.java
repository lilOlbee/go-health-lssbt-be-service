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
    //    try {
    final IssueEntity entity = queryIssuePort.findOne(ImmutableFindIssueByIdQuery.of(command.id()));
    //    } catch (BusinessException e) {
    //      throw new Busi
    //    }

    persistIssuePort.close(
        ImmutableIssueEntity.builder()
            .id(entity.id())
            .description(entity.description())
            .parentId(entity.parentId())
            .status(IssueStatus.CLOSED.getValue())
            .creationTimestampt(entity.creationTimestampt())
            .link(entity.link())
            .build());
  }
}
