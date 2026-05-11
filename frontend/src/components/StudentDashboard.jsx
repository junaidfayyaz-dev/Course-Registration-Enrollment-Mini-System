import { useEffect, useState } from 'react'
import axios from 'axios'

function StudentDashboard() {
  const [students, setStudents] = useState([])
  const [selectedStudentId, setSelectedStudentId] = useState('')
  const [registrations, setRegistrations] = useState([])
  const [statusFilter, setStatusFilter] = useState('ALL')

  // Load students
  useEffect(() => {
    axios.get('/api/students')
        .then(res => setStudents(res.data))
        .catch(err => console.log(err))
  }, [])

  // Load registrations
  const loadRegistrations = async (studentId) => {
    setSelectedStudentId(studentId)

    if (!studentId) {
      setRegistrations([])
      return
    }

    try {
      const res = await axios.get(`/api/students/${studentId}/registrations`)
      setRegistrations(res.data)
    } catch (err) {
      console.log(err)
      setRegistrations([])
    }
  }

  // Filter registrations
  const visible = registrations.filter(r =>
      statusFilter === 'ALL' ? true : r.status === statusFilter
  )

  return (
      <section>
        <h2>Student Dashboard</h2>

        <div className="controls">
          <select
              value={selectedStudentId}
              onChange={(e) => loadRegistrations(e.target.value)}
          >
            <option value="">Select Student</option>
            {students.map(s => (
                <option key={s.id} value={s.id}>
                  {s.name} ({s.rollNo})
                </option>
            ))}
          </select>

          <select
              value={statusFilter}
              onChange={(e) => setStatusFilter(e.target.value)}
          >
            <option value="ALL">All Statuses</option>
            <option value="REGISTERED">REGISTERED</option>
            <option value="COMPLETED">COMPLETED</option>
            <option value="DROPPED">DROPPED</option>
          </select>
        </div>

        <table border="1">
          <thead>
          <tr>
            <th>Course Code</th>
            <th>Course Title</th>
            <th>Semester</th>
            <th>Status</th>
            <th>Grade</th>
          </tr>
          </thead>

          <tbody>
          {visible.length > 0 ? (
              visible.map(r => (
                  <tr key={r.id}>
                    {/* IMPORTANT: assumes backend returns full object */}
                    <td>{r.course?.code || r.courseCode}</td>
                    <td>{r.course?.title || r.courseTitle}</td>
                    <td>{r.semester}</td>
                    <td>{r.status}</td>
                    <td>{r.grade || '-'}</td>
                  </tr>
              ))
          ) : (
              <tr>
                <td colSpan="5">No registrations found</td>
              </tr>
          )}
          </tbody>
        </table>
      </section>
  )
}

export default StudentDashboard