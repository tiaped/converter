package org.egedede.converter

import java.math.BigDecimal
import java.math.RoundingMode

class ConverterService {

  val converters : MutableMap<Pair<String, String>, Converter> = mutableMapOf()
  fun availableConverters(): Collection<Pair<String, String>> = converters.keys
  fun register(converter: Converter) {
    converters[Pair(converter.source, converter.target)]=converter
  }

}

class Converter(val source: String, val target: String, val convert: (String) -> Any)
val meterPerSecondToKilometersPerHour = Converter("m/s","km/h") {
  x: String -> BigDecimal(x)/BigDecimal(1000)*BigDecimal(3600).setScale(2, RoundingMode.HALF_EVEN)
}
