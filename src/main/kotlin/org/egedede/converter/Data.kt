package org.egedede.converter

data class Response(val value: Any){
  fun toJson() : String {
    return """
        {"value":$value}
    """.trimIndent()
  }
}
data class ErrorReponse(val code: String, val message: String){
  fun toJson() : String {
   return    """
        {
          "code":$code,
          "message":$message
    """.trimIndent()
  }
}
