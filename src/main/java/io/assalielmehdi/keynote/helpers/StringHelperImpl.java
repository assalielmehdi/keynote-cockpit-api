package io.assalielmehdi.keynote.helpers;

import org.springframework.stereotype.Component;

@Component
public class StringHelperImpl implements StringHelper {

  @Override
  public String removeExtraBlank(String value) {
    if (value == null) {
      return null;
    }

    value = value.trim();

    final var withoutExtraBlank = new StringBuilder();

    var blankLength = 0;
    for (var idx = 0; idx < value.length(); idx++) {
      if (Character.isWhitespace(value.charAt(idx))) {
        blankLength++;
      } else {
        if (blankLength > 0) {
          withoutExtraBlank.append(" ");
        }
        blankLength = 0;
        withoutExtraBlank.append(value.charAt(idx));
      }
    }

    return withoutExtraBlank.toString();
  }

}
