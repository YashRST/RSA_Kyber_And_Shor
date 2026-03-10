# RSA, Kyber, and Shor: Classical to Post-Quantum Cryptography in Java

## 1) Professional Project Description
This repository is a Java cryptography project that explores security across three important perspectives:
- **Classical cryptography** through an RSA implementation,
- **Post-quantum cryptography** through a Kyber-style key encapsulation workflow, and
- **Quantum-era security analysis** through the Shor’s algorithm factorisation model.

The project is designed as a practical software-engineering codebase: modular packages, interface-driven components, and test coverage for core primitives. It is ideal for demonstrating both **applied cryptography development** and **forward-looking quantum security understanding**.

### Project Purpose
The core objective is to show how modern developers can:
1. Build and test real cryptographic modules (RSA/Kyber),
2. Compare classical assumptions with post-quantum alternatives, and
3. Understand how Shor’s algorithm changes the risk profile of RSA-based systems.

### Key Capabilities Implemented in This Repository
- **RSA module**
  - Public/private key generation,
  - Encryption/decryption flows,
  - Supporting number-theory operations via reusable utility classes.

- **Kyber module**
  - Parameterized key-pair structures,
  - Shared-secret generation workflow,
  - Dedicated key/parameter abstractions to keep data handling explicit and maintainable.

- **Engineering utilities**
  - Hashing abstraction,
  - Serialization/deserialization components,
  - Mathematical helper library,
  - Unit tests for RSA, Kyber, and utilities.

### Shor’s Algorithm Emphasis (for Quantum Factorisation Context)
This project highlights how Shor’s algorithm impacts cryptography by focusing on the algorithmic chain:
- convert factorisation into order-finding,
- estimate period via quantum routines,
- recover non-trivial factors from measured period values.

In practical software terms, this repository positions Shor’s algorithm as the **security analysis lens** that motivates migration from RSA-centric security to post-quantum approaches such as Kyber.

### Technical Stack
- **Language:** Java
- **Build Tool:** Maven
- **Testing:** JUnit 4
- **Libraries:** EJML, Bouncy Castle utilities
- **Architecture Style:** Interface-first package design (`interfaces`, `source`, `parameters`) with modular separation between RSA, Kyber, and utilities.

### Why This Project Is Valuable
- Connects cryptography theory to production-style code organization,
- Demonstrates clear software design in a security domain,
- Communicates post-quantum transition thinking (important for future-proof systems),
- Provides a foundation for extending into quantum SDK integrations and algorithm research.# RSA_And_Kyber
