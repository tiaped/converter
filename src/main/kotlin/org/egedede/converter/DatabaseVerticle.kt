package org.egedede.converter

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.asyncsql.PostgreSQLClient

class DatabaseVerticle : AbstractVerticle() {
  var client;
  override fun start(startFuture: Future<Void>) {
    client = PostgreSQLClient.createShared(vertx, config().getJsonObject("database"))

  }

}
