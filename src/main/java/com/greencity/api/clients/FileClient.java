package com.greencity.api.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.List;

public class FileClient extends BaseClient {

    private static final String BASE_PATH = "/files";

    public FileClient(String baseUrl) {
        super(baseUrl);
    }

    private RequestSpecification preparedMultipartRequest() {
        return preparedRequest();
    }

    public Response uploadMultiple(List<File> files) {
        var req = preparedMultipartRequest();
        for (File f : files) {
            req.multiPart("files", f);
        }
        return req.post(BASE_PATH);
    }

    public Response uploadSingle(File file) {
        return preparedMultipartRequest()
                .multiPart("file", file)
                .post(BASE_PATH + "/single");
    }

    public Response deleteMultiple(List<String> paths) {
        return preparedRequest()
                .body(paths)
                .delete(BASE_PATH);
    }

    public Response deleteSingle(String path) {
        return preparedRequest()
                .queryParam("path", path)
                .delete(BASE_PATH + "/single");
    }
}
