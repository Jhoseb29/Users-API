package university.jala.usersapi.domain.util;


import org.springframework.stereotype.Component;
import university.jala.usersapi.domain.service.exception.WrongDataException;

@Component
public final class RequestDataValidatorUtil implements DataValidator {

  @Override
  public void validate(final String field, final String content)
      throws WrongDataException {
    nullDataValidator(field, content);
    blankDataValidator(field, content);
  }

  private void nullDataValidator(final String field, final String content)
      throws WrongDataException {
    if (content == null) {
      throw new WrongDataException(
          "The field '" + field + "' must not be null.");
    }
  }

  private void blankDataValidator(final String field, final String content)
      throws WrongDataException {
    if (content.isBlank() || content.isEmpty()) {
      throw new WrongDataException(
          "The field '" + field + "' must not be empty.");
    }
  }
}
