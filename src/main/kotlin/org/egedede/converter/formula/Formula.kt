package org.egedede.converter.formula

import org.egedede.converter.SubUnit
import org.egedede.converter.findMappingSubUnits
import java.math.BigDecimal

data class Formula (val value: BigDecimal, val parts: List<FormulaPart>) {

  override fun hashCode(): Int {
    var result = value.hashCode()
    result = 31 * result + parts.hashCode()
    return result
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Formula

    if (value.compareTo(other.value)!=0) return false
    if (parts != other.parts) return false

    return true
  }

}
data class FormulaPart (val unit: org.egedede.converter.Unit, val subUnit: SubUnit, val power: Int)
/**
 * Scientific formulas are of the form u1^2.u2^3
 */
class ScientificFormulaParser {

  /**
   * Returns Multiple formulas in case of units with the same symbol (like minute and meter)
   */
  fun parse(formula: String) : Collection<Formula> {
    // formula starts with a number then units : regexp= ([0-9\.]+)\s*([^\.]+(\^[0-9]+)
    val regex = Regex("([0-9\\.]+)\\s*(.+)")
    val match = regex.matchEntire(formula)
    val valueAsString = match!!.groupValues[1]
    val unitsAsString = match!!.groupValues[2]
    println(valueAsString)
    val value = BigDecimal(valueAsString)
    val partsArray = unitsAsString.split(".")
    val parts = ArrayList<FormulaPart>();
    for (part in partsArray) {
      // part is either u or u^x
      val partPart = part.split("\\^")
      val subUnits = findMappingSubUnits(partPart[0])[0]
      val formulaPart = if(partPart.size==1){
        FormulaPart(subUnits.first, subUnits.second,1)
      } else {
        FormulaPart(subUnits.first, subUnits.second,partPart[1].toInt())
      }
      parts.add(formulaPart)
    }
    val result = ArrayList<Formula>()
    result.add(Formula(value, parts))
    return result;
  }
}
