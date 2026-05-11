# Course Registration & Enrollment Mini-System 

## Project structure
- `backend` - Spring Boot + Spring Data JPA + REST
- `frontend` - React (Vite) UI

## Backend run
1. Install JDK 17.
2. Set `JAVA_HOME` to JDK 17 path.
3. Commands:
   - `cd backend`
   - `mvn spring-boot:run`
4. Base API URL: `http://localhost:8080/api`

## Frontend run
1. Commands:
   - `cd frontend`
   - `npm install`
   - `npm run dev`
2. Open the shown URL (usually `http://localhost:5173`).

## Implemented assignment requirements
- Student, Course, Registration entities with JPA relationships and validation.
- Repositories with derived methods:
  - `findByStudentId`
  - `findByCourseId`
  - `findBySemester`
- Seed data loader (5 students, 5 courses, 5 registrations, prerequisite included).
- CRUD APIs for students and courses.
- Registration APIs and required business rules:
  - max 5 active registrations in same semester
  - max seats for a course in a semester
  - prerequisite must be completed
- Central exception handling with JSON responses.
- React course listing + filter + registration form.
- Student dashboard with status filtering.
