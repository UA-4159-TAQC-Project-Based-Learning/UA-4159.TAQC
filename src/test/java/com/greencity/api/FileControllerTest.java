package com.greencity.api;

import com.greencity.api.clients.FileClient;
import com.greencity.api.testRunner.ApiTestRunnerWithUser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileControllerTest extends ApiTestRunnerWithUser {

    private static final String TEST_FILE_PATH = "src/test/resources/images/crop-image-test-01.jpg";

    private FileClient authClient() {
        FileClient client = new FileClient(testValueProvider.getBaseAPIUserUrl());
        client.setToken(signInResponse.getAccessToken());
        return client;
    }

    private FileClient notAuthClient() {
        return new FileClient(testValueProvider.getBaseAPIUserUrl());
    }

    private String uploadTestFileAndGetPath(FileClient client) {
        File file = new File(TEST_FILE_PATH);
        Assert.assertTrue(file.exists(), "Test file not found: " + file.getAbsolutePath());

        Response uploadResp = client.uploadSingle(file);
        Assert.assertEquals(
                uploadResp.statusCode(),
                200,
                "Upload should return 200. Body: " + uploadResp.asString()
        );

        String uploadedPath = uploadResp.asString();
        Assert.assertTrue(uploadedPath != null && !uploadedPath.isBlank(), "Upload response (path) is empty");
        return uploadedPath;
    }

    @Test
    public void shouldUploadAndDeleteSingleFile() {
        FileClient client = authClient();

        String uploadedPath = uploadTestFileAndGetPath(client);

        Response deleteResp = client.deleteSingle(uploadedPath);
        Assert.assertEquals(
                deleteResp.statusCode(),
                200,
                "Delete should return 200. Body: " + deleteResp.asString()
        );

        String deleteMsg = deleteResp.asString();
        Assert.assertTrue(deleteMsg != null && !deleteMsg.isBlank(), "Delete response is empty");
    }

    @Test
    public void deleteSingle_shouldBeIdempotent() {
        FileClient client = authClient();

        String uploadedPath = uploadTestFileAndGetPath(client);

        // 1st delete -> success
        Response firstDelete = client.deleteSingle(uploadedPath);
        Assert.assertEquals(
                firstDelete.statusCode(),
                200,
                "First delete should return 200. Body: " + firstDelete.asString()
        );

        // 2nd delete -> idempotency check
        Response secondDelete = client.deleteSingle(uploadedPath);

        // Not sure what should be returned - might be 404 - but it's not specified in API docs
        int code = secondDelete.statusCode();
        Assert.assertTrue(code == 404,
                "Second delete should be idempotent. Expected 404, but got "
                        + code + ". Body: " + secondDelete.asString()
        );
    }

    @Test
    public void deleteSingle_nonExistentFile_shouldReturnNotFoundk() {
        FileClient client = authClient();

        String nonExistentPath = "/uploads/does-not-exist-" + System.currentTimeMillis() + ".jpg";

        Response deleteResp = client.deleteSingle(nonExistentPath);

        int code = deleteResp.statusCode();
        Assert.assertTrue(
                code == 404,
                "Deleting non-existent file expected 404, but got "
                        + code + ". Body: " + deleteResp.asString()
        );
    }

    @Test
    public void uploadSingle_withoutToken_shouldReturn401() {
        FileClient client = notAuthClient();

        File file = new File(TEST_FILE_PATH);
        Assert.assertTrue(file.exists(), "Test file not found: " + file.getAbsolutePath());

        Response resp = client.uploadSingle(file);

        int code = resp.statusCode();
        Assert.assertTrue(
                code == 401,
                "Upload without token expected 401, but got "
                        + code + ". Body: " + resp.asString()
        );
    }

    @Test
    public void deleteSingle_withoutToken_shouldReturn401() {
        FileClient client = notAuthClient();

        Response resp = client.deleteSingle("/uploads/some-file.jpg");

        int code = resp.statusCode();
        Assert.assertTrue(
                code == 401 || code == 403,
                "Delete without token expected 401, but got "
                        + code + ". Body: " + resp.asString()
        );
    }

    @Test
    public void deleteSingle_blankPath_shouldReturnClientError() {
        FileClient client = authClient();

        Response resp = client.deleteSingle("");

        int code = resp.statusCode();
        Assert.assertTrue(
                code == 400,
                "Delete with blank path expected 400, but got "
                        + code + ". Body: " + resp.asString()
        );
    }
}
