package io.konf.client;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.io.IOException;
import java.util.Map;

/**
 * Created by kerem on 04/10/16.
 */
public class GoCDClient {

    private String username;
    private String password;
    
    public GoCDClient(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public int executeHttpPost(String url, Map<String,String> params) throws IOException {
        HttpClient httpClient=new HttpClient();
        httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.username, this.password));
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Confirm","true");
        for(String key: params.keySet()){
            postMethod.setParameter(key,params.get(key));
        }
        int response = httpClient.executeMethod(postMethod);
        return response;
    }
}
