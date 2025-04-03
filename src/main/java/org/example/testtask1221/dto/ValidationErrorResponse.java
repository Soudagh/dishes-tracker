package org.example.testtask1221.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents an error response containing a list of validation violations.
 */
@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {

  /**
   * A list of validation violations represented by {@link ViolationDto} objects.
   */
  private final List<ViolationDto> violations;
}

