package com.androidproject.organizasyonum;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public final class JSONParser {
    public final static JSONParser INSTANCE = new JSONParser();

    public enum requestMethod {
        Post,
        Get
    }

    private JSONParser() {

    }

/*    *//**
     * @param url
     * @return
     *//*
    public JSONObject getJSONFromUrl(String url) {
        String json = getJSONStringFromUrl(url);
        return convertJSONStringToJSONObject(json);
    }

    *//**
     * @param url
     * @return
     *//*
    public String getJSONStringFromUrl(String url) {
        InputStream inputStream = httpPostRequest(url, null);
        String response = getStringFromInputStream(inputStream);
        return response;
    }*/

    /**
     * function get json from url
     * by making HTTP POST or GET mehtod
     *
     * @param url
     * @param method
     * @param params
     * @return
     */
    public JSONObject makeHttpRequest(String url, requestMethod method, List<NameValuePair> params) {
        String json = makeHttpRequestReturnJSONString(url, method, params);
        return convertJSONStringToJSONObject(json);
    }

    /**
     * @param url
     * @param method
     * @param params
     * @return
     */
    public String makeHttpRequestReturnJSONString(String url, requestMethod method, List<NameValuePair> params) {
        InputStream inputStream = null;

        // Making HTTP request

        // check for request method
        if (method == requestMethod.Post) {
            // request method is POST
            inputStream = HttpPostRequest(url, params);
        } else if (method == requestMethod.Get) {
            // request method is GET
            inputStream = HttpGetRequest(url, params);
        }

        String response = GetStringFromInputStream(inputStream);

        return response;
    }

    private InputStream HttpPostRequest(String url, List<NameValuePair> params) {
        InputStream inputStream = null;
        if (!url.isEmpty()) {
            try {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            }
        }
        return inputStream;
    }
    private InputStream HttpGetRequest(String url, List<NameValuePair> params) {
        InputStream inputStream = null;
        if (!url.isEmpty()) {
            try {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                if (params != null) {
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            }
        }
        return inputStream;
    }
    private String GetStringFromInputStream(InputStream inputStream) {
        String response = null;
        if (inputStream != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                inputStream.close();
                response = sb.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            }
        }
        return response;
    }

    /**
     * @param JSONString
     * @return
     */
    public JSONObject convertJSONStringToJSONObject(String JSONString) {
        // try parse the string to a JSON object
        JSONObject jObj = null;
        if(!JSONString.isEmpty()) {
            try {
                jObj = new JSONObject(JSONString);
            } catch (JSONException e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Helper.INSTANCE.yazLog(Helper.logTypes.error, e.toString());
            }
        }
        // return JSON String
        return jObj;
    }
}
