package models

import play.api.libs.json.{Json, OFormat}

case class User(id: Int,
                sureName: String,
                firstName: String,
                gender: String,
                email: String,
                subscribedNewsletter: Boolean)
case class Template(id: Int, key: String, template: String)

object User {
  implicit val usersFormat: OFormat[User] = Json.format[User]
}

object Template {
  implicit val templateFormat: OFormat[Template] = Json.format[Template]
}
