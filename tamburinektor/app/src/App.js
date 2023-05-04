import { Helmet } from "react-helmet";

import {
    DashboardPageStudent,
    DashboardPageTeacher,
    IndexPage,
    LoginPage,
    RegistrationPage,
    TestCreatePage,
    LectureCreatePage,
    ClassRoomPage
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
            {localStorage.getItem("role") === "ROLE_TEACHER" && <Route exact path="/dashboard" element={<DashboardPageTeacher/>}/>}
            {localStorage.getItem("role") === "ROLE_STUDENT" && <Route exact path="/dashboard" element={<DashboardPageStudent/>}/>}
            <Route exact path="/dashboard" element={<DashboardPageStudent/>}/>}
            <Route exact path="/test/creation" element={<TestCreatePage/>}/>
            <Route exact path="/lecture/creation" element={<LectureCreatePage/>}/>
            <Route exact path="/classroom" element={<ClassRoomPage/>}/>
        </Routes>
      </div>
  );
}

export default App;
