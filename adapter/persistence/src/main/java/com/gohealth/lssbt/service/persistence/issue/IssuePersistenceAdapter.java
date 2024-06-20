package com.gohealth.lssbt.service.persistence.issue;

import com.gohealth.lssbt.service.business.issue.outbound.PersistIssuePort;
import com.gohealth.lssbt.service.business.issue.outbound.QueryIssuePort;

public interface IssuePersistenceAdapter extends PersistIssuePort, QueryIssuePort {}
