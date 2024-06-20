package com.gohealth.lssbt.service.business.issue.inbound;

import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;
import java.util.List;

public interface ListIssueUseCase {

  List<IssueEntity> execute(ListIssuesQuery query);
}
