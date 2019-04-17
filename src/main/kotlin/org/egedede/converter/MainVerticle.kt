package org.egedede.converter

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.core.json.Json
import io.vertx.kotlin.core.json.get

class MainVerticle : AbstractVerticle() {

  override fun start(startFuture: Future<Void>) {
    val converter = ConverterService
    converter.register(meterPerSecondToKilometersPerHour)
    var server = vertx.createHttpServer()

    var router = Router.router(vertx)
    router.route().handler{
      // This handler will be called for every request
      var response = it.response()
      response.putHeader("content-type", "application/json")
      it.next()
    }
    router.route().handler(BodyHandler.create())

    router.post("/convert").handler{
      val json = it.bodyAsJson
      val from = json.getString("unitFrom")
      val to = json.getString("unitTo")
      val  value = json.getDouble("value")
      val converted = value *2

      it.response().setStatusCode(200).end(Response(converted).toJson())

    }

    server.requestHandler(router).listen(8888) { http ->
      if (http.succeeded()) {
        startFuture.complete()
        println("HTTP server started on port 8888")
      } else {
        startFuture.fail(http.cause())
      }
    }

  }

}
