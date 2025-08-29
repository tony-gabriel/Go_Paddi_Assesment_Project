# Go Paddi Android App

A modern Android travel management application built with Jetpack Compose. Go Paddi allows users to manage trips with full CRUD operations, providing a sleek and responsive user experience.

---

## Features

- **Modern UI**: Built with Jetpack Compose following Material Design 3.
- **API Integration**: Full CRUD operations via RESTful API.
- **Responsive Design**: Optimized for phones and tablets.
- **Form Validation**: User-friendly client-side validation and error feedback.
- **Real-time Updates**: UI automatically refreshes after API operations.

---

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **HTTP Client**: OkHTTP
- **State Management**: StateFlow & Compose State
- **Architecture**: MVVM with Repository Pattern

---

## API Endpoints

The app integrates with these RESTful endpoints (Base URL: `https://caca18968819c215e3d8.free.beeceptor.com`):

- `GET /api/trips` — Retrieve all trips
- `GET /api/trips/{id}` — Retrieve trip by ID
- `POST /trips` — Create a new trip
- `PUT /trips/{id}` — Update an existing trip
- `DELETE /trips/{id}` — Delete a trip

---

## Getting Started

### Prerequisites

- Android Studio Electric Eel or newer
- Android device or emulator (API level 21+)
- Internet connection for API access

### Installation

1. Clone this repository:
   ```sh
   git clone https://github.com/tony-gabriel/Go_Paddi_Assesment_Project.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run on your device or emulator.
5. **If the API token has exceeded its limit:**  
   Request a new token from [Beeceptor CRUD API](https://beeceptor.com/crud-api/) and replace the baseUrl named `tripsUrl` in the `Network.kt` file.

---

## Usage

1. Launch the app.
2. Start managing your trips—create, update, view, or delete.

---

## License

Distributed under the MIT License. See `LICENSE` for more information.

---

## Contact

**Author**: [Tony Gabriel](https://github.com/tony-gabriel)

---

## Acknowledgements

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Retrofit](https://square.github.io/retrofit/)
- [Beeceptor](https://beeceptor.com/)
