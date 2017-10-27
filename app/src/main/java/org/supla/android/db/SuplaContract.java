package org.supla.android.db;

/*
 Copyright (C) AC SOFTWARE SP. Z O.O.

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import android.provider.BaseColumns;

public class SuplaContract {

    public SuplaContract() {}

    public static abstract class AccessIDEntry implements BaseColumns {

        public static final String TABLE_NAME = "accessid";

        public static final String _ID = "_id";  // Primary Key
        public static final String COLUMN_NAME_ACCESSID = "accessid"; // SuplaCfg.AccessID
        public static final String COLUMN_NAME_SERVERADDRESS = "serveraddress"; // SuplaCfg.Host

    }

    public static abstract class LocationEntry implements BaseColumns {

        public static final String TABLE_NAME = "location";

        public static final String _ID = "_id"; // Primary Key
        public static final String COLUMN_NAME_LOCATIONID = "locationid"; // SuplaLocation.Id
        public static final String COLUMN_NAME_CAPTION = "caption";
        public static final String COLUMN_NAME_VISIBLE = "visible";
        public static final String COLUMN_NAME_ACCESSID = "accessid"; // AccessIDEntry.COLUMN_NAME_ID


    }

    public static abstract class ChannelEntry implements BaseColumns {

        public static final String TABLE_NAME = "channel";

        public static final String _ID = "_id"; // Primary Key
        public static final String COLUMN_NAME_CHANNELID = "channelid"; // SuplaChannel.Id
        public static final String COLUMN_NAME_CAPTION = "caption";
        public static final String COLUMN_NAME_FUNC = "func";
        public static final String COLUMN_NAME_ONLINE = "online";
        public static final String COLUMN_NAME_SUBVALUE = "subvalue";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_VISIBLE = "visible";
        public static final String COLUMN_NAME_LOCATIONID = "locatonid"; // LocationEntry.COLUMN_NAME_ID
        public static final String COLUMN_NAME_ALTICON = "alticon";
        public static final String COLUMN_NAME_FLAGS = "flags";
        public static final String COLUMN_NAME_PROTOCOLVERSION = "protocolversion";
    }

    public static abstract class ColorListItemEntry implements BaseColumns {

        public static final String TABLE_NAME = "color_list_item";

        public static final String _ID = "_id"; // Primary Key
        public static final String COLUMN_NAME_CHANNEL = "channel"; // ChannelEntry._ID
        public static final String COLUMN_NAME_IDX = "idx";
        public static final String COLUMN_NAME_COLOR = "color";
        public static final String COLUMN_NAME_BRIGHTNESS = "brightness";
    }
}
