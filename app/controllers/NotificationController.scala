package controllers

import javax.inject.Inject
import models.{ErrorResponse, SuccessResponse, User}
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws._
import play.api.mvc._
import service.NotificationService
import utils.HttpConstants.{INTERNAL_SERVER_ERROR_MSG, INVALID_JSON_INPUT}

import scala.concurrent.{ExecutionContext, Future}

class NotificationController @Inject()(
    service: NotificationService,
    ws: WSClient,
    cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
    extends MessagesAbstractController(cc) {

  def sendWelcomeEmail: Action[JsValue] = Action.async(parse.json) { request =>
    request.body
      .validate[User]
      .map { user: User =>
        service
          .sendWelcomeEmail(ws, user)
          .map {
            case Right(msg) =>
              Ok(Json.toJson(SuccessResponse(OK, msg)))
            case Left(errormsg) =>
              Conflict(Json.toJson(ErrorResponse(CONFLICT, errormsg)))
          }
          .recover {
            case _ =>
              InternalServerError(
                Json.toJson(ErrorResponse(INTERNAL_SERVER_ERROR,
                                          INTERNAL_SERVER_ERROR_MSG)))
          }
      }
      .recoverTotal { _ =>
        Future.successful(
          BadRequest(
            Json.toJson(ErrorResponse(BAD_REQUEST, INVALID_JSON_INPUT))))
      }
  }

  def sendNewsletter: Action[AnyContent] =
    Action.async { implicit request =>
      service.sendNewsletter(ws)
        .map {
          case Right(msg) =>
            Ok(Json.toJson(SuccessResponse(OK, msg)))
          case Left(errormsg) =>
            Conflict(Json.toJson(ErrorResponse(CONFLICT, errormsg)))
        }
        .recover {
          case _ =>
            InternalServerError(Json.toJson(
              ErrorResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG)))
        }
    }
}
