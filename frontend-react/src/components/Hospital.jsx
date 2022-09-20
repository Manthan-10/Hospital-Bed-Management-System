import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import FloorDropdown from "./FloorDropdown.jsx";
import HospitalDropdown from "./HospitalDropdown.jsx";
import "./Hospital.css";
import BedService from "../services/bed.service.js";
import { useTranslation } from "react-i18next";

/**
 * This component displays the dropdowns of both Hospital and Floor
 *
 */
const Hospital = () => {
  const { hid } = useParams();
  const [hospital, setHospital] = useState(null);
  const { t } = useTranslation();

  /**
   * @param {hid} HospitalId of that Hospital
   * @return Hospital Details of that hospital
   */
  useEffect(() => {
    BedService.getHospitalById(hid).then((response) => {
      console.log(response);
      setHospital(response);
    });
  }, []);

  return (
    <>
      <div className="hospital">
        <div className="hospital-wrapper">
          <div className="hospital-dropdown">
            <HospitalDropdown />
          </div>
          <div className="floor-dropdown">
            <FloorDropdown hid={hid} />
          </div>
        </div>

        {hospital && (
          <p className="hospital-heading">
            {hospital.name} {t("Hospital")}
          </p>
        )}
      </div>
    </>
  );
};

export default Hospital;
