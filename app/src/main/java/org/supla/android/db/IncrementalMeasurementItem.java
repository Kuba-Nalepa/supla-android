package org.supla.android.db;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class IncrementalMeasurementItem extends MeasurementItem {
    protected boolean Calculated;
    protected boolean Divided;

    public IncrementalMeasurementItem() {
        super();
        Calculated = false;
        Divided = false;
    }

    public IncrementalMeasurementItem(IncrementalMeasurementItem src) {
        super(src);
        Calculated = src.Calculated;
        Divided = src.Divided;
    }

    public boolean isCalculated() {
        return Calculated;
    }

    public boolean isDivided() {
        return Divided;
    }

    public abstract void AssignJSONObject(JSONObject obj) throws JSONException;

    public abstract void DivideBy(long div);

    public abstract void Calculate(IncrementalMeasurementItem item);
}