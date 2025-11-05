import { useContext } from 'react'
import AuthContext from '../context/AuthContext'

const Dashboard = () => {
  const { user } = useContext(AuthContext)

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-3xl font-bold mb-6">Dashboard</h1>
        <div className="bg-white rounded-lg shadow-md p-6">
          <h2 className="text-xl font-semibold mb-4">Welcome, {user?.username}!</h2>
          <div className="space-y-4">
            <div>
              <p className="text-gray-600">Email: {user?.email}</p>
            </div>
            <div>
              <p className="text-gray-600">
                Roles: {user?.roles?.join(', ')}
              </p>
            </div>
            <div className="mt-6">
              <h3 className="text-lg font-semibold mb-2">Quick Actions</h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <a
                  href="/products"
                  className="bg-blue-500 text-white p-4 rounded-lg hover:bg-blue-600 transition"
                >
                  <h4 className="font-semibold">Manage Products</h4>
                  <p className="text-sm mt-1">View and manage your products</p>
                </a>
                {user?.roles?.some((r) => r === 'ROLE_ADMIN') && (
                  <a
                    href="/admin"
                    className="bg-green-500 text-white p-4 rounded-lg hover:bg-green-600 transition"
                  >
                    <h4 className="font-semibold">Admin Panel</h4>
                    <p className="text-sm mt-1">Access admin features</p>
                  </a>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Dashboard

