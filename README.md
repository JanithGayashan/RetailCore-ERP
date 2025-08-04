# RetailCore-ERP: A Modern E-commerce & Fulfillment Platform

This project is a complete, headless ERP system designed for modern retail and e-commerce businesses. It includes modules for product catalog management, inventory and warehouse management (WMS), order processing, and fulfillment logistics.

The architecture is API-first, with a robust backend capable of serving multiple frontends.

## Core Modules
- **`backend`**: The Spring Boot application serving the core REST API and business logic.
- **`storefront`**: The public-facing e-commerce website built with Angular, featuring Server-Side Rendering (SSR) for performance and SEO.
- **`portal`**: The internal administrative and warehouse management application, also built with Angular.

## Technology Stack
- **Backend:** Java 21, Spring Boot 3
- **Database:** PostgreSQL (managed via Docker)
- **Frontend:** TypeScript, Angular 17+
- **Architecture:** Headless, API-First
- **Containerization:** Docker / Docker Compose