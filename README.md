# Code_Review: You don't need to explain each and every module. Better explain it as an application like how it works, what are its limitations..
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
# Code_Review: You need to explain this section better. Like SQL is a language not a database so mention MySQL if it only supports that. Also mention which version of java is needed atleast.
To run the Ticket Management System, you will need the following:

- Java
- SQL (for database operations)

# Code_Review: Add a section on how to setup this application in their machine