import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import BedService from "../services/bed.service";
import Bed from "./BedList";
import "./Room.css";

/**
 * This component displays all the Rooms in a particular Floor
 *
 * @param {fid} FloorId of that Floor
 */
const Room = ({ fid }) => {
  const [data, setRoomList] = useState([]);
  const { t } = useTranslation();

  /**
   * @param {fid} FloorId of that Floor
   * @return All Rooms and its details of that Floor
   */
  useEffect(() => {
    BedService.getRoomsByFid(fid)
      .then((response) => {
        console.log(response);
        setRoomList(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  return (
    <div className="room">
      <div className="headings">
        {data.map((room, index) =>
          index > -1 && index < 1 ? (
            <div key={room.id}>
              <p className="floor-heading">
                {room.floor.floorNo} {t("Floor")}
              </p>
            </div>
          ) : undefined
        )}
      </div>

      <div className="room-wrapper">
        {data.map((room) => (
          <div key={room.id}>
            <p className="room-heading">
              {t("Room")} {room.roomNo}
            </p>
            <Bed rid={room.id} />
          </div>
        ))}
      </div>
    </div>
  );
};

export default Room;
