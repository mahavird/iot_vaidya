package com.bitreactive.iotvaidyaadcevent.start.adcibmevent.component;

public class DeviceSingletonSM extends no.ntnu.item.arctis.runtime.IStateMachine {


    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b1_b0_b2_c1_Conn_Handler_t0;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b1_b0_b2_c1_Conn_Handler_t1;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b1_b0_b2_c2_b0_Publish_Handler_t0;
    private com.bitreactive.library.mqtt.publisher.Publisher.Parameters _b1_b0_b2_c2_b0_Publish_Handler_t0_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b1_b0_b2_c2_b0_Publish_Handler_t2;
    private com.bitreactive.library.mqtt.MQTTMessage _b1_b0_b2_c2_b0_Publish_Handler_t2_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b1_b0_b2_c2_b0_Publish_Handler_t3;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b1_b0_b2_c2_b1_Publish_Handler_2_t0;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b1_b0_b2_c2_b1_Publish_Handler_2_t1;
    private com.bitreactive.library.mqtt.MQTTMessage _b1_b0_b2_c2_b1_Publish_Handler_2_t1_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b1_b0_b2_c2_b1_Publish_Handler_2_t2;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b1_b0_b2_c2_b1_Publish_Handler_2_t3;
    private com.bitreactive.library.mqtt.publisher.Publisher.Parameters _b1_b0_b2_c2_b1_Publish_Handler_2_t3_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b1_b0_b2_c2_Publish_Manager_t0;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b1_b0_b2_c3_c1_Timer_2_timer;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b1_b0_b2_c3_Subscribe_Handler_t0;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b1_b0_b3_Limiter_t0;
    private int _b1_b0_b3_Limiter_t0_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b1_b0_b2_c2_b0_c1_Buffer_Eager_t0;
    private org.reactiveblocks.ibmiot.devicesingleton.DeviceSingleton<java.lang.Object> b005;
    private com.bitreactive.library.gson.jsonserializer.JsonSerializer<org.reactiveblocks.ibmiot.Event> b006;
    private com.bitreactive.library.mqtt.robustmqtt.RobustMQTT b008;
    private com.bitreactive.library.mqtt.createcallback.CreateCallback b009;
    private com.bitreactive.library.mqtt.connhandler.ConnHandler b010;
    private com.bitreactive.library.mqtt.publishmanager.PublishManager b011;
    private com.bitreactive.library.mqtt.publishhandler.PublishHandler b012;
    private com.bitreactive.library.mqtt.publishpolicy.PublishPolicy b013;
    private com.bitreactive.library.buffering.buffereager.BufferEager<com.bitreactive.library.mqtt.MQTTMessage> b014;
    private com.bitreactive.library.mqtt.publisher.Publisher b015;
    private com.bitreactive.library.mqtt.publishhandler2.PublishHandler2 b016;
    private com.bitreactive.library.mqtt.publisher.Publisher b017;
    private com.bitreactive.library.buffering.persistentbuffer.PersistentBuffer<com.bitreactive.library.mqtt.MQTTMessage> b018;
    private com.bitreactive.library.mqtt.publishpolicy.PublishPolicy b019;
    private com.bitreactive.library.mqtt.subscribehandler.SubscribeHandler b020;
    private com.bitreactive.library.mqtt.subscriber.Subscriber b021;
    private com.bitreactive.library.timers.timer2.Timer2 b022;
    private com.bitreactive.library.flowlogic.limiter.Limiter<org.reactiveblocks.ibmiot.Event> b023;
    private boolean b017_PUB_OK_r0;
    private boolean b021_SUBS_ERROR_r1;
    private boolean b009_CONN_LOST_r0;
    private boolean b015_PUB_OK_r0;
    private boolean b010_INIT_FAILED_r1;
    private boolean b017_PUB_ERROR_r1;
    private boolean b010_RECONN_FAILED_r3;
    private boolean b010_RECONN_OK_r2;
    private boolean b018_INIT_r1;
    private boolean b021_SUBS_OK_r0;
    private boolean b009_MSG_ARRIVED_r2;
    private boolean b010_INIT_OK_r0;
    private boolean b015_PUB_ERROR_r1;
    private boolean b018_REMOVED_r0;
    private enum Org_reactiveblocks_ibmiot_DeviceSingleton {ACTIVE, _IDLE};
    private enum Com_bitreactive_library_gson_JsonSerializer {_IDLE};
    private enum Com_bitreactive_library_flowlogic_First {OTHERS, _IDLE};
    private enum Com_bitreactive_library_mqtt_RobustMQTT {ACTIVE, _IDLE, INITIALIZING};
    private enum Com_bitreactive_library_mqtt_CreateCallback {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_mqtt_ConnHandler {_IDLE, ACTIVE, INITIALIZING};
    private enum Com_bitreactive_library_mqtt_PublishManager {ACTIVE, _IDLE, INITIALIZING};
    private enum Com_bitreactive_library_mqtt_PublishHandler {ACTIVE, _IDLE};
    private enum Com_bitreactive_library_mqtt_PublishPolicy {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_buffering_BufferEager {STOPPING, FIRSTITEMADDED, _IDLE, READY, ACTIVE};
    private enum Com_bitreactive_library_mqtt_Publisher {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_mqtt_PublishHandler2 {ACTIVE, INITIALIZING, _IDLE};
    private enum Com_bitreactive_library_buffering_PersistentBuffer {_IDLE, ACTIVE, REMOVING, INITIALIZING};
    private enum Com_bitreactive_library_mqtt_SubscribeHandler {ACTIVE, _IDLE};
    private enum Com_bitreactive_library_mqtt_Subscriber {ACTIVE, _IDLE};
    private enum Com_bitreactive_library_timers_Timer2 {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_flowlogic_Limiter {_IDLE, BLOCKING};
    private enum Com_bitreactive_library_flowlogic_Switcher {OUT2, _IDLE};
    private Org_reactiveblocks_ibmiot_DeviceSingleton b005_state;
    private Com_bitreactive_library_gson_JsonSerializer b006_state;
    private Com_bitreactive_library_flowlogic_First b007_state;
    private Com_bitreactive_library_mqtt_RobustMQTT b008_state;
    private Com_bitreactive_library_mqtt_CreateCallback b009_state;
    private Com_bitreactive_library_mqtt_ConnHandler b010_state;
    private Com_bitreactive_library_mqtt_PublishManager b011_state;
    private Com_bitreactive_library_mqtt_PublishHandler b012_state;
    private Com_bitreactive_library_mqtt_PublishPolicy b013_state;
    private Com_bitreactive_library_buffering_BufferEager b014_state;
    private Com_bitreactive_library_mqtt_Publisher b015_state;
    private Com_bitreactive_library_mqtt_PublishHandler2 b016_state;
    private Com_bitreactive_library_mqtt_Publisher b017_state;
    private Com_bitreactive_library_buffering_PersistentBuffer b018_state;
    private Com_bitreactive_library_mqtt_PublishPolicy b019_state;
    private Com_bitreactive_library_mqtt_SubscribeHandler b020_state;
    private Com_bitreactive_library_mqtt_Subscriber b021_state;
    private Com_bitreactive_library_timers_Timer2 b022_state;
    private Com_bitreactive_library_flowlogic_Limiter b023_state;
    private Com_bitreactive_library_flowlogic_Switcher b024_state;
    private enum Com_bitreactive_library_buffering_BufferEager_Stm {_IDLE, READY, ISBUFFEREMPTY, HASROOMFORALL, ISEMPTYWHILEACTIVE, ISEMPTYWHILESTOPPING, HASROOMFORALLINITIAL, CHECKCONTENTADDALL, HASROOM, STOPPING, FIRSTITEMADDED, HASROOMFORALLAFTERADDINGFIRST, HASROOMAFTERADDINGFIRST, ACTIVE};
    private Com_bitreactive_library_buffering_BufferEager_Stm b014_stm_state;
    private static int sessionCount = 0;
    public DeviceSingletonSM(no.ntnu.item.arctis.runtime.Scheduler scheduler, java.lang.String sessionID, java.lang.String parentSessionID, java.lang.String parentSessionPath) {
        super(scheduler, "b005", sessionID, parentSessionID, parentSessionPath);
        ++ sessionCount;
    }


    public int getSessionCount() {
        return sessionCount;
    }

    public int fireInitial() {
        b005 = new org.reactiveblocks.ibmiot.devicesingleton.DeviceSingleton<java.lang.Object>();
        b005.setBlockID("b005", sessionID);
        b005.setRuntime(scheduler.getRuntime());
        b006 = new com.bitreactive.library.gson.jsonserializer.JsonSerializer<org.reactiveblocks.ibmiot.Event>();
        b006.setBlockID("b006", sessionID);
        b006.setRuntime(scheduler.getRuntime());
        b008 = new com.bitreactive.library.mqtt.robustmqtt.RobustMQTT();
        b008.setBlockID("b008", sessionID);
        b008.setRuntime(scheduler.getRuntime());
        b009 = new com.bitreactive.library.mqtt.createcallback.CreateCallback();
        b009.setBlockID("b009", sessionID);
        b009.setRuntime(scheduler.getRuntime());
        b010 = new com.bitreactive.library.mqtt.connhandler.ConnHandler();
        b010.setBlockID("b010", sessionID);
        b010.setRuntime(scheduler.getRuntime());
        b011 = new com.bitreactive.library.mqtt.publishmanager.PublishManager();
        b011.setBlockID("b011", sessionID);
        b011.setRuntime(scheduler.getRuntime());
        b012 = new com.bitreactive.library.mqtt.publishhandler.PublishHandler();
        b012.setBlockID("b012", sessionID);
        b012.setRuntime(scheduler.getRuntime());
        b013 = new com.bitreactive.library.mqtt.publishpolicy.PublishPolicy();
        b013.setBlockID("b013", sessionID);
        b013.setRuntime(scheduler.getRuntime());
        b014 = new com.bitreactive.library.buffering.buffereager.BufferEager<com.bitreactive.library.mqtt.MQTTMessage>(true, 100, true);
        b014.setBlockID("b014", sessionID);
        b014.setRuntime(scheduler.getRuntime());
        b015 = new com.bitreactive.library.mqtt.publisher.Publisher();
        b015.setBlockID("b015", sessionID);
        b015.setRuntime(scheduler.getRuntime());
        b016 = new com.bitreactive.library.mqtt.publishhandler2.PublishHandler2();
        b016.setBlockID("b016", sessionID);
        b016.setRuntime(scheduler.getRuntime());
        b017 = new com.bitreactive.library.mqtt.publisher.Publisher();
        b017.setBlockID("b017", sessionID);
        b017.setRuntime(scheduler.getRuntime());
        b018 = new com.bitreactive.library.buffering.persistentbuffer.PersistentBuffer<com.bitreactive.library.mqtt.MQTTMessage>();
        b018.setBlockID("b018", sessionID);
        b018.setRuntime(scheduler.getRuntime());
        b019 = new com.bitreactive.library.mqtt.publishpolicy.PublishPolicy();
        b019.setBlockID("b019", sessionID);
        b019.setRuntime(scheduler.getRuntime());
        b020 = new com.bitreactive.library.mqtt.subscribehandler.SubscribeHandler();
        b020.setBlockID("b020", sessionID);
        b020.setRuntime(scheduler.getRuntime());
        b021 = new com.bitreactive.library.mqtt.subscriber.Subscriber();
        b021.setBlockID("b021", sessionID);
        b021.setRuntime(scheduler.getRuntime());
        b022 = new com.bitreactive.library.timers.timer2.Timer2();
        b022.setBlockID("b022", sessionID);
        b022.setRuntime(scheduler.getRuntime());
        b023 = new com.bitreactive.library.flowlogic.limiter.Limiter<org.reactiveblocks.ibmiot.Event>(1000);
        b023.setBlockID("b023", sessionID);
        b023.setRuntime(scheduler.getRuntime());
        b005_state = Org_reactiveblocks_ibmiot_DeviceSingleton._IDLE;
        b006_state = Com_bitreactive_library_gson_JsonSerializer._IDLE;
        b007_state = Com_bitreactive_library_flowlogic_First._IDLE;
        b008_state = Com_bitreactive_library_mqtt_RobustMQTT._IDLE;
        b009_state = Com_bitreactive_library_mqtt_CreateCallback._IDLE;
        b010_state = Com_bitreactive_library_mqtt_ConnHandler._IDLE;
        b011_state = Com_bitreactive_library_mqtt_PublishManager._IDLE;
        b012_state = Com_bitreactive_library_mqtt_PublishHandler._IDLE;
        b013_state = Com_bitreactive_library_mqtt_PublishPolicy._IDLE;
        b014_state = Com_bitreactive_library_buffering_BufferEager._IDLE;
        b015_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
        b016_state = Com_bitreactive_library_mqtt_PublishHandler2._IDLE;
        b017_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
        b018_state = Com_bitreactive_library_buffering_PersistentBuffer._IDLE;
        b019_state = Com_bitreactive_library_mqtt_PublishPolicy._IDLE;
        b020_state = Com_bitreactive_library_mqtt_SubscribeHandler._IDLE;
        b021_state = Com_bitreactive_library_mqtt_Subscriber._IDLE;
        b022_state = Com_bitreactive_library_timers_Timer2._IDLE;
        b023_state = Com_bitreactive_library_flowlogic_Limiter._IDLE;
        b024_state = Com_bitreactive_library_flowlogic_Switcher._IDLE;
        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
        _b1_b0_b2_c1_Conn_Handler_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b1_b0_b2_c1_Conn_Handler_t0", this);
        _b1_b0_b2_c1_Conn_Handler_t1 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b1_b0_b2_c1_Conn_Handler_t1", this);
        _b1_b0_b2_c2_b0_Publish_Handler_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b1_b0_b2_c2_b0_Publish_Handler_t0", this);
        _b1_b0_b2_c2_b0_Publish_Handler_t2 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer(5000, "_b1_b0_b2_c2_b0_Publish_Handler_t2", this);
        _b1_b0_b2_c2_b0_Publish_Handler_t3 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b1_b0_b2_c2_b0_Publish_Handler_t3", this);
        _b1_b0_b2_c2_b1_Publish_Handler_2_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b1_b0_b2_c2_b1_Publish_Handler_2_t0", this);
        _b1_b0_b2_c2_b1_Publish_Handler_2_t1 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer(1000, "_b1_b0_b2_c2_b1_Publish_Handler_2_t1", this);
        _b1_b0_b2_c2_b1_Publish_Handler_2_t2 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b1_b0_b2_c2_b1_Publish_Handler_2_t2", this);
        _b1_b0_b2_c2_b1_Publish_Handler_2_t3 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b1_b0_b2_c2_b1_Publish_Handler_2_t3", this);
        _b1_b0_b2_c2_Publish_Manager_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b1_b0_b2_c2_Publish_Manager_t0", this);
        _b1_b0_b2_c3_c1_Timer_2_timer = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b1_b0_b2_c3_c1_Timer_2_timer", this);
        _b1_b0_b2_c3_Subscribe_Handler_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b1_b0_b2_c3_Subscribe_Handler_t0", this);
        _b1_b0_b3_Limiter_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b1_b0_b3_Limiter_t0", this);
        _b1_b0_b2_c2_b0_c1_Buffer_Eager_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0", this);
        return CONSUME_SIGNAL;
    }

    public int fireTimer(java.lang.String _timerID) {
        if(_timerID.equals("_b1_b0_b2_c3_c1_Timer_2_timer")) {
            return handleTimer__b1_b0_b2_c3_c1_Timer_2_timer();
        } else if(_timerID.equals("_b1_b0_b2_c1_Conn_Handler_t0")) {
            return handleTimer__b1_b0_b2_c1_Conn_Handler_t0();
        } else if(_timerID.equals("_b1_b0_b2_c2_Publish_Manager_t0")) {
            return handleTimer__b1_b0_b2_c2_Publish_Manager_t0();
        } else if(_timerID.equals("_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0")) {
            return handleTimer__b1_b0_b2_c2_b0_c1_Buffer_Eager_t0();
        } else if(_timerID.equals("_b1_b0_b2_c2_b1_Publish_Handler_2_t2")) {
            return handleTimer__b1_b0_b2_c2_b1_Publish_Handler_2_t2();
        } else if(_timerID.equals("_b1_b0_b2_c2_b0_Publish_Handler_t2")) {
            return handleTimer__b1_b0_b2_c2_b0_Publish_Handler_t2();
        } else if(_timerID.equals("_b1_b0_b3_Limiter_t0")) {
            return handleTimer__b1_b0_b3_Limiter_t0();
        } else if(_timerID.equals("_b1_b0_b2_c2_b0_Publish_Handler_t3")) {
            return handleTimer__b1_b0_b2_c2_b0_Publish_Handler_t3();
        } else if(_timerID.equals("_b1_b0_b2_c2_b1_Publish_Handler_2_t3")) {
            return handleTimer__b1_b0_b2_c2_b1_Publish_Handler_2_t3();
        } else if(_timerID.equals("_b1_b0_b2_c1_Conn_Handler_t1")) {
            return handleTimer__b1_b0_b2_c1_Conn_Handler_t1();
        } else if(_timerID.equals("_b1_b0_b2_c2_b1_Publish_Handler_2_t0")) {
            return handleTimer__b1_b0_b2_c2_b1_Publish_Handler_2_t0();
        } else if(_timerID.equals("_b1_b0_b2_c3_Subscribe_Handler_t0")) {
            return handleTimer__b1_b0_b2_c3_Subscribe_Handler_t0();
        } else if(_timerID.equals("_b1_b0_b2_c2_b0_Publish_Handler_t0")) {
            return handleTimer__b1_b0_b2_c2_b0_Publish_Handler_t0();
        } else if(_timerID.equals("_b1_b0_b2_c2_b1_Publish_Handler_2_t1")) {
            return handleTimer__b1_b0_b2_c2_b1_Publish_Handler_2_t1();
        }
        return DISCARD_SIGNAL;
    }

    public int fire(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.String _signalID, java.lang.Object _data) {
        if(_signalID.equals("singleton_subscribe")) {
            return handleEvent_singleton_subscribe(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("singleton_event")) {
            return handleEvent_singleton_event(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("INIT_b018")) {
            return handleEvent_INIT_b018(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("MSG_ARRIVED_b009")) {
            return handleEvent_MSG_ARRIVED_b009(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("SUBS_OK_b021")) {
            return handleEvent_SUBS_OK_b021(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("INIT_FAILED_b010")) {
            return handleEvent_INIT_FAILED_b010(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("CONN_LOST_b009")) {
            return handleEvent_CONN_LOST_b009(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("RECONN_FAILED_b010")) {
            return handleEvent_RECONN_FAILED_b010(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("RECONN_OK_b010")) {
            return handleEvent_RECONN_OK_b010(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("PUB_ERROR_b017")) {
            return handleEvent_PUB_ERROR_b017(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("REMOVED_b018")) {
            return handleEvent_REMOVED_b018(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("PUB_OK_b017")) {
            return handleEvent_PUB_OK_b017(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("SUBS_ERROR_b021")) {
            return handleEvent_SUBS_ERROR_b021(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("singleton_stop")) {
            return handleEvent_singleton_stop(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("singleton_start")) {
            return handleEvent_singleton_start(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("PUB_OK_b015")) {
            return handleEvent_PUB_OK_b015(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("PUB_ERROR_b015")) {
            return handleEvent_PUB_ERROR_b015(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("INIT_OK_b010")) {
            return handleEvent_INIT_OK_b010(_sender, receiverSessionID, _data);
        }
        return TRIGGER_UNKNOWN;
    }

    private int handleTimer__b1_b0_b2_c3_c1_Timer_2_timer() {
        b021.subscribe(b020.p);
        b021_SUBS_ERROR_r1 = true;
        b021_SUBS_OK_r0 = true;
        b021_state = Com_bitreactive_library_mqtt_Subscriber.ACTIVE;
        b022_state = Com_bitreactive_library_timers_Timer2._IDLE;
        // step 080ce;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b1_b0_b2_c1_Conn_Handler_t0() {
        b010.reconnect();
        b010_RECONN_OK_r2 = true;
        b010_RECONN_FAILED_r3 = true;
        // step 97c24;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b1_b0_b2_c2_Publish_Manager_t0() {
        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.start).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.start).getInstance()+ "_" + "ready", b005.start);
        b011_state = Com_bitreactive_library_mqtt_PublishManager.ACTIVE;
        b008_state = Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE;
        // step 48ddc;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b1_b0_b2_c2_b0_c1_Buffer_Eager_t0() {
        if(b014_stm_state==Com_bitreactive_library_buffering_BufferEager_Stm.FIRSTITEMADDED) {
            b014.getFromBuffer();
            b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.ACTIVE;
            b013.msg = b014.out;
            if(b013.discardDueToFreshness()) {
                b013.getNext();
                scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
                b014_state = Com_bitreactive_library_buffering_BufferEager.ACTIVE;
                // step dc713;
                return CONSUME_SIGNAL;
            } else {
                if(b013.isConnected()) {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b013_o2 = b013.addClient();
                    _b1_b0_b2_c2_b0_Publish_Handler_t0_data = b013_o2;
                    scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t0);
                    b014_state = Com_bitreactive_library_buffering_BufferEager.ACTIVE;
                    // step f625c;
                    return CONSUME_SIGNAL;
                } else {
                    if(b013.discardDueToQos()) {
                        b013.getNext();
                        scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
                        b014_state = Com_bitreactive_library_buffering_BufferEager.ACTIVE;
                        // step 98436;
                        return CONSUME_SIGNAL;
                    } else {
                        b013.toWait();
                        _b1_b0_b2_c2_b0_Publish_Handler_t2_data = b013.msg;
                        scheduler.startOrRestartTimer(_b1_b0_b2_c2_b0_Publish_Handler_t2);
                        b014_state = Com_bitreactive_library_buffering_BufferEager.ACTIVE;
                        // step 20d87;
                        return CONSUME_SIGNAL;
                    }
                }
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleTimer__b1_b0_b2_c2_b1_Publish_Handler_2_t2() {
        b018.remove();
        b018_REMOVED_r0 = true;
        b018_state = Com_bitreactive_library_buffering_PersistentBuffer.REMOVING;
        // step 216cf;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b1_b0_b2_c2_b0_Publish_Handler_t2() {
        b013.msg = _b1_b0_b2_c2_b0_Publish_Handler_t2_data;
        if(b013.discardDueToFreshness()) {
            b013.getNext();
            scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
            // step 1ab7d;
            return CONSUME_SIGNAL;
        } else {
            if(b013.isConnected()) {
                com.bitreactive.library.mqtt.publisher.Publisher.Parameters b013_o2 = b013.addClient();
                _b1_b0_b2_c2_b0_Publish_Handler_t0_data = b013_o2;
                scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t0);
                // step 93a45;
                return CONSUME_SIGNAL;
            } else {
                if(b013.discardDueToQos()) {
                    b013.getNext();
                    scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
                    // step fa923;
                    return CONSUME_SIGNAL;
                } else {
                    b013.toWait();
                    _b1_b0_b2_c2_b0_Publish_Handler_t2_data = b013.msg;
                    scheduler.startOrRestartTimer(_b1_b0_b2_c2_b0_Publish_Handler_t2);
                    // step 35c29;
                    return CONSUME_SIGNAL;
                }
            }
        }
    }

    private int handleTimer__b1_b0_b3_Limiter_t0() {
        b024_state = Com_bitreactive_library_flowlogic_Switcher._IDLE;
        b023_state = Com_bitreactive_library_flowlogic_Limiter._IDLE;
        // step f4505;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b1_b0_b2_c2_b0_Publish_Handler_t3() {
        if(b014.isEmpty()) {
            b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.READY;
            b014_state = Com_bitreactive_library_buffering_BufferEager.READY;
            // step 66c36;
            return CONSUME_SIGNAL;
        } else {
            b014.getFromBuffer();
            b013.msg = b014.out;
            if(b013.discardDueToFreshness()) {
                b013.getNext();
                scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
                // step c78a3;
                return CONSUME_SIGNAL;
            } else {
                if(b013.isConnected()) {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b013_o2 = b013.addClient();
                    _b1_b0_b2_c2_b0_Publish_Handler_t0_data = b013_o2;
                    scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t0);
                    // step 00a87;
                    return CONSUME_SIGNAL;
                } else {
                    if(b013.discardDueToQos()) {
                        b013.getNext();
                        scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
                        // step f3937;
                        return CONSUME_SIGNAL;
                    } else {
                        b013.toWait();
                        _b1_b0_b2_c2_b0_Publish_Handler_t2_data = b013.msg;
                        scheduler.startOrRestartTimer(_b1_b0_b2_c2_b0_Publish_Handler_t2);
                        // step 16f63;
                        return CONSUME_SIGNAL;
                    }
                }
            }
        }
    }

    private int handleTimer__b1_b0_b2_c2_b1_Publish_Handler_2_t3() {
        b017.publish(_b1_b0_b2_c2_b1_Publish_Handler_2_t3_data);
        b017_PUB_ERROR_r1 = true;
        b017_PUB_OK_r0 = true;
        b017_state = Com_bitreactive_library_mqtt_Publisher.ACTIVE;
        // step 7afcc;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b1_b0_b2_c1_Conn_Handler_t1() {
        b010.connect();
        b010_INIT_OK_r0 = true;
        b010_INIT_FAILED_r1 = true;
        // step cfae8;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b1_b0_b2_c2_b1_Publish_Handler_2_t0() {
        com.bitreactive.library.mqtt.MQTTMessage b018_o1 = b018.peek();
        if(b018_o1==null) {
            b016.empty();
            int b016_o2 = b016.getDuration();
            scheduler.startOrRestartTimer(_b1_b0_b2_c2_b1_Publish_Handler_2_t0, b016_o2);
            // step 17293;
            return CONSUME_SIGNAL;
        } else {
            b019.msg = b018_o1;
            if(b019.discardDueToFreshness()) {
                b019.getNext();
                scheduler.startBreak(_b1_b0_b2_c2_b1_Publish_Handler_2_t2);
                // step 4a509;
                return CONSUME_SIGNAL;
            } else {
                if(b019.isConnected()) {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b019_o2 = b019.addClient();
                    _b1_b0_b2_c2_b1_Publish_Handler_2_t3_data = b019_o2;
                    scheduler.startBreak(_b1_b0_b2_c2_b1_Publish_Handler_2_t3);
                    // step 622dc;
                    return CONSUME_SIGNAL;
                } else {
                    if(b019.discardDueToQos()) {
                        b019.getNext();
                        scheduler.startBreak(_b1_b0_b2_c2_b1_Publish_Handler_2_t2);
                        // step e361d;
                        return CONSUME_SIGNAL;
                    } else {
                        b019.toWait();
                        _b1_b0_b2_c2_b1_Publish_Handler_2_t1_data = b019.msg;
                        scheduler.startOrRestartTimer(_b1_b0_b2_c2_b1_Publish_Handler_2_t1);
                        // step e447a;
                        return CONSUME_SIGNAL;
                    }
                }
            }
        }
    }

    private int handleTimer__b1_b0_b2_c3_Subscribe_Handler_t0() {
        b021.subscribe(b020.p);
        b021_SUBS_ERROR_r1 = true;
        b021_SUBS_OK_r0 = true;
        b021_state = Com_bitreactive_library_mqtt_Subscriber.ACTIVE;
        // step a3113;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b1_b0_b2_c2_b0_Publish_Handler_t0() {
        b015.publish(_b1_b0_b2_c2_b0_Publish_Handler_t0_data);
        b015_PUB_OK_r0 = true;
        b015_PUB_ERROR_r1 = true;
        b015_state = Com_bitreactive_library_mqtt_Publisher.ACTIVE;
        // step 0ffb6;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b1_b0_b2_c2_b1_Publish_Handler_2_t1() {
        b019.msg = _b1_b0_b2_c2_b1_Publish_Handler_2_t1_data;
        if(b019.discardDueToFreshness()) {
            b019.getNext();
            scheduler.startBreak(_b1_b0_b2_c2_b1_Publish_Handler_2_t2);
            // step 62246;
            return CONSUME_SIGNAL;
        } else {
            if(b019.isConnected()) {
                com.bitreactive.library.mqtt.publisher.Publisher.Parameters b019_o2 = b019.addClient();
                _b1_b0_b2_c2_b1_Publish_Handler_2_t3_data = b019_o2;
                scheduler.startBreak(_b1_b0_b2_c2_b1_Publish_Handler_2_t3);
                // step bc2af;
                return CONSUME_SIGNAL;
            } else {
                if(b019.discardDueToQos()) {
                    b019.getNext();
                    scheduler.startBreak(_b1_b0_b2_c2_b1_Publish_Handler_2_t2);
                    // step 5b20b;
                    return CONSUME_SIGNAL;
                } else {
                    b019.toWait();
                    _b1_b0_b2_c2_b1_Publish_Handler_2_t1_data = b019.msg;
                    scheduler.startOrRestartTimer(_b1_b0_b2_c2_b1_Publish_Handler_2_t1);
                    // step 42392;
                    return CONSUME_SIGNAL;
                }
            }
        }
    }

    private int handleEvent_singleton_subscribe(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b1_b0_Device_Singleton_subscribe = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b005_state==Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE) {
            b005.subscribe(_b1_b0_Device_Singleton_subscribe);
            // step 5d0fd;
            return CONSUME_SIGNAL;
        } else if(b005_state==Org_reactiveblocks_ibmiot_DeviceSingleton._IDLE) {
            b005.subscribe(_b1_b0_Device_Singleton_subscribe);
            b005_state = Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE;
            // step 6671b;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_singleton_event(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b1_b0_Device_Singleton_event = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b005_state==Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE) {
            org.reactiveblocks.ibmiot.Event b005_o7 = b005.unwrapEvent(_b1_b0_Device_Singleton_event);
            if(b023_state==Com_bitreactive_library_flowlogic_Limiter._IDLE) {
                int b023_o0 = b023.getInterval();
                java.lang.String b006_o0 = b006.serialize(b005_o7);
                com.bitreactive.library.mqtt.MQTTMessage b005_o6 = b005.message(b005_o7, b006_o0);
                if(b005_o6==null) {
                    b005.logTooLarge();
                    _b1_b0_b3_Limiter_t0_data = b023_o0;
                    scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                    b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                    b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                    // step f7bd4;
                    return CONSUME_SIGNAL;
                } else {
                    if(b008_state==Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE) {
                        if(b012_state==Com_bitreactive_library_mqtt_PublishHandler.ACTIVE) {
                            b013.msg = b005_o6;
                            if(b013.isConnected()) {
                                b013.buffer();
                                if(b014_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                    b014.add = b013.msg;
                                    if(b014.hasRoom()) {
                                        b014.addToBuffer();
                                        _b1_b0_b3_Limiter_t0_data = b023_o0;
                                        scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                        b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        // step e9426;
                                        return CONSUME_SIGNAL;
                                    } else {
                                        b012.overflow(b014.overflow);
                                        _b1_b0_b3_Limiter_t0_data = b023_o0;
                                        scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                        b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        // step e6ef5;
                                        return CONSUME_SIGNAL;
                                    }
                                } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                    b014.add = b013.msg;
                                    b014.addToBuffer();
                                    scheduler.startBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.FIRSTITEMADDED;
                                    _b1_b0_b3_Limiter_t0_data = b023_o0;
                                    scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                    b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    b014_state = Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED;
                                    b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    // step 37da8;
                                    return CONSUME_SIGNAL;
                                } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                    b014.add = b013.msg;
                                    if(b014.hasRoom()) {
                                        b014.addToBuffer();
                                        _b1_b0_b3_Limiter_t0_data = b023_o0;
                                        scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                        b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        // step 99d54;
                                        return CONSUME_SIGNAL;
                                    } else {
                                        b012.overflow(b014.overflow);
                                        _b1_b0_b3_Limiter_t0_data = b023_o0;
                                        scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                        b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        // step 4aad4;
                                        return CONSUME_SIGNAL;
                                    }
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else {
                                if(b013.discardDueToQos()) {
                                    _b1_b0_b3_Limiter_t0_data = b023_o0;
                                    scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                    b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    // step dd053;
                                    return CONSUME_SIGNAL;
                                } else {
                                    b013.buffer();
                                    if(b014_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                        b014.add = b013.msg;
                                        if(b014.hasRoom()) {
                                            b014.addToBuffer();
                                            _b1_b0_b3_Limiter_t0_data = b023_o0;
                                            scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                            b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                            b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                            // step c9afa;
                                            return CONSUME_SIGNAL;
                                        } else {
                                            b012.overflow(b014.overflow);
                                            _b1_b0_b3_Limiter_t0_data = b023_o0;
                                            scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                            b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                            b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                            // step d3586;
                                            return CONSUME_SIGNAL;
                                        }
                                    } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                        b014.add = b013.msg;
                                        b014.addToBuffer();
                                        scheduler.startBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.FIRSTITEMADDED;
                                        _b1_b0_b3_Limiter_t0_data = b023_o0;
                                        scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                        b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        b014_state = Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED;
                                        b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        // step bc1c8;
                                        return CONSUME_SIGNAL;
                                    } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                        b014.add = b013.msg;
                                        if(b014.hasRoom()) {
                                            b014.addToBuffer();
                                            _b1_b0_b3_Limiter_t0_data = b023_o0;
                                            scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                            b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                            b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                            // step 9f548;
                                            return CONSUME_SIGNAL;
                                        } else {
                                            b012.overflow(b014.overflow);
                                            _b1_b0_b3_Limiter_t0_data = b023_o0;
                                            scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                            b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                            b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                            // step a4bfd;
                                            return CONSUME_SIGNAL;
                                        }
                                    } else {
                                        return DISCARD_SIGNAL;
                                    }
                                }
                            }
                        } else if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE) {
                            b019.msg = b005_o6;
                            if(b019.isConnected()) {
                                b019.buffer();
                                if(b018_state==Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE) {
                                    b018.add(b019.msg);
                                    _b1_b0_b3_Limiter_t0_data = b023_o0;
                                    scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                    b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    // step aca5e;
                                    return CONSUME_SIGNAL;
                                } else if(b018_state==Com_bitreactive_library_buffering_PersistentBuffer.REMOVING) {
                                    b018.add(b019.msg);
                                    _b1_b0_b3_Limiter_t0_data = b023_o0;
                                    scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                    b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    // step 11416;
                                    return CONSUME_SIGNAL;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else {
                                if(b019.discardDueToQos()) {
                                    _b1_b0_b3_Limiter_t0_data = b023_o0;
                                    scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                    b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    // step 974b8;
                                    return CONSUME_SIGNAL;
                                } else {
                                    b019.buffer();
                                    if(b018_state==Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE) {
                                        b018.add(b019.msg);
                                        _b1_b0_b3_Limiter_t0_data = b023_o0;
                                        scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                        b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        // step f9b53;
                                        return CONSUME_SIGNAL;
                                    } else if(b018_state==Com_bitreactive_library_buffering_PersistentBuffer.REMOVING) {
                                        b018.add(b019.msg);
                                        _b1_b0_b3_Limiter_t0_data = b023_o0;
                                        scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                                        b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        // step 7064b;
                                        return CONSUME_SIGNAL;
                                    } else {
                                        return DISCARD_SIGNAL;
                                    }
                                }
                            }
                        } else {
                            return BLOCKING_CHOICE;
                        }
                    } else if(b008_state==Com_bitreactive_library_mqtt_RobustMQTT._IDLE) {
                        _b1_b0_b3_Limiter_t0_data = b023_o0;
                        scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                        b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                        b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                        // step abacd;
                        return CONSUME_SIGNAL;
                    } else if(b008_state==Com_bitreactive_library_mqtt_RobustMQTT.INITIALIZING) {
                        _b1_b0_b3_Limiter_t0_data = b023_o0;
                        scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                        b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                        b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                        // step 46d72;
                        return CONSUME_SIGNAL;
                    } else {
                        return DISCARD_SIGNAL;
                    }
                }
            } else if(b023_state==Com_bitreactive_library_flowlogic_Limiter.BLOCKING) {
                b005.logOverload();
                // step 51ac4;
                return CONSUME_SIGNAL;
            } else {
                return DISCARD_SIGNAL;
            }
        } else if(b005_state==Org_reactiveblocks_ibmiot_DeviceSingleton._IDLE) {
            org.reactiveblocks.ibmiot.Event b005_o7 = b005.unwrapEvent(_b1_b0_Device_Singleton_event);
            int b023_o0 = b023.getInterval();
            java.lang.String b006_o0 = b006.serialize(b005_o7);
            com.bitreactive.library.mqtt.MQTTMessage b005_o6 = b005.message(b005_o7, b006_o0);
            if(b005_o6==null) {
                b005.logTooLarge();
                _b1_b0_b3_Limiter_t0_data = b023_o0;
                scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                b005_state = Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE;
                b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                // step 316e6;
                return CONSUME_SIGNAL;
            } else {
                _b1_b0_b3_Limiter_t0_data = b023_o0;
                scheduler.startOrRestartTimer(_b1_b0_b3_Limiter_t0, b023_o0);
                b023_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                b005_state = Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE;
                b024_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                // step bb884;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_INIT_b018(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b018_INIT_r1) {
            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.start).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.start).getInstance()+ "_" + "ready", b005.start);
            int b016_o2 = b016.getDuration();
            scheduler.startOrRestartTimer(_b1_b0_b2_c2_b1_Publish_Handler_2_t0, b016_o2);
            b018_INIT_r1 = false;
            b011_state = Com_bitreactive_library_mqtt_PublishManager.ACTIVE;
            b016_state = Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE;
            b018_state = Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE;
            b008_state = Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE;
            // step a498a;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_MSG_ARRIVED_b009(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b009_MSG_ARRIVED_r2) {
            if(b008_state==Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE) {
                org.reactiveblocks.ibmiot.Command b005_o5 = b005.command(((com.bitreactive.library.mqtt.MQTTMessage) _data));
                if(b005_o5==null) {
                    // step 9605a;
                    return CONSUME_SIGNAL;
                } else {
                    no.ntnu.item.arctis.runtime.SingletonData[] b005_o0 = b005.distribute(b005_o5);
                    for (no.ntnu.item.arctis.runtime.SingletonData _it : b005_o0) {
                        scheduler.sendToSession(this, _it.getSessionID(), _it.getInstance()+ "_" + "command", _it);
                    }
                    // step 1bc2c;
                    return CONSUME_SIGNAL;
                }
            } else {
                // step 9c786;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_SUBS_OK_b021(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b021_SUBS_OK_r0) {
            b021_SUBS_ERROR_r1 = false;
            scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b021");
            b021_SUBS_OK_r0 = false;
            scheduler.flushEventQueue(sessionID, "SUBS_OK_b021");
            b021_state = Com_bitreactive_library_mqtt_Subscriber._IDLE;
            // step 15e48;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_INIT_FAILED_b010(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b010_INIT_FAILED_r1) {
            int b010_o4 = b010.getWaitingDuration();
            scheduler.startOrRestartTimer(_b1_b0_b2_c1_Conn_Handler_t1, b010_o4);
            b010_INIT_FAILED_r1 = false;
            // step 1565b;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_CONN_LOST_b009(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b009_CONN_LOST_r0) {
            b008.connLost(((java.lang.String) _data));
            if(b010_state==Com_bitreactive_library_mqtt_ConnHandler.ACTIVE) {
                boolean b010_o5 = b010.tryReconnect();
                if(b010_o5==true) {
                    // step f352b;
                    return CONSUME_SIGNAL;
                } else {
                    b010.reconnect();
                    b010_RECONN_FAILED_r3 = true;
                    b010_RECONN_OK_r2 = true;
                    // step 46d39;
                    return CONSUME_SIGNAL;
                }
            } else {
                // step e814b;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_RECONN_FAILED_b010(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b010_RECONN_FAILED_r3) {
            int b010_o3 = b010.getWaitingDuration();
            scheduler.startOrRestartTimer(_b1_b0_b2_c1_Conn_Handler_t0, b010_o3);
            b010_RECONN_FAILED_r3 = false;
            // step 41bcf;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_RECONN_OK_b010(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b010_RECONN_OK_r2) {
            if(b020.containTopic()) {
                if(b022_state==Com_bitreactive_library_timers_Timer2._IDLE) {
                    if(b021_state==Com_bitreactive_library_mqtt_Subscriber.ACTIVE) {
                        b021.stop();
                        scheduler.stopTimer(_b1_b0_b2_c3_c1_Timer_2_timer);
                        scheduler.startBreak(_b1_b0_b2_c3_Subscribe_Handler_t0);
                        b010_RECONN_OK_r2 = false;
                        b021_SUBS_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b021");
                        b021_SUBS_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_OK_b021");
                        b021_state = Com_bitreactive_library_mqtt_Subscriber._IDLE;
                        // step 32442;
                        return CONSUME_SIGNAL;
                    } else if(b021_state==Com_bitreactive_library_mqtt_Subscriber._IDLE) {
                        b021.stop();
                        scheduler.stopTimer(_b1_b0_b2_c3_c1_Timer_2_timer);
                        scheduler.startBreak(_b1_b0_b2_c3_Subscribe_Handler_t0);
                        b010_RECONN_OK_r2 = false;
                        b021_SUBS_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b021");
                        b021_SUBS_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_OK_b021");
                        // step a6198;
                        return CONSUME_SIGNAL;
                    } else {
                        return DISCARD_SIGNAL;
                    }
                } else if(b022_state==Com_bitreactive_library_timers_Timer2.ACTIVE) {
                    b021.stop();
                    scheduler.stopTimer(_b1_b0_b2_c3_c1_Timer_2_timer);
                    scheduler.startBreak(_b1_b0_b2_c3_Subscribe_Handler_t0);
                    b010_RECONN_OK_r2 = false;
                    b021_SUBS_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b021");
                    b021_SUBS_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "SUBS_OK_b021");
                    b022_state = Com_bitreactive_library_timers_Timer2._IDLE;
                    // step 26325;
                    return CONSUME_SIGNAL;
                } else {
                    return DISCARD_SIGNAL;
                }
            } else {
                b010_RECONN_OK_r2 = false;
                // step 7980a;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_PUB_ERROR_b017(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b017_PUB_ERROR_r1) {
            b019.msg = ((com.bitreactive.library.mqtt.MQTTMessage) _data);
            if(b019.discardDueToFreshness()) {
                b019.getNext();
                scheduler.startBreak(_b1_b0_b2_c2_b1_Publish_Handler_2_t2);
                b017_PUB_ERROR_r1 = false;
                scheduler.flushEventQueue(sessionID, "PUB_ERROR_b017");
                b017_PUB_OK_r0 = false;
                scheduler.flushEventQueue(sessionID, "PUB_OK_b017");
                b017_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                // step 0a6b5;
                return CONSUME_SIGNAL;
            } else {
                if(b019.discardDueToQos()) {
                    b019.getNext();
                    scheduler.startBreak(_b1_b0_b2_c2_b1_Publish_Handler_2_t2);
                    b017_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b017");
                    b017_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b017");
                    b017_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step 55c49;
                    return CONSUME_SIGNAL;
                } else {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b019_o2 = b019.addClient();
                    _b1_b0_b2_c2_b1_Publish_Handler_2_t3_data = b019_o2;
                    scheduler.startBreak(_b1_b0_b2_c2_b1_Publish_Handler_2_t3);
                    b017_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b017");
                    b017_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b017");
                    b017_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step a295b;
                    return CONSUME_SIGNAL;
                }
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_REMOVED_b018(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b018_REMOVED_r0) {
            if(((com.bitreactive.library.mqtt.MQTTMessage) _data)==null) {
                b016.empty();
                int b016_o2 = b016.getDuration();
                scheduler.startOrRestartTimer(_b1_b0_b2_c2_b1_Publish_Handler_2_t0, b016_o2);
                b018_REMOVED_r0 = false;
                b018_state = Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE;
                // step d8cae;
                return CONSUME_SIGNAL;
            } else {
                b016.removed();
                int b016_o2 = b016.getDuration();
                scheduler.startOrRestartTimer(_b1_b0_b2_c2_b1_Publish_Handler_2_t0, b016_o2);
                b018_REMOVED_r0 = false;
                b018_state = Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE;
                // step 3c83f;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_PUB_OK_b017(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b017_PUB_OK_r0) {
            b018.remove();
            b017_PUB_OK_r0 = false;
            scheduler.flushEventQueue(sessionID, "PUB_OK_b017");
            b017_PUB_ERROR_r1 = false;
            scheduler.flushEventQueue(sessionID, "PUB_ERROR_b017");
            b018_REMOVED_r0 = true;
            b017_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
            b018_state = Com_bitreactive_library_buffering_PersistentBuffer.REMOVING;
            // step 454dd;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_SUBS_ERROR_b021(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b021_SUBS_ERROR_r1) {
            int b020_o0 = b020.getDuration();
            b022.duration = b020_o0;
            scheduler.startOrRestartTimer(_b1_b0_b2_c3_c1_Timer_2_timer, b022.duration);
            b021_SUBS_ERROR_r1 = false;
            scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b021");
            b021_SUBS_OK_r0 = false;
            scheduler.flushEventQueue(sessionID, "SUBS_OK_b021");
            b021_state = Com_bitreactive_library_mqtt_Subscriber._IDLE;
            b022_state = Com_bitreactive_library_timers_Timer2.ACTIVE;
            // step b330a;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_singleton_stop(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b1_b0_Device_Singleton_stop = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b005_state==Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE) {
            b005.stop = _b1_b0_Device_Singleton_stop;
            if(b008_state==Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE) {
                b009.stop();
                b010.stop();
                if(b022_state==Com_bitreactive_library_timers_Timer2._IDLE) {
                    if(b021_state==Com_bitreactive_library_mqtt_Subscriber.ACTIVE) {
                        b021.stop();
                        if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE) {
                            if(b017_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b017.stopped();
                                b015.stopped();
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step 6c888;
                                return TERMINATE_MACHINE;
                            } else if(b017_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b017.stopped();
                                b015.stopped();
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step df189;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                            b017.stopped();
                            if(b015_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b015.stopped();
                                if(b014_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                    b014.emptyBuffer();
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step 5701f;
                                    return TERMINATE_MACHINE;
                                } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step 0e659;
                                    return TERMINATE_MACHINE;
                                } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                    b014.emptyBuffer();
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step bddac;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else if(b015_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b015.stopped();
                                b014.emptyBuffer();
                                b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step d44ec;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else if(b021_state==Com_bitreactive_library_mqtt_Subscriber._IDLE) {
                        b021.stop();
                        if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE) {
                            if(b017_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b017.stopped();
                                b015.stopped();
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step b1474;
                                return TERMINATE_MACHINE;
                            } else if(b017_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b017.stopped();
                                b015.stopped();
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step daabc;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                            b017.stopped();
                            if(b015_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b015.stopped();
                                if(b014_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                    b014.emptyBuffer();
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step 72ad6;
                                    return TERMINATE_MACHINE;
                                } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step 863e9;
                                    return TERMINATE_MACHINE;
                                } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                    b014.emptyBuffer();
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step 0d5a9;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else if(b015_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b015.stopped();
                                b014.emptyBuffer();
                                b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step 289a3;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else {
                        return DISCARD_SIGNAL;
                    }
                } else if(b022_state==Com_bitreactive_library_timers_Timer2.ACTIVE) {
                    b021.stop();
                    if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE) {
                        if(b017_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                            b017.stopped();
                            b015.stopped();
                            scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                            -- sessionCount;
                            // step 9c50c;
                            return TERMINATE_MACHINE;
                        } else if(b017_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                            b017.stopped();
                            b015.stopped();
                            scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                            -- sessionCount;
                            // step 9fea1;
                            return TERMINATE_MACHINE;
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                        b017.stopped();
                        if(b015_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                            b015.stopped();
                            if(b014_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                b014.emptyBuffer();
                                b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step 86d77;
                                return TERMINATE_MACHINE;
                            } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step df438;
                                return TERMINATE_MACHINE;
                            } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                b014.emptyBuffer();
                                b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step 0f4db;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else if(b015_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                            b015.stopped();
                            b014.emptyBuffer();
                            b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                            scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                            -- sessionCount;
                            // step 34453;
                            return TERMINATE_MACHINE;
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else {
                        return DISCARD_SIGNAL;
                    }
                } else {
                    return DISCARD_SIGNAL;
                }
            } else if(b008_state==Com_bitreactive_library_mqtt_RobustMQTT.INITIALIZING) {
                b009.stop();
                if(b010_state==Com_bitreactive_library_mqtt_ConnHandler.ACTIVE) {
                    b010.stop();
                    if(b022_state==Com_bitreactive_library_timers_Timer2._IDLE) {
                        if(b021_state==Com_bitreactive_library_mqtt_Subscriber.ACTIVE) {
                            b021.stop();
                            if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING) {
                                b017.stopped();
                                b015.stopped();
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step fc94c;
                                return TERMINATE_MACHINE;
                            } else if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                                b017.stopped();
                                if(b015_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                    b015.stopped();
                                    if(b014_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                        b014.emptyBuffer();
                                        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                        -- sessionCount;
                                        // step 61ec7;
                                        return TERMINATE_MACHINE;
                                    } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                        -- sessionCount;
                                        // step d9454;
                                        return TERMINATE_MACHINE;
                                    } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                        b014.emptyBuffer();
                                        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                        -- sessionCount;
                                        // step c1f7b;
                                        return TERMINATE_MACHINE;
                                    } else {
                                        return DISCARD_SIGNAL;
                                    }
                                } else if(b015_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                    b015.stopped();
                                    b014.emptyBuffer();
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step 802c7;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else if(b021_state==Com_bitreactive_library_mqtt_Subscriber._IDLE) {
                            b021.stop();
                            if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING) {
                                b017.stopped();
                                b015.stopped();
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step 2e594;
                                return TERMINATE_MACHINE;
                            } else if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                                b017.stopped();
                                if(b015_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                    b015.stopped();
                                    if(b014_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                        b014.emptyBuffer();
                                        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                        -- sessionCount;
                                        // step 7751b;
                                        return TERMINATE_MACHINE;
                                    } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                        -- sessionCount;
                                        // step 9ca33;
                                        return TERMINATE_MACHINE;
                                    } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                        b014.emptyBuffer();
                                        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                        -- sessionCount;
                                        // step 47de1;
                                        return TERMINATE_MACHINE;
                                    } else {
                                        return DISCARD_SIGNAL;
                                    }
                                } else if(b015_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                    b015.stopped();
                                    b014.emptyBuffer();
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step 1278e;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else if(b022_state==Com_bitreactive_library_timers_Timer2.ACTIVE) {
                        b021.stop();
                        if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING) {
                            b017.stopped();
                            b015.stopped();
                            scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                            -- sessionCount;
                            // step 77968;
                            return TERMINATE_MACHINE;
                        } else if(b016_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                            b017.stopped();
                            if(b015_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b015.stopped();
                                if(b014_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                    b014.emptyBuffer();
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step 81dbe;
                                    return TERMINATE_MACHINE;
                                } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step bce50;
                                    return TERMINATE_MACHINE;
                                } else if(b014_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                    b014.emptyBuffer();
                                    b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                    -- sessionCount;
                                    // step 79198;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else if(b015_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b015.stopped();
                                b014.emptyBuffer();
                                b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                                -- sessionCount;
                                // step bff84;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else {
                        return DISCARD_SIGNAL;
                    }
                } else if(b010_state==Com_bitreactive_library_mqtt_ConnHandler.INITIALIZING) {
                    b010.stop();
                    b021.stop();
                    b017.stopped();
                    b015.stopped();
                    scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                    -- sessionCount;
                    // step 5cb55;
                    return TERMINATE_MACHINE;
                } else {
                    return DISCARD_SIGNAL;
                }
            } else if(b008_state==Com_bitreactive_library_mqtt_RobustMQTT._IDLE) {
                b009.stop();
                b010.stop();
                b021.stop();
                b017.stopped();
                b015.stopped();
                scheduler.stopBreak(_b1_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b005.stop).getInstance()+ "_" + "stopped", b005.stop);
                -- sessionCount;
                // step b6a0b;
                return TERMINATE_MACHINE;
            } else {
                return DISCARD_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_singleton_start(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b1_b0_Device_Singleton_start = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b005_state==Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE) {
            b005.start = _b1_b0_Device_Singleton_start;
            if(b007_state==Com_bitreactive_library_flowlogic_First.OTHERS) {
                // step 02208;
                return CONSUME_SIGNAL;
            } else if(b007_state==Com_bitreactive_library_flowlogic_First._IDLE) {
                com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters b005_o2 = b005.init(b005.start);
                b008.p = b005_o2;
                com.bitreactive.library.mqtt.MQTTConfigParam b008_o0 = b008.getConfig();
                org.eclipse.paho.client.mqttv3.MqttCallback b009_o0 = b009.create(b008_o0);
                com.bitreactive.library.mqtt.connhandler.ConnHandler.Parameters b008_o1 = b008.startConn(b009_o0);
                b010.p = b008_o1;
                b010.connect();
                b009_CONN_LOST_r0 = true;
                b009_MSG_ARRIVED_r2 = true;
                b010_INIT_OK_r0 = true;
                b010_INIT_FAILED_r1 = true;
                b008_state = Com_bitreactive_library_mqtt_RobustMQTT.INITIALIZING;
                b007_state = Com_bitreactive_library_flowlogic_First.OTHERS;
                b010_state = Com_bitreactive_library_mqtt_ConnHandler.INITIALIZING;
                b009_state = Com_bitreactive_library_mqtt_CreateCallback.ACTIVE;
                // step fd5dd;
                return CONSUME_SIGNAL;
            } else {
                return DISCARD_SIGNAL;
            }
        } else if(b005_state==Org_reactiveblocks_ibmiot_DeviceSingleton._IDLE) {
            b005.start = _b1_b0_Device_Singleton_start;
            com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters b005_o2 = b005.init(b005.start);
            b008.p = b005_o2;
            com.bitreactive.library.mqtt.MQTTConfigParam b008_o0 = b008.getConfig();
            org.eclipse.paho.client.mqttv3.MqttCallback b009_o0 = b009.create(b008_o0);
            com.bitreactive.library.mqtt.connhandler.ConnHandler.Parameters b008_o1 = b008.startConn(b009_o0);
            b010.p = b008_o1;
            b010.connect();
            b009_CONN_LOST_r0 = true;
            b009_MSG_ARRIVED_r2 = true;
            b010_INIT_OK_r0 = true;
            b010_INIT_FAILED_r1 = true;
            b008_state = Com_bitreactive_library_mqtt_RobustMQTT.INITIALIZING;
            b007_state = Com_bitreactive_library_flowlogic_First.OTHERS;
            b005_state = Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE;
            b010_state = Com_bitreactive_library_mqtt_ConnHandler.INITIALIZING;
            b009_state = Com_bitreactive_library_mqtt_CreateCallback.ACTIVE;
            // step 126af;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_PUB_OK_b015(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b015_PUB_OK_r0) {
            if(b014.isEmpty()) {
                b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.READY;
                b015_PUB_OK_r0 = false;
                scheduler.flushEventQueue(sessionID, "PUB_OK_b015");
                b015_PUB_ERROR_r1 = false;
                scheduler.flushEventQueue(sessionID, "PUB_ERROR_b015");
                b014_state = Com_bitreactive_library_buffering_BufferEager.READY;
                b015_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                // step e17dc;
                return CONSUME_SIGNAL;
            } else {
                b014.getFromBuffer();
                b013.msg = b014.out;
                if(b013.discardDueToFreshness()) {
                    b013.getNext();
                    scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
                    b015_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b015");
                    b015_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b015");
                    b015_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step 5240f;
                    return CONSUME_SIGNAL;
                } else {
                    if(b013.isConnected()) {
                        com.bitreactive.library.mqtt.publisher.Publisher.Parameters b013_o2 = b013.addClient();
                        _b1_b0_b2_c2_b0_Publish_Handler_t0_data = b013_o2;
                        scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t0);
                        b015_PUB_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "PUB_OK_b015");
                        b015_PUB_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "PUB_ERROR_b015");
                        b015_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                        // step 83d41;
                        return CONSUME_SIGNAL;
                    } else {
                        if(b013.discardDueToQos()) {
                            b013.getNext();
                            scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
                            b015_PUB_OK_r0 = false;
                            scheduler.flushEventQueue(sessionID, "PUB_OK_b015");
                            b015_PUB_ERROR_r1 = false;
                            scheduler.flushEventQueue(sessionID, "PUB_ERROR_b015");
                            b015_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                            // step bd698;
                            return CONSUME_SIGNAL;
                        } else {
                            b013.toWait();
                            _b1_b0_b2_c2_b0_Publish_Handler_t2_data = b013.msg;
                            scheduler.startOrRestartTimer(_b1_b0_b2_c2_b0_Publish_Handler_t2);
                            b015_PUB_OK_r0 = false;
                            scheduler.flushEventQueue(sessionID, "PUB_OK_b015");
                            b015_PUB_ERROR_r1 = false;
                            scheduler.flushEventQueue(sessionID, "PUB_ERROR_b015");
                            b015_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                            // step 64aae;
                            return CONSUME_SIGNAL;
                        }
                    }
                }
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_PUB_ERROR_b015(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b015_PUB_ERROR_r1) {
            b013.msg = ((com.bitreactive.library.mqtt.MQTTMessage) _data);
            if(b013.discardDueToFreshness()) {
                b013.getNext();
                scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
                b015_PUB_OK_r0 = false;
                scheduler.flushEventQueue(sessionID, "PUB_OK_b015");
                b015_PUB_ERROR_r1 = false;
                scheduler.flushEventQueue(sessionID, "PUB_ERROR_b015");
                b015_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                // step 9272c;
                return CONSUME_SIGNAL;
            } else {
                if(b013.discardDueToQos()) {
                    b013.getNext();
                    scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t3);
                    b015_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b015");
                    b015_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b015");
                    b015_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step be5a1;
                    return CONSUME_SIGNAL;
                } else {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b013_o2 = b013.addClient();
                    _b1_b0_b2_c2_b0_Publish_Handler_t0_data = b013_o2;
                    scheduler.startBreak(_b1_b0_b2_c2_b0_Publish_Handler_t0);
                    b015_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b015");
                    b015_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b015");
                    b015_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step 8be2b;
                    return CONSUME_SIGNAL;
                }
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_INIT_OK_b010(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b010_INIT_OK_r0) {
            if(b010_state==Com_bitreactive_library_mqtt_ConnHandler.INITIALIZING) {
                b008.client = ((org.eclipse.paho.client.mqttv3.MqttClient) _data);
                com.bitreactive.library.mqtt.subscriber.Subscriber.Parameters b008_o2 = b008.getSubscribeParam();
                b020.p = b008_o2;
                if(b020.containTopic()) {
                    b021.stop();
                    com.bitreactive.library.mqtt.publishmanager.PublishManager.Parameters b008_o3 = b008.getPublishParam();
                    b011.p = b008_o3;
                    boolean b011_o0 = b011.usePersistent();
                    if(b011_o0==true) {
                        com.bitreactive.library.mqtt.publishhandler2.PublishHandler2.Parameters b011_o2 = b011.createParam2();
                        b016.p = b011_o2;
                        java.lang.String b016_o0 = b016.getFilename();
                        b018.init(b016_o0);
                        com.bitreactive.library.mqtt.publishpolicy.PublishPolicy.Parameters b016_o5 = b016.getParams();
                        b019.p = b016_o5;
                        scheduler.stopTimer(_b1_b0_b2_c3_c1_Timer_2_timer);
                        scheduler.startBreak(_b1_b0_b2_c3_Subscribe_Handler_t0);
                        b010_INIT_OK_r0 = false;
                        b021_SUBS_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b021");
                        b021_SUBS_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_OK_b021");
                        b018_INIT_r1 = true;
                        b011_state = Com_bitreactive_library_mqtt_PublishManager.INITIALIZING;
                        b016_state = Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING;
                        b018_state = Com_bitreactive_library_buffering_PersistentBuffer.INITIALIZING;
                        b019_state = Com_bitreactive_library_mqtt_PublishPolicy.ACTIVE;
                        b010_state = Com_bitreactive_library_mqtt_ConnHandler.ACTIVE;
                        b020_state = Com_bitreactive_library_mqtt_SubscribeHandler.ACTIVE;
                        // step 154f3;
                        return CONSUME_SIGNAL;
                    } else {
                        com.bitreactive.library.mqtt.publishpolicy.PublishPolicy.Parameters b011_o1 = b011.createParam();
                        b014.emptyBuffer();
                        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.READY;
                        b013.p = b011_o1;
                        scheduler.stopTimer(_b1_b0_b2_c3_c1_Timer_2_timer);
                        scheduler.startBreak(_b1_b0_b2_c3_Subscribe_Handler_t0);
                        scheduler.startBreak(_b1_b0_b2_c2_Publish_Manager_t0);
                        b010_INIT_OK_r0 = false;
                        b021_SUBS_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b021");
                        b021_SUBS_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_OK_b021");
                        b011_state = Com_bitreactive_library_mqtt_PublishManager.INITIALIZING;
                        b012_state = Com_bitreactive_library_mqtt_PublishHandler.ACTIVE;
                        b013_state = Com_bitreactive_library_mqtt_PublishPolicy.ACTIVE;
                        b010_state = Com_bitreactive_library_mqtt_ConnHandler.ACTIVE;
                        b014_state = Com_bitreactive_library_buffering_BufferEager.READY;
                        b020_state = Com_bitreactive_library_mqtt_SubscribeHandler.ACTIVE;
                        // step 8a22f;
                        return CONSUME_SIGNAL;
                    }
                } else {
                    com.bitreactive.library.mqtt.publishmanager.PublishManager.Parameters b008_o3 = b008.getPublishParam();
                    b011.p = b008_o3;
                    boolean b011_o0 = b011.usePersistent();
                    if(b011_o0==true) {
                        com.bitreactive.library.mqtt.publishhandler2.PublishHandler2.Parameters b011_o2 = b011.createParam2();
                        b016.p = b011_o2;
                        java.lang.String b016_o0 = b016.getFilename();
                        b018.init(b016_o0);
                        com.bitreactive.library.mqtt.publishpolicy.PublishPolicy.Parameters b016_o5 = b016.getParams();
                        b019.p = b016_o5;
                        b010_INIT_OK_r0 = false;
                        b018_INIT_r1 = true;
                        b011_state = Com_bitreactive_library_mqtt_PublishManager.INITIALIZING;
                        b016_state = Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING;
                        b018_state = Com_bitreactive_library_buffering_PersistentBuffer.INITIALIZING;
                        b019_state = Com_bitreactive_library_mqtt_PublishPolicy.ACTIVE;
                        b010_state = Com_bitreactive_library_mqtt_ConnHandler.ACTIVE;
                        b020_state = Com_bitreactive_library_mqtt_SubscribeHandler.ACTIVE;
                        // step 8f8e0;
                        return CONSUME_SIGNAL;
                    } else {
                        com.bitreactive.library.mqtt.publishpolicy.PublishPolicy.Parameters b011_o1 = b011.createParam();
                        b014.emptyBuffer();
                        b014_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.READY;
                        b013.p = b011_o1;
                        scheduler.startBreak(_b1_b0_b2_c2_Publish_Manager_t0);
                        b010_INIT_OK_r0 = false;
                        b011_state = Com_bitreactive_library_mqtt_PublishManager.INITIALIZING;
                        b012_state = Com_bitreactive_library_mqtt_PublishHandler.ACTIVE;
                        b013_state = Com_bitreactive_library_mqtt_PublishPolicy.ACTIVE;
                        b010_state = Com_bitreactive_library_mqtt_ConnHandler.ACTIVE;
                        b014_state = Com_bitreactive_library_buffering_BufferEager.READY;
                        b020_state = Com_bitreactive_library_mqtt_SubscribeHandler.ACTIVE;
                        // step 7df21;
                        return CONSUME_SIGNAL;
                    }
                }
            } else {
                b010_INIT_OK_r0 = false;
                // step f8ac2;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    public java.lang.String getPartName() {
        return null;
    }

    public int getMultiplicityPattern() {
        return MULTIPLICITY_SINGLETON;
    }
}
