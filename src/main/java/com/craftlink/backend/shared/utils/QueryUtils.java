package com.craftlink.backend.shared.utils;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

@UtilityClass
public class QueryUtils {

  public String prepareSearchString(String searchPhrase) {
    if (!StringUtils.hasText(searchPhrase)) {
      return null;
    }

    searchPhrase = searchPhrase.trim();

    return "%" + searchPhrase + "%";
  }
}
