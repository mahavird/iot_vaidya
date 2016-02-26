package com.heartrate.block.a.component;

public class DeviceSingletonSM extends no.ntnu.item.arctis.runtime.IStateMachine {


    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b0_b0_b2_c1_Conn_Handler_t0;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b0_b0_b2_c1_Conn_Handler_t1;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b0_b0_b2_c2_b0_Publish_Handler_t0;
    private com.bitreactive.library.mqtt.publisher.Publisher.Parameters _b0_b0_b2_c2_b0_Publish_Handler_t0_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b0_b0_b2_c2_b0_Publish_Handler_t2;
    private com.bitreactive.library.mqtt.MQTTMessage _b0_b0_b2_c2_b0_Publish_Handler_t2_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b0_b0_b2_c2_b0_Publish_Handler_t3;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b0_b0_b2_c2_b1_Publish_Handler_2_t0;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b0_b0_b2_c2_b1_Publish_Handler_2_t1;
    private com.bitreactive.library.mqtt.MQTTMessage _b0_b0_b2_c2_b1_Publish_Handler_2_t1_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b0_b0_b2_c2_b1_Publish_Handler_2_t2;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b0_b0_b2_c2_b1_Publish_Handler_2_t3;
    private com.bitreactive.library.mqtt.publisher.Publisher.Parameters _b0_b0_b2_c2_b1_Publish_Handler_2_t3_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b0_b0_b2_c2_Publish_Manager_t0;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b0_b0_b2_c3_c1_Timer_2_timer;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b0_b0_b2_c3_Subscribe_Handler_t0;
    private no.ntnu.item.arctis.runtime.IStateMachine.Timer _b0_b0_b3_Limiter_t0;
    private int _b0_b0_b3_Limiter_t0_data;
    private no.ntnu.item.arctis.runtime.IStateMachine.Break _b0_b0_b2_c2_b0_c1_Buffer_Eager_t0;
    private org.reactiveblocks.ibmiot.devicesingleton.DeviceSingleton<java.lang.Object> b002;
    private com.bitreactive.library.gson.jsonserializer.JsonSerializer<org.reactiveblocks.ibmiot.Event> b003;
    private com.bitreactive.library.mqtt.robustmqtt.RobustMQTT b005;
    private com.bitreactive.library.mqtt.createcallback.CreateCallback b006;
    private com.bitreactive.library.mqtt.connhandler.ConnHandler b007;
    private com.bitreactive.library.mqtt.publishmanager.PublishManager b008;
    private com.bitreactive.library.mqtt.publishhandler.PublishHandler b009;
    private com.bitreactive.library.mqtt.publishpolicy.PublishPolicy b010;
    private com.bitreactive.library.buffering.buffereager.BufferEager<com.bitreactive.library.mqtt.MQTTMessage> b011;
    private com.bitreactive.library.mqtt.publisher.Publisher b012;
    private com.bitreactive.library.mqtt.publishhandler2.PublishHandler2 b013;
    private com.bitreactive.library.mqtt.publisher.Publisher b014;
    private com.bitreactive.library.buffering.persistentbuffer.PersistentBuffer<com.bitreactive.library.mqtt.MQTTMessage> b015;
    private com.bitreactive.library.mqtt.publishpolicy.PublishPolicy b016;
    private com.bitreactive.library.mqtt.subscribehandler.SubscribeHandler b017;
    private com.bitreactive.library.mqtt.subscriber.Subscriber b018;
    private com.bitreactive.library.timers.timer2.Timer2 b019;
    private com.bitreactive.library.flowlogic.limiter.Limiter<org.reactiveblocks.ibmiot.Event> b020;
    private boolean b014_PUB_ERROR_r1;
    private boolean b006_MSG_ARRIVED_r2;
    private boolean b007_RECONN_FAILED_r3;
    private boolean b015_INIT_r1;
    private boolean b014_PUB_OK_r0;
    private boolean b012_PUB_OK_r0;
    private boolean b015_REMOVED_r0;
    private boolean b007_RECONN_OK_r2;
    private boolean b018_SUBS_ERROR_r1;
    private boolean b018_SUBS_OK_r0;
    private boolean b007_INIT_OK_r0;
    private boolean b012_PUB_ERROR_r1;
    private boolean b007_INIT_FAILED_r1;
    private boolean b006_CONN_LOST_r0;
    private enum Org_reactiveblocks_ibmiot_DeviceSingleton {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_gson_JsonSerializer {_IDLE};
    private enum Com_bitreactive_library_flowlogic_First {OTHERS, _IDLE};
    private enum Com_bitreactive_library_mqtt_RobustMQTT {INITIALIZING, ACTIVE, _IDLE};
    private enum Com_bitreactive_library_mqtt_CreateCallback {ACTIVE, _IDLE};
    private enum Com_bitreactive_library_mqtt_ConnHandler {INITIALIZING, ACTIVE, _IDLE};
    private enum Com_bitreactive_library_mqtt_PublishManager {ACTIVE, INITIALIZING, _IDLE};
    private enum Com_bitreactive_library_mqtt_PublishHandler {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_mqtt_PublishPolicy {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_buffering_BufferEager {STOPPING, READY, ACTIVE, FIRSTITEMADDED, _IDLE};
    private enum Com_bitreactive_library_mqtt_Publisher {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_mqtt_PublishHandler2 {_IDLE, ACTIVE, INITIALIZING};
    private enum Com_bitreactive_library_buffering_PersistentBuffer {ACTIVE, INITIALIZING, _IDLE, REMOVING};
    private enum Com_bitreactive_library_mqtt_SubscribeHandler {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_mqtt_Subscriber {ACTIVE, _IDLE};
    private enum Com_bitreactive_library_timers_Timer2 {_IDLE, ACTIVE};
    private enum Com_bitreactive_library_flowlogic_Limiter {BLOCKING, _IDLE};
    private enum Com_bitreactive_library_flowlogic_Switcher {_IDLE, OUT2};
    private Org_reactiveblocks_ibmiot_DeviceSingleton b002_state;
    private Com_bitreactive_library_gson_JsonSerializer b003_state;
    private Com_bitreactive_library_flowlogic_First b004_state;
    private Com_bitreactive_library_mqtt_RobustMQTT b005_state;
    private Com_bitreactive_library_mqtt_CreateCallback b006_state;
    private Com_bitreactive_library_mqtt_ConnHandler b007_state;
    private Com_bitreactive_library_mqtt_PublishManager b008_state;
    private Com_bitreactive_library_mqtt_PublishHandler b009_state;
    private Com_bitreactive_library_mqtt_PublishPolicy b010_state;
    private Com_bitreactive_library_buffering_BufferEager b011_state;
    private Com_bitreactive_library_mqtt_Publisher b012_state;
    private Com_bitreactive_library_mqtt_PublishHandler2 b013_state;
    private Com_bitreactive_library_mqtt_Publisher b014_state;
    private Com_bitreactive_library_buffering_PersistentBuffer b015_state;
    private Com_bitreactive_library_mqtt_PublishPolicy b016_state;
    private Com_bitreactive_library_mqtt_SubscribeHandler b017_state;
    private Com_bitreactive_library_mqtt_Subscriber b018_state;
    private Com_bitreactive_library_timers_Timer2 b019_state;
    private Com_bitreactive_library_flowlogic_Limiter b020_state;
    private Com_bitreactive_library_flowlogic_Switcher b021_state;
    private enum Com_bitreactive_library_buffering_BufferEager_Stm {_IDLE, READY, FIRSTITEMADDED, STOPPING, ISBUFFEREMPTY, HASROOMFORALLINITIAL, CHECKCONTENTADDALL, ISEMPTYWHILESTOPPING, ACTIVE, HASROOMFORALL, HASROOMFORALLAFTERADDINGFIRST, ISEMPTYWHILEACTIVE, HASROOM, HASROOMAFTERADDINGFIRST};
    private Com_bitreactive_library_buffering_BufferEager_Stm b011_stm_state;
    private static int sessionCount = 0;
    public DeviceSingletonSM(no.ntnu.item.arctis.runtime.Scheduler scheduler, java.lang.String sessionID, java.lang.String parentSessionID, java.lang.String parentSessionPath) {
        super(scheduler, "b002", sessionID, parentSessionID, parentSessionPath);
        ++ sessionCount;
    }


    public int getSessionCount() {
        return sessionCount;
    }

    public int fireInitial() {
        b002 = new org.reactiveblocks.ibmiot.devicesingleton.DeviceSingleton<java.lang.Object>();
        b002.setBlockID("b002", sessionID);
        b002.setRuntime(scheduler.getRuntime());
        b003 = new com.bitreactive.library.gson.jsonserializer.JsonSerializer<org.reactiveblocks.ibmiot.Event>();
        b003.setBlockID("b003", sessionID);
        b003.setRuntime(scheduler.getRuntime());
        b005 = new com.bitreactive.library.mqtt.robustmqtt.RobustMQTT();
        b005.setBlockID("b005", sessionID);
        b005.setRuntime(scheduler.getRuntime());
        b006 = new com.bitreactive.library.mqtt.createcallback.CreateCallback();
        b006.setBlockID("b006", sessionID);
        b006.setRuntime(scheduler.getRuntime());
        b007 = new com.bitreactive.library.mqtt.connhandler.ConnHandler();
        b007.setBlockID("b007", sessionID);
        b007.setRuntime(scheduler.getRuntime());
        b008 = new com.bitreactive.library.mqtt.publishmanager.PublishManager();
        b008.setBlockID("b008", sessionID);
        b008.setRuntime(scheduler.getRuntime());
        b009 = new com.bitreactive.library.mqtt.publishhandler.PublishHandler();
        b009.setBlockID("b009", sessionID);
        b009.setRuntime(scheduler.getRuntime());
        b010 = new com.bitreactive.library.mqtt.publishpolicy.PublishPolicy();
        b010.setBlockID("b010", sessionID);
        b010.setRuntime(scheduler.getRuntime());
        b011 = new com.bitreactive.library.buffering.buffereager.BufferEager<com.bitreactive.library.mqtt.MQTTMessage>(true, 100, true);
        b011.setBlockID("b011", sessionID);
        b011.setRuntime(scheduler.getRuntime());
        b012 = new com.bitreactive.library.mqtt.publisher.Publisher();
        b012.setBlockID("b012", sessionID);
        b012.setRuntime(scheduler.getRuntime());
        b013 = new com.bitreactive.library.mqtt.publishhandler2.PublishHandler2();
        b013.setBlockID("b013", sessionID);
        b013.setRuntime(scheduler.getRuntime());
        b014 = new com.bitreactive.library.mqtt.publisher.Publisher();
        b014.setBlockID("b014", sessionID);
        b014.setRuntime(scheduler.getRuntime());
        b015 = new com.bitreactive.library.buffering.persistentbuffer.PersistentBuffer<com.bitreactive.library.mqtt.MQTTMessage>();
        b015.setBlockID("b015", sessionID);
        b015.setRuntime(scheduler.getRuntime());
        b016 = new com.bitreactive.library.mqtt.publishpolicy.PublishPolicy();
        b016.setBlockID("b016", sessionID);
        b016.setRuntime(scheduler.getRuntime());
        b017 = new com.bitreactive.library.mqtt.subscribehandler.SubscribeHandler();
        b017.setBlockID("b017", sessionID);
        b017.setRuntime(scheduler.getRuntime());
        b018 = new com.bitreactive.library.mqtt.subscriber.Subscriber();
        b018.setBlockID("b018", sessionID);
        b018.setRuntime(scheduler.getRuntime());
        b019 = new com.bitreactive.library.timers.timer2.Timer2();
        b019.setBlockID("b019", sessionID);
        b019.setRuntime(scheduler.getRuntime());
        b020 = new com.bitreactive.library.flowlogic.limiter.Limiter<org.reactiveblocks.ibmiot.Event>(1000);
        b020.setBlockID("b020", sessionID);
        b020.setRuntime(scheduler.getRuntime());
        b002_state = Org_reactiveblocks_ibmiot_DeviceSingleton._IDLE;
        b003_state = Com_bitreactive_library_gson_JsonSerializer._IDLE;
        b004_state = Com_bitreactive_library_flowlogic_First._IDLE;
        b005_state = Com_bitreactive_library_mqtt_RobustMQTT._IDLE;
        b006_state = Com_bitreactive_library_mqtt_CreateCallback._IDLE;
        b007_state = Com_bitreactive_library_mqtt_ConnHandler._IDLE;
        b008_state = Com_bitreactive_library_mqtt_PublishManager._IDLE;
        b009_state = Com_bitreactive_library_mqtt_PublishHandler._IDLE;
        b010_state = Com_bitreactive_library_mqtt_PublishPolicy._IDLE;
        b011_state = Com_bitreactive_library_buffering_BufferEager._IDLE;
        b012_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
        b013_state = Com_bitreactive_library_mqtt_PublishHandler2._IDLE;
        b014_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
        b015_state = Com_bitreactive_library_buffering_PersistentBuffer._IDLE;
        b016_state = Com_bitreactive_library_mqtt_PublishPolicy._IDLE;
        b017_state = Com_bitreactive_library_mqtt_SubscribeHandler._IDLE;
        b018_state = Com_bitreactive_library_mqtt_Subscriber._IDLE;
        b019_state = Com_bitreactive_library_timers_Timer2._IDLE;
        b020_state = Com_bitreactive_library_flowlogic_Limiter._IDLE;
        b021_state = Com_bitreactive_library_flowlogic_Switcher._IDLE;
        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
        _b0_b0_b2_c1_Conn_Handler_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b0_b0_b2_c1_Conn_Handler_t0", this);
        _b0_b0_b2_c1_Conn_Handler_t1 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b0_b0_b2_c1_Conn_Handler_t1", this);
        _b0_b0_b2_c2_b0_Publish_Handler_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b0_b0_b2_c2_b0_Publish_Handler_t0", this);
        _b0_b0_b2_c2_b0_Publish_Handler_t2 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer(5000, "_b0_b0_b2_c2_b0_Publish_Handler_t2", this);
        _b0_b0_b2_c2_b0_Publish_Handler_t3 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b0_b0_b2_c2_b0_Publish_Handler_t3", this);
        _b0_b0_b2_c2_b1_Publish_Handler_2_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b0_b0_b2_c2_b1_Publish_Handler_2_t0", this);
        _b0_b0_b2_c2_b1_Publish_Handler_2_t1 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer(1000, "_b0_b0_b2_c2_b1_Publish_Handler_2_t1", this);
        _b0_b0_b2_c2_b1_Publish_Handler_2_t2 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b0_b0_b2_c2_b1_Publish_Handler_2_t2", this);
        _b0_b0_b2_c2_b1_Publish_Handler_2_t3 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b0_b0_b2_c2_b1_Publish_Handler_2_t3", this);
        _b0_b0_b2_c2_Publish_Manager_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b0_b0_b2_c2_Publish_Manager_t0", this);
        _b0_b0_b2_c3_c1_Timer_2_timer = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b0_b0_b2_c3_c1_Timer_2_timer", this);
        _b0_b0_b2_c3_Subscribe_Handler_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b0_b0_b2_c3_Subscribe_Handler_t0", this);
        _b0_b0_b3_Limiter_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Timer("_b0_b0_b3_Limiter_t0", this);
        _b0_b0_b2_c2_b0_c1_Buffer_Eager_t0 = new no.ntnu.item.arctis.runtime.IStateMachine.Break("_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0", this);
        return CONSUME_SIGNAL;
    }

    public int fireTimer(java.lang.String _timerID) {
        if(_timerID.equals("_b0_b0_b2_c2_b1_Publish_Handler_2_t0")) {
            return handleTimer__b0_b0_b2_c2_b1_Publish_Handler_2_t0();
        } else if(_timerID.equals("_b0_b0_b2_c1_Conn_Handler_t1")) {
            return handleTimer__b0_b0_b2_c1_Conn_Handler_t1();
        } else if(_timerID.equals("_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0")) {
            return handleTimer__b0_b0_b2_c2_b0_c1_Buffer_Eager_t0();
        } else if(_timerID.equals("_b0_b0_b2_c3_Subscribe_Handler_t0")) {
            return handleTimer__b0_b0_b2_c3_Subscribe_Handler_t0();
        } else if(_timerID.equals("_b0_b0_b2_c1_Conn_Handler_t0")) {
            return handleTimer__b0_b0_b2_c1_Conn_Handler_t0();
        } else if(_timerID.equals("_b0_b0_b2_c2_b1_Publish_Handler_2_t3")) {
            return handleTimer__b0_b0_b2_c2_b1_Publish_Handler_2_t3();
        } else if(_timerID.equals("_b0_b0_b2_c2_b0_Publish_Handler_t3")) {
            return handleTimer__b0_b0_b2_c2_b0_Publish_Handler_t3();
        } else if(_timerID.equals("_b0_b0_b2_c2_Publish_Manager_t0")) {
            return handleTimer__b0_b0_b2_c2_Publish_Manager_t0();
        } else if(_timerID.equals("_b0_b0_b2_c2_b0_Publish_Handler_t2")) {
            return handleTimer__b0_b0_b2_c2_b0_Publish_Handler_t2();
        } else if(_timerID.equals("_b0_b0_b2_c2_b0_Publish_Handler_t0")) {
            return handleTimer__b0_b0_b2_c2_b0_Publish_Handler_t0();
        } else if(_timerID.equals("_b0_b0_b3_Limiter_t0")) {
            return handleTimer__b0_b0_b3_Limiter_t0();
        } else if(_timerID.equals("_b0_b0_b2_c2_b1_Publish_Handler_2_t2")) {
            return handleTimer__b0_b0_b2_c2_b1_Publish_Handler_2_t2();
        } else if(_timerID.equals("_b0_b0_b2_c2_b1_Publish_Handler_2_t1")) {
            return handleTimer__b0_b0_b2_c2_b1_Publish_Handler_2_t1();
        } else if(_timerID.equals("_b0_b0_b2_c3_c1_Timer_2_timer")) {
            return handleTimer__b0_b0_b2_c3_c1_Timer_2_timer();
        }
        return DISCARD_SIGNAL;
    }

    public int fire(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.String _signalID, java.lang.Object _data) {
        if(_signalID.equals("singleton_stop")) {
            return handleEvent_singleton_stop(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("PUB_ERROR_b014")) {
            return handleEvent_PUB_ERROR_b014(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("SUBS_OK_b018")) {
            return handleEvent_SUBS_OK_b018(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("REMOVED_b015")) {
            return handleEvent_REMOVED_b015(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("INIT_b015")) {
            return handleEvent_INIT_b015(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("INIT_OK_b007")) {
            return handleEvent_INIT_OK_b007(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("MSG_ARRIVED_b006")) {
            return handleEvent_MSG_ARRIVED_b006(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("RECONN_OK_b007")) {
            return handleEvent_RECONN_OK_b007(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("singleton_event")) {
            return handleEvent_singleton_event(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("singleton_subscribe")) {
            return handleEvent_singleton_subscribe(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("SUBS_ERROR_b018")) {
            return handleEvent_SUBS_ERROR_b018(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("CONN_LOST_b006")) {
            return handleEvent_CONN_LOST_b006(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("PUB_OK_b014")) {
            return handleEvent_PUB_OK_b014(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("singleton_start")) {
            return handleEvent_singleton_start(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("RECONN_FAILED_b007")) {
            return handleEvent_RECONN_FAILED_b007(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("PUB_OK_b012")) {
            return handleEvent_PUB_OK_b012(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("PUB_ERROR_b012")) {
            return handleEvent_PUB_ERROR_b012(_sender, receiverSessionID, _data);
        } else if(_signalID.equals("INIT_FAILED_b007")) {
            return handleEvent_INIT_FAILED_b007(_sender, receiverSessionID, _data);
        }
        return TRIGGER_UNKNOWN;
    }

    private int handleTimer__b0_b0_b2_c2_b1_Publish_Handler_2_t0() {
        com.bitreactive.library.mqtt.MQTTMessage b015_o1 = b015.peek();
        if(b015_o1==null) {
            b013.empty();
            int b013_o2 = b013.getDuration();
            scheduler.startOrRestartTimer(_b0_b0_b2_c2_b1_Publish_Handler_2_t0, b013_o2);
            // step 76451;
            return CONSUME_SIGNAL;
        } else {
            b016.msg = b015_o1;
            if(b016.discardDueToFreshness()) {
                b016.getNext();
                scheduler.startBreak(_b0_b0_b2_c2_b1_Publish_Handler_2_t2);
                // step 5cb83;
                return CONSUME_SIGNAL;
            } else {
                if(b016.isConnected()) {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b016_o2 = b016.addClient();
                    _b0_b0_b2_c2_b1_Publish_Handler_2_t3_data = b016_o2;
                    scheduler.startBreak(_b0_b0_b2_c2_b1_Publish_Handler_2_t3);
                    // step 9fc49;
                    return CONSUME_SIGNAL;
                } else {
                    if(b016.discardDueToQos()) {
                        b016.getNext();
                        scheduler.startBreak(_b0_b0_b2_c2_b1_Publish_Handler_2_t2);
                        // step 8873f;
                        return CONSUME_SIGNAL;
                    } else {
                        b016.toWait();
                        _b0_b0_b2_c2_b1_Publish_Handler_2_t1_data = b016.msg;
                        scheduler.startOrRestartTimer(_b0_b0_b2_c2_b1_Publish_Handler_2_t1);
                        // step 809f9;
                        return CONSUME_SIGNAL;
                    }
                }
            }
        }
    }

    private int handleTimer__b0_b0_b2_c1_Conn_Handler_t1() {
        b007.connect();
        b007_INIT_OK_r0 = true;
        b007_INIT_FAILED_r1 = true;
        // step b9b8e;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b0_b0_b2_c2_b0_c1_Buffer_Eager_t0() {
        if(b011_stm_state==Com_bitreactive_library_buffering_BufferEager_Stm.FIRSTITEMADDED) {
            b011.getFromBuffer();
            b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.ACTIVE;
            b010.msg = b011.out;
            if(b010.discardDueToFreshness()) {
                b010.getNext();
                scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
                b011_state = Com_bitreactive_library_buffering_BufferEager.ACTIVE;
                // step 91788;
                return CONSUME_SIGNAL;
            } else {
                if(b010.isConnected()) {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b010_o2 = b010.addClient();
                    _b0_b0_b2_c2_b0_Publish_Handler_t0_data = b010_o2;
                    scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t0);
                    b011_state = Com_bitreactive_library_buffering_BufferEager.ACTIVE;
                    // step 45c79;
                    return CONSUME_SIGNAL;
                } else {
                    if(b010.discardDueToQos()) {
                        b010.getNext();
                        scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
                        b011_state = Com_bitreactive_library_buffering_BufferEager.ACTIVE;
                        // step 2b6df;
                        return CONSUME_SIGNAL;
                    } else {
                        b010.toWait();
                        _b0_b0_b2_c2_b0_Publish_Handler_t2_data = b010.msg;
                        scheduler.startOrRestartTimer(_b0_b0_b2_c2_b0_Publish_Handler_t2);
                        b011_state = Com_bitreactive_library_buffering_BufferEager.ACTIVE;
                        // step 4864b;
                        return CONSUME_SIGNAL;
                    }
                }
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleTimer__b0_b0_b2_c3_Subscribe_Handler_t0() {
        b018.subscribe(b017.p);
        b018_SUBS_ERROR_r1 = true;
        b018_SUBS_OK_r0 = true;
        b018_state = Com_bitreactive_library_mqtt_Subscriber.ACTIVE;
        // step cf1b2;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b0_b0_b2_c1_Conn_Handler_t0() {
        b007.reconnect();
        b007_RECONN_FAILED_r3 = true;
        b007_RECONN_OK_r2 = true;
        // step 0ebbb;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b0_b0_b2_c2_b1_Publish_Handler_2_t3() {
        b014.publish(_b0_b0_b2_c2_b1_Publish_Handler_2_t3_data);
        b014_PUB_ERROR_r1 = true;
        b014_PUB_OK_r0 = true;
        b014_state = Com_bitreactive_library_mqtt_Publisher.ACTIVE;
        // step 2bcff;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b0_b0_b2_c2_b0_Publish_Handler_t3() {
        if(b011.isEmpty()) {
            b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.READY;
            b011_state = Com_bitreactive_library_buffering_BufferEager.READY;
            // step 050e3;
            return CONSUME_SIGNAL;
        } else {
            b011.getFromBuffer();
            b010.msg = b011.out;
            if(b010.discardDueToFreshness()) {
                b010.getNext();
                scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
                // step 7ec31;
                return CONSUME_SIGNAL;
            } else {
                if(b010.isConnected()) {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b010_o2 = b010.addClient();
                    _b0_b0_b2_c2_b0_Publish_Handler_t0_data = b010_o2;
                    scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t0);
                    // step 8fe9b;
                    return CONSUME_SIGNAL;
                } else {
                    if(b010.discardDueToQos()) {
                        b010.getNext();
                        scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
                        // step 3ee63;
                        return CONSUME_SIGNAL;
                    } else {
                        b010.toWait();
                        _b0_b0_b2_c2_b0_Publish_Handler_t2_data = b010.msg;
                        scheduler.startOrRestartTimer(_b0_b0_b2_c2_b0_Publish_Handler_t2);
                        // step b2cfd;
                        return CONSUME_SIGNAL;
                    }
                }
            }
        }
    }

    private int handleTimer__b0_b0_b2_c2_Publish_Manager_t0() {
        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.start).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.start).getInstance()+ "_" + "ready", b002.start);
        b008_state = Com_bitreactive_library_mqtt_PublishManager.ACTIVE;
        b005_state = Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE;
        // step b8a0e;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b0_b0_b2_c2_b0_Publish_Handler_t2() {
        b010.msg = _b0_b0_b2_c2_b0_Publish_Handler_t2_data;
        if(b010.discardDueToFreshness()) {
            b010.getNext();
            scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
            // step 21680;
            return CONSUME_SIGNAL;
        } else {
            if(b010.isConnected()) {
                com.bitreactive.library.mqtt.publisher.Publisher.Parameters b010_o2 = b010.addClient();
                _b0_b0_b2_c2_b0_Publish_Handler_t0_data = b010_o2;
                scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t0);
                // step 32f62;
                return CONSUME_SIGNAL;
            } else {
                if(b010.discardDueToQos()) {
                    b010.getNext();
                    scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
                    // step ac16e;
                    return CONSUME_SIGNAL;
                } else {
                    b010.toWait();
                    _b0_b0_b2_c2_b0_Publish_Handler_t2_data = b010.msg;
                    scheduler.startOrRestartTimer(_b0_b0_b2_c2_b0_Publish_Handler_t2);
                    // step b12c7;
                    return CONSUME_SIGNAL;
                }
            }
        }
    }

    private int handleTimer__b0_b0_b2_c2_b0_Publish_Handler_t0() {
        b012.publish(_b0_b0_b2_c2_b0_Publish_Handler_t0_data);
        b012_PUB_ERROR_r1 = true;
        b012_PUB_OK_r0 = true;
        b012_state = Com_bitreactive_library_mqtt_Publisher.ACTIVE;
        // step 7779f;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b0_b0_b3_Limiter_t0() {
        b021_state = Com_bitreactive_library_flowlogic_Switcher._IDLE;
        b020_state = Com_bitreactive_library_flowlogic_Limiter._IDLE;
        // step 2b40d;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b0_b0_b2_c2_b1_Publish_Handler_2_t2() {
        b015.remove();
        b015_REMOVED_r0 = true;
        b015_state = Com_bitreactive_library_buffering_PersistentBuffer.REMOVING;
        // step ae7fc;
        return CONSUME_SIGNAL;
    }

    private int handleTimer__b0_b0_b2_c2_b1_Publish_Handler_2_t1() {
        b016.msg = _b0_b0_b2_c2_b1_Publish_Handler_2_t1_data;
        if(b016.discardDueToFreshness()) {
            b016.getNext();
            scheduler.startBreak(_b0_b0_b2_c2_b1_Publish_Handler_2_t2);
            // step 97822;
            return CONSUME_SIGNAL;
        } else {
            if(b016.isConnected()) {
                com.bitreactive.library.mqtt.publisher.Publisher.Parameters b016_o2 = b016.addClient();
                _b0_b0_b2_c2_b1_Publish_Handler_2_t3_data = b016_o2;
                scheduler.startBreak(_b0_b0_b2_c2_b1_Publish_Handler_2_t3);
                // step 097c9;
                return CONSUME_SIGNAL;
            } else {
                if(b016.discardDueToQos()) {
                    b016.getNext();
                    scheduler.startBreak(_b0_b0_b2_c2_b1_Publish_Handler_2_t2);
                    // step eeab0;
                    return CONSUME_SIGNAL;
                } else {
                    b016.toWait();
                    _b0_b0_b2_c2_b1_Publish_Handler_2_t1_data = b016.msg;
                    scheduler.startOrRestartTimer(_b0_b0_b2_c2_b1_Publish_Handler_2_t1);
                    // step 42a29;
                    return CONSUME_SIGNAL;
                }
            }
        }
    }

    private int handleTimer__b0_b0_b2_c3_c1_Timer_2_timer() {
        b018.subscribe(b017.p);
        b018_SUBS_ERROR_r1 = true;
        b018_SUBS_OK_r0 = true;
        b019_state = Com_bitreactive_library_timers_Timer2._IDLE;
        b018_state = Com_bitreactive_library_mqtt_Subscriber.ACTIVE;
        // step 6a6e1;
        return CONSUME_SIGNAL;
    }

    private int handleEvent_singleton_stop(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b0_b0_Device_Singleton_stop = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b002_state==Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE) {
            b002.stop = _b0_b0_Device_Singleton_stop;
            if(b005_state==Com_bitreactive_library_mqtt_RobustMQTT.INITIALIZING) {
                b006.stop();
                if(b007_state==Com_bitreactive_library_mqtt_ConnHandler.ACTIVE) {
                    b007.stop();
                    if(b019_state==Com_bitreactive_library_timers_Timer2._IDLE) {
                        if(b018_state==Com_bitreactive_library_mqtt_Subscriber.ACTIVE) {
                            b018.stop();
                            if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                                b014.stopped();
                                if(b012_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                    b012.stopped();
                                    if(b011_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                        -- sessionCount;
                                        // step 3ae27;
                                        return TERMINATE_MACHINE;
                                    } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                        b011.emptyBuffer();
                                        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                        -- sessionCount;
                                        // step 2bca1;
                                        return TERMINATE_MACHINE;
                                    } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                        b011.emptyBuffer();
                                        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                        -- sessionCount;
                                        // step eec0f;
                                        return TERMINATE_MACHINE;
                                    } else {
                                        return DISCARD_SIGNAL;
                                    }
                                } else if(b012_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                    b012.stopped();
                                    b011.emptyBuffer();
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step 0949f;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING) {
                                b014.stopped();
                                b012.stopped();
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step dd996;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else if(b018_state==Com_bitreactive_library_mqtt_Subscriber._IDLE) {
                            b018.stop();
                            if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                                b014.stopped();
                                if(b012_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                    b012.stopped();
                                    if(b011_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                        -- sessionCount;
                                        // step 1ee3d;
                                        return TERMINATE_MACHINE;
                                    } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                        b011.emptyBuffer();
                                        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                        -- sessionCount;
                                        // step 5f75b;
                                        return TERMINATE_MACHINE;
                                    } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                        b011.emptyBuffer();
                                        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                        scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                        -- sessionCount;
                                        // step 791fb;
                                        return TERMINATE_MACHINE;
                                    } else {
                                        return DISCARD_SIGNAL;
                                    }
                                } else if(b012_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                    b012.stopped();
                                    b011.emptyBuffer();
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step ed02e;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING) {
                                b014.stopped();
                                b012.stopped();
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step 2496d;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else if(b019_state==Com_bitreactive_library_timers_Timer2.ACTIVE) {
                        b018.stop();
                        if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                            b014.stopped();
                            if(b012_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b012.stopped();
                                if(b011_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step 46536;
                                    return TERMINATE_MACHINE;
                                } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                    b011.emptyBuffer();
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step 00384;
                                    return TERMINATE_MACHINE;
                                } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                    b011.emptyBuffer();
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step d416b;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else if(b012_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b012.stopped();
                                b011.emptyBuffer();
                                b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step 5a8b0;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING) {
                            b014.stopped();
                            b012.stopped();
                            scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                            -- sessionCount;
                            // step 591ad;
                            return TERMINATE_MACHINE;
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else {
                        return DISCARD_SIGNAL;
                    }
                } else if(b007_state==Com_bitreactive_library_mqtt_ConnHandler.INITIALIZING) {
                    b007.stop();
                    b018.stop();
                    b014.stopped();
                    b012.stopped();
                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                    -- sessionCount;
                    // step e4fa5;
                    return TERMINATE_MACHINE;
                } else {
                    return DISCARD_SIGNAL;
                }
            } else if(b005_state==Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE) {
                b006.stop();
                b007.stop();
                if(b019_state==Com_bitreactive_library_timers_Timer2._IDLE) {
                    if(b018_state==Com_bitreactive_library_mqtt_Subscriber.ACTIVE) {
                        b018.stop();
                        if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                            b014.stopped();
                            if(b012_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b012.stopped();
                                if(b011_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step 7f39d;
                                    return TERMINATE_MACHINE;
                                } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                    b011.emptyBuffer();
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step 100b3;
                                    return TERMINATE_MACHINE;
                                } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                    b011.emptyBuffer();
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step 9e278;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else if(b012_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b012.stopped();
                                b011.emptyBuffer();
                                b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step 46d4d;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE) {
                            if(b014_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b014.stopped();
                                b012.stopped();
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step 00389;
                                return TERMINATE_MACHINE;
                            } else if(b014_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b014.stopped();
                                b012.stopped();
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step b949d;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else if(b018_state==Com_bitreactive_library_mqtt_Subscriber._IDLE) {
                        b018.stop();
                        if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                            b014.stopped();
                            if(b012_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b012.stopped();
                                if(b011_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step 1947d;
                                    return TERMINATE_MACHINE;
                                } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                    b011.emptyBuffer();
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step 7cca1;
                                    return TERMINATE_MACHINE;
                                } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                    b011.emptyBuffer();
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                    scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                    -- sessionCount;
                                    // step 95717;
                                    return TERMINATE_MACHINE;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else if(b012_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b012.stopped();
                                b011.emptyBuffer();
                                b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step fce01;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE) {
                            if(b014_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                                b014.stopped();
                                b012.stopped();
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step 57085;
                                return TERMINATE_MACHINE;
                            } else if(b014_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                                b014.stopped();
                                b012.stopped();
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step 18323;
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
                } else if(b019_state==Com_bitreactive_library_timers_Timer2.ACTIVE) {
                    b018.stop();
                    if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2._IDLE) {
                        b014.stopped();
                        if(b012_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                            b012.stopped();
                            if(b011_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step 14aee;
                                return TERMINATE_MACHINE;
                            } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                b011.emptyBuffer();
                                b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step 2cd1b;
                                return TERMINATE_MACHINE;
                            } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                b011.emptyBuffer();
                                b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                                -- sessionCount;
                                // step 53f65;
                                return TERMINATE_MACHINE;
                            } else {
                                return DISCARD_SIGNAL;
                            }
                        } else if(b012_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                            b012.stopped();
                            b011.emptyBuffer();
                            b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm._IDLE;
                            scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                            -- sessionCount;
                            // step 02f46;
                            return TERMINATE_MACHINE;
                        } else {
                            return DISCARD_SIGNAL;
                        }
                    } else if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE) {
                        if(b014_state==Com_bitreactive_library_mqtt_Publisher.ACTIVE) {
                            b014.stopped();
                            b012.stopped();
                            scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                            -- sessionCount;
                            // step 48ea4;
                            return TERMINATE_MACHINE;
                        } else if(b014_state==Com_bitreactive_library_mqtt_Publisher._IDLE) {
                            b014.stopped();
                            b012.stopped();
                            scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                            -- sessionCount;
                            // step 1e489;
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
            } else if(b005_state==Com_bitreactive_library_mqtt_RobustMQTT._IDLE) {
                b006.stop();
                b007.stop();
                b018.stop();
                b014.stopped();
                b012.stopped();
                scheduler.stopBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.stop).getInstance()+ "_" + "stopped", b002.stop);
                -- sessionCount;
                // step 69df9;
                return TERMINATE_MACHINE;
            } else {
                return DISCARD_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_PUB_ERROR_b014(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b014_PUB_ERROR_r1) {
            b016.msg = ((com.bitreactive.library.mqtt.MQTTMessage) _data);
            if(b016.discardDueToFreshness()) {
                b016.getNext();
                scheduler.startBreak(_b0_b0_b2_c2_b1_Publish_Handler_2_t2);
                b014_PUB_ERROR_r1 = false;
                scheduler.flushEventQueue(sessionID, "PUB_ERROR_b014");
                b014_PUB_OK_r0 = false;
                scheduler.flushEventQueue(sessionID, "PUB_OK_b014");
                b014_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                // step 1e63d;
                return CONSUME_SIGNAL;
            } else {
                if(b016.discardDueToQos()) {
                    b016.getNext();
                    scheduler.startBreak(_b0_b0_b2_c2_b1_Publish_Handler_2_t2);
                    b014_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b014");
                    b014_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b014");
                    b014_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step 5690d;
                    return CONSUME_SIGNAL;
                } else {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b016_o2 = b016.addClient();
                    _b0_b0_b2_c2_b1_Publish_Handler_2_t3_data = b016_o2;
                    scheduler.startBreak(_b0_b0_b2_c2_b1_Publish_Handler_2_t3);
                    b014_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b014");
                    b014_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b014");
                    b014_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step 89336;
                    return CONSUME_SIGNAL;
                }
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_SUBS_OK_b018(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b018_SUBS_OK_r0) {
            b018_SUBS_ERROR_r1 = false;
            scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b018");
            b018_SUBS_OK_r0 = false;
            scheduler.flushEventQueue(sessionID, "SUBS_OK_b018");
            b018_state = Com_bitreactive_library_mqtt_Subscriber._IDLE;
            // step b8531;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_REMOVED_b015(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b015_REMOVED_r0) {
            if(((com.bitreactive.library.mqtt.MQTTMessage) _data)==null) {
                b013.empty();
                int b013_o2 = b013.getDuration();
                scheduler.startOrRestartTimer(_b0_b0_b2_c2_b1_Publish_Handler_2_t0, b013_o2);
                b015_REMOVED_r0 = false;
                b015_state = Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE;
                // step 5eed0;
                return CONSUME_SIGNAL;
            } else {
                b013.removed();
                int b013_o2 = b013.getDuration();
                scheduler.startOrRestartTimer(_b0_b0_b2_c2_b1_Publish_Handler_2_t0, b013_o2);
                b015_REMOVED_r0 = false;
                b015_state = Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE;
                // step 626a7;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_INIT_b015(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b015_INIT_r1) {
            scheduler.sendToSession(this, ((no.ntnu.item.arctis.runtime.SingletonData)b002.start).getSessionID(), ((no.ntnu.item.arctis.runtime.SingletonData)b002.start).getInstance()+ "_" + "ready", b002.start);
            int b013_o2 = b013.getDuration();
            scheduler.startOrRestartTimer(_b0_b0_b2_c2_b1_Publish_Handler_2_t0, b013_o2);
            b015_INIT_r1 = false;
            b008_state = Com_bitreactive_library_mqtt_PublishManager.ACTIVE;
            b005_state = Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE;
            b013_state = Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE;
            b015_state = Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE;
            // step 0540d;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_INIT_OK_b007(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b007_INIT_OK_r0) {
            if(b007_state==Com_bitreactive_library_mqtt_ConnHandler.INITIALIZING) {
                b005.client = ((org.eclipse.paho.client.mqttv3.MqttClient) _data);
                com.bitreactive.library.mqtt.subscriber.Subscriber.Parameters b005_o2 = b005.getSubscribeParam();
                b017.p = b005_o2;
                if(b017.containTopic()) {
                    b018.stop();
                    com.bitreactive.library.mqtt.publishmanager.PublishManager.Parameters b005_o3 = b005.getPublishParam();
                    b008.p = b005_o3;
                    boolean b008_o0 = b008.usePersistent();
                    if(b008_o0==true) {
                        com.bitreactive.library.mqtt.publishhandler2.PublishHandler2.Parameters b008_o2 = b008.createParam2();
                        b013.p = b008_o2;
                        java.lang.String b013_o0 = b013.getFilename();
                        b015.init(b013_o0);
                        com.bitreactive.library.mqtt.publishpolicy.PublishPolicy.Parameters b013_o5 = b013.getParams();
                        b016.p = b013_o5;
                        scheduler.stopTimer(_b0_b0_b2_c3_c1_Timer_2_timer);
                        scheduler.startBreak(_b0_b0_b2_c3_Subscribe_Handler_t0);
                        b007_INIT_OK_r0 = false;
                        b018_SUBS_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b018");
                        b018_SUBS_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_OK_b018");
                        b015_INIT_r1 = true;
                        b008_state = Com_bitreactive_library_mqtt_PublishManager.INITIALIZING;
                        b007_state = Com_bitreactive_library_mqtt_ConnHandler.ACTIVE;
                        b016_state = Com_bitreactive_library_mqtt_PublishPolicy.ACTIVE;
                        b017_state = Com_bitreactive_library_mqtt_SubscribeHandler.ACTIVE;
                        b013_state = Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING;
                        b015_state = Com_bitreactive_library_buffering_PersistentBuffer.INITIALIZING;
                        // step daced;
                        return CONSUME_SIGNAL;
                    } else {
                        com.bitreactive.library.mqtt.publishpolicy.PublishPolicy.Parameters b008_o1 = b008.createParam();
                        b011.emptyBuffer();
                        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.READY;
                        b010.p = b008_o1;
                        scheduler.stopTimer(_b0_b0_b2_c3_c1_Timer_2_timer);
                        scheduler.startBreak(_b0_b0_b2_c2_Publish_Manager_t0);
                        scheduler.startBreak(_b0_b0_b2_c3_Subscribe_Handler_t0);
                        b007_INIT_OK_r0 = false;
                        b018_SUBS_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b018");
                        b018_SUBS_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_OK_b018");
                        b008_state = Com_bitreactive_library_mqtt_PublishManager.INITIALIZING;
                        b007_state = Com_bitreactive_library_mqtt_ConnHandler.ACTIVE;
                        b017_state = Com_bitreactive_library_mqtt_SubscribeHandler.ACTIVE;
                        b009_state = Com_bitreactive_library_mqtt_PublishHandler.ACTIVE;
                        b010_state = Com_bitreactive_library_mqtt_PublishPolicy.ACTIVE;
                        b011_state = Com_bitreactive_library_buffering_BufferEager.READY;
                        // step cf2ba;
                        return CONSUME_SIGNAL;
                    }
                } else {
                    com.bitreactive.library.mqtt.publishmanager.PublishManager.Parameters b005_o3 = b005.getPublishParam();
                    b008.p = b005_o3;
                    boolean b008_o0 = b008.usePersistent();
                    if(b008_o0==true) {
                        com.bitreactive.library.mqtt.publishhandler2.PublishHandler2.Parameters b008_o2 = b008.createParam2();
                        b013.p = b008_o2;
                        java.lang.String b013_o0 = b013.getFilename();
                        b015.init(b013_o0);
                        com.bitreactive.library.mqtt.publishpolicy.PublishPolicy.Parameters b013_o5 = b013.getParams();
                        b016.p = b013_o5;
                        b007_INIT_OK_r0 = false;
                        b015_INIT_r1 = true;
                        b008_state = Com_bitreactive_library_mqtt_PublishManager.INITIALIZING;
                        b007_state = Com_bitreactive_library_mqtt_ConnHandler.ACTIVE;
                        b016_state = Com_bitreactive_library_mqtt_PublishPolicy.ACTIVE;
                        b017_state = Com_bitreactive_library_mqtt_SubscribeHandler.ACTIVE;
                        b013_state = Com_bitreactive_library_mqtt_PublishHandler2.INITIALIZING;
                        b015_state = Com_bitreactive_library_buffering_PersistentBuffer.INITIALIZING;
                        // step 883fa;
                        return CONSUME_SIGNAL;
                    } else {
                        com.bitreactive.library.mqtt.publishpolicy.PublishPolicy.Parameters b008_o1 = b008.createParam();
                        b011.emptyBuffer();
                        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.READY;
                        b010.p = b008_o1;
                        scheduler.startBreak(_b0_b0_b2_c2_Publish_Manager_t0);
                        b007_INIT_OK_r0 = false;
                        b008_state = Com_bitreactive_library_mqtt_PublishManager.INITIALIZING;
                        b007_state = Com_bitreactive_library_mqtt_ConnHandler.ACTIVE;
                        b017_state = Com_bitreactive_library_mqtt_SubscribeHandler.ACTIVE;
                        b009_state = Com_bitreactive_library_mqtt_PublishHandler.ACTIVE;
                        b010_state = Com_bitreactive_library_mqtt_PublishPolicy.ACTIVE;
                        b011_state = Com_bitreactive_library_buffering_BufferEager.READY;
                        // step 3b55d;
                        return CONSUME_SIGNAL;
                    }
                }
            } else {
                b007_INIT_OK_r0 = false;
                // step 84fe8;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_MSG_ARRIVED_b006(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b006_MSG_ARRIVED_r2) {
            if(b005_state==Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE) {
                org.reactiveblocks.ibmiot.Command b002_o5 = b002.command(((com.bitreactive.library.mqtt.MQTTMessage) _data));
                if(b002_o5==null) {
                    // step b542f;
                    return CONSUME_SIGNAL;
                } else {
                    no.ntnu.item.arctis.runtime.SingletonData[] b002_o0 = b002.distribute(b002_o5);
                    for (no.ntnu.item.arctis.runtime.SingletonData _it : b002_o0) {
                        scheduler.sendToSession(this, _it.getSessionID(), _it.getInstance()+ "_" + "command", _it);
                    }
                    // step dc971;
                    return CONSUME_SIGNAL;
                }
            } else {
                // step 467cf;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_RECONN_OK_b007(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b007_RECONN_OK_r2) {
            if(b017.containTopic()) {
                if(b019_state==Com_bitreactive_library_timers_Timer2._IDLE) {
                    if(b018_state==Com_bitreactive_library_mqtt_Subscriber.ACTIVE) {
                        b018.stop();
                        scheduler.stopTimer(_b0_b0_b2_c3_c1_Timer_2_timer);
                        scheduler.startBreak(_b0_b0_b2_c3_Subscribe_Handler_t0);
                        b007_RECONN_OK_r2 = false;
                        b018_SUBS_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b018");
                        b018_SUBS_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_OK_b018");
                        b018_state = Com_bitreactive_library_mqtt_Subscriber._IDLE;
                        // step 0399e;
                        return CONSUME_SIGNAL;
                    } else if(b018_state==Com_bitreactive_library_mqtt_Subscriber._IDLE) {
                        b018.stop();
                        scheduler.stopTimer(_b0_b0_b2_c3_c1_Timer_2_timer);
                        scheduler.startBreak(_b0_b0_b2_c3_Subscribe_Handler_t0);
                        b007_RECONN_OK_r2 = false;
                        b018_SUBS_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b018");
                        b018_SUBS_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "SUBS_OK_b018");
                        // step 88337;
                        return CONSUME_SIGNAL;
                    } else {
                        return DISCARD_SIGNAL;
                    }
                } else if(b019_state==Com_bitreactive_library_timers_Timer2.ACTIVE) {
                    b018.stop();
                    scheduler.stopTimer(_b0_b0_b2_c3_c1_Timer_2_timer);
                    scheduler.startBreak(_b0_b0_b2_c3_Subscribe_Handler_t0);
                    b007_RECONN_OK_r2 = false;
                    b018_SUBS_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b018");
                    b018_SUBS_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "SUBS_OK_b018");
                    b019_state = Com_bitreactive_library_timers_Timer2._IDLE;
                    // step b6d27;
                    return CONSUME_SIGNAL;
                } else {
                    return DISCARD_SIGNAL;
                }
            } else {
                b007_RECONN_OK_r2 = false;
                // step 03705;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_singleton_event(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b0_b0_Device_Singleton_event = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b002_state==Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE) {
            org.reactiveblocks.ibmiot.Event b002_o7 = b002.unwrapEvent(_b0_b0_Device_Singleton_event);
            if(b020_state==Com_bitreactive_library_flowlogic_Limiter.BLOCKING) {
                b002.logOverload();
                // step ad7cf;
                return CONSUME_SIGNAL;
            } else if(b020_state==Com_bitreactive_library_flowlogic_Limiter._IDLE) {
                int b020_o0 = b020.getInterval();
                java.lang.String b003_o0 = b003.serialize(b002_o7);
                com.bitreactive.library.mqtt.MQTTMessage b002_o6 = b002.message(b002_o7, b003_o0);
                if(b002_o6==null) {
                    b002.logTooLarge();
                    _b0_b0_b3_Limiter_t0_data = b020_o0;
                    scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                    b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                    b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                    // step 4df40;
                    return CONSUME_SIGNAL;
                } else {
                    if(b005_state==Com_bitreactive_library_mqtt_RobustMQTT.INITIALIZING) {
                        _b0_b0_b3_Limiter_t0_data = b020_o0;
                        scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                        b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                        b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                        // step 7d74b;
                        return CONSUME_SIGNAL;
                    } else if(b005_state==Com_bitreactive_library_mqtt_RobustMQTT.ACTIVE) {
                        if(b009_state==Com_bitreactive_library_mqtt_PublishHandler.ACTIVE) {
                            b010.msg = b002_o6;
                            if(b010.isConnected()) {
                                b010.buffer();
                                if(b011_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                    b011.add = b010.msg;
                                    b011.addToBuffer();
                                    scheduler.startBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                    b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.FIRSTITEMADDED;
                                    _b0_b0_b3_Limiter_t0_data = b020_o0;
                                    scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                    b011_state = Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED;
                                    b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    // step e5190;
                                    return CONSUME_SIGNAL;
                                } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                    b011.add = b010.msg;
                                    if(b011.hasRoom()) {
                                        b011.addToBuffer();
                                        _b0_b0_b3_Limiter_t0_data = b020_o0;
                                        scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                        b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        // step 923e8;
                                        return CONSUME_SIGNAL;
                                    } else {
                                        b009.overflow(b011.overflow);
                                        _b0_b0_b3_Limiter_t0_data = b020_o0;
                                        scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                        b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        // step df074;
                                        return CONSUME_SIGNAL;
                                    }
                                } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                    b011.add = b010.msg;
                                    if(b011.hasRoom()) {
                                        b011.addToBuffer();
                                        _b0_b0_b3_Limiter_t0_data = b020_o0;
                                        scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                        b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        // step 6685c;
                                        return CONSUME_SIGNAL;
                                    } else {
                                        b009.overflow(b011.overflow);
                                        _b0_b0_b3_Limiter_t0_data = b020_o0;
                                        scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                        b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        // step ba5a6;
                                        return CONSUME_SIGNAL;
                                    }
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else {
                                if(b010.discardDueToQos()) {
                                    _b0_b0_b3_Limiter_t0_data = b020_o0;
                                    scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                    b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    // step dc404;
                                    return CONSUME_SIGNAL;
                                } else {
                                    b010.buffer();
                                    if(b011_state==Com_bitreactive_library_buffering_BufferEager.READY) {
                                        b011.add = b010.msg;
                                        b011.addToBuffer();
                                        scheduler.startBreak(_b0_b0_b2_c2_b0_c1_Buffer_Eager_t0);
                                        b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.FIRSTITEMADDED;
                                        _b0_b0_b3_Limiter_t0_data = b020_o0;
                                        scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                        b011_state = Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED;
                                        b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        // step a6992;
                                        return CONSUME_SIGNAL;
                                    } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.ACTIVE) {
                                        b011.add = b010.msg;
                                        if(b011.hasRoom()) {
                                            b011.addToBuffer();
                                            _b0_b0_b3_Limiter_t0_data = b020_o0;
                                            scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                            b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                            b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                            // step 671ba;
                                            return CONSUME_SIGNAL;
                                        } else {
                                            b009.overflow(b011.overflow);
                                            _b0_b0_b3_Limiter_t0_data = b020_o0;
                                            scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                            b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                            b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                            // step 34ee0;
                                            return CONSUME_SIGNAL;
                                        }
                                    } else if(b011_state==Com_bitreactive_library_buffering_BufferEager.FIRSTITEMADDED) {
                                        b011.add = b010.msg;
                                        if(b011.hasRoom()) {
                                            b011.addToBuffer();
                                            _b0_b0_b3_Limiter_t0_data = b020_o0;
                                            scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                            b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                            b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                            // step 32e86;
                                            return CONSUME_SIGNAL;
                                        } else {
                                            b009.overflow(b011.overflow);
                                            _b0_b0_b3_Limiter_t0_data = b020_o0;
                                            scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                            b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                            b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                            // step 4d7b7;
                                            return CONSUME_SIGNAL;
                                        }
                                    } else {
                                        return DISCARD_SIGNAL;
                                    }
                                }
                            }
                        } else if(b013_state==Com_bitreactive_library_mqtt_PublishHandler2.ACTIVE) {
                            b016.msg = b002_o6;
                            if(b016.isConnected()) {
                                b016.buffer();
                                if(b015_state==Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE) {
                                    b015.add(b016.msg);
                                    _b0_b0_b3_Limiter_t0_data = b020_o0;
                                    scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                    b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    // step 5b830;
                                    return CONSUME_SIGNAL;
                                } else if(b015_state==Com_bitreactive_library_buffering_PersistentBuffer.REMOVING) {
                                    b015.add(b016.msg);
                                    _b0_b0_b3_Limiter_t0_data = b020_o0;
                                    scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                    b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    // step 12743;
                                    return CONSUME_SIGNAL;
                                } else {
                                    return DISCARD_SIGNAL;
                                }
                            } else {
                                if(b016.discardDueToQos()) {
                                    _b0_b0_b3_Limiter_t0_data = b020_o0;
                                    scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                    b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                    b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                    // step 5a2be;
                                    return CONSUME_SIGNAL;
                                } else {
                                    b016.buffer();
                                    if(b015_state==Com_bitreactive_library_buffering_PersistentBuffer.ACTIVE) {
                                        b015.add(b016.msg);
                                        _b0_b0_b3_Limiter_t0_data = b020_o0;
                                        scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                        b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        // step ecbd5;
                                        return CONSUME_SIGNAL;
                                    } else if(b015_state==Com_bitreactive_library_buffering_PersistentBuffer.REMOVING) {
                                        b015.add(b016.msg);
                                        _b0_b0_b3_Limiter_t0_data = b020_o0;
                                        scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                                        b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                                        b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                                        // step ff875;
                                        return CONSUME_SIGNAL;
                                    } else {
                                        return DISCARD_SIGNAL;
                                    }
                                }
                            }
                        } else {
                            return BLOCKING_CHOICE;
                        }
                    } else if(b005_state==Com_bitreactive_library_mqtt_RobustMQTT._IDLE) {
                        _b0_b0_b3_Limiter_t0_data = b020_o0;
                        scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                        b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                        b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                        // step 96509;
                        return CONSUME_SIGNAL;
                    } else {
                        return DISCARD_SIGNAL;
                    }
                }
            } else {
                return DISCARD_SIGNAL;
            }
        } else if(b002_state==Org_reactiveblocks_ibmiot_DeviceSingleton._IDLE) {
            org.reactiveblocks.ibmiot.Event b002_o7 = b002.unwrapEvent(_b0_b0_Device_Singleton_event);
            int b020_o0 = b020.getInterval();
            java.lang.String b003_o0 = b003.serialize(b002_o7);
            com.bitreactive.library.mqtt.MQTTMessage b002_o6 = b002.message(b002_o7, b003_o0);
            if(b002_o6==null) {
                b002.logTooLarge();
                _b0_b0_b3_Limiter_t0_data = b020_o0;
                scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                b002_state = Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE;
                b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                // step 494d9;
                return CONSUME_SIGNAL;
            } else {
                _b0_b0_b3_Limiter_t0_data = b020_o0;
                scheduler.startOrRestartTimer(_b0_b0_b3_Limiter_t0, b020_o0);
                b002_state = Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE;
                b021_state = Com_bitreactive_library_flowlogic_Switcher.OUT2;
                b020_state = Com_bitreactive_library_flowlogic_Limiter.BLOCKING;
                // step 3adc1;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_singleton_subscribe(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b0_b0_Device_Singleton_subscribe = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b002_state==Org_reactiveblocks_ibmiot_DeviceSingleton._IDLE) {
            b002.subscribe(_b0_b0_Device_Singleton_subscribe);
            b002_state = Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE;
            // step cb6a3;
            return CONSUME_SIGNAL;
        } else if(b002_state==Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE) {
            b002.subscribe(_b0_b0_Device_Singleton_subscribe);
            // step d773f;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_SUBS_ERROR_b018(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b018_SUBS_ERROR_r1) {
            int b017_o0 = b017.getDuration();
            b019.duration = b017_o0;
            scheduler.startOrRestartTimer(_b0_b0_b2_c3_c1_Timer_2_timer, b019.duration);
            b018_SUBS_ERROR_r1 = false;
            scheduler.flushEventQueue(sessionID, "SUBS_ERROR_b018");
            b018_SUBS_OK_r0 = false;
            scheduler.flushEventQueue(sessionID, "SUBS_OK_b018");
            b019_state = Com_bitreactive_library_timers_Timer2.ACTIVE;
            b018_state = Com_bitreactive_library_mqtt_Subscriber._IDLE;
            // step 59693;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_CONN_LOST_b006(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b006_CONN_LOST_r0) {
            b005.connLost(((java.lang.String) _data));
            if(b007_state==Com_bitreactive_library_mqtt_ConnHandler.ACTIVE) {
                boolean b007_o5 = b007.tryReconnect();
                if(b007_o5==true) {
                    // step b9914;
                    return CONSUME_SIGNAL;
                } else {
                    b007.reconnect();
                    b007_RECONN_FAILED_r3 = true;
                    b007_RECONN_OK_r2 = true;
                    // step de466;
                    return CONSUME_SIGNAL;
                }
            } else {
                // step 09dbc;
                return CONSUME_SIGNAL;
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_PUB_OK_b014(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b014_PUB_OK_r0) {
            b015.remove();
            b014_PUB_ERROR_r1 = false;
            scheduler.flushEventQueue(sessionID, "PUB_ERROR_b014");
            b014_PUB_OK_r0 = false;
            scheduler.flushEventQueue(sessionID, "PUB_OK_b014");
            b015_REMOVED_r0 = true;
            b014_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
            b015_state = Com_bitreactive_library_buffering_PersistentBuffer.REMOVING;
            // step 331a0;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_singleton_start(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        no.ntnu.item.arctis.runtime.SingletonData _b0_b0_Device_Singleton_start = ((no.ntnu.item.arctis.runtime.SingletonData) _data);
        if(b002_state==Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE) {
            b002.start = _b0_b0_Device_Singleton_start;
            if(b004_state==Com_bitreactive_library_flowlogic_First.OTHERS) {
                // step 84213;
                return CONSUME_SIGNAL;
            } else if(b004_state==Com_bitreactive_library_flowlogic_First._IDLE) {
                com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters b002_o2 = b002.init(b002.start);
                b005.p = b002_o2;
                com.bitreactive.library.mqtt.MQTTConfigParam b005_o0 = b005.getConfig();
                org.eclipse.paho.client.mqttv3.MqttCallback b006_o0 = b006.create(b005_o0);
                com.bitreactive.library.mqtt.connhandler.ConnHandler.Parameters b005_o1 = b005.startConn(b006_o0);
                b007.p = b005_o1;
                b007.connect();
                b006_MSG_ARRIVED_r2 = true;
                b007_INIT_OK_r0 = true;
                b007_INIT_FAILED_r1 = true;
                b006_CONN_LOST_r0 = true;
                b007_state = Com_bitreactive_library_mqtt_ConnHandler.INITIALIZING;
                b005_state = Com_bitreactive_library_mqtt_RobustMQTT.INITIALIZING;
                b006_state = Com_bitreactive_library_mqtt_CreateCallback.ACTIVE;
                b004_state = Com_bitreactive_library_flowlogic_First.OTHERS;
                // step c7cf3;
                return CONSUME_SIGNAL;
            } else {
                return DISCARD_SIGNAL;
            }
        } else if(b002_state==Org_reactiveblocks_ibmiot_DeviceSingleton._IDLE) {
            b002.start = _b0_b0_Device_Singleton_start;
            com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters b002_o2 = b002.init(b002.start);
            b005.p = b002_o2;
            com.bitreactive.library.mqtt.MQTTConfigParam b005_o0 = b005.getConfig();
            org.eclipse.paho.client.mqttv3.MqttCallback b006_o0 = b006.create(b005_o0);
            com.bitreactive.library.mqtt.connhandler.ConnHandler.Parameters b005_o1 = b005.startConn(b006_o0);
            b007.p = b005_o1;
            b007.connect();
            b006_MSG_ARRIVED_r2 = true;
            b007_INIT_OK_r0 = true;
            b007_INIT_FAILED_r1 = true;
            b006_CONN_LOST_r0 = true;
            b002_state = Org_reactiveblocks_ibmiot_DeviceSingleton.ACTIVE;
            b007_state = Com_bitreactive_library_mqtt_ConnHandler.INITIALIZING;
            b005_state = Com_bitreactive_library_mqtt_RobustMQTT.INITIALIZING;
            b006_state = Com_bitreactive_library_mqtt_CreateCallback.ACTIVE;
            b004_state = Com_bitreactive_library_flowlogic_First.OTHERS;
            // step aaa32;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_RECONN_FAILED_b007(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b007_RECONN_FAILED_r3) {
            int b007_o3 = b007.getWaitingDuration();
            scheduler.startOrRestartTimer(_b0_b0_b2_c1_Conn_Handler_t0, b007_o3);
            b007_RECONN_FAILED_r3 = false;
            // step a73e0;
            return CONSUME_SIGNAL;
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_PUB_OK_b012(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b012_PUB_OK_r0) {
            if(b011.isEmpty()) {
                b011_stm_state = Com_bitreactive_library_buffering_BufferEager_Stm.READY;
                b012_PUB_OK_r0 = false;
                scheduler.flushEventQueue(sessionID, "PUB_OK_b012");
                b012_PUB_ERROR_r1 = false;
                scheduler.flushEventQueue(sessionID, "PUB_ERROR_b012");
                b011_state = Com_bitreactive_library_buffering_BufferEager.READY;
                b012_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                // step ed4eb;
                return CONSUME_SIGNAL;
            } else {
                b011.getFromBuffer();
                b010.msg = b011.out;
                if(b010.discardDueToFreshness()) {
                    b010.getNext();
                    scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
                    b012_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b012");
                    b012_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b012");
                    b012_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step cfba8;
                    return CONSUME_SIGNAL;
                } else {
                    if(b010.isConnected()) {
                        com.bitreactive.library.mqtt.publisher.Publisher.Parameters b010_o2 = b010.addClient();
                        _b0_b0_b2_c2_b0_Publish_Handler_t0_data = b010_o2;
                        scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t0);
                        b012_PUB_OK_r0 = false;
                        scheduler.flushEventQueue(sessionID, "PUB_OK_b012");
                        b012_PUB_ERROR_r1 = false;
                        scheduler.flushEventQueue(sessionID, "PUB_ERROR_b012");
                        b012_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                        // step e47f6;
                        return CONSUME_SIGNAL;
                    } else {
                        if(b010.discardDueToQos()) {
                            b010.getNext();
                            scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
                            b012_PUB_OK_r0 = false;
                            scheduler.flushEventQueue(sessionID, "PUB_OK_b012");
                            b012_PUB_ERROR_r1 = false;
                            scheduler.flushEventQueue(sessionID, "PUB_ERROR_b012");
                            b012_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                            // step 37518;
                            return CONSUME_SIGNAL;
                        } else {
                            b010.toWait();
                            _b0_b0_b2_c2_b0_Publish_Handler_t2_data = b010.msg;
                            scheduler.startOrRestartTimer(_b0_b0_b2_c2_b0_Publish_Handler_t2);
                            b012_PUB_OK_r0 = false;
                            scheduler.flushEventQueue(sessionID, "PUB_OK_b012");
                            b012_PUB_ERROR_r1 = false;
                            scheduler.flushEventQueue(sessionID, "PUB_ERROR_b012");
                            b012_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                            // step 02832;
                            return CONSUME_SIGNAL;
                        }
                    }
                }
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_PUB_ERROR_b012(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b012_PUB_ERROR_r1) {
            b010.msg = ((com.bitreactive.library.mqtt.MQTTMessage) _data);
            if(b010.discardDueToFreshness()) {
                b010.getNext();
                scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
                b012_PUB_ERROR_r1 = false;
                scheduler.flushEventQueue(sessionID, "PUB_ERROR_b012");
                b012_PUB_OK_r0 = false;
                scheduler.flushEventQueue(sessionID, "PUB_OK_b012");
                b012_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                // step 289f9;
                return CONSUME_SIGNAL;
            } else {
                if(b010.discardDueToQos()) {
                    b010.getNext();
                    scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t3);
                    b012_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b012");
                    b012_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b012");
                    b012_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step eee38;
                    return CONSUME_SIGNAL;
                } else {
                    com.bitreactive.library.mqtt.publisher.Publisher.Parameters b010_o2 = b010.addClient();
                    _b0_b0_b2_c2_b0_Publish_Handler_t0_data = b010_o2;
                    scheduler.startBreak(_b0_b0_b2_c2_b0_Publish_Handler_t0);
                    b012_PUB_ERROR_r1 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_ERROR_b012");
                    b012_PUB_OK_r0 = false;
                    scheduler.flushEventQueue(sessionID, "PUB_OK_b012");
                    b012_state = Com_bitreactive_library_mqtt_Publisher._IDLE;
                    // step b633a;
                    return CONSUME_SIGNAL;
                }
            }
        } else {
            return DISCARD_SIGNAL;
        }
    }

    private int handleEvent_INIT_FAILED_b007(java.lang.Object _sender, java.lang.Object receiverSessionID, java.lang.Object _data) {
        if(b007_INIT_FAILED_r1) {
            int b007_o4 = b007.getWaitingDuration();
            scheduler.startOrRestartTimer(_b0_b0_b2_c1_Conn_Handler_t1, b007_o4);
            b007_INIT_FAILED_r1 = false;
            // step e06b9;
            return CONSUME_SIGNAL;
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
