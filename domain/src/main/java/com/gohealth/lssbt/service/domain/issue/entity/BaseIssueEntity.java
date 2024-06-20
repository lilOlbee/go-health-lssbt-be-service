package com.gohealth.lssbt.service.domain.issue.entity;

import lssbt.service.domain.shared.entity.Entity;
import org.immutables.value.Value;

@Value.Immutable
public interface BaseIssueEntity extends Entity {

  String id();
}
