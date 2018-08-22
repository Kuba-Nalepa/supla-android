package org.supla.android.restapi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;
import org.json.JSONObject;
import org.supla.android.Trace;
import org.supla.android.db.DbHelper;
import org.supla.android.db.ElectricityMeasurementItem;


public class DownloadElectricityMeterMeasurements extends DownloadMeasurementLogs {

    private ElectricityMeasurementItem older_emi = null;
    private ElectricityMeasurementItem younger_emi = null;
    private boolean added = false;

    public DownloadElectricityMeterMeasurements(Context context) {
        super(context);
    }

    protected long getMaxTimestamp() {
        return getMeasurementsDbH().getElectricityMeterMaxTimestamp(getChannelId());
    }

    protected void SaveMeasurementItem(SQLiteDatabase db,
                                       long timestamp, JSONObject obj) throws JSONException {

        younger_emi = new ElectricityMeasurementItem();
        younger_emi.AssignJSONObject(obj);
        younger_emi.setChannelId(getChannelId());

        if (older_emi==null) {
            older_emi = getMeasurementsDbH().getOlderUncalculatedElectricityMeasurement(db,
                    younger_emi.getChannelId(),
                    younger_emi.getTimestamp());

            if (older_emi!=null) {
                getMeasurementsDbH().deleteUncalculatedElectricityMeasurements(db,
                        younger_emi.getChannelId());
            }
        }

        if (older_emi!=null) {
            if (younger_emi.getTimestamp() < older_emi.getTimestamp()) {
                throw new JSONException("Wrong timestamp order!");
            }

            ElectricityMeasurementItem cemi = new ElectricityMeasurementItem(younger_emi);
            cemi.Calculate(older_emi);

            long diff = cemi.getTimestamp() - older_emi.getTimestamp();


            if (diff >= 1200 ) {

                long n = diff / 600;
                cemi.DivideBy(n);

                for(int a=0;a<n;a++) {
                    getMeasurementsDbH().addElectricityMeasurement(db, cemi);
                    cemi.setTimestamp(cemi.getTimestamp()-600);
                }

            } else {
                getMeasurementsDbH().addElectricityMeasurement(db, cemi);
            }

            added = true;
        }

        older_emi = younger_emi;
    }

    protected void noRemoteDataAvailable(SQLiteDatabase db) throws JSONException {
        super.noRemoteDataAvailable(db);
        if (older_emi != null
                && added) {
            getMeasurementsDbH().addElectricityMeasurement(db, older_emi);
        }
    }

}