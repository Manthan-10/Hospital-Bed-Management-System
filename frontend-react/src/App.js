import React from "react";
import { Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import LoginContainer from "./LoginContainer";
import DefaultContainer from "./DefaultContainer";
import Login from "./components/Login"
import Hospital from "./components/Hospital";
import Floor from "./components/Floor";


const App = () => {

  
  return (
    <div>
      
        <Routes>
          <Route element={<LoginContainer/>}>
            <Route path="/" element={<Login/>}/>
            <Route path="/login" element={<Login/>}/>
          </Route>

          <Route element={<DefaultContainer/>}>
          <Route path="/hospitals/" element={<Hospital/>}/>
            <Route path="/hospitals/:hid" element={<Hospital/>}/>
            <Route path="/hospitals/:hid/floor/:fid" element={<Floor/>}/>
          </Route>
          
         
        </Routes>
    </div>
  );
};
export default App;