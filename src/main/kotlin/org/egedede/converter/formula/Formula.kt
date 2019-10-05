package org.egedede.converter.formula

import org.egedede.converter.SubUnit
import java.math.BigDecimal

data class Formula (val value: BigDecimal, val parts: List<FormulaPart>)
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
    val parts = formula.split("\\.")
    for (part in parts) {
      // part is either u or u^x
      val partPart = part.split("\\^")

//      val formulaPart = if(partPart.size==1){
//        FormulaPart(,1)
//      } else {
//        FormulaPart(,partPart[1].toInt())
//      }
    }
    return ArrayList();
  }
}
