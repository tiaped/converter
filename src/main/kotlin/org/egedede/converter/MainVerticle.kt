package org.egedede.converter

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

class MainVerticle : AbstractVerticle() {

  override fun start(startFuture: Future<Void>) {
    val converter = SimpleConverterService()
    converter.register(meterPerSecondToKilometersPerHour)
    converter.register(kilometerPerHourToMinutePerKilometer)
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
      val from = json.getString("from")
      val to = json.getString("to")
      val  value = json.getString("value")
      val converted = converter.convert(from, to, value)

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
