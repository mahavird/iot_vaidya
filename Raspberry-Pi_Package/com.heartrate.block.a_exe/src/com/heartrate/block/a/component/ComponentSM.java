package com.heartrate.block.a.component;

public class ComponentSM extends no.ntnu.item.arctis.runtime.IStateMachine {


    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b3_b0_MCP3008_ADC_t0;
    private com.heartrate.block.a.component.Component b000;
    private org.reactiveblocks.ibmiot.iotfoundation.IoTFoundation b001;
    private org.reactiveblocks.ibmiot.event.Event<java.lang.String> b022;
    private com.heartrate.block.sensorblock.SensorBlock b043;
    private com.heartrate.block.mcp3008adc.MCP3008ADC b044;
    private enum Org_reactiveblocks_ibmiot_IoTFoundation {STOPPING, ACTIVE, INITIALIZING, _IDLE};
    private enum Org_reactiveblocks_ibmiot_Event {_IDLE};
    private enum Com_heartrate_block_SensorBlock {ACTIVE, _IDLE};
    private enum Com_heartrate_block_MCP3008ADC {ACTIVE, _IDLE};
    private Org_reactiveblocks_ibmiot_IoTFoundation b001_state;
    private Org_reactiveblocks_ibmiot_Event b022_state;
    private Com_heartrate_block_SensorBlock b043_state;
    private Com_heartrate_block_MCP3008ADC b044_state;
    public ComponentSM(no.ntnu.item.arctis.runtime.Scheduler scheduler) {
        super(scheduler);
    }


    public int fireInitial() {
        b000 = new com.heartrate.block.a.component.Component();
        b000.setBlockID("b000", sessionID);
        b000.setRuntime(scheduler.getRuntime());
        b001 = new org.reactiveblocks.ibmiot.iotfoundation.IoTFoundation();
        b001.setBlockID("b001", sessionID);
        b001.setRuntime(scheduler.getRuntime());
        b022 = new org.reactiveblocks.ibmiot.event.Event<java.lang.String>("evt");
        b022.setBlockID("b022", sessionID);
        b022.setRuntime(scheduler.getRuntime());
        b043 = new com.heartrate.block.sensorblock.SensorBlock();
        b043.setBlockID("b043", sessionID);
        b043.setRuntime(scheduler.getRuntime());
        b044 = new com.heartrate.block.mcp3008adc.MCP3008ADC();
        b044.setBlockID("b044", sessionID);
        b044.setRuntime(scheduler.getRuntime());
        b001_state = Org_reactiveblocks_ibmiot_IoTFoundation._IDLE;
        b022_state = Org_reactiveblocks_ibmiot_Event._IDLE;
        b043_state = Com_heartrate_block_SensorBlock._IDLE;
        b044_state = Com_heartrate_block_MCP3008ADC._IDLE;
        _b3_b0_MCP3008_ADC_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b3_b0_MCP3008_ADC_t0", this);
        org.reactiveblocks.ibmiot.IOTParams b000_o0 = b000.createParams();
        no.ntnu.item.arctis.runtime.SingletonData b001_o0 = b001.wrap(b000_o0);
                    ((no.ntnu.item.arctis.runtime.SingletonData)b001_o0).setSessionID(this.sessionID);
            ((no.ntnu.item.arctis.runtime.SingletonData)b001_o0).setInstance("_b0_b0_Device_Singleton");
            scheduler.sendToSession(this, "singleton_-302180997", "singleton_start", b001_o0);
        b001_state = Org_reactiveblocks_ibmiot_IoTFoundation.INITIALIZING;
        // step 24ec6;
        return CONSUME_SIGNAL;
    }

    public int fireTimer(java.lang.String _timerID) {
        if(_timerID.equals("_b3_b0_MCP3008_ADC_t0")) {
            return handleTimer__b3_b0_MCP3008_ADC_t0();
        }
        return DISCARD_SIGNAL;
    }

    public int fire(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.String _signalID, java.lang.Object _data) {
        if(_signalID.equals("_b0_b0_Device_Singleton_ready")) {
            return handleEvent__b0_b0_Device_Singleton_ready(_sender, receiverSessionID, _data);
        }
        return TRIGGER_UNKNOWN;
    }

    private int handleTimer__b3_b0_MCP3008_ADC_t0() {
        java.lang.String b044_o2 = b044.read();
        java.lang.String b043_o1 = b043.heartbeat(b044_o2);
        java.lang.String b000_o3 = b000.sendData(b043_o1);
        no.ntnu.item.arctis.runtime.SingletonData b022_o1 = b022.wrap(b000_o3);
                    ((no.ntnu.item.arctis.runtime.SingletonData)b022_o1).setSessionID(this.sessionID);
            ((no.ntnu.item.arctis.runtime.SingletonData)b022_o1).setInstance("_b1_b0_Device_Singleton");
            scheduler.sendToSession(this, "singleton_-302180997", "singleton_event", b022_o1);
        // step d4209;
        return CONSUME_SIGNAL;
    }

    private int handleEvent__b0_b0_Device_Singleton_ready(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b0_b0_Device_Singleton_ready = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b001_state==Org_reactiveblocks_ibmiot_IoTFoundation.STOPPING) {
            // step b49f9;
            return CONSUME_SIGNAL;
        } else if(b001_state==Org_reactiveblocks_ibmiot_IoTFoundation.ACTIVE) {
            // step df2a6;
            return CONSUME_SIGNAL;
        } else if(b001_state==Org_reactiveblocks_ibmiot_IoTFoundation.INITIALIZING) {
            b000.ready();
            int b000_o4 = b000.samprate();
            com.heartrate.block.mcp3008adc.MCP3008ADC.Parameters b043_o0 = b043.getParams(b000_o4);
            b044.p = b043_o0;
            java.lang.String b044_o0 = b044.init();
            if(b044_o0==null) {
                b000.blockinitialized();
                scheduler.startBreak(_b3_b0_MCP3008_ADC_t0);
                b044_state = Com_heartrate_block_MCP3008ADC.ACTIVE;
                b043_state = Com_heartrate_block_SensorBlock.ACTIVE;
                b001_state = Org_reactiveblocks_ibmiot_IoTFoundation.ACTIVE;
                // step d9c47;
                return CONSUME_SIGNAL;
            } else {
                scheduler.stopBreak(_b3_b0_MCP3008_ADC_t0);
                b001_state = Org_reactiveblocks_ibmiot_IoTFoundation.ACTIVE;
                // step cd725;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    public java.lang.String getPartName() {
        return null;
    }
}
