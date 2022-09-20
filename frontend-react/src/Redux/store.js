import { configureStore, combineReducers } from "@reduxjs/toolkit";
import storage from "redux-persist/lib/storage";
import {

    persistStore,
  
    persistReducer,
  
    FLUSH,
  
    REHYDRATE,
  
    PAUSE,
  
    PERSIST,
  
    PURGE,
  
    REGISTER,
  
  } from "redux-persist";

import patientIdReducer from "./PatientSlice"

const persistConfig = { 
    key: 'root',
    version: '1',
    storage,
    blacklist: ['navigation']
};

const rootReducer = combineReducers({
    patient : patientIdReducer,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
    reducer:persistedReducer,
    middleware: (getDefaultMiddleware) => 
    getDefaultMiddleware({
        serializableCheck:{
            ignoreActions : [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
        },
    }),
});

export let persistor = persistStore(store);