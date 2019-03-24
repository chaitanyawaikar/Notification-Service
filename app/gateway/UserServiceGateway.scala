package gateway

import com.google.inject.Inject
import config.AppConfig
import models.{Template, User}
import play.api.libs.ws.{WSClient, WSRequest}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class UserServiceGateway @Inject()(implicit context: ExecutionContext) {

  private def createTemplateRequest(ws: WSClient): WSRequest = {

    val request = ws.url(AppConfig.userServiceUrl)
    request
      .withHttpHeaders("Accept" -> "application/json")
      .withRequestTimeout(100.millis)
    request
  }

  def fetchUsers(ws: WSClient): Future[Option[Seq[User]]] = {
    createTemplateRequest(ws).get().map { response =>
      response.json.validate[Seq[User]].asOpt
    }
  }
}
