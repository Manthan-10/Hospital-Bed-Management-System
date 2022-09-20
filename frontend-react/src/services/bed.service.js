import axios from "axios";
import authHeader from "./auth-header";


const { REACT_APP_API_ENDPOINT } = process.env;


const getBedDetails = () => {
  return axios.get(`${REACT_APP_API_ENDPOINT}/test/bed/getallbeds`, { headers: authHeader() });
};

const getHospitals = () => {
  return axios.get(`${REACT_APP_API_ENDPOINT}/test/hospital/gethospital`, {
    headers: authHeader(),
  });
};

const getFloorsByHid = (hid) => {
  return axios.get(`${REACT_APP_API_ENDPOINT}/test/floor/getbyhid/${hid}`, {
    headers: authHeader(),
  });
};

const getRoomsByFid = (fid) => {
  return axios.get(`${REACT_APP_API_ENDPOINT}/test/room/getbyfid/${fid}`, {
    headers: authHeader(),
  });
};

const getBedsByRid = (rid) => {
  return axios.get(`${REACT_APP_API_ENDPOINT}/test/bed/getbedsbyrid/${rid}`, {
    headers: authHeader(),
  });
};

const getBedAssignmentsByBid = async (bid) => {
  try {
    const bedassignment = await axios.get(
      `${REACT_APP_API_ENDPOINT}/test/bedassignment/getbedassignmentbybed/${bid}`,
      { headers: authHeader() }
    );
    return bedassignment.data;
  } catch (error) {
    console.log("Error", error);
  }
};

const getBedAssignmentsByPid = async (pid) => {
  try {
    const bedassignmentPatient = await axios.get(
      `${REACT_APP_API_ENDPOINT}/test/bedassignment/getbedassignmentbypatient/${pid}`,
      { headers: authHeader() }
    );
    return bedassignmentPatient.data;
  } catch (error) {
    console.log("Error", error);
  }
};

const deleteBedAssignmentByPid = async (pid) => {
  try {
    const delBedAssignment = await axios.delete(
      `${REACT_APP_API_ENDPOINT}/test/bedassignment/deletebedassignmentbypatient/${pid}`,
      { headers: authHeader() }
    );
    return delBedAssignment.data;
  } catch (error) {
    console.log("Error", error);
  }
}

const getPatientsByHid = async (hid) => {
  try {
    const patientDetails = await axios.get(
      `${REACT_APP_API_ENDPOINT}/test/patients/byhospitalid/${hid}`,
      { headers: authHeader() }
    );
    return patientDetails.data;
  } catch(error) {
    console.log("Error", error);
  }
};




const getHospitalById = async (hid) => {
  try {
    const hospitalDetails = await axios.get(
      `${REACT_APP_API_ENDPOINT}/test/hospital/${hid}`,
      { headers: authHeader() }
    );
    return hospitalDetails.data;
  } catch(error) {
    console.log("Error", error);
  }
};

const BedService = {
  getBedDetails,
  getHospitals,
  getFloorsByHid,
  getRoomsByFid,
  getBedsByRid,
  getBedAssignmentsByBid,
  getPatientsByHid,
  getHospitalById,
  getBedAssignmentsByPid,
  deleteBedAssignmentByPid
};
export default BedService;
