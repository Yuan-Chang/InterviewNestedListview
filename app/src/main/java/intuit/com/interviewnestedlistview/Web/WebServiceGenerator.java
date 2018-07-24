package intuit.com.interviewnestedlistview.Web;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ychang3 on 9/26/16.
 */

public class WebServiceGenerator {

    private static WebClient webClient;
    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        GsonBuilder gsonBuilder = new GsonBuilder();
        builder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

        httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }


    public static WebClient getWebClient(String baseURL)
    {
        builder.baseUrl(baseURL);
        if (webClient == null)
            webClient = WebServiceGenerator.createService(WebClient.class);
        return webClient;
    }


}
