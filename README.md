# Ticket Management System

## Introduction

The Ticket Management System is a project designed to efficiently handle and resolve user-raised tickets by ticket resolvers.

## Functionality

### User Registration

- Users can register in the system, choosing to sign up either as a regular user or as a ticket resolver.

### User Login

- Registered users can log in using their credentials.

### Ticket Submission

- Logged-in users can create and submit tickets related to products with which they are dissatisfied.

### Ticket Viewing

- Users can view the tickets they have submitted.

### Resolver Login

- Ticket resolvers can log in to the system using their credentials.

### Ticket Assignment

- Resolvers have the capability to view and assign tickets raised by users to themselves for resolution. Once assigned, the ticket becomes unavailable for other resolvers to work on.

### Ticket Resolution

- Resolvers can work on and resolve the assigned tickets.

## Data Storage

In this system, we utilize two main tables for data storage:

- **Users Table**: This table stores user credentials, including usernames, passwords and roles.

- **Tickets Table**: Here, we store ticket details, to track and manage user-raised tickets.

## Requirements
To run the Ticket Management System, you will need the following:

- Java
- SQL (for database operations)
