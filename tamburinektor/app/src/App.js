import './App.css';

import {
  IndexPage
} from "./pages";
import {Route, Routes} from "react-router-dom";

function App() {
  return (
      <div>
        <Routes>
          <Route exact path="/" element={<IndexPage/>}/>
        </Routes>
      </div>
  );
}

export default App;
