package com.aluracursos.conversordemonedas.servicios;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdaptadorLocalDateTime implements JsonSerializer <LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private final DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formateador.format(src));
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(), formateador);
    }


}
