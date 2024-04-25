package university.jala.usersapi.core.application.utils;


import org.springframework.stereotype.Component;
import university.jala.usersapi.core.domain.exceptions.WrongDataException;

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
