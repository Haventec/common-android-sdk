# Haventec Common Android SDK

A collection of functions for native Android apps used in other Haventec SDKs. This library is not meant to be used directly by a third party application.

Please use instead [authenticate-android-sdk](https://github.com/Haventec/authenticate-android-sdk) or
[sanctum-android-sdk](https://github.com/Haventec/sanctum-android-sdk)


## Development
To build, run the following:
```
./gradlew clean build publish
```

## Installation

The bundled .aar file is imported by other Haventec SDKs

If using gradle, add the following dependencies:

```
implementation "com.haventec.common.android.sdk:common-android-sdk:0.1@aar"
```

Ensure to use the latest published version of the SDK

## Usage

The main functionality provided relates to the SHA-512 Hashing of the PIN that is required by Haventec Authenticate endpoints,
so these SDK functions provide a convenient and consistent way of implementing these functions in an Android app.

To use the SDK, import the following class and its dependencies:
```
import com.haventec.common.android.sdk.api.HaventecCommon;
```

This class has the following methods:
```
public class HaventecCommon {

 public static byte[] generateSalt() throws HaventecCommonException {
 }

 public static String hashPin(String pin, byte[] salt) throws HaventecException {
 }
}
```

To initialize, call the generateSalt() method, and retain the returned value. This is required for hashing the PIN to generate the same hash each time.
If you don't retain the value, you won't be able to authenticate and you will need to invoke the Authenticate /forgot-pin and /reset-pin flow.
```
byte[] saltBytes = HaventecCommon.generateSalt();
```

To hash the PIN, call the hashPin method:
```
String hashedPin = HaventecCommon.hashPin(pinCode, salt);
```

The returned value is a Base64-encoding of the SHA-512 hash of the pinCode, along with the salt previously generated.
This can be sent to the Haventec Authenticate endpoints that require a hashedPin, such as /authentication/activate/user


## Contributors

 - Justin Crosbie

## License

This code is available under the MIT license. A copy of the license can be found in the LICENSE file included with the distribution.
