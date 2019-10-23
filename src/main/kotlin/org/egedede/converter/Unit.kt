package org.egedede.converter

class Unit(val name: String, val symbol: String, val units: Collection<SubUnit> ){
  override fun toString(): String {
    return "Unit(name='$name', symbol='$symbol')"
  }
}

class SubUnit (val name: String, val symbol: String, val factor: Double) {
  override fun toString(): String {
    return "SubUnit(name='$name')"
  }
}

val time = Unit("time","T", setOf(
  SubUnit("second", "s", 1.0),
  SubUnit("minute", "m", 60.0),
  SubUnit("hour", "h", 3600.0),
  SubUnit("day", "d", 24.0*3600.0),
  SubUnit("millisecond", "ms", 0.001),
  SubUnit("microsecond", "\u00B5s", 0.000001)
))
val length = Unit("length","L", setOf(
  SubUnit("meter", "m", 1.0),
  SubUnit("kilometer", "km", 1000.0),
  SubUnit("centimeter", "cm", 0.01),
  SubUnit("millimeter", "mm", 0.001),
  SubUnit("micrometer", "\u00B5m", 0.000001)
))

fun findMappingSubUnits(symbol: String) : List<Pair<Unit,SubUnit>> {
  val result = ArrayList<Pair<Unit,SubUnit>>()
  for(unit in setOf(time, length)) {
    unit.units.forEach() {
      if (symbol == it.symbol) {
        result.add(Pair(unit,it))
      }
    }
  }
  return result
}
