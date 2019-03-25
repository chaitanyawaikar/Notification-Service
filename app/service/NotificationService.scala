package service

import com.google.inject.{Inject, Singleton}
import gateway.{TemplateServiceGateway, UserServiceGateway}
import models.User
import play.api.libs.ws.WSClient
import service.NotificationAggregator.sendBatchNotifications
import utils.TemplateConstants._
import utils.TemplateFormatter._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class NotificationService @Inject()(
    templateServiceGateway: TemplateServiceGateway,
    userServiceGateway: UserServiceGateway)(implicit ec: ExecutionContext) {

  def sendWelcomeEmail(ws: WSClient,
                       user: User): Future[Either[String, String]] = {
    templateServiceGateway.fetchTemplate(ws, WELCOME_TEMPLATE_KEY).map {
      case Some(template) =>
        val enrichedTemplate = template.format(user)
        // Send this template to the imaginary mail server
        println(enrichedTemplate)
        Right(enrichedTemplate)
      case None =>
        Left(TEMPLATE_NOT_FOUND_BY_KEY)
    }
  }

  def sendNewsletter(ws: WSClient): Future[Either[String, String]] = {
    userServiceGateway.fetchUsers(ws).flatMap {
      case Some(users) =>
        templateServiceGateway.fetchTemplate(ws, NEWSLETTER_TEMPLATE_KEY).map {
          case Some(template) =>
            sendBatchNotifications(template, users)
            Right(template.template)
          case None =>
            Left(TEMPLATE_NOT_FOUND_BY_KEY)
        }
      case _ =>
        Future.successful(Left(""))
    }
  }

}
