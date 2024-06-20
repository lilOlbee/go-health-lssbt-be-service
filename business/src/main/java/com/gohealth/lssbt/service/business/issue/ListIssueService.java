package com.gohealth.lssbt.service.business.issue;

import com.gohealth.lssbt.service.business.issue.inbound.ListIssueUseCase;
import com.gohealth.lssbt.service.business.issue.outbound.QueryIssuePort;
import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListIssueService implements ListIssueUseCase {

  private final QueryIssuePort queryIssuePort;

  @Autowired
  public ListIssueService(QueryIssuePort queryIssuePort) {
    this.queryIssuePort = queryIssuePort;
  }

  @Override
  public List<IssueEntity> execute(ListIssuesQuery query) {
    return queryIssuePort.list(query);
  }
}
