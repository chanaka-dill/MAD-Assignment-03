# MAD-Assignment-03
SEN4302 - Mobile Application Development Assignment 03, Student ID: 11366

# TaskNoteApp

## App Description

TaskNoteApp is a simple and modern Android task management application built using Kotlin and Android Studio.  
The application allows users to create, view, complete, and delete tasks easily.

The app stores task data locally using SharedPreferences and follows basic secure coding practices such as input validation and safe data storage considerations.

---

## Features

- Add new tasks (Title required, Description optional)
- View tasks in a clean modern UI
- Mark tasks as completed
- Delete tasks
- Persistent local storage (data remains after app restart)
- Input validation for secure and stable behavior

---

## Secure Coding Awareness

This project demonstrates secure coding practices:

1. **Input Validation**
   - Prevents saving empty task titles.
   - Avoids invalid or unexpected application behavior.

2. **Safe Data Storage**
   - Only non-sensitive data is stored in SharedPreferences.
   - No hard-coded passwords, API keys, or tokens.
   - Uses MODE_PRIVATE to restrict app data access.

---

## Architecture & Design Choices

- **MVVM-like Structure**
  - Model → `Task`
  - View → Activities & XML layouts
  - Repository → Handles data persistence
  - ViewModel → Manages UI-related data

- **RecyclerView**
  Used to efficiently display dynamic task lists.

- **Material Design Components**
  Used TextInputLayout and MaterialButton for a clean modern UI.

- **Gson Library**
  Used to convert task objects into JSON format for storage.

---

## Screenshots

### Main Screen
<img width="461" height="989" alt="1" src="https://github.com/user-attachments/assets/a55bc923-6472-477b-aa32-fd9692ff87b4" />


### Add Task Screen
<img width="440" height="913" alt="2" src="https://github.com/user-attachments/assets/77b6db2b-fbd6-482d-a000-934c47e4e3d4" />


### Completed Task Example
<img width="424" height="921" alt="3" src="https://github.com/user-attachments/assets/652bcba9-5589-4901-90b1-26c4fbe3421a" />
<img width="433" height="892" alt="4" src="https://github.com/user-attachments/assets/ee33778c-4b51-4e33-b15c-0b94c110873d" />

---

## Technologies Used

- Kotlin
- Android Studio
- RecyclerView
- SharedPreferences
- Gson
- Material Design Components

---

## Getting Started
To get a local copy of this project up and running:

Clone the repository:
   ```sh
   git clone https://github.com/chanaka-dill/MAD-Assignment-03.git
```
---

B.R.C. Dilshan 
Atudent ID: 11366
SEN4302 - Mobile Application Development
Assignment 03 – Mini Project
