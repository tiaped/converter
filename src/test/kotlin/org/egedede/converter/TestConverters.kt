package org.egedede.converter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TestConverters {

  @Test
  fun kilometerPerHourToMinutesPerKilometer() {
    val converter = kilometerPerHourToMinutePerKilometer
    Assertions.assertEquals(BigDecimal("6.00"), converter.convert("10"))
    Assertions.assertEquals(BigDecimal("5.00"), converter.convert("12"))
    Assertions.assertEquals(BigDecimal("10.00"), converter.convert("6"))
  }

}
