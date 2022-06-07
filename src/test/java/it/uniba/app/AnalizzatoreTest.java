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

    @Test
    void syntaxAnalyseString() {
        assertTrue(Analizzatore.analizzatoreSintattico("pippo"));
    }

    @Test
    void commandAnalyseMostra() throws InputUserNotValid {
        assertEquals(Analizzatore.Comando.MOSTRA, Analizzatore.analizzatoreComando("/mostra"));
    }
    @Test
    void commandAnalyseHelp() throws InputUserNotValid {
        assertEquals(Analizzatore.Comando.AIUTO, Analizzatore.analizzatoreComando("/help"));
    }

    @Test
    void commandAnalyseLessLessHelp() throws InputUserNotValid {
        assertEquals(Analizzatore.Comando.AIUTO, Analizzatore.analizzatoreComando("--help"));
    }

    @Test
    void commandAnalyseLessH() throws InputUserNotValid {
        assertEquals(Analizzatore.Comando.AIUTO, Analizzatore.analizzatoreComando("-h"));
    }

    @Test
    void commandAnalyseNuovaWithWord() throws InputUserNotValid {
        assertEquals(Analizzatore.Comando.NUOVA, Analizzatore.analizzatoreComando("/nuova porta"));
    }

    @Test
    void commandAnalyseNuovaWithoutWord() {
        assertThrows(InputUserNotValid.class,
                () -> {
                    Analizzatore.analizzatoreComando("/nuova");
                }
                );
    }


}