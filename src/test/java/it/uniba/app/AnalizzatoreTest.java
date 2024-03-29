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
        assertEquals(Comandi.MOSTRA, Analizzatore.analizzatoreComando("/mostra"));
    }

    @Test
    void commandAnalyseHelp() throws InputUserNotValid {
        assertEquals(Comandi.AIUTO, Analizzatore.analizzatoreComando("/help"));
    }

    /*
    @Test
    void commandAnalyseLessLessHelp() throws InputUserNotValid {
        assertEquals(Analizzatore.Comando.AIUTO, Analizzatore.analizzatoreComando("--help"));
    }
    */
    
    /*
    @Test
    void commandAnalyseLessH() throws InputUserNotValid {
        assertEquals(Analizzatore.Comando.AIUTO, Analizzatore.analizzatoreComando("-h"));
    }
    */

    @Test
    void commandAnalyseNuovaWithWord() throws InputUserNotValid {
        assertEquals(Comandi.NUOVA, Analizzatore.analizzatoreComando("/nuova porta"));
    }

    @Test
    void commandAnalyseNuovaWithoutWord() {
        assertThrows(InputUserNotValid.class,
                () -> {
                    Analizzatore.analizzatoreComando("/nuova");
                }
        );
    }

    @Test
    void commandAnalyseGioca() throws InputUserNotValid {
        assertEquals(Comandi.GIOCA, Analizzatore.analizzatoreComando("/gioca"));
    }

    @Test
    void commandAnalyseAbbandona() throws InputUserNotValid {
        assertEquals(Comandi.ABBANDONA, Analizzatore.analizzatoreComando("/abbandona"));
    }

    @Test
    void commandAnalyseEsci() throws InputUserNotValid {
        assertEquals(Comandi.ESCI, Analizzatore.analizzatoreComando("/esci"));
    }

    @Test
    void commandAnalyseLengthEqualsTwoButNoTExists() {
        assertThrows(InputUserNotValid.class,
                () -> {
                    Analizzatore.analizzatoreComando("/mostra parola");
                }
        );
    }

    @Test
    void commandAnalyseLengthGreaterTwo() {
        assertThrows(InputUserNotValid.class,
                () -> {
                    Analizzatore.analizzatoreComando("/mostra parola nuova");
                }
        );
    }

    @Test
    void commandAnalyseNullCommand() throws InputUserNotValid {
        assertEquals(Comandi.ERRORE, Analizzatore.analizzatoreComando(""));
    }

    @Test
    void tryAnalyseCorrectWord() {
        Colori green = Colori.VERDE;
        Colori[] coloriCaratteri = {green, green,green, green, green}; //aspettato
        String input = "porta";

        Colori[] arrayOfColors = Analizzatore.analizzatoreTentativo(input, input);

        for(int i = 0; i < arrayOfColors.length; i++) {
            assertEquals(coloriCaratteri[i], arrayOfColors[i]);
        }
    }

    @Test
    void tryAnalyseIncorrectWord() {
        Colori green = Colori.VERDE;
        Colori yellow = Colori.GIALLO;
        Colori gray = Colori.GRIGIO;

        Colori[] coloriCaratteri = {green, yellow, gray , yellow, green}; //aspettato
        String input = "rules";
        String parolaSegreta = "rebus";

        Colori[] arrayOfColors = Analizzatore.analizzatoreTentativo(input, parolaSegreta);

        for(int i = 0; i < arrayOfColors.length; i++) {
            assertEquals(coloriCaratteri[i], arrayOfColors[i]);
        }
    }
}