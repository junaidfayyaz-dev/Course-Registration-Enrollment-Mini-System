import { useEffect, useState } from 'react'
import axios from 'axios'

function CourseList() {
  const [courses, setCourses] = useState([])
  const [students, setStudents] = useState([])
  const [filterText, setFilterText] = useState('')
  const [studentId, setStudentId] = useState('')
  const [semester, setSemester] = useState('Spring 2026')
  const [message, setMessage] = useState('')
  const [isError, setIsError] = useState(false)

  // Load data
  useEffect(() => {
    axios.get('/api/courses')
        .then(res => setCourses(res.data))
        .catch(err => console.log(err))

    axios.get('/api/students')
        .then(res => setStudents(res.data))
        .catch(err => console.log(err))
  }, [])

  // Register course
  const register = async (courseId) => {
    if (!studentId || !semester.trim()) {
      setIsError(true)
      setMessage('Please select student and semester')
      return
    }

    try {
      await axios.post('/api/registrations', {
        studentId: Number(studentId),
        courseId,
        semester
      })

      setIsError(false)
      setMessage('Registration successful')
    } catch (error) {
      setIsError(true)
      setMessage(error?.response?.data?.message || 'Registration failed')
    }
  }

  // Filter courses
  const filteredCourses = courses.filter(course => {
    const val = filterText.toLowerCase()
    return (
        course.code?.toLowerCase().includes(val) ||
        course.title?.toLowerCase().includes(val)
    )
  })

  return (
      <section>
        <h2>Course Listing</h2>

        <div className="controls">
          <input
              value={filterText}
              onChange={(e) => setFilterText(e.target.value)}
              placeholder="Filter by code/title"
          />

          <select value={studentId} onChange={(e) => setStudentId(e.target.value)}>
            <option value="">Select Student</option>
            {students.map(s => (
                <option key={s.id} value={s.id}>
                  {s.name} ({s.rollNo})
                </option>
            ))}
          </select>

          <input
              value={semester}
              onChange={(e) => setSemester(e.target.value)}
              placeholder="Semester"
          />
        </div>

        <table border="1">
          <thead>
          <tr>
            <th>Code</th>
            <th>Title</th>
            <th>Credit Hours</th>
            <th>Max Seats</th>
            <th>Action</th>
          </tr>
          </thead>

          <tbody>
          {filteredCourses.length > 0 ? (
              filteredCourses.map(course => (
                  <tr key={course.id}>
                    <td>{course.code}</td>
                    <td>{course.title}</td>
                    <td>{course.creditHours}</td>
                    <td>{course.maxSeats}</td>
                    <td>
                      <button onClick={() => register(course.id)}>
                        Register
                      </button>
                    </td>
                  </tr>
              ))
          ) : (
              <tr>
                <td colSpan="5">No courses found</td>
              </tr>
          )}
          </tbody>
        </table>

        {message && (
            <p style={{ color: isError ? 'red' : 'green' }}>
              {message}
            </p>
        )}
      </section>
  )
}

export default CourseList