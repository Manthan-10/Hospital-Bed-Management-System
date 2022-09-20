import React, { useEffect, useState } from "react";
import BedService from "../services/bed.service";
import "./Bed.css";
import Bed from "./Bed";

/**
 * This component displays all the beds in a particular Room
 * using roomId of that Room
 * @param {rid}  RoomId
 */
const BedList = ({ rid }) => {
  const [data, setBedList] = useState([]);

  /**
   * @param {rid} RoomId of that Room
   * @return Beds for that Particular Room
   */

  useEffect(() => {
    BedService.getBedsByRid(rid)
      .then((response) => {
        console.log(response);
        setBedList(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  return (
    <div className="bed">
      {data.map((bed) => (
        <Bed bed={bed} key={bed.id} />
      ))}
    </div>
  );
};

export default BedList;
