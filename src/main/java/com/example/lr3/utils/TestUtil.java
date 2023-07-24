package com.example.lr3.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }
}
