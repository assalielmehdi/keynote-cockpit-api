package io.assalielmehdi.keynote.helpers;

import io.assalielmehdi.keynote.dto.PresentationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PresentationHelperImpl implements PresentationHelper {

  private final StringHelper stringHelper;

  @Override
  public void normalizeRequest(PresentationDto presentationDto) {
    presentationDto.setTitle(stringHelper.removeExtraBlank(presentationDto.getTitle()));
    presentationDto.setToken(null);
  }

}
