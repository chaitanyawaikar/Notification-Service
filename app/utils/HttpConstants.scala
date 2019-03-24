package utils

object HttpConstants {

  val USER_CREATED_SUCCESSFULLY = "User has been created successfully with id "
  val INTERNAL_SERVER_ERROR_MSG = "There was some problem in the application"
  val INVALID_JSON_INPUT = "Json input is invalid"
  val USER_ALREADY_EXISTS = "User with given email id already exists in system"

}

object TemplateConstants {

  val WELCOME_TEMPLATE_KEY = "WELCOME"
  val NEWSLETTER_TEMPLATE_KEY = "NEWSLETTER"

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
