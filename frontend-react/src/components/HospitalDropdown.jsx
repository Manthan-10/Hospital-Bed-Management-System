import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { fetchPatientIdSuccess } from "../Redux/PatientSlice";
import BedService from "../services/bed.service";

import "./HospitalDropdown.css";

/**
 * This component displays all the Hospitals in a dropdown
 *
 */
const HospitalDropdown = () => {
  const [data, setHospitalList] = useState([]);
  const { t } = useTranslation();

  /**
   * @return Details of all the Hospitals
   *
   */
  useEffect(() => {
    BedService.getHospitals()
      .then((response) => {
        console.log(response);
        setHospitalList(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  let navigate = useNavigate();
  const dispatch = useDispatch();

  /**
   * Handles Choosing of Hospitals in a dropdown
   */
  function handleChange(value) {
    if (value === "1") {
      navigate(`/hospitals/${value}/floor/1`);
      dispatch(fetchPatientIdSuccess(null));
      window.location.reload(false);
    } else {
      navigate(`/hospitals/${value}/floor/4`);
      dispatch(fetchPatientIdSuccess(null));
      window.location.reload(false);
    }
  }

  return (
    <div className="hsp-dropdown">
      <select
        className="select-container"
        size="large"
        onChange={(event) => handleChange(event.target.value)}
      >
        <option>{t("Select_Hospital")}</option>
        {data.map((hospital) => (
          <option key={hospital.id} value={hospital.id}>
            {hospital.name}
          </option>
        ))}
      </select>
    </div>
  );
};

export default HospitalDropdown;
