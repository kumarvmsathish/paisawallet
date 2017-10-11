# CryptocurrencyInformationApi

All URIs are relative to *http://34.229.215.228:28017/test*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getCryptoByPair**](CryptocurrencyInformationApi.md#getCryptoByPair) | **GET** /exchanges/ | Gets exchange value for specified cryptocurrency


<a name="getCryptoByPair"></a>
# **getCryptoByPair**
> Crypto getCryptoByPair(filterName, filterPriceEur)

Gets exchange value for specified cryptocurrency

The current fiat exchange rate information is returned for the specified cryptocurrency. You can specify the national fiat currency to return in the paramater. The default is US dollars.

### Example
```java
// Import classes:
//import io.swagger.client.api.CryptocurrencyInformationApi;

CryptocurrencyInformationApi apiInstance = new CryptocurrencyInformationApi();
String filterName = "Bitcoin"; // String | Name of the cryptocurrency to return information for.
String filterPriceEur = "filterPriceEur_example"; // String | NOTE: NOT IMPLEMENTED, DO NOT USE. Ticker name of the fiat currency to return (i.e. usd, eur). The default is USD and EUR.
try {
    Crypto result = apiInstance.getCryptoByPair(filterName, filterPriceEur);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CryptocurrencyInformationApi#getCryptoByPair");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filterName** | **String**| Name of the cryptocurrency to return information for. | [default to Bitcoin]
 **filterPriceEur** | **String**| NOTE: NOT IMPLEMENTED, DO NOT USE. Ticker name of the fiat currency to return (i.e. usd, eur). The default is USD and EUR. | [optional]

### Return type

[**Crypto**](Crypto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

