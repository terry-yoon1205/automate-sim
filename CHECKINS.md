## Check-in 1 (Week 2)

IoT device management:
- High-level purpose: The DSL can be used by people to help plan their smart home device automations and integrations. It will generate a simulation of the smart home, in which the user can trigger events and see their automations occur in action.
- Rich features:
  1. Define types of devices, complete with a set of states a device of the defined type can have, as well as its initial state. Type hierarchy is possible.
  2. Build a mockup of the smart home. The user can define a room containing a set of devices which are of a certain type.
  3. Define actions that will be triggered upon the change of the specified device’s state. The action can contain if-conditions and loops.
- Example:
    ```
    type Light {
        ON,
        OFF
    }: OFF
    
    type SmallLight inherits Light {
        DIMMED
    }: OFF
    
    type Switch {
        ON,
        OFF
    }: OFF
    
  
    room bedroom {
        device bedroom_light of Light,
        device bedroom_lamp of SmallLight
    }
    
    room living_room {
        device main_switch of Switch,
        device living_light of Light
    }
    
    
    action turn_on_bedroom on living_light {
        if living_light is ON {
            set bedroom_light ON
            set bedroom_lamp DIMMED
        }
    }
    
    action turn_off_all_lights on main_switch {
        if main_switch is OFF {
            set living_light OFF
            for light of Light in bedroom {
                set light OFF
            }
        }
    }
    ```