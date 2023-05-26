
package testrestapi;
import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class ValidateIDInAWS {

  public static void main(final String[] args) throws IOException {

    final byte[] readFileToByteArray =
      FileUtils.readFileToByteArray(new File("C://DL//Sample.jpg"));
    final OkHttpClient client = new OkHttpClient().newBuilder().build();
    final MediaType mediaType = MediaType.parse("text/plain");
    final RequestBody body =
      new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("file", "Sample.jpg", RequestBody.create(
          readFileToByteArray, MediaType.parse("application/octet-stream")))
        .build();
    RequestBody.create(readFileToByteArray,
      MediaType.parse("application/octet-stream"));
    final Request request = new Request.Builder()
      .url("https://wbz3apnbc3.execute-api.us-east-1.amazonaws.com/dev/ocr")
      .method("POST", body)
      .addHeader("x-api-key", "uCjYYhXB+U2+Fc5pvx1wECQBU4DNrklOjJYDiPum")
      .build();
    final Response response = client.newCall(request).execute();
//    System.out.println(response.body().string());
    
    //Read JSON response and print
//    JSONObject myResponse = new JSONObject(response.toString());
//    System.out.println("statusMessage- "+myResponse.getString("Status"));
//    System.out.println(myResponse.toString());
    
    String jsonData = response.body().string();
    JSONObject Jobject = new JSONObject(jsonData);
    System.out.println(Jobject.toString());
    System.out.println("statusMessage- "+Jobject.getString("Status"));
  }
}
