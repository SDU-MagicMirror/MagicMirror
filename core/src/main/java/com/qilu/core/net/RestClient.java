package com.qilu.core.net;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.qilu.core.net.callback.IError;
import com.qilu.core.net.callback.IFailure;
import com.qilu.core.net.callback.IRequest;
import com.qilu.core.net.callback.ISuccess;
import com.qilu.core.net.callback.RequestCallbacks;
import com.qilu.core.ui.loader.LoaderStyle;
import com.qilu.core.ui.loader.QiluLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public final class RestClient {

    private final WeakHashMap<String, String> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private final String FILE;
    private final WeakHashMap<String, String> FILES;

    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;

    RestClient(String url,
               WeakHashMap<String, String> params,
               IRequest request,
               ISuccess success,
               IFailure failure,
               IError error,
               WeakHashMap<String, String> files,
               String file,
               Context context,
               LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS = params;

        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;

        // upload
        this.FILES = files;
        this.FILE = file;

        // loader
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            QiluLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;

            case GET_NO_PARAMS:
                call = service.getWithNoParams(URL);
                break;

            case POST_RAW:
                WeakHashMap<String, RequestBody> tempParams = new WeakHashMap<>();
                for (String key : PARAMS.keySet()) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), PARAMS.get(key) == null ? "" : PARAMS.get(key));
                    tempParams.put(key, requestBody);
                }
                call = service.postRaw(URL, tempParams);
                break;

            case POST_WITH_FILES:
                WeakHashMap<String, RequestBody> requestBodyMap = new WeakHashMap<>();
                List<MultipartBody.Part> partList = new ArrayList<>();
                for (WeakHashMap.Entry<String, String> entry : PARAMS.entrySet()) {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), val);
                    requestBodyMap.put(key, body);
                }
                for (WeakHashMap.Entry<String, String> entry : FILES.entrySet()) {
                    String key = entry.getKey();
                    File val = new File(entry.getValue());
                    RequestBody body = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), val);
                    MultipartBody.Part part = MultipartBody.Part.createFormData(key, val.getName(), body);
                    partList.add(part);
                }
                call = service.postWithFiles(URL, requestBodyMap, partList);
                break;

            case UPLOAD:
                File file = new File(FILE);
                final RequestBody requestFile =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                call = service.upload(URL,body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }
    public final void getWithNoParams() {
        request(HttpMethod.GET_NO_PARAMS);
    }

    public final void postRaw() {
        request(HttpMethod.POST_RAW);
    }

    public final void postWithFiles() {
        request(HttpMethod.POST_WITH_FILES);

    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }
}
