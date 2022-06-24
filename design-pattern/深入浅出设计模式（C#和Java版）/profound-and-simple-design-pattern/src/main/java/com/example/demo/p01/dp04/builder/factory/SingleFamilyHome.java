package com.example.demo.p01.dp04.builder.factory;

import com.example.demo.p01.dp04.builder.bean.Room;

import java.util.*;

//单一家庭房屋，有5间房间，配有后院：
public class SingleFamilyHome implements House {
    private final boolean mblnBackyard;
    private final Vector Rooms;

    public SingleFamilyHome() {
        Room room = new Room();
        Rooms = new Vector();
        room.roomName = "Master Bedroom";
        Rooms.addElement(room);
        room = new Room();
        room.roomName = "Second Bedroom";
        Rooms.addElement(room);
        room.roomName = "Thrid Room";
        Rooms.addElement(room);
        room = new Room();
        room.roomName = "Living Room";
        Rooms.addElement(room);
        room = new Room();
        room.roomName = "Guest Room";
        Rooms.addElement(room);
        mblnBackyard = true;
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
        strDescription = strDescription + "这间房子有后院\n";
        for (int i = 1; i <= Rooms.size(); i++) {
            strDescription = strDescription + "\n" + "房间" + i + "\t" + ((Room) Rooms.elementAt(i - 1)).roomName;
        }
        return strDescription;
    }
}
