package in.androidtest.bhavesh.HeadyApp.network;


import in.androidtest.bhavesh.HeadyApp.models.ProductResponse.ProductResponse;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;


public interface NetworkInterface {

    @GET("json")
    Observable<Response<ProductResponse>> getProductResponse();
}
