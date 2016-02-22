package org.csstudio.saverestore.data;

import java.io.Serializable;

import org.diirt.vtype.Alarm;
import org.diirt.vtype.AlarmSeverity;
import org.diirt.vtype.VType;

/**
 *
 * <code>VNoData</code> represents a {@link VType} without any known value, while not being disconnected.
 *
 * @author <a href="mailto:jaka.bobnar@cosylab.com">Jaka Bobnar</a>
 *
 */
public class VNoData implements VType, Alarm, Serializable {

    private static final long serialVersionUID = -2399970529728581034L;

    /** The singleton instance */
    public static final VNoData INSTANCE = new VNoData();

    private static final String TO_STRING = "---";

    private VNoData() {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return TO_STRING;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.diirt.vtype.Alarm#getAlarmSeverity()
     */
    @Override
    public AlarmSeverity getAlarmSeverity() {
        return AlarmSeverity.NONE;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.diirt.vtype.Alarm#getAlarmName()
     */
    @Override
    public String getAlarmName() {
        return TO_STRING;
    }
}
