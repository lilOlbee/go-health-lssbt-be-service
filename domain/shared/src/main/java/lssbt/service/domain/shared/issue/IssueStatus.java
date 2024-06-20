package lssbt.service.domain.shared.issue;

public enum IssueStatus {
  CLOSED("Closed"),
  OPEN("Open");

  private final String value;

  IssueStatus(String value) {
    this.value = value;
  }

  public final String getValue() {
    return value;
  }
}
