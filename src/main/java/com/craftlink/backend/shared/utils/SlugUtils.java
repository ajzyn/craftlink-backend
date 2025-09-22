package com.craftlink.backend.shared.utils;

import java.text.Normalizer;
import java.util.Locale;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class SlugUtils {

  public String generateSlug(String text) {
    if (StringUtils.isBlank(text)) {
      return "";
    }

    String normalized = Normalizer.normalize(text.trim(), Normalizer.Form.NFD);
    String withoutDiacritics = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

    return withoutDiacritics
        .toLowerCase(Locale.ROOT)
        .replaceAll("\\s+", "-")
        .replaceAll("[^a-z0-9\\-]", "")
        .replaceAll("-+", "-")
        .replaceAll("^-|-$", "");
  }
}