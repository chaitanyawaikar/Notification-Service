package service

import models.{Template, User}
import utils.TemplateFormatter._

object NotificationAggregator {

  def aggregateUsersInBatches(users: Seq[User],
                              template: Template): Iterator[Seq[String]] = {
    users
      .filter(_.subscribedNewsletter)
      .map(user => template.format(user))
      .grouped(10)
  }

  def sendBatchNotifications(template: Template, users: Seq[User]): Unit = {
    aggregateUsersInBatches(users, template).foreach { batch =>
      println("----Batch Notifications Start----")
      batch.foreach(println(_))
      println("----Batch Notifications Ends----")
    }
  }
}
