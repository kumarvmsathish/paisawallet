/**
 * KryptoPal Wallet API
 * This is the API specification for KryptoPal
 *
 * OpenAPI spec version: 0.0.1
 * Contact: dev@KryptoPal.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;
import io.swagger.client.ApiInvoker;
import io.swagger.client.Pair;
import io.swagger.client.model.Crypto;

public class CryptocurrencyInformationApi {
  String basePath = "http://34.229.215.228:28017/test";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public void addHeader(String key, String value) {
    getInvoker().addDefaultHeader(key, value);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  /**
  * Gets exchange value for specified cryptocurrency
  * The current fiat exchange rate information is returned for the specified cryptocurrency. You can specify the national fiat currency to return in the paramater. The default is US dollars.
   * @param filterName Name of the cryptocurrency to return information for.
   * @param filterPriceEur NOTE: NOT IMPLEMENTED, DO NOT USE. Ticker name of the fiat currency to return (i.e. usd, eur). The default is USD and EUR.
   * @return Crypto
  */
  public Crypto getCryptoByPair (String filterName, String filterPriceEur) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
    Object postBody = null;
    // verify the required parameter 'filterName' is set
    if (filterName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'filterName' when calling getCryptoByPair",
        new ApiException(400, "Missing the required parameter 'filterName' when calling getCryptoByPair"));
    }

    // create path and map variables
    String path = "/exchanges/";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();
    queryParams.addAll(ApiInvoker.parameterToPairs("", "filter_name", filterName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "filter_price_eur", filterPriceEur));
    String[] contentTypes = {
      "application/json"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
    }

    String[] authNames = new String[] {  };

    try {
      String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
      if (localVarResponse != null) {
         return (Crypto) ApiInvoker.deserialize(localVarResponse, "", Crypto.class);
      } else {
         return null;
      }
    } catch (ApiException ex) {
       throw ex;
    } catch (InterruptedException ex) {
       throw ex;
    } catch (ExecutionException ex) {
      if (ex.getCause() instanceof VolleyError) {
        VolleyError volleyError = (VolleyError)ex.getCause();
        if (volleyError.networkResponse != null) {
          throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        }
      }
      throw ex;
    } catch (TimeoutException ex) {
      throw ex;
    }
  }

      /**
   * Gets exchange value for specified cryptocurrency
   * The current fiat exchange rate information is returned for the specified cryptocurrency. You can specify the national fiat currency to return in the paramater. The default is US dollars.
   * @param filterName Name of the cryptocurrency to return information for.   * @param filterPriceEur NOTE: NOT IMPLEMENTED, DO NOT USE. Ticker name of the fiat currency to return (i.e. usd, eur). The default is USD and EUR.
  */
  public void getCryptoByPair (String filterName, String filterPriceEur, final Response.Listener<Crypto> responseListener, final Response.ErrorListener errorListener) {
    Object postBody = null;

    // verify the required parameter 'filterName' is set
    if (filterName == null) {
      VolleyError error = new VolleyError("Missing the required parameter 'filterName' when calling getCryptoByPair",
        new ApiException(400, "Missing the required parameter 'filterName' when calling getCryptoByPair"));
    }

    // create path and map variables
    String path = "/exchanges/".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    queryParams.addAll(ApiInvoker.parameterToPairs("", "filter_name", filterName));
    queryParams.addAll(ApiInvoker.parameterToPairs("", "filter_price_eur", filterPriceEur));


    String[] contentTypes = {
      "application/json"
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = localVarBuilder.build();
      postBody = httpEntity;
    } else {
      // normal form params
          }

    String[] authNames = new String[] {  };

    try {
      apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String localVarResponse) {
            try {
              responseListener.onResponse((Crypto) ApiInvoker.deserialize(localVarResponse,  "", Crypto.class));
            } catch (ApiException exception) {
               errorListener.onErrorResponse(new VolleyError(exception));
            }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            errorListener.onErrorResponse(error);
          }
      });
    } catch (ApiException ex) {
      errorListener.onErrorResponse(new VolleyError(ex));
    }
  }
}