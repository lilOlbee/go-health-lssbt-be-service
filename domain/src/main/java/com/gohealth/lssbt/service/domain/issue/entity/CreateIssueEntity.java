package com.gohealth.lssbt.service.domain.issue.entity;

import org.immutables.value.Value;

@Value.Immutable
public sealed interface CreateIssueEntity extends BaseIssueEntity
    permits ImmutableCreateIssueEntity {}
