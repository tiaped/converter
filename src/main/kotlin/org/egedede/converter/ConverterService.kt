package org.egedede.converter

interface ConverterService {
  fun convert(source: String, target: String, value: String, metadata: Map<String, Any> = emptyMap()): Any
}
