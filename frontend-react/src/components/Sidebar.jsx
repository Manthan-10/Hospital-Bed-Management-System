import React, { useState } from "react";
import Patient from "./Patient";

/**
 * This component displays all the Patients data in a Sidebar
 * @param {data} PatientDetails
 */
const Sidebar = ({ data }) => {
  const [searchTerm, setSearchTerm] = useState("");

  return (
    <nav className="sidebar">
      <div className="search-bar">
        <input
          type="text"
          className="search"
          placeholder="Search..."
          onChange={(event) => {
            setSearchTerm(event.target.value);
          }}
        />
      </div>
      {data &&
        data
          .filter((data) => {
            if (searchTerm === "") {
              return data;
            } else if (
              data.name.toLowerCase().includes(searchTerm.toLowerCase())
            ) {
              return data;
            }
          })
          .map((patient) => <Patient patient={patient} />)}
    </nav>
  );
};

export default Sidebar;
