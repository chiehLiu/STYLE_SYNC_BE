```mermaid
sequenceDiagram
    participant U as User
    participant F as Frontend (Angular)
    participant A as Auth Guard
    participant B as Backend API
    participant DB as Database
    participant JWT as JWT Service

    U->>F: Enter credentials
    F->>F: Validate input format

    F->>B: POST /api/auth/login
    
    B->>DB: Check credentials
    DB-->>B: Return user data
    
    alt Invalid Credentials
        B-->>F: Return 401 Unauthorized
        F-->>U: Show error message
    else Valid Credentials
        B->>JWT: Generate JWT token
        JWT-->>B: Return token
        B-->>F: Return 200 OK with token
        F->>F: Store token in localStorage
        F->>A: Update authentication state
        F-->>U: Redirect to dashboard
    end

    Note over F,B: Subsequent requests will include JWT token in Authorization header
    