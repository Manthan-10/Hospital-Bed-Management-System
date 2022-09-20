import React from "react";
import { useParams } from "react-router";
import Hospital from "./Hospital";
import Room from "./Room";
import "./Floor.css";

/**
 * This component displays the floor in a particular Hospital
 *
 */
const Floor = () => {
  const { fid } = useParams();

  return (
    <div>
      <Hospital />
      <div className="rooms">
        <Room fid={fid} />
      </div>
    </div>
  );
};

export default Floor;
