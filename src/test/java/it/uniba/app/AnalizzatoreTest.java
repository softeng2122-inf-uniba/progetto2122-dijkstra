package it.uniba.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnalizzatoreTest {

    @Test
    void syntaxAnalyseAlphaNumericString() {
        assertFalse(Analizzatore.analizzatoreSintattico("p0rt4"));
    }

    @Test
    void syntaxAnalyseStringWithSpace() {
        assertFalse(Analizzatore.analizzatoreSintattico("port a"));
    }

}