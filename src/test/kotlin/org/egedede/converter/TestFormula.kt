package org.egedede.converter

import org.egedede.converter.formula.Formula
import org.egedede.converter.formula.FormulaPart
import org.egedede.converter.formula.ScientificFormulaParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TestFormula {

  @Test
  fun subUnitFinder() {
    Assertions.assertEquals(findMappingSubUnits("m"), listOf(
      Pair(time, time.units.stream().filter { it.symbol=="m" }.findFirst().get()),
      Pair(length, length.units.stream().filter { it.symbol=="m" }.findFirst().get())
    ))
    Assertions.assertEquals(findMappingSubUnits("s"), listOf(
      Pair(time, time.units.stream().filter { it.symbol=="s" }.findFirst().get())
    ))
    Assertions.assertNotEquals(findMappingSubUnits("m"), listOf(
      Pair(time, time.units.stream().filter { it.symbol=="m" }.findFirst().get())
    ))
  }
  @Test
  fun formulaParser() {
    val parser = ScientificFormulaParser()
    var parse = parser.parse("1km.h")
    var ref = listOf(
      Formula(BigDecimal("1.0"), listOf(
        FormulaPart(length, length.units.stream().filter { it.symbol=="km" }.findFirst().get(),1 ),
        FormulaPart(time, time.units.stream().filter { it.symbol=="h" }.findFirst().get(),1 )
      ))
    )
    Assertions.assertEquals(ref, parse)
    parse = parser.parse("1 km.h")
    ref = listOf(
      Formula(BigDecimal("1.0"), listOf(
        FormulaPart(length, length.units.stream().filter { it.symbol=="km" }.findFirst().get(),1 ),
        FormulaPart(time, time.units.stream().filter { it.symbol=="h" }.findFirst().get(),1 )
      ))
    )
    Assertions.assertEquals(ref, parse)
  }
}
