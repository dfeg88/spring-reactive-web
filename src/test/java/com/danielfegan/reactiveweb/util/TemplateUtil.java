package com.danielfegan.reactiveweb.util;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.function.BiFunction;

import static java.nio.file.Files.readString;

public class TemplateUtil {

    public static final BiFunction<Resource, String, String> templateErrorResponse = (resource, message) ->
    {
        try {
            return readString(resource.getFile().toPath()).replace("MESSAGE_PLACEHOLDER", message);
        } catch (IOException e) {
            return null;
        }
    };

}
