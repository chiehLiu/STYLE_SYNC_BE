```mermaid
sequenceDiagram
    actor Customer
    participant App
    participant System
    participant Stylist

    Customer->>App: Opens application
    App->>Customer: Displays available services

    Note over Customer,App: Service Selection
    Customer->>App: Browses services
    Customer->>App: Selects desired service
    App->>Customer: Shows service details and pricing

    Note over Customer,App: Stylist Selection
    Customer->>App: Views available stylists
    App->>Customer: Displays stylist profiles & ratings
    Customer->>App: Selects preferred stylist

    Note over Customer,System: Date & Time Selection
    Customer->>App: Requests available dates
    App->>System: Checks stylist schedule
    System->>App: Returns available slots
    App->>Customer: Shows available time slots
    Customer->>App: Selects date and time

    Note over Customer,System: Booking Confirmation
    Customer->>App: Confirms booking details
    App->>System: Validates appointment
    System->>Stylist: Sends notification
    System->>Customer: Sends confirmation email
    App->>Customer: Shows booking confirmation

    Note over Customer,System: Pre-Appointment
    System->>Customer: Sends reminder (24h before)
    Customer->>App: Can view/modify/cancel booking
```
