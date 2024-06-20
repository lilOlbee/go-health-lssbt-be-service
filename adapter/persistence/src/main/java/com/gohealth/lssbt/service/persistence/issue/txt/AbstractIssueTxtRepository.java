package com.gohealth.lssbt.service.persistence.issue.txt;

import static lssbt.service.domain.shared.issue.IssueTxtFileStructure.*;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractIssueTxtRepository {

  protected static final String DELIMITER = ",";

  protected static final Map<String, Integer> FILE_DATA_STRUCTURE =
      new HashMap<>() {
        {
          put(ID.name(), 0);
          put(DESCRIPTION.name(), 1);
          put(PARENT_ID.name(), 2);
          put(STATUS.name(), 3);
          put(CREATION_TIMESTAMP.name(), 4);
          put(LINK.name(), 5);
        }
      };
}
