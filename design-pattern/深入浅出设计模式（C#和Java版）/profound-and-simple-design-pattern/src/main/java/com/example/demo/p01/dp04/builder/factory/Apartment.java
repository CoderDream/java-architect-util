package com.example.demo.p01.dp04.builder.factory;

import com.example.demo.p01.dp04.builder.bean.Room;

import java.util.*;

/**
 * Apartment公寓，有3间房间，没有后院
 */
public class Apartment implements House {
    private final boolean mblnBackyard;
    private final Vector Rooms;

    public Apartment() {
        Room room = new Room();
        Rooms = new Vector();
        room.roomName = "Master Room";
        Rooms.addElement(room);
        room = new Room();
        room.roomName = "Second Bedroom";
        Rooms.addElement(room);
        room = new Room();
        room.roomName = "Living Room";
        Rooms.addElement(room);
        mblnBackyard = false;
    }

    public boolean GetBackyard() {
        return mblnBackyard;
    }

    public long NoOfRooms() {
        return Rooms.size();
    }

    public String Description() {
        String strDescription;
        strDescription = "这是一间公寓，有" + Rooms.size() + "间房间\n";
        strDescription = strDescription + "这间公寓没有后院";
        for (int i = 1; i <= Rooms.size(); i++) {
            strDescription = strDescription + "\n" + "房间" + i + "\t" + ((Room) Rooms.elementAt(i - 1)).roomName;
        }
        return strDescription;
    }
}
