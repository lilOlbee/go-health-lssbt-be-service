package com.gohealth.lssbt.service.business.issue.outbound;

import com.gohealth.lssbt.service.domain.issue.entity.IssueEntity;
import com.gohealth.lssbt.service.domain.issue.query.FindIssueByIdQuery;
import com.gohealth.lssbt.service.domain.issue.query.ListIssuesQuery;
import java.util.List;

public interface QueryIssuePort {

  IssueEntity findOne(FindIssueByIdQuery query);

  List<IssueEntity> list(ListIssuesQuery query);
}
