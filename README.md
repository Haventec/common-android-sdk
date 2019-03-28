# Haventec Common Android SDK

A collection of functions used by the android Haventec SDKs. This library is not meant to be used directly by a third party applications.

Please use instead [authenticate-android-sdk](https://github.com/Haventec/authenticate-android-sdk) or
[sanctum-android-sdk](https://github.com/Haventec/sanctum-android-sdk)

## Requirements

Android 4.4 or later

## Installation

The bundled .aar file is imported by other Haventec SDKs

If using gradle, add the following dependencies:

```
implementation 'com.haventec.common.android.sdk:common-android-sdk:0.2'
```

Ensure to use the latest published version of the SDK
[ ![Download](https://api.bintray.com/packages/haventec/maven/common-android-sdk/images/download.svg?version=0.2) ](https://bintray.com/haventec/maven/common-android-sdk/0.2/link)

## Usage

The main functionality provided relates to the SHA-512 Hashing of the PIN that is required by Haventec Authenticate endpoints,
so these SDK functions provide a convenient and consistent way of implementing these functions in an Android app.

It provides the following methods:
* generateSalt(): It generates a random salt.
* hashPin(String pin, byte[] salt): It creates a Hash of the PIN. The returned value is a Base64-encoding of the SHA-512 hash of the PIN.

## Development
To build and publish it locally, run the following:
```
./gradlew clean build publish
```

## License

This code is available under the MIT license. A copy of the license can be found in the LICENSE file included with the distribution.
