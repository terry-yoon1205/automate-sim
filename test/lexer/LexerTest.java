package lexer;

import org.antlr.runtime.MismatchedTokenException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import org.junit.jupiter.api.Test;
import parser.AutomateSimLexer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

    @Test
    public void roomTest() throws MismatchedTokenException {
        AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString("""
                room Bedroom {
                    bedHeater of Heater(ON, 5)
                    bedLight of Light(OFF)
                    lamp1 of Light(OFF)
                    lamp2 of Light(OFF)
                    doorSensorBed of DoorSensor(OFF)
                    bedSwitch of Switch(OFF)
                }
                """));

        List<? extends Token> tokens = lexer.getAllTokens();
        // Ignore hidden tokens
        tokens.removeIf(t -> t.getChannel() != 0);
        assertEquals(42, tokens.size());

        assertNotEquals(tokens.get(0).getType(), tokens.get(1).getType());
    }

    @Test
    public void actionTest() throws MismatchedTokenException {
        AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString("""
                action AdjustBrightness on reading_lamp {
                     set reading_lamp.Brightness to 8
                 
                     if reading_lamp.Brightness is 8 {
                         set reading_lamp.Brightness to 2
                     }
                 
                     for light of DimmableLight in LivingRoom {
                         set light.Brightness to 0
                     }
                 }
                """));

        List<? extends Token> tokens = lexer.getAllTokens();
        // Ignore hidden tokens
        tokens.removeIf(t -> t.getChannel() != 0);
        assertEquals(40, tokens.size());

        assertNotEquals(tokens.get(0).getType(), tokens.get(1).getType());
    }

    @Test
    public void typeTest() throws MismatchedTokenException {
        AutomateSimLexer lexer = new AutomateSimLexer(CharStreams.fromString("""
                type Light {
                    enum PowerState [state0, state1]
                    string Location
                    number Brightness [0, 10]
                }
                
                type DimmableLight inherits Light {
                    enum PowerState [state2]
                    string Brand
                }
                
                type TemperatureSensor {
                    enum Status [active, inactive]
                    string Location
                    number Reading [0, 100]
                }
                """));

        List<? extends Token> tokens = lexer.getAllTokens();
        // Ignore hidden tokens
        tokens.removeIf(t -> t.getChannel() != 0);
        assertEquals(53, tokens.size());

        assertNotEquals(tokens.get(0).getType(), tokens.get(1).getType());
    }
}
