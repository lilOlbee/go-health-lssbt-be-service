package com.gohealth.lssbt.service.ui.util;

import static com.gohealth.lssbt.service.ui.dto.issue.validation.DTOValidator.closeIssueRequestValidator;
import static com.gohealth.lssbt.service.ui.dto.issue.validation.DTOValidator.createIssueRequestValidator;

import am.ik.yavi.core.ConstraintViolation;
import com.gohealth.lssbt.service.ui.dto.issue.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public abstract class IssueUIUtil {

  public static CreateIssueRequest resolveCreateIssueRequest(Scanner scanner) {
    System.out.println("\nPlease, specify data related to this issue.");

    System.out.print("1. ParentIssueId (optional, press `enter` to keep it empty): ");
    final String parentIssueId = scanner.nextLine();
    final Optional<String> optionalParentIssueId =
        !parentIssueId.isEmpty() ? Optional.of(parentIssueId) : Optional.empty();

    System.out.print("2. Description: ");
    final String description = scanner.nextLine();

    System.out.print("3. Link: ");
    final String link = scanner.nextLine();

    return ImmutableCreateIssueRequest.builder()
        .parentId(optionalParentIssueId)
        .description(description)
        .link(link)
        .build();
  }

  public static CloseIssueRequest resolveCloseIssueRequest(Scanner scanner) {
    System.out.println("\nPlease, specify the id of issue to be closed.");

    System.out.print("1. id: ");
    final String id = scanner.nextLine();

    return ImmutableCloseIssueRequest.builder().id(id).build();
  }

  /*
     returns `false` if not valid
     returns `true` if valid
  */
  public static boolean isCreateIssueRequestValid(CreateIssueRequest request) {
    final List<ConstraintViolation> constraintViolations =
        createIssueRequestValidator.validate(request);
    if (!constraintViolations.isEmpty()) {
      System.out.println("\nIssue cannot be successfully created, see problems:");
      constraintViolations.forEach(
          constraintViolation -> System.out.println(constraintViolation.message()));
      return false;
    }

    return true;
  }

  /*
     returns `false` if not valid
     returns `true` if valid
  */
  public static boolean isCloseIssueRequestValid(CloseIssueRequest request) {
    final List<ConstraintViolation> constraintViolations =
        closeIssueRequestValidator.validate(request);
    if (!constraintViolations.isEmpty()) {
      System.out.println("\nIssue cannot be successfully closed, see problems:");
      constraintViolations.forEach(
          constraintViolation -> System.out.println(constraintViolation.message()));
      return false;
    }

    return true;
  }
}
