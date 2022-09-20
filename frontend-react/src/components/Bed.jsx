import axios from "axios";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import authHeader from "../services/auth-header";
import { FaBed } from "react-icons/fa";
import { fetchPatientIdSuccess } from "../Redux/PatientSlice";
import BedService from "../services/bed.service";

/**
 * This component displays all the beds in a particular floor
 *
 * @param {bed}  BedDetails
 */

const Bed = ({ bed }) => {
  const { REACT_APP_API_ENDPOINT } = process.env;
  const [patientData, setPatientData] = useState();
  const [gender, setGender] = useState();
  const patient = useSelector((state) => state.patient.patientId);
  const dispatch = useDispatch();

  /**
   * @param {bed.id} BedId of the bed
   * @return BedAssignment details
   */

  useEffect(() => {
    BedService.getBedAssignmentsByBid(bed.id)
      .then((response) => {
        console.log("Api data:", response);
        setGender(response.patient.gender);
        setPatientData(response.patient);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  /**
   * Handles Assigning of the bed
   */

  const handleBedClick = (e, id) => {
    e.preventDefault();

    if (patient === null) {
      alert("Please select a patient");
    } else {
      const assignment = {
        beds: {
          id: id,
        },

        patient: {
          id: patient.id,
        },
      };

      axios
        .post(
          `${REACT_APP_API_ENDPOINT}/test/bedassignment/assign`,
          assignment,
          { headers: authHeader() }
        )
        .then((response) => {
          console.log(response);
          alert("Bed assigned to patient successfully");
          setGender(patient.gender);
          dispatch(fetchPatientIdSuccess(null));
          window.location.reload(false);
        })
        .catch(function (error) {
          console.log(error);
          alert("The Patient or Bed is already assigned");
          dispatch(fetchPatientIdSuccess(null));
          window.location.reload(false);
        });
    }
  };
  return (
    <div className="bed-div">
      <button
        className={`bed-btn ${gender}`}
        onClick={(e) => handleBedClick(e, bed.id)}
      >
        <FaBed size="35px" /> <span>{bed.bedNo}</span>
        {patientData && <p>{patientData.name}</p>}
      </button>
    </div>
  );
};

export default Bed;
