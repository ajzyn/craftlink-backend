package com.craftlink.backend.shared.utils;

import org.springframework.util.StringUtils;

public class QueryUtils {

    public static String prepareSearchString(String searchPhrase) {
        if (!StringUtils.hasText(searchPhrase)) {
            return null;
        }

        searchPhrase = searchPhrase.trim();

        return "%" + searchPhrase + "%";
    }
}
