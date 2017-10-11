# swagger-android-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-android-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-android-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-android-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.api.CryptocurrencyInformationApi;

public class CryptocurrencyInformationApiExample {

    public static void main(String[] args) {
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://34.229.215.228:28017/test*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*CryptocurrencyInformationApi* | [**getCryptoByPair**](docs/CryptocurrencyInformationApi.md#getCryptoByPair) | **GET** /exchanges/ | Gets exchange value for specified cryptocurrency
*UserApi* | [**createUser**](docs/UserApi.md#createUser) | **POST** /users/ | Create user
*UserApi* | [**getUserByPhone**](docs/UserApi.md#getUserByPhone) | **GET** /users/ | Find user by phone number
*UserApi* | [**updateUser**](docs/UserApi.md#updateUser) | **PUT** /users/ | Update an existing user


## Documentation for Models

 - [Crypto](docs/Crypto.md)
 - [User](docs/User.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

dev@KryptoPal.io

