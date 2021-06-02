package io.assalielmehdi.keynote.controllers;

import io.assalielmehdi.keynote.dto.PresentationDto;
import io.assalielmehdi.keynote.services.PresentationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/presentations")
public class PresentationController {

  private final PresentationService presentationService;

  @PostMapping
  public PresentationDto create(@RequestBody PresentationDto presentationDto) {
    return presentationService.create(presentationDto);
  }

}
