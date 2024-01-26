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
    
    
## Check-in 2 (Week 3)

### Jan 21 - 27
Jan 26 - Check-in 2 
- All:
  - Assign responsibilities for everyone.
  - Create a complete example of the DSL, with all finalized language features.
  - (Above are complete - progress so far.)
### Jan 28 - Feb 3
Feb 2 - Check-in 3
- All: 
  - Define grammar rules of the DSL.
  - Define the structure of the AST.
  - Plan and finish the first user study.
- Get started on the implementation.
  - Parser: String containing DSL --> AST
    - Richard
    - Jeffery
  - Evaluator: AST --> working console application
    - Ning
    - Terry
    - Leo
- Tests for components will be written by whoever wrote the component itself, as they will be the most familiar with
  what the tests should be about.
- With an agreed-upon definition of the AST, tests for the parser and evaluator components can be written independently
  and the implementation can be done in parallel.
### Feb 4 - Feb 10
Feb 9 - Check-in 4
- All: Plan for the second user study.
- Finish bulk of the implementation. Revise language design/tests/implementation based on results of the first user study.
### Feb 11 - Feb 17
Feb 16 - Check-in 5
- All: Make a draft for the video.
- Possibly make final changes to language design/tests/implementation based on the second user study.
### Feb 18 - Feb 24
- All: Make the demo video.
### Feb 25 - Feb 26
Feb 26 - Due date
