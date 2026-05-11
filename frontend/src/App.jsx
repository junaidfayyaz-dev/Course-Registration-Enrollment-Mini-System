import CourseList from './components/CourseList'
import StudentDashboard from './components/StudentDashboard'

function App() {
  return (
    <div className="container">
      <h1>Course Registration & Enrollment Mini-System</h1>
      <CourseList />
      <hr />
      <StudentDashboard />
    </div>
  )
}

export default App
