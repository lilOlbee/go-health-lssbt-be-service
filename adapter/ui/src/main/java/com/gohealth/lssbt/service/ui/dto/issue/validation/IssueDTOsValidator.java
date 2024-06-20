package com.gohealth.lssbt.service.ui.dto.issue.validation;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import com.gohealth.lssbt.service.ui.dto.issue.CloseIssueRequest;
import com.gohealth.lssbt.service.ui.dto.issue.CreateIssueRequest;

// usually, DTO objects are validated with Jakarta Validator, but for the CLI, I decided to try it
// with Yavi.
public abstract class IssueDTOsValidator {

  public static final Validator<CreateIssueRequest> createIssueRequestValidator =
      ValidatorBuilder.<CreateIssueRequest>of()
          .constraint(
              CreateIssueRequest::description,
              "description",
              description -> description.notEmpty().notBlank())
          .constraint(CreateIssueRequest::link, "link", link -> link.notEmpty().notBlank())
          .build();

  public static final Validator<CloseIssueRequest> closeIssueRequestValidator =
      ValidatorBuilder.<CloseIssueRequest>of()
          .constraint(CloseIssueRequest::id, "id", id -> id.notNull().notEmpty().notBlank())
          .build();
}
