package lssbt.service.domain.shared.issue;

public enum IssueTxtFileStructure {
  ID("id"),
  DESCRIPTION("description"),
  PARENT_ID("parent_id"),
  STATUS("status"),
  CREATION_TIMESTAMP("creationTimestamp"),
  LINK("link");

  private final String value;

  IssueTxtFileStructure(String value) {
    this.value = value;
  }

  public final String getValue() {
    return value;
  }
}
