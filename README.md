# EatRoom
EatRoom is microservice application with android client for delivering food.

Video: https://www.dropbox.com/s/3ppzze9hbaalx3x/2022-08-20%2015-13-21.mkv?dl=0
## Running the application
### Backend
### Frontend
Frontend requires installing Android Studio to run. There are two ways to run the application: on emulator or on physical device.

For emulator just clone project and follow this instructions: https://developer.android.com/studio/run/emulator, and then run the project in Android Studio

Steps for seting up applicaton on physical device:
1. Clone project
2. Run backend on your computer
3. Connect your phone and computer on same network and set network on computer to privete
4. Find ip adress of your computer with command ipconfig on Windows or ifconfig on Linux
5. In Android/EatRoom/app/src/main/java/com/example/eatroom/AppModule.kt file change 4 url that have value http://10.0.2.2:800x to http://your_ip_adress:800x
6. Connect phone with usb cable to computer and run project in Android Studion

## Services
### Identity
### Restaurants
### Basket
Basket service mages user basket. 
It caches dishes that user put in basket with redis. 
Users can add items in basket, retrive already existing items or delete whole basket.
When user order contents of the basket are send to Order service and user basket clears.
### Order
