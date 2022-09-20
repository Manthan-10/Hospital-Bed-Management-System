import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router-dom";
import BedService from "../services/bed.service";
import "./FloorDropdown.css";

/**
 * This component displays all the floors in a particular Hospital
 * in a dropdown
 * @param {hid} HospitalId of that Hospital
 */
const FloorDropdown = ({ hid }) => {
  const [data, setFloorList] = useState([]);
  const { t } = useTranslation();

  /**
   * @param {hid} HospitalId of that Hospital
   * @return Floor Details of that hospital
   */
  useEffect(() => {
    BedService.getFloorsByHid(hid)
      .then((response) => {
        console.log(response);
        setFloorList(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  let navigate = useNavigate();

  /**
   * Handles Choosing of floors in a dropdown
   */
  function handleChange(value) {
    navigate(`/hospitals/${hid}/floor/${value}`);
    window.location.reload(false);
  }

  return (
    <div className="floors">
      <select
        className="select-container"
        size="large"
        onChange={(event) => handleChange(event.target.value)}
      >
        <option>{t("Select_Floor")} </option>
        {data.map((floor) => (
          <option key={floor.id} value={floor.id}>
            {floor.floorNo}
          </option>
        ))}
      </select>
    </div>
  );
};

export default FloorDropdown;
