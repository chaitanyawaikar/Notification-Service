package utils

object HttpConstants {

  val INTERNAL_SERVER_ERROR_MSG = "There was some problem in the application"
  val INVALID_JSON_INPUT = "Json input is invalid"

}

object TemplateConstants {

  val WELCOME_TEMPLATE_KEY = "WELCOME"
  val NEWSLETTER_TEMPLATE_KEY = "NEWSLETTER"
  val TEMPLATE_NOT_FOUND_BY_KEY = "Template for the given key does not exist"

}

object RegexConstants {

  val SALUTATION_REGEX = "\\{\\{user.salutation\\}\\}"
  val NAME_REGEX = "\\{\\{user.name\\}\\}"
  val IDENTIFIER_REGEX = "\\{\\{user.identifier\\}\\}"
  val MALE = "male"
  val FEMALE = "female"
  val SALUTATION_MR = "Mr."
  val SALUTATION_MRS = "Mrs."

}
