**Secure Chat Application**

This is a secure chat application built using Java, implementing multithreading to handle multiple clients simultaneously and cryptographic utilities to ensure secure communication between users. The application supports real-time messaging with encryption to safeguard data integrity and confidentiality.

**Features**
**End-to-End Encryption:** Uses cryptographic algorithms (e.g., AES/RSA) to encrypt messages exchanged between clients.
**Multithreading:** Ensures simultaneous handling of multiple clients in a scalable way, providing real-time communication.
**Client-Server Architecture:** The chat system follows a client-server model, where the server manages multiple client connections and the secure exchange of messages.
**Message Integrity:** Verifies that messages are not tampered with during transit by using cryptographic checks (e.g., message digests).
**User Authentication:** Basic user authentication to ensure secure access.
**Thread-Safe Communication:** Proper synchronization mechanisms for secure and safe message handling across multiple threads.
**How It Works**
**Multithreading**: The server handles multiple clients concurrently, assigning a dedicated thread to each connected client.
