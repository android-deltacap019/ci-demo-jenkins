package com.deltacap019.cidemojenkins.util.network;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class NetworkUtil {

    private final String TAG = NetworkUtil.class.getSimpleName();
    private String mUrl, mParams;
    private RequestType mRequestType;
    private Map<String, String> headers;

    public enum RequestType {
        GET,
        POST,
        OPTIONS,
        HEAD,
        PUT,
        DELETE,
        TRACE;

    }

    public NetworkUtil(String url, Map<String, String> headers, String params, RequestType requestType) {
        if (url.contains("http") || url.contains("https")) {
            mUrl = url;
        } else {
            mUrl = "http://" + url;
        }
        this.headers = headers;
        mParams = params;
        mRequestType = requestType;
    }

    public NetworkUtil(String url, RequestType requestType) {
        mUrl = url;
        mRequestType = requestType;
    }

    public NetworkResponse getResponse() {
        if (mRequestType == RequestType.GET)
            return makeGETRequest();
        else if (mRequestType == RequestType.POST)
            return makePOSTRequest();
        else
            return null;
    }

    private void setRequestProperties(HttpURLConnection con) {

        if (headers != null && headers.size() > 0) {
            Iterator it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                con.setRequestProperty(pair.getKey().toString(), pair.getValue().toString());
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    private void setConnectionParameters(HttpURLConnection con) {
        con.setConnectTimeout(3000);
    }

    private NetworkResponse makeGETRequest() {

        NetworkResponse networkResponse = new NetworkResponse();
        try {

            URL url = new URL(mUrl);//+"&janfebmarch=1"
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            setConnectionParameters(con);
            setRequestProperties(con);
            con.setRequestMethod(RequestType.GET.name());

            parseResponse(con, networkResponse);

        } catch (ProtocolException e) {
            networkResponse.setResponseCodeMessage(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            networkResponse.setResponseCodeMessage(e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            networkResponse.setResponseCodeMessage(e.toString());
            e.printStackTrace();
        }
        return networkResponse;
    }

    private NetworkResponse makePOSTRequest() {
        NetworkResponse networkResponse = new NetworkResponse();
        try {
            URL url = new URL(mUrl);
            HttpURLConnection con = (HttpURLConnection) url
                    .openConnection();
            setConnectionParameters(con);
            setRequestProperties(con);
            con.setRequestMethod(RequestType.POST.name());

            //  String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            if (!TextUtils.isEmpty(mParams))
                wr.writeBytes(mParams);
            wr.flush();
            wr.close();

            parseResponse(con, networkResponse);

        } catch (Exception e) {
            networkResponse.setResponseCodeMessage(e.toString());
            e.printStackTrace();
        }
        return networkResponse;
    }


    private String readStream(HttpURLConnection urlConnection) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    urlConnection.getInputStream().close();
                    urlConnection.disconnect();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();

    }

    private NetworkResponse parseResponse(HttpURLConnection con, NetworkResponse networkResponse) throws IOException {
        switch (con.getResponseCode()) {
            //2XX Action requested by the client was received, understood, accepted and processed successfully.
            case HttpURLConnection.HTTP_OK://200
                networkResponse.setResponseCode(HttpURLConnection.HTTP_OK);
                networkResponse.setResponse(readStream(con));
                networkResponse.setResponseCodeMessage("Http Class 2XX = Success + Response Code = 200");
                break;
            case HttpURLConnection.HTTP_CREATED://201
                networkResponse.setResponseCode(HttpURLConnection.HTTP_CREATED);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 2XX = Success HTTP_CREATED + Response Code = 201");
                break;
            case HttpURLConnection.HTTP_ACCEPTED://202
                networkResponse.setResponseCode(HttpURLConnection.HTTP_ACCEPTED);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 2XX = Success HTTP_ACCEPTED + Response Code = 202");
                break;
            case HttpURLConnection.HTTP_NOT_AUTHORITATIVE://203
                networkResponse.setResponseCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 2XX = Success HTTP_CREATED + Response Code = 203");
                break;
            case HttpURLConnection.HTTP_NO_CONTENT://204
                networkResponse.setResponseCode(HttpURLConnection.HTTP_NO_CONTENT);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 2XX = Success HTTP_CREATED + Response Code = 204");
                break;
            case HttpURLConnection.HTTP_RESET://205
                networkResponse.setResponseCode(HttpURLConnection.HTTP_RESET);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 2XX = Success HTTP_CREATED + Response Code = 205");
                break;
            case HttpURLConnection.HTTP_PARTIAL://206
                networkResponse.setResponseCode(HttpURLConnection.HTTP_PARTIAL);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 2XX = Success HTTP_CREATED + Response Code = 206");
                break;
            //3XX This class of status code indicates the client must take additional action to complete the request. Many of these status codes are used in URL redirection.
            case HttpURLConnection.HTTP_MULT_CHOICE://300
                networkResponse.setResponseCode(HttpURLConnection.HTTP_MULT_CHOICE);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 3XX = Redirection HTTP_MULT_CHOICE + Response Code = 300");
                break;
            case HttpURLConnection.HTTP_MOVED_PERM://301
                networkResponse.setResponseCode(HttpURLConnection.HTTP_MOVED_PERM);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 3XX = Redirection HTTP_MOVED_PERM + Response Code = 301");
                break;
            case HttpURLConnection.HTTP_MOVED_TEMP://302
                networkResponse.setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 3XX = Redirection HTTP_MOVED_TEMP + Response Code = 302");
                break;
            case HttpURLConnection.HTTP_SEE_OTHER://303
                networkResponse.setResponseCode(HttpURLConnection.HTTP_SEE_OTHER);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 3XX = Redirection HTTP_SEE_OTHER + Response Code = 303");
                break;
            case HttpURLConnection.HTTP_NOT_MODIFIED://304
                networkResponse.setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 3XX = Redirection HTTP_NOT_MODIFIED + Response Code = 304");
                break;
            //4XX Cases in which the client seems to have erred
            case HttpURLConnection.HTTP_BAD_REQUEST://400
                networkResponse.setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_BAD_REQUEST + Response Code = 400");
                break;
            case HttpURLConnection.HTTP_PAYMENT_REQUIRED://402
                networkResponse.setResponseCode(HttpURLConnection.HTTP_PAYMENT_REQUIRED);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_PAYMENT_REQUIRED + Response Code = 402");
                break;
            case HttpURLConnection.HTTP_FORBIDDEN://403
                networkResponse.setResponseCode(HttpURLConnection.HTTP_FORBIDDEN);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_FORBIDDEN + Response Code = 403");
                break;
            case HttpURLConnection.HTTP_NOT_FOUND://404
                networkResponse.setResponseCode(HttpURLConnection.HTTP_NOT_FOUND);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_NOT_FOUND + Response Code = 404");
                break;
            case HttpURLConnection.HTTP_BAD_METHOD://405
                networkResponse.setResponseCode(HttpURLConnection.HTTP_BAD_METHOD);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_BAD_METHOD + Response Code = 405");
                break;
            case HttpURLConnection.HTTP_NOT_ACCEPTABLE://406
                networkResponse.setResponseCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_NOT_ACCEPTABLE + Response Code = 406");
                break;
            case HttpURLConnection.HTTP_PROXY_AUTH://407
                networkResponse.setResponseCode(HttpURLConnection.HTTP_PROXY_AUTH);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_PROXY_AUTH + Response Code = 407");
                break;
            case HttpURLConnection.HTTP_CLIENT_TIMEOUT://408
                networkResponse.setResponseCode(HttpURLConnection.HTTP_CLIENT_TIMEOUT);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_CLIENT_TIMEOUT + Response Code = 408");
                break;
            case HttpURLConnection.HTTP_CONFLICT://409
                networkResponse.setResponseCode(HttpURLConnection.HTTP_CONFLICT);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_CONFLICT + Response Code = 409");
                break;
            case HttpURLConnection.HTTP_GONE://410
                networkResponse.setResponseCode(HttpURLConnection.HTTP_GONE);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_GONE + Response Code = 410");
                break;
            case HttpURLConnection.HTTP_LENGTH_REQUIRED://411
                networkResponse.setResponseCode(HttpURLConnection.HTTP_LENGTH_REQUIRED);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_LENGTH_REQUIRED + Response Code = 411");
                break;
            case HttpURLConnection.HTTP_PRECON_FAILED://412
                networkResponse.setResponseCode(HttpURLConnection.HTTP_PRECON_FAILED);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_PRECON_FAILED + Response Code = 412");
                break;
            case HttpURLConnection.HTTP_ENTITY_TOO_LARGE://413
                networkResponse.setResponseCode(HttpURLConnection.HTTP_ENTITY_TOO_LARGE);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_ENTITY_TOO_LARGE + Response Code = 413");
                break;
            case HttpURLConnection.HTTP_REQ_TOO_LONG://414
                networkResponse.setResponseCode(HttpURLConnection.HTTP_REQ_TOO_LONG);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 4XX = Client Error HTTP_REQ_TOO_LONG + Response Code = 414");
                break;
            //5XX The server failed to fulfill an apparently valid request.
            case HttpURLConnection.HTTP_INTERNAL_ERROR://500
                networkResponse.setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 5XX = Server Error HTTP_INTERNAL_ERROR + Response Code = 500");
                break;
            case HttpURLConnection.HTTP_NOT_IMPLEMENTED://501
                networkResponse.setResponseCode(HttpURLConnection.HTTP_NOT_IMPLEMENTED);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 5XX = Server Error HTTP_NOT_IMPLEMENTED + Response Code = 501");
                break;
            case HttpURLConnection.HTTP_BAD_GATEWAY://502
                networkResponse.setResponseCode(HttpURLConnection.HTTP_BAD_GATEWAY);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 5XX = Server Error HTTP_BAD_GATEWAY + Response Code = 502");
                break;
            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT://504
                networkResponse.setResponseCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT);
                networkResponse.setResponse("");
                networkResponse.setResponseCodeMessage("Http Class 5XX = Server Error HTTP_GATEWAY_TIMEOUT + Response Code = 504");
                break;


        }
        return networkResponse;
    }




	/*public void writeRequestDataIntoDisc(String request, String response) {

		try {
			File dir = new File(Environment.getExternalStorageDirectory(),"/taxinet/");
			dir.mkdirs();
			File debugFile = new File(dir+"/request_data_" + getServiceName() + "_" + new Date().toString() + ".txt");
			debugFile.createNewFile();
			FileOutputStream fOut = new FileOutputStream(debugFile);
			OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
			myOutWriter.append("*************************REQUEST DATA**********************");
			myOutWriter.append("\n\n");
			myOutWriter.append(request);
			myOutWriter.append("\n\n***********************END REQUEST**********************\n");
			myOutWriter.append("\n\n");
			myOutWriter.append("*************************RESPONSE DATA**********************");
			myOutWriter.append("\n\n");
			myOutWriter.append(response);
			myOutWriter.append("\n\n***********************END RESPONSE**********************\n");
			myOutWriter.append("\n\n");
			myOutWriter.append("*************************SERVICE NAME WITH URL**********************\n\n");
			myOutWriter.append(getRequesURI());
			myOutWriter.close();
			fOut.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}*/

}
