# PhoneNumberInfo
### Project Description
This is a phone number validator Android Application with MVVM pattern.

### Features
- Get general information (Country, Location, Operator) of entered phone number.

### Libraries
- Retrofit
- Hilt
- LiveData
- Serialization
- Coroutines
- Timber

### API
I used [Num Lookup API](https://app.numlookupapi.com) for collecting phone number information.

### Project Setup
Clone the project and open it using Android Studio. Then create `apiinfo.properties` in root file. You need to specify the `api_key` and `base_url` in your `apiinfo.properties` file. You can find this values here: [Num Lookup API](https://app.numlookupapi.com/api-keys).

Example:
```properties
#this is apiinfo.properties example
api_key="your api key"
base_url="https://api.numlookupapi.com/"
```
