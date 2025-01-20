# Automotive Projection Android Test

This project contains a test for the automotive projection capability using Appium and OpenCV.

## Prerequisites
- Appium
- OpenCV - OpenCV is an open-source computer vision library.
  Using matchTemplate method of OpenCV to find the location of elements on the DHU screen and interact with it.
See build.gradle file

## Setup

1. Clone the repository.
2. Install the required dependencies using Gradle.
3. Set up the environment variables for `CLOUD_URL`, `ACCESS_KEY`, `APPIUM_VERSION`, and `DHU_SCREEN_SIZE`.

## Test Description

The test performs the following steps:

1. Sets up the desired capabilities for the Android driver.
2. Launches the Google Maps application.
3. Simulates location playback using a predefined file. file should be uploaded to file repository first. for more info: 
https://docs.digital.ai/continuous-testing/docs/te/test-execution-home/mobile-android-and-ios/appium/seetest-appium-extension/device-commands/setlocationplaybackfile
4. Uses OpenCV to find and interact with UI elements in the DHU (Desktop Head Unit) screenshot.
5. Taps on the search bar, enters a location, and starts navigation.

## Additional Information

For more information about this capability, see https://docs.digital.ai/continuous-testing/docs/te/test-execution-home/integrations/automative-car-projection-testing/test-execution-with-android-auto#create-android-auto-test