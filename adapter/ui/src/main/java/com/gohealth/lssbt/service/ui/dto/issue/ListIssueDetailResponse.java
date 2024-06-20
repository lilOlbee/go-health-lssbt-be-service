package com.gohealth.lssbt.service.ui.dto.issue;

import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
public sealed interface ListIssueDetailResponse permits ImmutableListIssueDetailResponse {

  List<FindIssueByIdDetailResponse> issues();
}
