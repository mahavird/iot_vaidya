package com.bitreactive.iotvaidyaadcevent.start.adcibmevent.component;

public class ComponentSM extends no.ntnu.item.arctis.runtime.IStateMachine {


    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b0_b0_c1_Timer_Periodic_2_t;
    private com.bitreactive.iotvaidyaadcevent.start.adcibmevent.component.Component b000;
    private com.bitreactive.library.rpi.peripherals.gp2y0a21yk0f.GP2Y0A21YK0F b001;
    private com.bitreactive.library.rpi.peripherals.mcp3008adc.MCP3008ADC b002;
    private com.bitreactive.library.timers.timerperiodic2.TimerPeriodic2 b003;
    private org.reactiveblocks.ibmiot.iotfoundation.IoTFoundation b004;
    private org.reactiveblocks.ibmiot.event.Event<java.lang.Object> b025;
    private enum Com_bitreactive_library_rpi_peripherals_GP2Y0A21YK0F {ACTIVE, _IDLE};
    private enum Com_bitreactive_library_rpi_peripherals_MCP3008ADC {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_timers_TimerPeriodic2 {_IDLE, ACTIVE};
    private enum Org_reactiveblocks_ibmiot_IoTFoundation {_IDLE, INITIALIZING, STOPPING, ACTIVE};
    private enum Org_reactiveblocks_ibmiot_Event {_IDLE};
    private Com_bitreactive_library_rpi_peripherals_GP2Y0A21YK0F b001_state;
    private Com_bitreactive_library_rpi_peripherals_MCP3008ADC b002_state;
    private Com_bitreactive_library_timers_TimerPeriodic2 b003_state;
    private Org_reactiveblocks_ibmiot_IoTFoundation b004_state;
    private Org_reactiveblocks_ibmiot_Event b025_state;
    public ComponentSM(no.ntnu.item.arctis.runtime.Scheduler scheduler) {
        super(scheduler);
    }


    public int fireInitial() {
        b000 = new com.bitreactive.iotvaidyaadcevent.start.adcibmevent.component.Component();
        b000.setBlockID("b000", sessionID);
        b000.setRuntime(scheduler.getRuntime());
        b001 = new com.bitreactive.library.rpi.peripherals.gp2y0a21yk0f.GP2Y0A21YK0F();
        b001.setBlockID("b001", sessionID);
        b001.setRuntime(scheduler.getRuntime());
        b002 = new com.bitreactive.library.rpi.peripherals.mcp3008adc.MCP3008ADC();
        b002.setBlockID("b002", sessionID);
        b002.setRuntime(scheduler.getRuntime());
        b003 = new com.bitreactive.library.timers.timerperiodic2.TimerPeriodic2();
        b003.setBlockID("b003", sessionID);
        b003.setRuntime(scheduler.getRuntime());
        b004 = new org.reactiveblocks.ibmiot.iotfoundation.IoTFoundation();
        b004.setBlockID("b004", sessionID);
        b004.setRuntime(scheduler.getRuntime());
        b025 = new org.reactiveblocks.ibmiot.event.Event<java.lang.Object>("event");
        b025.setBlockID("b025", sessionID);
        b025.setRuntime(scheduler.getRuntime());
        b001_state = Com_bitreactive_library_rpi_peripherals_GP2Y0A21YK0F._IDLE;
        b002_state = Com_bitreactive_library_rpi_peripherals_MCP3008ADC._IDLE;
        b003_state = Com_bitreactive_library_timers_TimerPeriodic2._IDLE;
        b004_state = Org_reactiveblocks_ibmiot_IoTFoundation._IDLE;
        b025_state = Org_reactiveblocks_ibmiot_Event._IDLE;
        _b0_b0_c1_Timer_Periodic_2_t = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b0_b0_c1_Timer_Periodic_2_t", this);
        org.reactiveblocks.ibmiot.IOTParams b000_o4 = b000.createParams();
        no.ntnu.item.arctis.runtime.SingletonData b004_o0 = b004.wrap(b000_o4);
                    ((no.ntnu.item.arctis.runtime.SingletonData)b004_o0).setSessionID(this.sessionID);
            ((no.ntnu.item.arctis.runtime.SingletonData)b004_o0).setInstance("_b1_b0_Device_Singleton");
            scheduler.sendToSession(this, "singleton_1698104509", "singleton_start", b004_o0);
        b004_state = Org_reactiveblocks_ibmiot_IoTFoundation.INITIALIZING;
        // step 26b6b;
        return CONSUME_SIGNAL;
    }

    public int fireTimer(java.lang.String _timerID) {
        if(_timerID.equals("_b0_b0_c1_Timer_Periodic_2_t")) {
            return handleTimer__b0_b0_c1_Timer_Periodic_2_t();
        }
        return DISCARD_SIGNAL;
    }

    public int fire(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.String _signalID, java.lang.Object _data) {
        if(_signalID.equals("_b1_b0_Device_Singleton_ready")) {
            return handleEvent__b1_b0_Device_Singleton_ready(_sender, receiverSessionID, _data);
        }
        return TRIGGER_UNKNOWN;
    }

    private int handleTimer__b0_b0_c1_Timer_Periodic_2_t() {
        int b002_o2 = b002.read();
        double b001_o1 = b001.toDistance(b002_o2);
        java.lang.String b000_o2 = b000.printoutput(b001_o1);
        com.bitreactive.iotvaidyaadcevent.start.adcibmevent.component.Component.EventData b000_o5 = b000.adcEvent(b000_o2);
        no.ntnu.item.arctis.runtime.SingletonData b025_o1 = b025.wrap(b000_o5);
                    ((no.ntnu.item.arctis.runtime.SingletonData)b025_o1).setSessionID(this.sessionID);
            ((no.ntnu.item.arctis.runtime.SingletonData)b025_o1).setInstance("_b2_b0_Device_Singleton");
            scheduler.sendToSession(this, "singleton_1698104509", "singleton_event", b025_o1);
        scheduler.startOrRestartTimer(_b0_b0_c1_Timer_Periodic_2_t, b003.duration);
        // step cec3d;
        return CONSUME_SIGNAL;
    }

    private int handleEvent__b1_b0_Device_Singleton_ready(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b1_b0_Device_Singleton_ready = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b004_state==Org_reactiveblocks_ibmiot_IoTFoundation.INITIALIZING) {
            b000.helloWorld();
            int b000_o1 = b000.samplinginterval();
            com.bitreactive.library.rpi.peripherals.mcp3008adc.MCP3008ADC.Parameters b001_o0 = b001.getParams(b000_o1);
            b002.p = b001_o0;
            java.lang.String b002_o0 = b002.init();
            if(b002_o0==null) {
                int b002_o1 = b002.getInterval();
                b003.duration = b002_o1;
                b000.blockinitialized();
                scheduler.startOrRestartTimer(_b0_b0_c1_Timer_Periodic_2_t, b003.duration);
                b002_state = Com_bitreactive_library_rpi_peripherals_MCP3008ADC.ACTIVE;
                b001_state = Com_bitreactive_library_rpi_peripherals_GP2Y0A21YK0F.ACTIVE;
                b003_state = Com_bitreactive_library_timers_TimerPeriodic2.ACTIVE;
                b004_state = Org_reactiveblocks_ibmiot_IoTFoundation.ACTIVE;
                // step 353ac;
                return CONSUME_SIGNAL;
            } else {
                scheduler.stopTimer(_b0_b0_c1_Timer_Periodic_2_t);
                b003_state = Com_bitreactive_library_timers_TimerPeriodic2._IDLE;
                b004_state = Org_reactiveblocks_ibmiot_IoTFoundation.ACTIVE;
                // step 96e7b;
                return CONSUME_SIGNAL;
            }
        } else if(b004_state==Org_reactiveblocks_ibmiot_IoTFoundation.STOPPING) {
            // step e23a6;
            return CONSUME_SIGNAL;
        } else if(b004_state==Org_reactiveblocks_ibmiot_IoTFoundation.ACTIVE) {
            // step f272b;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    public java.lang.String getPartName() {
        return null;
    }
}
