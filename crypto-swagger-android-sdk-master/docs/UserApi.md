# UserApi

All URIs are relative to *http://34.229.215.228:28017/test*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createUser**](UserApi.md#createUser) | **POST** /users/ | Create user
[**getUserByPhone**](UserApi.md#getUserByPhone) | **GET** /users/ | Find user by phone number
[**updateUser**](UserApi.md#updateUser) | **PUT** /users/ | Update an existing user


<a name="createUser"></a>
# **createUser**
> createUser(body)

Create user

This can only be done by the logged in user (not implemented).

### Example
```java
// Import classes:
//import io.swagger.client.api.UserApi;

UserApi apiInstance = new UserApi();
User body = new User(); // User | Created user object
try {
    apiInstance.createUser(body);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#createUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**User**](User.md)| Created user object |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getUserByPhone"></a>
# **getUserByPhone**
> User getUserByPhone(filterPhoneNumber)

Find user by phone number

Returns a single user

### Example
```java
// Import classes:
//import io.swagger.client.api.UserApi;

UserApi apiInstance = new UserApi();
Integer filterPhoneNumber = 56; // Integer | Phonenumber of the user to find
try {
    User result = apiInstance.getUserByPhone(filterPhoneNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#getUserByPhone");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filterPhoneNumber** | **Integer**| Phonenumber of the user to find | [optional]

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateUser"></a>
# **updateUser**
> updateUser(body)

Update an existing user



### Example
```java
// Import classes:
//import io.swagger.client.api.UserApi;

UserApi apiInstance = new UserApi();
User body = new User(); // User | User object that needs to be updated
try {
    apiInstance.updateUser(body);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#updateUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**User**](User.md)| User object that needs to be updated |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

