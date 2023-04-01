import './App.css';

import { Helmet } from "react-helmet";

import {
  IndexPage,
  LoginPage,
  RegistrationPage
} from "./pages";

import {Route, Routes} from "react-router-dom";

function App() {
  return (
      <div>
        <Helmet>
          <title>Tamburinektor</title>
          <link href='https://fonts.googleapis.com/css?family=Manrope' rel='stylesheet'/>
          <meta name="description" content="My app description" />
        </Helmet>
        <Routes>
          <Route exact path="/" element={<IndexPage/>}/>
          <Route exact path="/login" element={<LoginPage/>}/>
          <Route exact path="/register" element={<RegistrationPage/>}/>
        </Routes>
      </div>
  );
}

export default App;
