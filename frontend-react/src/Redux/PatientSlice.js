import { createSlice } from "@reduxjs/toolkit";

const patientSlice = createSlice({
    name : "patient",

    initialState: {
        patientId: null,
        isFetching: false,
        error:false,
        errorStatus:"",
    },

    reducers: {

        fetchPatientIdStart: (state) => {
            state.isFetching = true;
        },

        fetchPatientIdSuccess: (state, action) => {
            state.isFetching = false;
            state.patientId = action.payload;
        },

        fetchPatientIdFailure: (state, action) => {
            state.isFetching = false;
            state.error = true;
            state.errorStatus = action.payload;
        },
    },
});

export const {fetchPatientIdStart, fetchPatientIdSuccess, fetchPatientIdFailure} = patientSlice.actions;
export default patientSlice.reducer;