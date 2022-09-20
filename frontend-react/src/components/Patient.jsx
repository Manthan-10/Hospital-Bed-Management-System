import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useDispatch } from "react-redux";
import { fetchPatientIdSuccess } from "../Redux/PatientSlice";
import BedService from "../services/bed.service";

/**
 * This component displays Patients their details
 *
 */
const Patient = ({ patient }) => {
  const { id, name, age, gender, address, dateOfBirth, phone, aadhaar } =
    patient;
  const [btnColor, setBtnColor] = useState(false);
  const [btnView, setBtnView] = useState("dont-view");

  const { t } = useTranslation();
  const dispatch = useDispatch();

  /**
   * @param {id} PatientId of the Patient
   * @return BedAssignment Details of that Patient
   */
  useEffect(() => {
    BedService.getBedAssignmentsByPid(id)
      .then((response) => {
        console.log("Api Bed Assignment data:", response);
        if (response.patient.id === id) {
          setBtnView("view");
          setBtnColor("#33547e");
        }
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  /**
   * @param {id} PatientId of the patient
   * It deletes the BedAssignment for that patient
   */
  const handleUnassignClick = (e, id) => {
    e.preventDefault();
    if (id != null) {
      BedService.deleteBedAssignmentByPid(id).then((response) => {
        console.log(response);
        alert("Bed assignment deleted successfully");
        dispatch(fetchPatientIdSuccess(null));
        window.location.reload(false);
      });
    } else {
      alert("Patient has not been assigned a bed");
    }
  };

  /**
   *
   * It stores the PatientId in Redux
   */
  const handlePatientClick = (e, id) => {
    e.preventDefault();
    dispatch(fetchPatientIdSuccess(patient));
    btnColor === "#007cba" ? setBtnColor("orange") : setBtnColor("#007cba");
  };

  return (
    <div key={id} className="all-patients">
      <div
        id={id}
        className="navlinks"
        onClick={(e) => handlePatientClick(e, id)}
        style={{ backgroundColor: btnColor }}
      >
        <p className="menu-group-Name">
          <span className="menu-info-Name">
            <strong>{t("Name")} :</strong>{" "}
          </span>
          <span className="menu-info-name">{name}</span>
        </p>

        <p className="menu-group-Age">
          <span className="menu-info-Age">
            <strong>{t("Age")} :</strong>{" "}
          </span>
          <span className="menu-info-age">{age}</span>
        </p>

        <p className="menu-group-Gender">
          <span className="menu-info-Gender">
            <strong>{t("Gender")} :</strong>{" "}
          </span>
          <span className="menu-info-gender">{gender}</span>
        </p>

        <p className="menu-group-Address">
          <span className="menu-info-Address">
            <strong>{t("Address")} :</strong>{" "}
          </span>
          <span className="menu-info-address">{address}</span>
        </p>

        <p className="menu-group-DOB">
          <span className="menu-info-DOB">
            <strong>{t("Date_Of_Birth")} :</strong>{" "}
          </span>
          <span className="menu-info-dob">{dateOfBirth}</span>
        </p>

        <p className="menu-group-Ph">
          <span className="menu-info-Phone">
            <strong>{t("Phone")} : </strong>
          </span>
          <span className="menu-info-phone">{phone}</span>
        </p>

        <p className="menu-group-ANo">
          <span className="menu-info-Aadhaar">
            <strong>Aadhaar No :</strong>{" "}
          </span>
          <span className="menu-info-aadhaar">{aadhaar}</span>
        </p>

        <span>
          <button
            className={`${btnView}`}
            onClick={(e) => handleUnassignClick(e, id)}
          >
            <strong>Unassign</strong>
          </button>
        </span>
      </div>
    </div>
  );
};

export default Patient;
