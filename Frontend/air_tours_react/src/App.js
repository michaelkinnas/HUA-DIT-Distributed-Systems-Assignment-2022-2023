import { Link, Routes, Route } from "react-router-dom";
import About from "./components/About";
import Index from "./components/Index";
import { UserProvider } from './UserContext'

function App() {
  return (
    <div>
      <UserProvider>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/about">About</Link>
            </li>
          </ul>
        </nav>
        <Routes>

          <Route path="/" element={<Index />} />
          <Route path="/about" element={<About />} />

        </Routes>
      </UserProvider>
    </div>
  );
}

export default App;
