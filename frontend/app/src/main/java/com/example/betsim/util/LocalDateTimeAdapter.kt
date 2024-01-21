package com.example.betsim.util

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME

class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {

    override fun write(out: JsonWriter, value: LocalDateTime) {
        out.value(ISO_OFFSET_DATE_TIME.format(value))
    }

    override fun read(input: JsonReader): LocalDateTime{
        return LocalDateTime.parse(input.nextString(), ISO_OFFSET_DATE_TIME)
    }
}