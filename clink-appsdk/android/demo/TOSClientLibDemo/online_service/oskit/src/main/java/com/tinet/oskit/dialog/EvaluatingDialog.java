package com.tinet.oskit.dialog;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tinet.oskit.R;
import com.tinet.oskit.view.SatisfactionStatusChange;
import com.tinet.oslib.listener.ChatInfoCallback;
import com.tinet.oslib.manager.OnlineManager;
import com.tinet.oslib.model.message.content.ChatInvestigationMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.tinet.http.okhttp3.OkHttpClient;
import org.tinet.http.okhttp3.Request;
import org.tinet.http.okhttp3.Response;

import java.util.Map;
import java.util.Set;


/**
 * 满意度
 *
 * @author: liuzeren
 * @date: 2022/3/30
 */
public class EvaluatingDialog extends TinetDialog {

    private WebView webView;

    private ChatInvestigationMessage message;

    private SatisfactionStatusChange change;

    public EvaluatingDialog(ChatInvestigationMessage message, SatisfactionStatusChange change) {
        this.message = message;
        this.change = change;
    }

    @Override
    protected int layoutId() {
        return R.layout.dlg_evaluating;
    }

    @Override
    void initView(View view) {
        webView = view.findViewById(R.id.webView);

//        Map<String, Object> headers = TIMBaseManager.getInstance().getInitOption().getAdvanceParams();
//        if (headers.containsKey("deBugEnv"))
//            if ("ktTest".equals(headers.get("deBugEnv")))
//                webView.setWebViewClient(new setWebViewClient());

        if (!TextUtils.isEmpty(message.getInvestigationUrl())) {
            String url = message.getInvestigationUrl() + "&uniqueId=" + message.getUniqueId();
            webView.loadUrl(url);
        } else {
            OnlineManager.getInvestigationUrl(message.getMainUniqueId(), new ChatInfoCallback() {
                @Override
                public void onSuccess(JSONObject data) {
                    try {
                        String url = data.getString("investigationUrl") + "&uniqueId=" + message.getUniqueId();
                        webView.loadUrl(url);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
//        String url = message.getInvestigationUrl() + "&uniqueId=" + message.getUniqueId();
//        webView.loadUrl(url);
        view.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        change.SatisfactionStatus(message.getMainUniqueId(), message.getUniqueId());
    }

    @Override
    protected DialogLocation location() {
        return DialogLocation.bottom;
    }

    private class setWebViewClient extends WebViewClient {

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            return getNewResponse(url, request.getRequestHeaders());
        }

        private WebResourceResponse getNewResponse(String url, Map<String, String> headers) {

            try {
                OkHttpClient httpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder()
                        .url(url.trim())
                        .addHeader("X-Virtual-Env", "dev.chat");

                Set<String> keySet = headers.keySet();
                for (String key : keySet) {
                    builder.addHeader(key, headers.get(key));
                }

                Request request = builder.build();

                final Response response = httpClient.newCall(request).execute();

                String conentType = response.header("Content-Type", response.body().contentType().type());

                return new WebResourceResponse(
                        conentType,
                        response.header("Content-Encoding", "utf-8"),
                        response.body().byteStream()
                );

            } catch (Exception e) {
                return null;
            }

        }

    }
}
