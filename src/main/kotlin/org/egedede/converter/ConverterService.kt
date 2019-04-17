package org.egedede.converter

import java.math.BigDecimal
import java.math.RoundingMode

class ConverterService {

  val converters : MutableMap<Pair<String, String>, Converter> = mutableMapOf()
  fun availableConverters(): Collection<Pair<String, String>> = converters.keys
  fun register(converter: Converter) {
    converters[Pair(converter.source, converter.target)]=converter
  }
  fun convert(source: String, target: String, value: String): Any = converters[Pair(source,target)]!!.convert.invoke(value)

}

class Converter(val source: String, val target: String, val convert: (String) -> Any)
val meterPerSecondToKilometersPerHour = Converter("m/s","km/h") {
  x: String ->
    (BigDecimal(x)*BigDecimal(3600).divide(BigDecimal(1000),15,RoundingMode.HALF_EVEN))
    .setScale(2, RoundingMode.HALF_EVEN)
}
val kilometerPerHourToMinutePerKilometer = Converter("km/h","m/km") {
  x: String ->
    (BigDecimal(60)/BigDecimal(x))
    .setScale(2, RoundingMode.HALF_EVEN)
}
