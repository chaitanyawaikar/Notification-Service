package utils

import models.{Template, User}
import RegexConstants._

object TemplateFormatter {

  implicit class TemplateWelcomeFormat(template: Template) {

    def format(user: User): String = {
      template.template
        .replaceAll(SALUTATION_REGEX, gender(user))
        .replaceAll(NAME_REGEX, user.firstName)
        .replaceAll(IDENTIFIER_REGEX, user.id.toString)
    }

    private def gender(user: User): String = {
      user.gender.toLowerCase match {
        case MALE   => SALUTATION_MR
        case FEMALE => SALUTATION_MRS
      }
    }
  }

}
