package gateway

import com.google.inject.Inject
import config.AppConfig
import models.Template
import play.api.libs.ws.{WSClient, WSRequest}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class TemplateServiceGateway @Inject()(implicit context: ExecutionContext) {

  private def createTemplateRequest(ws: WSClient, key: String): WSRequest = {

    val request = ws.url(AppConfig.templateServiceUrl + key)
    request
      .withHttpHeaders("Accept" -> "application/json")
      .withRequestTimeout(100.millis)
    request
  }

  def fetchTemplate(ws: WSClient, key: String): Future[Option[Template]] = {
    createTemplateRequest(ws, key).get().map { response =>
      response.json.validate[Template].asOpt
    }
  }
}
