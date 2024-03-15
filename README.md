# Contact

A contacts app. 📱

## Description

An essential application to manage your contacts efficiently in a clean and simple interface. With full create, read, update, and delete (CRUD) capabilities, as well as an instant call option using built-in Intents.
Easily organize your contacts and quickly access them when you need them.

**The app contains three screens:**
- **The main screen:** the entry point of the app and the place where all saved contacts will be displayed, has a search engine and a button to create a new contact. In turn, when a contact is selected, a dialog will appear with its information and the possibility of calling, editing or deleting the item.
- **The new contact screen:** pressing the floating button will open a screen with basic information to save the contact data. This is incomplete since more text fields are missing to take advantage of the space.
- **The third screen:** a screen similar to the addition screen will open, this will show the contact data thanks to the sending of data through navigation.

***Supports light/dark themes and has support for dynamic colors for Android versions +12 and up.***

> [!NOTE]
> You can download the Apk of the project to see it directly on your mobile. [Contact Apk](https://drive.google.com/file/d/1LTZBtiuwVzyD_1WFhNVFwxYhdBMdKKRT/view?usp=drive_link)

## Architecture
The type of architecture used for this project was MVVM(Model-View-ViewModel).

This is divided into the:

- Model: Which represents the data and business logic.
- View: Which represents the UI.
- ViewModel: Which represents the bridge between the View and the Model.

![1 oCv7nJ66-uq2d1f7eipV9w](https://github.com/ifMaxi/Contact/assets/112733459/1559d06a-9c31-44aa-bdb0-54d6a0cbc61a)

**This contains only two layers:**
- **The data layer:** Which contains the repositories and the database.
- **The presentation layer:** which contains all the UI components that will be rendered on the screen. It also has a ViewModel that works as a state container and contains business logic.

## Language and libraries

- Kotlin
  - Coroutines
  - Flow
- Jetpack
  - Compose
  - Hilt
  - Room
  - ViewModel
  - Navigation
- Material Ui 3

## Screenshots

**Light mode**

| Main screen | Detail contact | New contact |
| ----------- | -------------- | ----------- |
| <img src="https://github.com/ifMaxi/Contact/assets/112733459/e447245d-231f-47d0-a129-535134868ea4" width="290" height="600"> | <img src="https://github.com/ifMaxi/Contact/assets/112733459/003038b0-57bf-43c7-a2cc-776fe2c4eff6" width="290" height="600"> | <img src="https://github.com/ifMaxi/Contact/assets/112733459/8edb6448-220e-454c-a64d-9f33292f5737" width="290" height="600"> |

**Dark mode**


| Main screen | Detail contact |
| ----------- | -------------- |
| <img src="https://github.com/ifMaxi/Contact/assets/112733459/1a95942b-a816-4bbd-b5f7-9a6fbbc90c0f" width="290" height="600"> | <img src="https://github.com/ifMaxi/Contact/assets/112733459/a7ccf03e-9605-41ad-9db4-7419a20e2e5f" width="290" height="600"> |
