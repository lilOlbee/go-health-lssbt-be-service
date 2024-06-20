package com.gohealth.lssbt.service.ui.mapper.command;

import com.gohealth.lssbt.service.domain.issue.command.CloseIssueCommand;
import com.gohealth.lssbt.service.domain.issue.command.CreateIssueCommand;
import com.gohealth.lssbt.service.domain.issue.command.ImmutableCloseIssueCommand;
import com.gohealth.lssbt.service.domain.issue.command.ImmutableCreateIssueCommand;
import com.gohealth.lssbt.service.ui.dto.issue.CloseIssueRequest;
import com.gohealth.lssbt.service.ui.dto.issue.CreateIssueRequest;

// mapstruct could be used, but Ii prefer this way of mapping
public abstract class IssueCommandMapper {

  public static CreateIssueCommand map(CreateIssueRequest request) {
    return ImmutableCreateIssueCommand.builder()
        .description(request.description())
        .parentId(request.parentId())
        .link(request.link())
        .build();
  }

  public static CloseIssueCommand map(CloseIssueRequest request) {
    return ImmutableCloseIssueCommand.builder().id(request.id()).build();
  }
}
